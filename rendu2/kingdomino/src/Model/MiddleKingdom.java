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
        if(isKingdomMiddle(p)){
            return 10;
        }
        return 0;
    }


    @Override
    public boolean isKingdomMiddle(PlayerBoard p){
        Positionable middle = p.getPositionnable(p.getBOARD_SIZE()/2,p.getBOARD_SIZE()/2);
        if(middle == null){
            return false;
        }
        return middle.getColor() == GroundColor.GREY;
    }
    @Override
    public String getnamegame() {
       return "MiddleKingdom";
    }

    @Override
    public boolean isHarmony(PlayerBoard p) {
        return super.isHarmony(p);
    }

}
