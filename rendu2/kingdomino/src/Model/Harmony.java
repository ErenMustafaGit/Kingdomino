package Model;

public class Harmony extends ModeDecorator {
    public Harmony(GameMode gameMode) {
        super(gameMode);
    }

    @Override
    public int calculateScore(PlayerBoard p) {
        return p.isHarmony() ? calculateScore(p)+5 : calculateScore(p);
    }



    @Override
    public String getnamegame() {
        return "Harmony";
    }
}

