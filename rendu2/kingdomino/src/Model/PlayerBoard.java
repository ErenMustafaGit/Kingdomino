package Model;

public class PlayerBoard
{
    private final int getBOARD_SIZE = 5;

    //Tableau de positionable (5x5 de base --> static)
    //9x9 pour en avoir un dynamique
    private Positionable[][] board = new Positionable[getBOARD_SIZE][getBOARD_SIZE];


    public PlayerBoard()
    {

    }

    public PlayerBoard(Castle castle)
    {
        this.board[2][2] = castle;
    }

    /**
     * @param x, y : Position du terrain gauche de la tuile
     * @return TRUE si nous avons reussi à placer la tuile
     */
    public boolean setCastle(int x, int y, Castle castle)
    {
        //Si les cases existent et qu'ils sont disponibles
        if( isPosable(x, y) ){
            this.board[x][y] = castle;
            return true;
        }
        return false;
    }

    /**
     * @param x, y : Position du terrain gauche de la tuile
     * @param dir : Direction de la tuile
     * @param tile : Tuile à placé
     * @return TRUE si nous avons reussi à placer la tuile
     */
    public boolean setTile(int x, int y, Direction dir, Tile tile)
    { //x : 2, y:1
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
                this.board[x][y] = tile.getLeft();
                this.board[x2][y2] = tile.getRight();
                return true;
            }
        }
        //Renvoie faux si nous avons pas reussi à placer la tuile
        return false;
    }

    private boolean isPosable(int x, int y)
    {
        //Si c'est en dehors du tableau
        if(x < 0 || x>= getBOARD_SIZE || y < 0 || y>= getBOARD_SIZE){
            return false;
        }

        if(this.board[x][y] != null){
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
        if(y-1 >= 0){
            if(board[x][y-1] !=null){
                type = board[x][y-1].getColor();

                if( type == ground.getColor() || type == GroundColor.GREY ){
                    return true;
                }
            }

        }
        if(x+1 < getBOARD_SIZE){
            if(board[x+1][y] !=null){
                type = board[x+1][y].getColor();
                if( type == ground.getColor() || type == GroundColor.GREY ){
                    return true;
                }
            }

        }
        if(x-1 >= 0){
            if(board[x-1][y] !=null){
                type = board[x-1][y].getColor();
                if( type == ground.getColor() || type == GroundColor.GREY ){
                    return true;
                }
            }

        }
        if(y+1 < getBOARD_SIZE){
            if(board[x][y+1] !=null){
                type = board[x][y+1].getColor();
                if( type == ground.getColor() || type == GroundColor.GREY ){
                    return true;
                }
            }

        }
        return false;
    }

    public int getBOARD_SIZE() {
        return getBOARD_SIZE;
    }

    public Positionable getPositionnable(int x, int y){
        return this.board[x][y];
    }

    public Positionable[][] getBoard() {
        return this.board;
    }

}
