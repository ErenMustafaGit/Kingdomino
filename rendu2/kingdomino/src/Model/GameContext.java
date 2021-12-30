package Model;

import java.util.*;

public class GameContext
{
    private PlayerStrategy nbPlayersStrat; //stratégie associé au nombre de joueurs
    private GameMode gameMode; //stratégie associé au mode de jeu choisi
    private Deck deck; // Packet de tuiles utilisé pour la partie
    private Map<Tile, King> currentTiles = new HashMap<>(); // Tuiles correspondante aux choix diponible pour les joueurs
    private ArrayList<GameObserver> observers = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();

    //Liste des king du round actuel
    private ArrayList<King> kings = new ArrayList<>();
    //Liste des king pour le prochain round
    private ArrayList<King> nextRoundKings = new ArrayList<>();

    private int turn;

    public GameContext()
    {
        turn = 0;
    }

    public void destroy(){
        this.nbPlayersStrat = null;
        this.gameMode = null;
        this.deck = null;
        this.currentTiles.clear();
        this.players.clear();
        this.kings.clear();
        this.nextRoundKings.clear();
        this.turn = 0;
    }




    public Map<Tile, King> getCurrentTiles() {
        return currentTiles;
    }

    public void addObserver(GameObserver gameObserver)
    {
        observers.add( gameObserver );
    }

    //Implémentation de la variable d'instance nbPlayersStrat en fonction du nombre de joueur
    public void setPlayerStrategy(int nbPlayers)
    {
        switch (nbPlayers) {
            case 2 -> nbPlayersStrat = new TwoPlayers();
            case 3 -> nbPlayersStrat = new ThreePlayers();
            case 4 -> nbPlayersStrat = new FourPlayers();
        }
    }

    public GameMode getGameMode(){
        return this.gameMode;
    }

    public PlayerStrategy getNbPlayersStrat(){
        return this.nbPlayersStrat;
    }

    //Implémentation de la variable d'instance gameMode en fonction du mode de jeu
    public void setGameStrategy(int gameModeChoosen)
    {
        GameMode normalMode = new NormalMode();
        this.gameMode = normalMode;
        switch (gameModeChoosen) {
            case 1 -> gameMode = new Harmony(normalMode);
            case 2 -> gameMode = new MiddleKingdom(normalMode);
            case 3 -> gameMode = new MiddleKingdom( new Harmony(normalMode) );
        }
    }

    public void initGame(ArrayList<KingColor> colors){
        createDeck();
        createPlayers(colors);
        pickTiles();
        System.out.println( this.kings );
        System.out.println( this.players );

    }

    public ArrayList<Player> getPlayers(){
        return new ArrayList<>(this.players);
    }

    //Création du packet de tuiles de la bonne taille
    private void createDeck()
    {
        this.deck = new Deck(nbPlayersStrat.getnbTile());
    }

    private void createKing(KingColor color, int number, Player player)
    {
        for (int i = 0; i< number ; i++){
            this.kings.add( new King(color, player) );
        }
    }

    private void createPlayers(ArrayList<KingColor> colors)
    {
        int nbPlayer = nbPlayersStrat.getnbBoard();
        ArrayList<Player> newPlayers = new ArrayList<>();
        for (int i = 0; i< nbPlayer ; i++){
            KingColor color = colors.get(i);
            Player newPlayer = new Player( color, new PlayerBoard());
            if(nbPlayer ==2){
                this.createKing( color , 2, newPlayer);
            }else{
                this.createKing( color, 1, newPlayer );
            }
            newPlayers.add ( newPlayer );
        }


        Collections.shuffle( this.kings );

        //Inversion du king n°2 avec le n°3 si le meme joueur joue 2 fois en premier pour + d'équité
        if(this.kings.get(0).getColor() == this.kings.get(1).getColor()){
            King temp = this.kings.get(2);
            this.kings.set(2, this.kings.get(1));
            this.kings.set( 1, temp );
        }
        Collections.shuffle( newPlayers );
        this.players = newPlayers;
    }

    public Player getPlayerCastleTurn(){
        return this.players.get( turn % this.players.size() );
    }

    public King getKingTurn(){
        return this.kings.get(turn % this.kings.size() );
    }

    public int getTurn(){
        return this.turn;
    }


    public void pickTiles()
    {
        currentTiles.clear();
        for (int i = 0; i< nbPlayersStrat.getnbKings(); i++ ){
            if (deck.getNbTiles() != 0) {
                currentTiles.put(deck.getTile(), null);
            }
        }


        /***ORDER TILES BY THEIR NUMBER***/
        TreeMap<Tile, King> sorted = new TreeMap<>(this.currentTiles);
        this.currentTiles = sorted;
    }

    public boolean setCastle(Player player,int x, int y)
    {
        Castle castle = new Castle();
        if(player.getBoard().setCastle(x, y, castle)) {
            this.turn++;
            notifyObservers();
            return true;
        }
        return false;
    }

    /**
     * @param x, y : Position du terrain gauche de la tuile
     * @return TRUE si nous avons reussi à placer la tuile
     */
    public boolean setTile(int x, int y)
    {
        //Si toutes les tuiles ont été choisi
        if(!allTilesChoosen()){
            return false;
        }

        Player player = this.getKingTurn().getPlayer();
        Tile tile = this.getKingTurn().getTile();
        Map< Tile, King > currentTiles = this.getCurrentTiles();

        /* Ancienne facon pour obtenir la tuile du joueur qu'il doit jouer
        for ( Map.Entry<Tile, Player> choosenTile : currentTiles.entrySet()) {
            if(choosenTile.getValue() == player){
                tile = choosenTile.getKey();
                break;
            }
        }*/

        if(player.getBoard().setTile(  x,  y, tile.getDirection(), tile  )) {
            currentTiles.remove(tile);
            this.getKingTurn().removeTile();
            turn++;
            if(currentTiles.size() == 0){
                this.pickTiles();
            }
            notifyObservers();
            return true;
        }
        return false;
    }

    public boolean allTilesChoosen() {
        for ( Map.Entry<Tile, King> choosenTile : this.currentTiles.entrySet()) {
            if(choosenTile.getValue() == null){
                return false;
            }
        }
        return true;
    }

    public void chooseTile(Tile tile) {
        currentTiles.replace(tile, this.getKingTurn());
        this.getKingTurn().setChoosenTile(tile);
        turn++;

        //Si toutes les tuiles sont choisi et que le nombre de tuile est au meme nombre de roi
        // (S'execute une fois que tout le monde a choisi ces tuiles)
        if(this.allTilesChoosen() && this.currentTiles.size() == this.getNbPlayersStrat().getnbKings()){
            this.orderNextRoundKings();
        }
        this.notifyObservers();
    }

    private void orderNextRoundKings(){
        // Tuile+Roi qu'il l'a choisi
        Map<Tile, King> tiles = new HashMap<>(this.currentTiles);
        int min;
        int tileNumber;
        King king = null;
        Tile tile = null;
        for (int i = 0; i<this.kings.size(); i++){
            //Récupere la tuile avec la plus petite valeur
            min = 10000;

            for ( Map.Entry<Tile, King> choosenTile : tiles.entrySet()) {
                tileNumber = choosenTile.getKey().getNumber();
                if(min > tileNumber){
                    min = tileNumber;
                    king = choosenTile.getValue();
                    tile = choosenTile.getKey();
                }
            }

            //Ajout du roi qui a pris cette tuile
            this.nextRoundKings.add( king );

            //Suppression de cette tuile là (dans notre liste temporaire)
            tiles.remove(tile);
        }

        //Met le nouvelle ordre des rois
        if( !this.nextRoundKings.isEmpty() ){
            this.kings = new ArrayList<>(this.nextRoundKings);
        }
        this.nextRoundKings.clear();
    }

    public void rotateCurrentTile(){
        this.getKingTurn().getTile().rotate();
        notifyObservers();
    }

    public void reverseCurrentTile(){
        this.getKingTurn().getTile().reverse();
        notifyObservers();
    }



    private void notifyObservers(){
        for (GameObserver observer : this.observers){
            observer.update(this);
        }
    }

}
