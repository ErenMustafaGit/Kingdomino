package Model;

public class Castle implements Positionable{
    boolean isCounted;

    @Override
    public GroundColor getColor() {
        return GroundColor.GREY;
    }

    @Override
    public int getCrownNumber() {
        return 0;
    }

    @Override
    public boolean isCounted() {
        return isCounted;
    }

    @Override
    public void setCounted(boolean counted) {
        isCounted=true;
    }
}
