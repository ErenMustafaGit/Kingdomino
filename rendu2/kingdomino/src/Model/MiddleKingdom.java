package Model;


public class MiddleKingdom extends ModeDecorator {
    public MiddleKingdom(GameMode gameMode) {
        super(gameMode);
    }

    @Override
    public int calculateScore(PlayerBoard p) {
        return p.isKingdomMiddle() ? calculateScore(p)+10 : calculateScore(p);
    }

    @Override
    public String getnamegame() {
       return "MiddleKingdom";
    }
}
