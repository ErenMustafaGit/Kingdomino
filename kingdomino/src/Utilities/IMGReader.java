package Utilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class IMGReader {

    private static final String ressourcePath = "./a31-kingdomino/kingdomino/ressources/";

    public static JLabel getImage(String filename) {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File(ressourcePath + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = myPicture.getScaledInstance(100, 100,
                Image.SCALE_SMOOTH);

        return new JLabel(new ImageIcon(dimg));
    }

}
