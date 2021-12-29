package Model;

import java.awt.*;

public enum KingColor {
    PINK,
    BLUE,
    GREEN,
    YELLOW;

    public static Color getColor(KingColor color){
        switch (color) {
            case PINK -> {
                return new Color( 192, 2, 169 );
            }
            case BLUE -> {
                return new Color( 2, 148, 192 );
            }
            case GREEN -> {
                return new Color( 3, 166, 10 );
            }
            case YELLOW -> {
                return new Color( 178, 173, 19 );
            }
        }
        return Color.DARK_GRAY;
    }
}
