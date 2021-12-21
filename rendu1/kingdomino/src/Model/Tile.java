package Model;

public class Tile implements Comparable<Tile>
{
    private int number;
    private Ground left;
    private Ground right;


    public Tile(int number)
    {
        this.number = number;
    }

    public Tile(int number, Ground left, Ground right)
    {
        this.number = number;
        this.left = left;
        this.right = right;
    }

    public Ground getLeft() {
        return left;
    }

    public void setLeft(Ground left) {
        this.left = left;
    }

    public Ground getRight() {
        return right;
    }

    public void setRight(Ground right) {
        this.right = right;
    }

    public int getNumber()
    {
        return this.number;
    }
    public void setNumber(int number)
    {
        this.number = number;
    }

    @Override
    public int compareTo(Tile o) {
        return (int)(this.getNumber() - o.getNumber());
    }
}
