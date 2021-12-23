package Model;


public class MiddleKingdom extends ModeDecorator
{
    Ground ground;
    public MiddleKingdom(GameMode gameMode) {
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
                /** On réupère la couleur de la case où l'on est**/
                //p.getBoard().getPositionnable(i,j).setCounted(true);
                //si la case est null ou si la case contient un chateau
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
        int score = nbVoisin * nbCrown;
        /**chateau au milieu*/
        return score;
    }

    @Override
    public String getnamegame() {
       return "MiddleKingdom";
    }
}
