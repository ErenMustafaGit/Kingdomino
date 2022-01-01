package Model;

public class PlayerBoard
{
    private final int BOARD_SIZE = 5;

    //Tableau de positionable (5x5 de base --> static)
    //9x9 pour en avoir un dynamique
    private Positionable[][] board = new Positionable[BOARD_SIZE][BOARD_SIZE];


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
        int xy[] = getRightXY(x,y,dir);
        int x2 = xy[0], y2 = xy[1];


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

    public int[] getRightXY(int x, int y,Direction dir){
        int[] xy = new int[2];
        switch(dir){

            case NORTH:
                xy[0] = x;
                xy[1] = y-1;
                break;

            case SOUTH:
                xy[0] = x;
                xy[1] = y+1;
                break;

            case WEST:
                xy[0] = x-1;
                xy[1] = y;
                break;
            default:
                xy[0] = x+1;
                xy[1] = y;
                break;
        }
        return xy;
    }

    public boolean isPosable(int x, int y)
    {
        //Si c'est en dehors du tableau
        if(x < 0 || x>= BOARD_SIZE || y < 0 || y>= BOARD_SIZE ){
            return false;
        }

        if(this.board[x][y] != null){
            return false;
        }


        return true;
    }

    /** Fonction qui regarde si le plateau peut acceuillir cette tuile
     *
     * @param tile : Tuile à poser
     * @return TRUE si une case est disponible à jouer
     */
    public boolean isPlayable(Tile tile){
        int xy[];
        Direction[] directions = Direction.values();

        for(int j = 0; j<this.BOARD_SIZE; j++) {
            for (int i = 0; i < this.BOARD_SIZE; i++) {

                //Toutes les directions possibles
                for (int d = 0 ; d<directions.length; d++){
                    xy = getRightXY(i,j,directions[d]);
                    int x2 = xy[0], y2 = xy[1];

                    //Si les cases existent et qu'ils sont disponibles
                    if( isPosable(i, j) && isPosable(x2,y2)){
                        //Si un des 2 côtés est bien placé en terme de terrain
                        if(isSameGround(i, j, tile.getLeft()) || isSameGround(x2,y2, tile.getRight())){
                            return true;
                        }
                    }
                }


            }
        }
        return false;
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
        if(x+1 < BOARD_SIZE){
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
        if(y+1 < BOARD_SIZE){
            if(board[x][y+1] !=null){
                type = board[x][y+1].getColor();
                if( type == ground.getColor() || type == GroundColor.GREY ){
                    return true;
                }
            }

        }
        return false;
    }

    public boolean isHarmony(){
        boolean isHarmony = true;
        for(int j = 0; j<this.BOARD_SIZE; j++) {
            for (int i = 0; i < this.BOARD_SIZE; i++) {
                if(this.board[i][j] == null){
                    return false;
                }
            }
        }
        return isHarmony;
    }
    public boolean isKingdomMiddle(){
        return this.board[ this.getBOARD_SIZE()/2 ][this.getBOARD_SIZE()].getColor() == GroundColor.GREY;
    }

    public Positionable getPositionnable(int x, int y){
        return this.board[x][y];
    }

    public Positionable[][] getBoard() {
        return this.board;
    }

    public int getBOARD_SIZE() {
        return this.BOARD_SIZE;
    }
}
