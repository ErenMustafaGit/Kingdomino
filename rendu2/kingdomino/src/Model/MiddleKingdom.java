package Model;


public class MiddleKingdom extends ModeDecorator {
    Ground ground;

    public MiddleKingdom(GameMode gameMode) {
        super(gameMode);
    }

    @Override
    public int calculateScore(PlayerBoard p) {
        return 0;
    }

    @Override
    public String getnamegame() {
       return "MiddleKingdom";
    }
}
