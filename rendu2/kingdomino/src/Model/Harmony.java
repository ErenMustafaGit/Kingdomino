package Model;

public class Harmony extends ModeDecorator {
    //les terrains
    Ground ground;

    public Harmony(GameMode gameMode) {
        super(gameMode);
    }

    @Override
    public int calculateScore(PlayerBoard p) {
        return 0;
    }

    @Override
    public String getnamegame() {
        return null;
    }

    @Override
    public int calculateNeighbor(PlayerBoard p, Ground g, int i, int j) {
        GroundColor color;
        int nbNeighbor = 1;
        /**Calcul le nombre de voisin par domaine**/
        for (i = 0; i < p.getBOARD_SIZE(); i++) {
            for (j = 0; j < p.getBOARD_SIZE(); j++) {
                if (p.getPositionnable(i, j) != null) {
                    if (j - 1 >= 0) {
                        //si la case du dessus n'est pas vide
                        if (p.getPositionnable(i, j - 1) != null) {
                            /**On récupère la couleur de la case au dessus**/
                            color = p.getPositionnable(i, j - 1).getColor();
                            /**Si la couleur de la case suivane est la même que celle qu'on a **/
                            if (color == p.getPositionnable(i, j).getColor() && !p.getPositionnable(i, j - 1).isGroundCounted()) {
                                g = (Ground) p.getPositionnable(i, j - 1);
                                /**RECURSIVITE**/
                                nbNeighbor += 1;
                                calculateNeighbor(p, g, i, j - 1);
                                p.getPositionnable(i, j - 1).setGroundCounted(true);
                            }

                        }
                    }
                    if (j + 1 < p.getBOARD_SIZE()) {
                        //si la case d'en bas n'est pas vide
                        if (p.getPositionnable(i, j + 1) != null) {
                            /**On récupère la couleur de la case de droite**/
                            color = p.getPositionnable(i, j + 1).getColor();

                            /**Si la couleur de la case suivane est la même que celle qu'on a **/
                            if (color == p.getPositionnable(i, j).getColor() && !p.getPositionnable(i, j + 1).isGroundCounted()) {
                                g = (Ground) p.getPositionnable(i, j + 1);
                                /**RECURSIVITE**/
                                nbNeighbor += 1;
                                calculateNeighbor(p, g, i, j + 1);
                                p.getPositionnable(i, j + 1).setGroundCounted(true);
                            }
                        }
                    }
//                    if (i - 1 > 0) {
//                        //si la case de gauche est pas vide
//                        if (p.getPositionnable(i - 1, j) != null) {
//                            /**On récupère la couleur de la case de droite**/
//                            color = p.getPositionnable(i - 1, j).getColor();
//                            /**Si la couleur de la case suivane est la même que celle qu'on a **/
//                            if (color == p.getPositionnable(i, j).getColor() && !p.getPositionnable(i - 1, j).getCounted()) {
//                                g = (Ground) p.getPositionnable(i - 1, j);
//                                /**RECURSIVITE**/
//                                nbNeighbor +=1;
//                                calculateNeighbor(p, g, i - 1, j);
//                                p.getPositionnable(i - 1, j).setCounted(true);
//                            }
//                        }
//                    }
//                    if (i + 1 < p.BOARD_SIZE) {
//                        if (p.getPositionnable(i + 1, j) != null) {
//                            /**On récupère la couleur de la case de droite**/
//                            color = p.getPositionnable(i + 1, j).getColor();
//                            /**Si la couleur de la case suivante est la même que celle qu'on a **/
//                            if (color == p.getPositionnable(i, j).getColor() && !p.getPositionnable(i + 1, j).getCounted()) {
//                                g = (Ground) p.getPositionnable(i + 1, j);
//                                /**RECURSIVITE**/
//                                nbNeighbor +=1;
//                                calculateNeighbor(p, g, i + 1, j);
//                                p.getPositionnable(i + 1, j).setCounted(true);
//                            }
//                        }
//                    }
                }
            }
        }
        return nbNeighbor;
    }

    @Override
    public int calculateNbCrown(PlayerBoard p, Ground g, int i, int j) {
        return 0;
    }
}

    // public int calculateNbCrown(PlayerBoard p, Ground g, int i, int j) {
//        GroundColor color;
   // int nbCrown = 0;
//        /**Calcul le nombre de voisin par domaine**/
//        for (i = 0; i < p.BOARD_SIZE; i++) {
//            for (j = 0; j < p.BOARD_SIZE; j++) {
//                if (p.getPositionnable(i, j) != null) {
//                    if (j - 1 >= 0) {
//                        //si la case du dessus n'est pas vide
//                        if (p.getPositionnable(i, j - 1) != null) {
//                            /**On récupère la couleur de la case au dessus**/
//                            color = p.getPositionnable(i, j - 1).getColor();
//                            /**Si la couleur de la case suivane est la même que celle qu'on a **/
//                            if (color == p.getPositionnable(i, j).getColor() && !p.getPositionnable(i, j - 1).getCounted()) {
//                                g = (Ground) p.getPositionnable(i, j - 1);
//                                /**RECURSIVITE**/
//                                nbCrown +=1;
//                                calculateNbCrown(p, g, i, j - 1);
//                                p.getPositionnable(i, j - 1).setCounted(true);
//                            }
//
//                        }
//                    }
//                    if (j + 1 < p.BOARD_SIZE) {
//                        //si la case d'en bas n'est pas vide
//                        if (p.getPositionnable(i, j + 1) != null) {
//                            /**On récupère la couleur de la case de droite**/
//                            color = p.getPositionnable(i, j + 1).getColor();
//
//                            /**Si la couleur de la case suivane est la même que celle qu'on a **/
//                            if (color == p.getPositionnable(i, j).getColor() && !p.getPositionnable(i, j + 1).getCounted()) {
//                                g = (Ground) p.getPositionnable(i, j + 1);
//                                /**RECURSIVITE**/
//                                nbCrown +=1;
//                                calculateNeighbor(p, g, i, j + 1);
//                                p.getPositionnable(i, j + 1).setCounted(true);
//                            }
//                        }
//                    }
//                    if (i - 1 > 0) {
//                        //si la case de gauche est pas vide
//                        if (p.getPositionnable(i - 1, j) != null) {
//                            /**On récupère la couleur de la case de droite**/
//                            color = p.getPositionnable(i - 1, j).getColor();
//                            /**Si la couleur de la case suivane est la même que celle qu'on a **/
//                            if (color == p.getPositionnable(i, j).getColor() && !p.getPositionnable(i - 1, j).getCounted()) {
//                                g = (Ground) p.getPositionnable(i - 1, j);
//                                /**RECURSIVITE**/
//                                nbCrown +=1;
//                                calculateNbCrown(p, g, i - 1, j);
//                                p.getPositionnable(i - 1, j).setCounted(true);
//                            }
//                        }
//                    }
//                    if (i + 1 < p.BOARD_SIZE) {
//                        if (p.getPositionnable(i + 1, j) != null) {
//                            /**On récupère la couleur de la case de droite**/
//                            color = p.getPositionnable(i + 1, j).getColor();
//                            /**Si la couleur de la case suivante est la même que celle qu'on a **/
//                            if (color == p.getPositionnable(i, j).getColor() && !p.getPositionnable(i + 1, j).getCounted()) {
//                                g = (Ground) p.getPositionnable(i + 1, j);
//                                /**RECURSIVITE**/
//                                nbCrown +=1;
//                                calculateNbCrown(p, g, i + 1, j);
//                                p.getPositionnable(i + 1, j).setCounted(true);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return nbCrown;
//    }
//
//    @Override
//    public String getnamegame() {
//        return "Harmony";
//    }

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
