package Model;

public class Ground implements Positionable{
    private GroundColor color;
    private int crownNumber;
    private boolean groundCounted;
    private boolean crownCounted;

    public Ground(GroundColor color, int crownNumber){
        this.color = color;
        this.crownNumber = crownNumber;
        this.groundCounted = false;
        this.crownCounted = false;
    }



    @Override
    public boolean isGroundCounted() {
        return groundCounted;
    }

    @Override
    public boolean isCrownCounted() {
        return this.crownCounted;
    }


    public void setGroundCounted(boolean groundCounted) {
        this.groundCounted = groundCounted;
    }

    @Override
    public void setCrownCounted(boolean crownCounted) {
        this.crownCounted = crownCounted;
    }

    @Override
    public GroundColor getColor() {
        return this.color;
    }

    @Override
    public int getCrownNumber() {
        return this.crownNumber;
    }


}
