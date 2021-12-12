package Model;

public class PlayerBoard
{
    private final int BOARD_SIZE = 5;

    //Tableau de positionable (5x5 de base --> static)
    //9x9 pour en avoir un dynamique
    Positionable[][] board = new Positionable[BOARD_SIZE][BOARD_SIZE];


    public PlayerBoard()
    {

    }

    //Assignation du board du joueur avec le chateau au milieu du 9x9
    public PlayerBoard(Castle castle)
    {
        System.out.println( "castle position " + BOARD_SIZE/2);
        this.board[BOARD_SIZE/2][BOARD_SIZE/2] = castle;
    }

    public boolean setTile(int x, int y, Direction dir, Tile tile)
    {
        int x2, y2;
        switch(dir){

            case NORTH:
                x2 = x;
                y2 = y-1;
                break;

            case SOUTH:
                x2 = x;
                y2 = y+1;
                break;

            case WEST:
                x2 = x-1;
                y2 = y;
                break;
            default:
                x2 = x+1;
                y2 = y;
                break;
        }

        if( isPosable(x, y, tile.getLeft()) && isPosable(x2,y2, tile.getRight()) ){
            this.board[y][x] = tile.getLeft();
        }
        return true;
    }

    public boolean isPosable(int x, int y, Ground ground)
    {
        //Si c'est en dehors du tableau
        if(x < 0 || x>= BOARD_SIZE || y < 0 || y>= BOARD_SIZE ){
            return false;
        }

        if(this.board[y][x] != null){
            return false;
        }


        return true;
    }

    /**  Fonction qui regarde si le terrain est entouré par un de ses terrains ou le chateau

        param : x,y : Position du terrain
                ground : Type du terrain à poser
     **/
    private boolean isSameGround(int x, int y, Ground ground){
        //Regarde autour de la tuile

        GroundColor type;
        if(x-1 >= 0){
            type = board[x-1][y].getType();
            if( type == ground.getType() || type == GroundColor. )
        }
    }




}
