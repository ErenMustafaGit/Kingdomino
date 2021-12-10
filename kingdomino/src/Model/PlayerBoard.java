package Model;

public class PlayerBoard
{
    private final int BOARD_SIZE = 9;

    //Tableau de positionable (9x9 de base --> qui permet d'en avoir un dynamique)
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
        if( isPosable(x, y) && isPosable(x,y) ){
            this.board[y][x] = tile.getLeft();
        }
        return true;
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

    private boolean getDynamiqueBoard(int x, int y){
        int element = 0;
        for (int i = 0; i<BOARD_SIZE; i++){

            //Obtiens les elements colonne J et ligne I
            for (int j = 0; i<BOARD_SIZE; j++){

                //Si il y a un élément dans cette place ou si c'est
                if(this.board[i][j] != null || j == x && i == y ){
                    element++;
                }
            }
        }

        return false;
    }

    //Renvoie faux si le terrain que nous allons posé va dépasser le 5*5
    private boolean nbElementLine(int x, int y, Direction dir){
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

        //Verification Ligne par Ligne
        int element = 0;
        for(int i = 0; i<BOARD_SIZE; i++){
            for(int j = 0; j<BOARD_SIZE; j++){

                if((i == y && j == x )|| (i == y2 && j == x2 ) || this.board[i][j] != null ){
                    element++;
                    break;
                }
            }
        }

        if(element > 5){
            return false;
        }

        //Verification Colonne par Colonne (comme sur figma)
        element = 0;
        for(int i = 0; i<BOARD_SIZE; i++){
            for(int j = 0; j<BOARD_SIZE; j++){

                if((i == y && j == x )|| (i == y2 && j == x2 ) || this.board[j][i] != null ){
                    element++;
                    break;
                }
            }
        }
        if(element > 5){
            return false;
        }

        return true;
    }

}
