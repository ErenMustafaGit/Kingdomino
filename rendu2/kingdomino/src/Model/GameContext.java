package Model;

import java.util.*;

public class GameContext
{
    private PlayerStrategy nbPlayersStrat; //stratégie associé au nombre de joueurs
    private GameMode gameMode; //stratégie associé au mode de jeu choisi
    private Deck deck; // Packet de tuiles utilisé pour la partie
    private Map<Tile, Player> currentTiles = new HashMap<>(); // Tuiles correspondante aux choix diponible pour les joueurs
    private ArrayList<GameObserver> observers = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private int turn;

    public GameContext()
    {
        turn = 0;
    }



    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }



    public Map<Tile, Player> getCurrentTiles() {
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
    }

    public ArrayList<Player> getPlayers(){
        return new ArrayList<>(this.players);
    }

    //Création du packet de tuiles de la bonne taille
    private void createDeck()
    {
        this.deck = new Deck(nbPlayersStrat.getnbTile());
    }

    private ArrayList<King> createKing(KingColor color, int number)
    {
        ArrayList<King> kings = new ArrayList<>();
        for (int i = 0; i< number ; i++){
            kings.add( new King(color) );
        }
        return kings;
    }

    private void createPlayers(ArrayList<KingColor> colors)
    {
        int nbPlayer = nbPlayersStrat.getnbBoard();
        ArrayList<Player> newPlayers = new ArrayList<>();
        for (int i = 0; i< nbPlayer ; i++){
            ArrayList<King> kings = new ArrayList<>();
            //KingColor color = this.getUnchoosenColor();
            KingColor color = colors.get(i);
            if(nbPlayer ==2){
                kings = this.createKing(color , 2);
            }else{
                kings = this.createKing( color, 1 );
            }
            newPlayers.add ( new Player( color, kings, new PlayerBoard()));
        }
        Collections.shuffle( newPlayers );
        this.players = newPlayers;
    }

    public Player getPlayerTurn(){
        return this.players.get(turn % this.players.size() );
    }

    public int getTurn(){
        return this.turn;
    }

    private KingColor getUnchoosenColor(){

        boolean same = false;
        for (int i = 0; i<this.players.size(); i++){
            if(KingColor.BLUE == this.players.get(i).getPlayerColor()){
                same = true;
            }
        }
        if(!same){
            return KingColor.BLUE;
        }
        for (int i = 0; i<this.players.size(); i++){
            if(KingColor.GREEN == this.players.get(i).getPlayerColor()){
                same = true;
            }
        }
        if(!same){
            return KingColor.GREEN;
        }
        for (int i = 0; i<this.players.size(); i++){
            if(KingColor.YELLOW == this.players.get(i).getPlayerColor()){
                same = true;
            }
        }
        if(!same){
            return KingColor.YELLOW;
        }
        for (int i = 0; i<this.players.size(); i++){
            if(KingColor.PINK == this.players.get(i).getPlayerColor()){
                same = true;
            }
        }
        if(!same){
            return KingColor.PINK;
        }
        return null;
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
        TreeMap<Tile, Player> sorted = new TreeMap<>(this.currentTiles);
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
     * @param dir : Direction de la tuile
     * @return TRUE si nous avons reussi à placer la tuile
     */
    public boolean setTile(int x, int y, Direction dir)
    {
        Player player = this.getPlayerTurn();
        Tile tile = null;
        Map< Tile, Player > currentTiles = this.getCurrentTiles();

        for ( Map.Entry<Tile, Player> choosenTile : currentTiles.entrySet()) {
            if(choosenTile.getValue() == player){
                tile = choosenTile.getKey();
                break;
            }
        }

        if(player.getBoard().setTile(  x,  y, dir, tile  )) {
            turn++;
            notifyObservers();
            return true;
        }
        return false;
    }

    private void notifyObservers(){
        for (GameObserver observer : this.observers){
            observer.update(this);
        }
    }

    public void chooseTile(Tile tile) {
        currentTiles.replace(tile, this.getPlayerTurn());
        turn++;
        this.notifyObservers();
    }
}
