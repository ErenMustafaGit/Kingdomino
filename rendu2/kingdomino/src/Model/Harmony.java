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
        return "Harmony";
    }
}

