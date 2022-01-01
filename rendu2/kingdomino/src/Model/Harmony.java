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
        if(p.isHarmony()){
            return 5;
        }
        return 0;
    }

    @Override
    public String getnamegame() {
        return "Harmony";
    }
}

