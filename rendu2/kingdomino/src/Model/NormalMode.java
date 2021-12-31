package Model;

import java.awt.*;

public class NormalMode implements GameMode {
   //private static int BOARD_SIZE = 5;
    private Positionable[][] board;
    private Ground g;

    @Override
    public int calculateScore(PlayerBoard p) {
        int count =0;
        int cell = 0;
        int crown =0;
        board = p.getBoard();
        //pour les couronnes ici
       for(int i =0; i<p.BOARD_SIZE; i++){
           for(int j=0; j<p.BOARD_SIZE; j++){
                  if( board[i][j]!=null) {
                      if (board[i][j].isCounted() == false) {
                          cell = countcell(p, i, i);
                          crown = countcrown(p, i, j);
                          count = cell * crown;
                          board[i][j].setCounted(true);
                      }
                  }
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
                    if( type == board[x][y-1].getColor() && !board[x][y-1].isCounted()){
                       board[x][y-1].setCounted(true);
                       count +=countcell(p,x, y-1);

                    }
                }
            }
            if(x+1 < p.BOARD_SIZE){
                //si la case de droite n'est pas vide
                if(board[x+1][y] !=null){
                    if( type == board[x+1][y].getColor() && !board[x+1][y].isCounted()){
                        board[x+1][y].setCounted(true);
                        count +=countcell(p,x+1, y);
                    }
                }
            }
            if(x-1 > 0){
                //si la case d'a gauche n'est pas vide
                if(board[x-1][y] !=null){
                    if( type == board[x-1][y].getColor() && !board[x-1][y].isCounted()){
                        board[x-1][y].setCounted(true);
                        count +=countcell(p,x-1, y);
                    }
                }
            }
            if(y+1 < p.BOARD_SIZE){
                //si la case d'en bas n'est pas vide
                if(board[x][y+1] !=null && !board[x][y+1].isCounted()){
                    if( type == board[x][y+1].getColor()){
                        board[x][y+1].setCounted(true);
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
        //g = (Ground) ;

        if(board[x][y]!=null){
            //on récupère le nombre de couronne dans la case
            count = board[x][y].getCrownNumber();

        }
        else {
            count = 0;
        }
        return count;
    }

    @Override
    public String getnamegame() {
        return "Normalmode";
    }
}
