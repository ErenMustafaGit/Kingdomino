package Model;

public class NormalMode implements GameMode {
   //private static int BOARD_SIZE = 5;
    private Positionable[][] board;
    private Ground g;

    @Override
    public int calculateScore(PlayerBoard p) {
        int count =0;
        board = p.getBoard();
        //pour les couronnes ici
//       for(int i =0; i<p.BOARD_SIZE; i++){
//           for(int j=0; j<p.BOARD_SIZE; j++){
//               //count += countcell(p, i,j);
//                    count +=countcell(p, i , j);
//                    System.out.println("nombre = "+count);
//               //si y'a une couronne : couronnes * countcell(p,i,j)
//               //sinon 0
//
//           }
//       }
       for(int i =0; i<p.BOARD_SIZE; i++){
          for(int j=0; j<p.BOARD_SIZE; j++) {
              count += countcrown(p, i,j);
              System.out.println("nombre = "+count);

          }
       }


       return count;
    }
    public int countcell(PlayerBoard p, int x, int y){
        //on récupère le plateau du joueur
        board = p.getBoard();
        //on définit une variable couleur
        GroundColor type;
        //on met le compteur à 1
        int count = 1;

        if(board[x][y]!=null){
            //on récupère la couleur du plateau
            type = board[x][y].getColor();
            if(y-1 > 0){
                //si la case d'au dessus n'est pas nul
                if(board[x][y-1] !=null){
                    if( type == board[x][y-1].getColor()){
                       count +=countcell(p,x, y-1);
                    }
                }
            }
            if(x+1 < p.BOARD_SIZE){
                //si la case de droite n'est pas vide
                if(board[x+1][y] !=null){
                    if( type == board[x+1][y].getColor()){
                        count +=countcell(p,x+1, y);
                    }
                }
            }
            if(x-1 > 0){
                //si la case d'a gauche n'est pas vide
                if(board[x-1][y] !=null){
                    if( type == board[x-1][y].getColor()){
                        count +=countcell(p,x-1, y);
                    }
                }
            }
            if(y+1 < p.BOARD_SIZE){
                //si la case d'en bas n'est pas vide
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
        //on récupère le plateau du joueur
        board = p.getBoard();
        //on met le compteur à 0
        int count = 0;


        if(board[x][y]!=null){
            //on récupère le nombre de couronne dans la case
            int crown = board[x][y].getCrownNumber();

            if (board[x][y].getCrownNumber() > 0) {
                count += board[x][y].getCrownNumber() + countcrown(p, x,  y);
            }

        }

        return count;
    }

    @Override
    public String getnamegame() {
        return "Normalmode";
    }
}
