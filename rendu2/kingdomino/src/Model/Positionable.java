package Model;

public interface Positionable {

    public GroundColor getColor();

    default public boolean isCounted(){
        return true;
    }
    default public void setCounted(boolean isCounted){
        isCounted = isCounted();
    }

    int getCrown();
}