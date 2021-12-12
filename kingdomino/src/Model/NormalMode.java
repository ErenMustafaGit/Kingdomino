package Model;

import java.util.Arrays;

public class NormalMode implements GameStrategy{

    //les terrains
    private Ground ground;

    //le joueur
    private Player p;
    private PlayerBoard playerboard;
    @Override
    public int calculateScore() {

        int score =0;
        //faire un nouveau tableau de positionnable
        Positionable[][] crownplace = new Positionable[5][5];
        GroundColor type;

        //on positionne d'abord dans un nouveau tableau les cases où il n'y a que des couronnes
        for(int i=0; i<p.board.BOARD_SIZE; i++){
            for(int j=0; j<p.board.BOARD_SIZE; j++){
                p.board.getPositionnable(i,j);
                //si dans le terrain il y'a au moins une couronne
                if(ground.getCrownNumber()>0){
                    ground.getColor();
                    crownplace[i][j]=ground;
                    ground.isCounted();
                    //si autour du terrain il y'a la meme couleur
                    if(i-1 >= 0){
                        if(p.board[i][j-1] != null){
                            type = p.board[i][j-1].getColor();

                            if( type == ground.getColor()){
                                ground.getColor();
                                ground.isCounted();
                            }
                        }

                    }
                    if(i+1 < p.board.BOARD_SIZE){
                        if(p.board[i][j+1] !=null){
                            type = p.board[i][j+1].getColor();
                            if( type == ground.getColor()){
                                ground.getColor();
                                ground.isCounted();
                            }
                        }

                    }
                    if(j-1 >= 0){
                        if(p.board[i-1][j] !=null){
                            type = p.board[i-1][j].getColor();
                            if( type == ground.getColor()){
                                ground.getColor();
                                ground.isCounted();
                            }
                        }

                    }
                    if(j+1 >= 0){
                        if(p.board[i+1][j] !=null){
                            type = p.board[i+1][j].getColor();
                            if( type == ground.getColor()){
                                ground.getColor();
                                ground.isCounted();
                            }
                        }

                    }

                    }
                }

            }




        return score;
    }
}
