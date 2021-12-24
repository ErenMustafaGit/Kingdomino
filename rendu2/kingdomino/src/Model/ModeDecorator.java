package Model;

public abstract class ModeDecorator implements GameMode {
    private GameMode wrapee;

    public ModeDecorator(GameMode gameMode){
        this.wrapee = gameMode;
    }

    public abstract int calculateScore(PlayerBoard p);
    public abstract int calculateNeighbor(PlayerBoard p, Ground g,int i, int j );
    public abstract int calculateNbCrown(PlayerBoard p, Ground g, int i, int j);

}
