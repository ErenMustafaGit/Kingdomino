package Model;

public class Harmony extends ModeDecorator {
    //les terrains
    Ground ground;

    public Harmony(GameMode gameMode) {
        super(gameMode);
    }


    @Override
    public int calculateScore(Player p) {
        GroundColor color;
        int nbVoisin = 1;
        int nbCrown = 0;
        /**Calcul le nombre de voisin par domaine**/
        for(int i = 0; i<p.getBoard().BOARD_SIZE; i++) {
            for (int j = 0; j < p.getBoard().BOARD_SIZE; j++) {
                /** On récupère la couleur de la case où l'on est**/
                if (p.getBoard().getPositionnable(i, j) !=null){
                    if(j-1>=0){
                        if(p.getBoard().getPositionnable(i, j-1)!= null){
                            /**On récupère la couleur de la case de droite**/
                            color = p.getBoard().getPositionnable(i, j-1).getColor();

                            /**Si la couleur de la case suivane est la même que celle qu'on a **/
                            if(color == p.getBoard().getPositionnable(i,j).getColor() && !p.getBoard().getPositionnable(i,j-1).isCounted()){
                                ground = (Ground) p.getBoard().getPositionnable(i,j-1);
                                nbCrown += ground.getCrownNumber();
                                /**RECURSIVITE**/
                                calculateScore(p);
                                p.getBoard().getPositionnable(i, j-1).setCounted(true);
                            }
                        }
                    }
                    if(j+1<p.getBoard().BOARD_SIZE){
                        if(p.getBoard().getPositionnable(i, j+1)!= null){
                            /**On récupère la couleur de la case de droite**/
                            color = p.getBoard().getPositionnable(i, j+1).getColor();

                            /**Si la couleur de la case suivane est la même que celle qu'on a **/
                            if(color == p.getBoard().getPositionnable(i,j).getColor() && !p.getBoard().getPositionnable(i,j+1).isCounted()){
                                ground = (Ground) p.getBoard().getPositionnable(i,j+1);
                                nbCrown += ground.getCrownNumber();
                                /**RECURSIVITE**/
                                calculateScore(p);
                                p.getBoard().getPositionnable(i, j+1).setCounted(true);
                            }
                        }
                    }
                    if(i-1>0){
                        if(p.getBoard().getPositionnable(i-1, j)!= null){
                            /**On récupère la couleur de la case de droite**/
                            color = p.getBoard().getPositionnable(i-1, j).getColor();
                            /**Si la couleur de la case suivane est la même que celle qu'on a **/
                            if(color == p.getBoard().getPositionnable(i,j).getColor() && !p.getBoard().getPositionnable(i-1,j).isCounted()){
                                ground = (Ground) p.getBoard().getPositionnable(i-1,j);
                                nbCrown += ground.getCrownNumber();
                                /**RECURSIVITE**/
                                calculateScore(p);
                                p.getBoard().getPositionnable(i-1, j).setCounted(true);
                            }
                        }
                    }
                    if(i+1 <p.getBoard().BOARD_SIZE){
                        if(p.getBoard().getPositionnable(i+1, j)!= null){
                            /**On récupère la couleur de la case de droite**/
                            color = p.getBoard().getPositionnable(i+1, j).getColor();
                            /**Si la couleur de la case suivante est la même que celle qu'on a **/
                            if(color == p.getBoard().getPositionnable(i,j).getColor() && !p.getBoard().getPositionnable(i+1,j).isCounted()){
                                ground = (Ground) p.getBoard().getPositionnable(i+1,j);
                                nbCrown += ground.getCrownNumber();
                                /**RECURSIVITE**/
                                calculateScore(p);
                                p.getBoard().getPositionnable(i+1, j).setCounted(true);
                            }
                        }
                    }
                }
            }
        }

        return nbVoisin*nbCrown;
        /**terrain complet*/
    }

    @Override
    public String getnamegame() {
        return "Harmony";
    }
}
/*
 GroundColor color;
        //donne le score
        int score =0;
        //nombre de couronne sur le terrain
        int crownumber=ground.getCrownNumber();

        //compte le nombre de case
        int box =1;
        for(int i=0; i<p.getBoard().BOARD_SIZE; i++){
            for(int j=0; j<p.getBoard().BOARD_SIZE; j++){
                //si dans le terrain il y'a au moins une couronne
                if(crownumber>0){
                    box +=1;
                    ground.setCounted(true);

                    if(i-1 >= 0){
                        //si la case n'est pas vide
                        if(p.getBoard().getPositionnable(i, j-1)!= null){
                            //on récupère la couleur de la case
                            color = p.getBoard().getPositionnable(i, j-1).getColor();
                            //si la couleur de la case précédente est la meme que celle que celle qu'on voit (avec une couronne)
                            if( color == ground.getColor() && !p.getBoard().getPositionnable(i,j-1).isCounted()){
                                otherGround=(Ground)p.getBoard().getPositionnable(i,j-1);
                                crownumber+=otherGround.getCrownNumber();
                                box +=1;
                                ground.setCounted(true);
                            }
                        }
                    }
                    if(i+1 < p.getBoard().BOARD_SIZE){
                        if(p.getBoard().getPositionnable(i,j+1) !=null){
                            color = p.getBoard().getPositionnable(i, j+1).getColor();
                            if( color == ground.getColor() && !p.getBoard().getPositionnable(i, j+1).isCounted()){
                                crownumber+= otherGround.getCrownNumber();
                                box +=1;
                                p.getBoard().getPositionnable(i, j+1).setCounted(true);
                            }
                        }

                    }
                    if(j-1 >= 0){
                        if(p.getBoard().getPositionnable(i-1,j) !=null){
                            color = p.getBoard().getPositionnable(i-1,j).getColor();
                            if(color == ground.getColor() && !p.getBoard().getPositionnable(i-1,j).isCounted()){
                                crownumber+= otherGround.getCrownNumber();
                                box +=1;
                                p.getBoard().getPositionnable(i-1,j).setCounted(true);
                            }
                        }

                    }
                    if(j+1 < p.getBoard().BOARD_SIZE){
                        if(p.getBoard().getPositionnable(i+1,j) !=null){
                            color = p.getBoard().getPositionnable(i+1,j).getColor();
                            if( color == ground.getColor() && ! p.getBoard().getPositionnable(i+1,j).isCounted()){
                                crownumber+= otherGround.getCrownNumber();
                                box +=1;
                                p.getBoard().getPositionnable(i+1,j).setCounted(true);
                            }
                        }
                    }
                }
                score =crownumber*box + calculateScore(p);
            }
        }
        //on va dire que le plateau est complet
        score +=5;
        for(int i =0; i<5;i++){
            for(int j=0; j<5;j++){
                //si le tableau est pas compté alors on enlève les 5 points qu'on a ajouté et on enlève la pénalité => 10
                if(p.getBoard().getPositionnable(i,j)==null){
                    score-=10;
                    break;
                }
            }
        }

        return score;
* */
