package Utilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class IMGReader {

        /* Solution pour les images dans l'executable
        ClassLoader.getSystemClassLoader().getResourceAsStream("backGraph.png").readAllBytes()
            _font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream(path)));
         */
    private static final String RESSOURCE_PATH = "./kingdomino/ressources/";

    public static JLabel getImagePnl(String filename) {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File(RESSOURCE_PATH + filename));
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
            myPicture = ImageIO.read(new File(RESSOURCE_PATH + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = myPicture.getScaledInstance(width, height,
                Image.SCALE_SMOOTH);

        return new JLabel(new ImageIcon(dimg));
    }

    public static ImageIcon getImage(String filename) {

        ImageIcon img = null;
        try {
            img = new ImageIcon(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream(filename)).readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println( img );
        return img;
        /*
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File(RESSOURCE_PATH + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myPicture;*/




    }

}
