package Model;

public abstract class ModeDecorator implements GameMode {
    private GameMode wrapee;

    public ModeDecorator(GameMode gameMode){
        this.wrapee = gameMode;
    }

    public int calculateScore(PlayerBoard p){
        return wrapee.calculateScore(p);
    }

}
