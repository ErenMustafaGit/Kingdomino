package Model;

import java.util.ArrayList;

public class GameContext
{
    private PlayerStrategy nbPlayersStrat; //stratégie associé au nombre de joueurs
    private GameMode gameMode; //stratégie associé au mode de jeu choisi
    private Deck gameDeck; // Packet de tuiles utilisé pour la partie
    private ArrayList<Tile> currentTiles; // Tuiles correspondante aux choix diponible pour les joueurs
    private ArrayList<GameObserver> observers = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();


    public void setNbPlayersStrat(PlayerStrategy nbPlayersStrat) {
        this.nbPlayersStrat = nbPlayersStrat;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public Deck getGameDeck() {
        return gameDeck;
    }

    public void setGameDeck(Deck gameDeck) {
        this.gameDeck = gameDeck;
    }

    public ArrayList<Tile> getCurrentTiles() {
        return currentTiles;
    }

    public void setCurrentTiles(ArrayList<Tile> currentTiles) {
        this.currentTiles = currentTiles;
    }

    public ArrayList<GameObserver> getObservers() {
        return observers;
    }

    public void setObservers(ArrayList<GameObserver> observers) {
        this.observers = observers;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public GameContext()
    {
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

    public void initGame(){
        createDeck();
        createPlayers();
    }

    public ArrayList<Player> getPlayers(){
        return this.players;
    }

    //Création du packet de tuiles de la bonne taille
    private void createDeck()
    {
        gameDeck = new Deck(nbPlayersStrat.getnbTile());
    }

    private ArrayList<King> createKing(KingColor color, int number)
    {
        ArrayList<King> kings = new ArrayList<>();
        for (int i = 0; i< number ; i++){
            kings.add( new King(color) );
        }
        return kings;
    }

    private void createPlayers()
    {
        int nbPlayer = nbPlayersStrat.getnbBoard();
        for (int i = 0; i< nbPlayer ; i++){
            ArrayList<King> kings = new ArrayList<>();
            KingColor color = this.getUnchoosenColor();
            if(nbPlayer ==2){
                kings = this.createKing(color , 2);
            }else{
                kings = this.createKing( color, 1 );
            }

            this.players.add( new Player( color, kings, new PlayerBoard()  ) );
        }
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
        if (nbPlayersStrat instanceof TwoPlayers)
        {
            for (int i = 0; i<2; i++)
            {
                currentTiles.add(gameDeck.getTile());
            }
        }
        else
        {
            for (int i = 0; i < 4; i++)
            {
                currentTiles.add(gameDeck.getTile());
            }
        }
    }

}
