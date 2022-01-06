package Model;

public abstract class ModeDecorator implements GameMode {
    private GameMode gameMode;

    public ModeDecorator(GameMode gameMode){
        this.gameMode = gameMode;
    }


    public int calculateScore(PlayerBoard p){
        return gameMode.calculateScore(p);
    }

    @Override
    public boolean isHarmony(PlayerBoard p){
        return false;
    }

    @Override
    public boolean isKingdomMiddle(PlayerBoard p){
        return false;
    }

    @Override
    public boolean hasHarmony(){
        return false;
    }

}
