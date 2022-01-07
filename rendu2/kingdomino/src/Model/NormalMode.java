package Model;

public class NormalMode implements GameMode {


    @Override
    public int calculateScore(PlayerBoard p) {

        int count =0;
        int cell = 0;
        int crown =1;

        for(int j =0; j<p.getBOARD_SIZE(); j++){
            for(int i=0; i<p.getBOARD_SIZE(); i++){
                crown = 0;
                cell = 0;
                if(  p.getPositionnable(i,j) !=null) {
                    if (!p.getPositionnable(i,j).isGroundCounted()) {
                        p.getPositionnable( i,j ).setGroundCounted(true);
                        cell = countcell(p, i, j);

                    }

                    if(!p.getPositionnable(i,j).isCrownCounted()){
                        p.getPositionnable( i,j ).setCrownCounted(true);
                        crown = countcrown(p, i, j);
                    }
                }
                count += cell * crown;
            }
        }

        return count;
    }
    public int countcell(PlayerBoard p, int x, int y){
        //on récupère le plateau du joueur
        //on définit une variable couleur
        GroundColor type;
        //on met le compteur à 1
        int count = 0;
        if(p.getPositionnable(x,y)!=null){
            count = 1;
            //on récupère la couleur du plateau
            type = p.getPositionnable(x,y).getColor();


            if(y-1 >= 0){
                //si la case d'au dessus n'est pas nul
                if(p.getPositionnable(x,y-1) !=null){
                    if( type == p.getPositionnable(x,y-1).getColor() && !p.getPositionnable(x,y-1).isGroundCounted()){
                        p.getPositionnable(x,y-1).setGroundCounted(true);
                        count +=countcell(p,x, y-1);

                    }
                }
            }
            if(x+1 < p.getBOARD_SIZE()){
                //si la case de droite n'est pas vide
                if(p.getPositionnable(x+1,y) !=null){
                    if( type == p.getPositionnable(x+1,y).getColor() && !p.getPositionnable(x+1,y).isGroundCounted()){

                        p.getPositionnable(x+1,y).setGroundCounted(true);
                        count +=countcell(p,x+1, y);
                    }
                }
            }
            if(x-1 >= 0){
                //si la case d'a gauche n'est pas vide
                if(p.getPositionnable(x-1,y) !=null){
                    if( type == p.getPositionnable(x-1,y).getColor() && !p.getPositionnable(x-1,y).isGroundCounted()){
                        p.getPositionnable(x-1,y).setGroundCounted(true);
                        count +=countcell(p,x-1, y);
                    }
                }
            }
            if(y+1 < p.getBOARD_SIZE()){
                //si la case d'en bas n'est pas vide
                if(p.getPositionnable(x,y+1) !=null && !p.getPositionnable(x,y+1).isGroundCounted()){

                    if( type == p.getPositionnable(x,y+1).getColor()){
                        p.getPositionnable(x,y+1).setGroundCounted(true);
                        count +=countcell(p,x, y+1);
                    }
                }
            }
        }
        return count;
    }


    //Méthode de test -- A SUPP
    private void print(PlayerBoard p){
        System.out.println("print----");

        for(int j =0; j<p.getBOARD_SIZE(); j++) {
            for (int i = 0; i < p.getBOARD_SIZE(); i++) {
                if(p.getPositionnable(i,j) == null){
                    System.out.println("null");
                }else {
                    System.out.println( i + " " + j + p.getPositionnable(i,j) + " " + p.getPositionnable(i,j).isGroundCounted() + p.getPositionnable(i,j).getCrownNumber() );

                }
            }
        }
    }

    public int countcrown(PlayerBoard p, int x, int y){
        //on définit une variable couleur
        GroundColor type = null;
        //on met le compteur à 0
        int count = 0;

        if(p.getPositionnable(x,y)!=null){
            count = p.getPositionnable( x,y ).getCrownNumber();

            //on récupère la couleur de la case actuelle
            type = p.getPositionnable(x,y).getColor();
            if(y-1 > 0){
                //si la case d'au dessus n'est pas nul
                if(p.getPositionnable(x,y-1) !=null){
                    if( type == p.getPositionnable(x,y-1).getColor() && !p.getPositionnable(x,y-1).isCrownCounted()){
                        p.getPositionnable(x,y-1).setCrownCounted(true);
                        count += countcrown(p,x, y-1);

                    }
                }
            }
            if(x+1 < p.getBOARD_SIZE()){
                //si la case de droite n'est pas vide
                if(p.getPositionnable(x+1,y) !=null){
                    if( type == p.getPositionnable(x+1,y).getColor() && !p.getPositionnable(x+1,y).isCrownCounted()){
                        p.getPositionnable(x+1,y).setCrownCounted(true);
                        count +=countcrown(p,x+1, y);
                    }
                }
            }
            if(x-1 > 0){
                //si la case d'a gauche n'est pas vide
                if(p.getPositionnable(x-1,y) !=null){
                    if( type == p.getPositionnable(x-1,y).getColor() && !p.getPositionnable(x-1,y).isCrownCounted()){
                        p.getPositionnable(x-1,y).setCrownCounted(true);
                        count +=countcrown(p,x-1, y);
                    }
                }
            }
            if(y+1 < p.getBOARD_SIZE()){
                //si la case d'en bas n'est pas vide
                if(p.getPositionnable(x,y+1) !=null && !p.getPositionnable(x,y+1).isCrownCounted()){
                    if( type == p.getPositionnable(x,y+1).getColor()){
                        p.getPositionnable(x,y+1).setCrownCounted(true);

                        count +=countcrown(p,x, y+1);

                    }
                }
            }
        }
        return count;
    }

    @Override
    public boolean hasHarmony() {
        return false;
    }

    @Override
    public boolean isHarmony(PlayerBoard p) {
        return false;
    }

    @Override
    public boolean isKingdomMiddle(PlayerBoard p) {
        return false;
    }
}
