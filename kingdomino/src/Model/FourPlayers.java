package Model;

public class FourPlayers implements PlayerStrategy
{
    int nbofTile;
    @Override
    public int getnbTile() {
        return nbofTile=48;
    }
}
