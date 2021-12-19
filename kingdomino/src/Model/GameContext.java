package Model;

import java.util.ArrayList;

public class GameContext
{
    private PlayerStrategy nbPlayersStrat; //stratégie associé au nombre de joueurs
    private GameMode gameMode; //stratégie associé au mode de jeu choisi
    private Deck gameDeck; // Packet de tuiles utilisé pour la partie
    private ArrayList<Tile> currentTiles; // Tuiles correspondante aux choix diponible pour les joueurs
    private ArrayList<GameObserver> observers = new ArrayList<>();


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
        }
    }

    public void initGame(){
        createDeck();

    }

    //Création du packet de tuiles de la bonne taille
    private void createDeck()
    {
        gameDeck = new Deck(nbPlayersStrat.getnbTile());
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
