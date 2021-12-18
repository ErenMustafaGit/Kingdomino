package Utilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class IMGReader {

    private static final String ressourcePath = "./kingdomino/ressources/";

    public static JLabel getImagePnl(String filename) {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File(ressourcePath + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = myPicture.getScaledInstance(50, 50,
                Image.SCALE_SMOOTH);

        return new JLabel(new ImageIcon(dimg));
    }

    public static JLabel getImagePnl(String filename, int width, int height) {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File(ressourcePath + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = myPicture.getScaledInstance(width, height,
                Image.SCALE_SMOOTH);

        return new JLabel(new ImageIcon(dimg));
    }

    public static Image getImage(String filename) {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File(ressourcePath + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myPicture;
    }

}
