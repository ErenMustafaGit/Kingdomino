package Model;

public enum GroundColor {
    GREY, //Castle
    YELLOW,
    DARK_GREEN,
    BLUE,
    LIGHT_GREEN,
    BLACK,
    BROWN;

    static GroundColor getColor(String color){
        if(color.equals("dark green")) return GroundColor.DARK_GREEN;
        if(color.equals("light green")) return GroundColor.LIGHT_GREEN;
        return GroundColor.valueOf(color.toUpperCase());
    }
}
