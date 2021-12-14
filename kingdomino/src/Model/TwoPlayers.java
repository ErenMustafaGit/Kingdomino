package Model;

public class TwoPlayers implements PlayerStrategy{
    int nbofTile;
    @Override
    public int getnbTile() {
        return nbofTile=24;
    }
}
