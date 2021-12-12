package Model;

public enum GroundColor {
    grey, //Castle
    yellow,
    darkGreen,
    blue,
    lightGreen,
    black,
    brown;

    static GroundColor getColor(String color){
        if(color.equals("dark green")) return GroundColor.darkGreen;
        if(color.equals("light green")) return GroundColor.lightGreen;
        return GroundColor.valueOf(color);
    }
}
