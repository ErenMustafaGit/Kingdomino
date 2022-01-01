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

    @Override
    public boolean isGroundCounted() {
        return true;
    }

    @Override
    public boolean isCrownCounted() {
        return true;
    }

    @Override
    public void setGroundCounted(boolean groundCounted) {
        //Nothing to do
    }

    @Override
    public void setCrownCounted(boolean crownCounted) {
        //Nothing to do

    }

}
