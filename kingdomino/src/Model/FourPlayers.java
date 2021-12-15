package Model;

public class FourPlayers implements PlayerStrategy
{
    @Override
    public int getnbTile() {
        return 48;
    }

    @Override
    public int getnbBoard() {
        return 4;
    }
}
