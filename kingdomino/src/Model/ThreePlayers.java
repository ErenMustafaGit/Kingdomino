package Model;

public class ThreePlayers implements PlayerStrategy{
    int nbofTile;
    @Override
    public int getnbTile() {
        return nbofTile=36;
    }
}
