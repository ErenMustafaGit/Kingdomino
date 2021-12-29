package Model;

public class NormalMode implements GameMode {
   //private static int BOARD_SIZE = 5;
    private Positionable[][] board;

    @Override
    public int calculateScore(PlayerBoard p) {
        int count =0;
       for(int i =0; i<p.BOARD_SIZE; i++){
           for(int j=0; j<p.BOARD_SIZE; j++){
               //count += countcell(p, i,j);
               count +=countcrown(p, i , j);
               //mettre que la case est deja visité
               System.out.println("nombre = "+count);
           }
       }
       return count;
    }
    public int countcell(PlayerBoard p, int x, int y){
        //on récupère le plateau du joueur
        board = p.getBoard();
        GroundColor type;
        int count = 1;

        type = board[x][y].getColor();
        if(board[x][y]!=null){
            if(y-1 >= 0){
                if(board[x][y-1] !=null){
                    if( type == board[x][y-1].getColor()  ){
                       count +=countcell(p,x, y-1);
                    }
                }
            }
            if(x+1 < p.BOARD_SIZE){
                if(board[x+1][y] !=null){
                    if( type == board[x+1][y].getColor()){
                        count +=countcell(p,x+1, y);
                    }
                }

            }
            if(x-1 >= 0){
                if(board[x-1][y] !=null){
                    if( type == board[x-1][y].getColor()){
                        count +=countcell(p,x-1, y);
                    }
                }
            }
            if(y+1 < p.BOARD_SIZE){
                if(board[x][y+1] !=null){
                    if( type == board[x][y+1].getColor()){
                        count +=countcell(p,x, y+1);
                    }
                }
            }
        }
        return count;
    }
    public int countcrown(PlayerBoard p, int x, int y){
        board = p.getBoard();

        Ground g = new Ground(board[x][y].getColor(), board[x][y-1].getCrown());
        int crown = g.getCrownNumber();
        int count = 1;


        if(board[x][y]!=null){
            if(y-1 >= 0){
                if(board[x][y-1] !=null){
                    if(crown>0){
                        count +=countcrown(p,x, y-1);
                    }
                }
            }
            if(x+1 < p.BOARD_SIZE){
                if(board[x+1][y] !=null){
                    if( crown>0){
                        count +=countcrown(p,x+1, y);
                    }
                }

            }
            if(x-1 >= 0){
                if(board[x-1][y] !=null){
                    if( crown>0){
                        count +=countcrown(p,x-1, y);
                    }
                }
            }
            if(y+1 < p.BOARD_SIZE){
                if(board[x][y+1] !=null){
                    if( crown >0){
                        count +=countcrown(p,x, y+1);
                    }
                }
            }
        }
        return count;
    }

    @Override
    public String getnamegame() {
        return "Normalmode";
    }
}
