package Model;

public class ThreePlayers implements PlayerStrategy{
    @Override
    public int getnbTile() {
        return 36;
    }

    @Override
    public int getnbBoard() {
        return 3;
    }

    @Override
    public int getnbKings() {
        return 3;
    }
}
