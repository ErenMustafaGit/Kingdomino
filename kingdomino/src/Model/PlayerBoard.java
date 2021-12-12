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

    /**
     * @param x, y : Position du terrain gauche de la tuile
     * @param dir : Direction de la tuile
     * @param tile : Tuile à placé
     * @return TRUE si nous avons reussi à placer la tuile
     */
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

        //Si les cases existent et qu'ils sont disponibles
        if( isPosable(x, y) && isPosable(x2,y2)){

            //Si un des 2 côtés est bien placé en terme de terrain
            if(isSameGround(x, y, tile.getLeft()) || isSameGround(x2,y2, tile.getRight())){
                this.board[y][x] = tile.getLeft();
                return true;
            }
        }
        //Renvoie faux si nous avons pas reussi à placer la tuile
        return false;
    }

    public boolean isPosable(int x, int y)
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

    /** Fonction qui regarde si le terrain est entouré par un de ses terrains ou le chateau
     *
     * @param x, y : Position du terrain
     * @param ground : Terrain
     * @return TRUE si il est collé à chateau ou à son même type
     */
    private boolean isSameGround(int x, int y, Ground ground){
        //Regarde autour de la tuile

        GroundColor type;
        if(x-1 >= 0){
            type = board[y][x-1].getColor();
            if( type == ground.getColor() || type == GroundColor.grey ){
                return true;
            }
        }
        if(x+1 < BOARD_SIZE){
            type = board[y][x+1].getColor();
            if( type == ground.getColor() || type == GroundColor.grey ){
                return true;
            }
        }
        if(y-1 >= 0){
            type = board[y-1][x].getColor();
            if( type == ground.getColor() || type == GroundColor.grey ){
                return true;
            }
        }
        if(y+1 >= 0){
            type = board[y+1][x].getColor();
            if( type == ground.getColor() || type == GroundColor.grey ){
                return true;
            }
        }
        return false;
    }




}
