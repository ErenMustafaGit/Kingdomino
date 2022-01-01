package Model;

public abstract class ModeDecorator implements GameMode {
    private GameMode wrapee;

    public ModeDecorator(GameMode gameMode){
        this.wrapee = gameMode;
    }

    public abstract int calculateScore(PlayerBoard p);

}
