package Model;

public class Harmony implements GameStrategy{
    //les terrains
    private Ground ground;
    private Ground otherGround;

    //le joueur
    private Player p;
    private PlayerBoard playerboard;
    @Override
    public int calculateScore() {
        //donne le score
        int score =0;
        //compte le nombre de couronne
        int crownumber=0;
        //compte le nombre de case
        int box =0;
        //faire un nouveau tableau de positionnable
        Positionable[][] crownplace = new Positionable[5][5];

        //on positionne d'abord dans un nouveau tableau les cases où il n'y a que des couronnes
        for(int i=0; i<p.board.BOARD_SIZE; i++){
            for(int j=0; j<p.board.BOARD_SIZE; j++){
                //si dans le terrain il y'a au moins une couronne
                if(ground.getCrownNumber()>0){
                    crownplace[i][j]=ground;
                    crownumber= ground.getCrownNumber();
                    box +=1;
                    ground.isCounted();
                    //si autour du terrain il y'a la meme couleur

                    //si à gauche du terrain ca dépasse pas le tableau 5*5 :
                    if(i-1 >= 0){
                        //si la case n'est pas vide
                        if(p.board.getPositionnable(i, j-1)!= null){
                            //on récupère la couleur de la case
                            otherGround = (Ground) p.board.getPositionnable(i, j-1);
                            //si la couleur de la case précédente est la meme que celle que celle qu'on voit (avec une couronne)
                            if( otherGround.getColor() == ground.getColor() && !otherGround.isCounted()){
                                crownplace[i][j-1]=otherGround;
                                crownumber+= otherGround.getCrownNumber();
                                box +=1;
                                otherGround.isCounted();
                            }
                        }
                    }
                    if(i+1 < p.board.BOARD_SIZE){
                        if(p.board.getPositionnable(i,j+1) !=null){
                            otherGround = (Ground) p.board.getPositionnable(i, j+1);
                            if( otherGround.getColor() == ground.getColor() && !otherGround.isCounted()){
                                crownplace[i][j+1]=otherGround;
                                crownumber+= otherGround.getCrownNumber();
                                box +=1;
                                otherGround.isCounted();
                            }
                        }

                    }
                    if(j-1 >= 5){
                        if(p.board.getPositionnable(i-1,j) !=null){
                            otherGround = (Ground) p.board.getPositionnable(i-1,j);
                            if(otherGround.getColor() == ground.getColor() && !otherGround.isCounted()){
                                crownplace[i-1][j]=otherGround;
                                crownumber+= otherGround.getCrownNumber();
                                box +=1;
                                otherGround.isCounted();
                            }
                        }

                    }
                    if(j+1 >= 4){
                        if(p.board.getPositionnable(i+1,j) !=null){
                            otherGround = (Ground) p.board.getPositionnable(i+1,j);
                            if( otherGround.getColor() == ground.getColor() && !otherGround.isCounted()){
                                crownplace[i+1][j]=otherGround;
                                crownumber+= otherGround.getCrownNumber();
                                box +=1;
                                otherGround.isCounted();
                            }
                        }
                    }
                }

            }
        }
    //ici le score est calculé avec un plateau complet
        score = crownumber * box + 5;
        for(int i =0; i<5;i++){
            for(int j=0; j<5;j++){
                if(p.board.getPositionnable(i,j)==null){

                    score-=10;
                    break;
                }
            }
        }
        return score;
    }
}
