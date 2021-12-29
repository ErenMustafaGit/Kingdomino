package Model;

public class Castle implements Positionable{
    @Override
    public GroundColor getColor() {
        return GroundColor.GREY;
    }

    @Override
    public int getCrownNumber() {
        return 0;
    }
}
