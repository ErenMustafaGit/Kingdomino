package Model;


public class MiddleKingdom extends ModeDecorator {
    Ground ground;

    public MiddleKingdom(GameMode gameMode) {
        super(gameMode);
    }

    @Override
    public int calculateScore(PlayerBoard p) {
        int neighbor=0;
        int crown=0;
        for(int i =0; i<p.getBOARD_SIZE(); i++){
            for(int j=0; j<p.getBOARD_SIZE();j++){
                neighbor =calculateNeighbor(p, ground,i,j);
                crown = calculateNbCrown(p, ground, i, j);
            }
        }
        return neighbor*crown;
    }

    @Override
    public int calculateNeighbor(PlayerBoard p, Ground g, int i, int j) {
//        GroundColor color;
//        int nbNeighbor =1;
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
//                                nbNeighbor +=1;
//                                calculateNeighbor(p, g, i, j - 1);
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
//                                nbNeighbor +=1;
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
//                }
//            }
//        }
//        return nbNeighbor;
        return 0;
    }

    @Override
    public int calculateNbCrown(PlayerBoard p, Ground g, int i, int j) {
//        GroundColor color;
//        int nbCrown =0;
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
        return 0;
    }

    @Override
    public String getnamegame() {
       return "MiddleKingdom";
    }
}
