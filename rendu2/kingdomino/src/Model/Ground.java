package Model;

public class Ground implements Positionable{
    private GroundColor color;
    private int crownNumber;
    private boolean isCounted;
    public Ground(GroundColor color, int crownNumber){
        this.color = color;
        this.crownNumber = crownNumber;
    }

    @Override

    public int getCrownNumber() {
        return this.crownNumber;
    }

    @Override
    public boolean isCounted() {
        return isCounted;
    }

    public void setCounted(boolean counted) {
        isCounted= counted;
    }

    public void setCrownNumber(int crownNumber) {
        this.crownNumber = crownNumber;
    }

    @Override
    public GroundColor getColor() {
        return this.color;
    }

}
