package Model;


public class MiddleKingdom extends ModeDecorator {
    public MiddleKingdom(GameMode gameMode) {
        super(gameMode);
    }

    @Override
    public int calculateScore(PlayerBoard p) {

        return super.calculateScore(p) + calculateKingdomBonus(p);
    }

    private int calculateKingdomBonus(PlayerBoard p){
        if(p.isKingdomMiddle()){
            return 10;
        }
        return 0;
    }

    @Override
    public String getnamegame() {
       return "MiddleKingdom";
    }
}
