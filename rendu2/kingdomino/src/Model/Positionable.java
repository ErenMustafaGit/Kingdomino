package Model;

public interface Positionable {

    public GroundColor getColor();

    public int getCrownNumber();

    public boolean isGroundCounted();
    public boolean isCrownCounted();

    public void setGroundCounted(boolean groundCounted);
    public void setCrownCounted(boolean crownCounted);

}