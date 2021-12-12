package Model;

public class Ground implements Positionable{
    private GroundColor color;
    private int crownNumber;

    public Ground(GroundColor color, int crownNumber){
        this.color = color;
        this.crownNumber = crownNumber;
    }


    public int getCrownNumber() {
        return this.crownNumber;
    }

    public void setCrownNumber(int crownNumber) {
        this.crownNumber = crownNumber;
    }

    @Override
    public GroundColor getColor() {
        return this.color;
    }
}
