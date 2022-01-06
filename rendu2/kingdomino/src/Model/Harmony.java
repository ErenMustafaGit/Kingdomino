package Model;

public class Harmony extends ModeDecorator {
    public Harmony(GameMode gameMode) {
        super(gameMode);
    }

    @Override
    public int calculateScore(PlayerBoard p) {
        return super.calculateScore(p) + calculateHarmonyBonus(p);
    }

    private int calculateHarmonyBonus(PlayerBoard p){
        if(isHarmony(p)){
            return 5;
        }
        return -5;
    }

    @Override
    public boolean isHarmony(PlayerBoard p){
        boolean isHarmony = true;
        for(int j = 0; j<p.getBOARD_SIZE(); j++) {
            for (int i = 0; i < p.getBOARD_SIZE(); i++) {
                if(p.getPositionnable(i,j) == null){
                    return false;
                }
            }
        }
        return isHarmony;
    }

    @Override
    public boolean hasHarmony() {
        return true;
    }

    @Override
    public boolean isKingdomMiddle(PlayerBoard p) {
        return super.isKingdomMiddle(p);
    }

}

