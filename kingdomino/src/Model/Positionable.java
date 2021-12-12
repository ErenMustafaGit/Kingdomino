package Model;

public interface Positionable {

    public GroundColor getColor();

    default public boolean isCounted(){
        return true;
    }
}
