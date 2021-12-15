package Model;

import java.util.ArrayList;

public class GameContext
{
    public PlayerStrategy nbPlayersStrat; //stratégie associé au nombre de joueurs
    public GameStrategy gameModeStrat; //stratégie associé au mode de jeu choisi
    public Deck gameDeck; // Packet de tuiles utilisé pour la partie
    public ArrayList<Tile> currentTiles; // Tuiles correspondante aux choix diponible pour les joueurs

    // constructeur de la classe
    public GameContext(int nbPlayer, int gameMode)
    {
        setPlayerStrategy(nbPlayer);
        setGameStrategy(gameMode);
    }

    //Implémentation de la variable d'instance nbPlayersStrat en fonction du nombre de joueur
    public void setPlayerStrategy(int nbPlayers)
    {
        switch (nbPlayers) {
            case 2 -> nbPlayersStrat = new TwoPlayers();
            case 3 -> nbPlayersStrat = new ThreePlayers();
            case 4 -> nbPlayersStrat = new FourPlayers();
        }
        createDeck();
    }

    //Implémentation de la variable d'instance gameMode en fonction du mode de jeu
    public void setGameStrategy(int gameMode)
    {
        switch (gameMode) {
            case 1 -> gameModeStrat = new NormalMode();
            case 2 -> gameModeStrat = new Harmony();
            case 3 -> gameModeStrat = new MiddleKingdom();
        }
    }

    //Création du packet de tuiles de la bonne taille
    private void createDeck()
    {
        if (nbPlayersStrat instanceof TwoPlayers)
        {
            gameDeck = new Deck(nbPlayersStrat.getnbTile());
        }
        else if (nbPlayersStrat instanceof ThreePlayers)
        {
            gameDeck = new Deck(nbPlayersStrat.getnbTile());
        }
        else if(nbPlayersStrat instanceof FourPlayers)
        {
            gameDeck = new Deck(nbPlayersStrat.getnbTile());
        }
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
