package Model;

public class TwoPlayers implements PlayerStrategy{
    @Override
    public int getnbTile() {
        return 24;
    }

    @Override
    public int getnbBoard() {
        return 2;
    }

    @Override
    public int getnbKings() {
        return 4;
    }
}
