package Model;

public class Tile implements Positionable
{
    private int number;

    public Tile(int n)
    {
        number = n;
    }

    public int getNumber()
    {
        return this.number;
    }
}
