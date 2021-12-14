package Model;

public class GameContext
{
    public PlayerStrategy nbPlayersStrat;
    public GameStrategy gameModeStrat;

    public GameContext(int nbPlayer, int gameMode)
    {
        setPlayerStrategy(nbPlayer);
        setGameStrategy(gameMode);
    }

    public void setPlayerStrategy(int nbPlayers)
    {
        switch (nbPlayers) {
            case 2 -> nbPlayersStrat = new TwoPlayers();
            case 3 -> nbPlayersStrat = new ThreePlayers();
            case 4 -> nbPlayersStrat = new FourPlayers();
        }
    }

    public void setGameStrategy(int gameMode)
    {
        switch (gameMode) {
            case 1 -> gameModeStrat = new NormalMode();
            case 2 -> gameModeStrat = new Harmony();
            case 3 -> gameModeStrat = new MiddleKingdom();
        }
    }

}
