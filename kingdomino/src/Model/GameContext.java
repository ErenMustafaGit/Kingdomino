package Model;

public class GameContext
{
    PlayerStrategy nbPlayersStrat;
    GameStrategy gameModeStrat;

    public GameContext(int nbPlayer, int gameMode)
    {
        setPlayerStrategy(nbPlayer);
        setGameStrategy(gameMode);
    }

    public void setPlayerStrategy(int nbPlayers)
    {
        switch (nbPlayers)
        {
            case 2:
            nbPlayersStrat = new TwoPlayers();
            break;

            case 3:
                nbPlayersStrat = new ThreePlayers();
                break;

            case 4:
                nbPlayersStrat = new FourPlayers();
        }
    }

    public void setGameStrategy(int gameMode)
    {
        switch (gameMode)
        {
            case 1:
                gameModeStrat = new NormalMode();
                break;

            case 2:
                gameModeStrat = new Harmony();
                break;

            case 3:
                gameModeStrat = new MiddleKingdom();
                break;
        }
    }

}
