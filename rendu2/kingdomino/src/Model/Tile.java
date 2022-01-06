package Model;

public class Tile implements Comparable<Tile>
{
    private int number;
    private Ground left;
    private Ground right;
    private Direction direction;


    public Tile(int number)
    {
        this.number = number;
    }

    public Tile(int number, Ground left, Ground right)
    {
        this.number = number;
        this.left = left;
        this.right = right;
        this.direction = Direction.EAST;
    }

    public Ground getLeft() {
        return left;
    }
    public Ground getRight() {
        return right;
    }
    public int getNumber()
    {
        return this.number;
    }

    public void rotate(){
        switch(this.direction){

            case NORTH:
                this.direction = Direction.EAST;
                break;

            case SOUTH:
                this.direction = Direction.WEST;
                break;

            case WEST:
                this.direction = Direction.NORTH;
                break;
            default:
                this.direction = Direction.SOUTH;
                break;
        }
    }
    public void reverse(){
        switch(this.direction){
            case NORTH:
                this.direction = Direction.SOUTH;
                break;

            case SOUTH:
                this.direction = Direction.NORTH;
                break;

            case WEST:
                this.direction = Direction.EAST;
                break;
            default:
                this.direction = Direction.WEST;
                break;
        }
    }

    public Direction getDirection(){
        return this.direction;
    }

    @Override
    public int compareTo(Tile o) {
        return (int)(this.getNumber() - o.getNumber());
    }
}
