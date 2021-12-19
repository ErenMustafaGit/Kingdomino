package Model;

public class MiddleKingdom extends ModeDecorator
{
    Ground ground;
    private Ground otherGround;
    private GroundColor color;
    Castle castle;

    public MiddleKingdom(GameMode gameMode) {
        super(gameMode);
    }

    @Override
    public int calculateScore(Player p) {
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
                    if(j+1 <p.getBoard().BOARD_SIZE){
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
        if(castle == p.getBoard().getPositionnable(2,2)){
            score+=10;
        }
        else{
            score-=10;
        }
        return score;
    }

    @Override
    public String getnamegame() {
       return "MiddleKingdom";
    }
}
