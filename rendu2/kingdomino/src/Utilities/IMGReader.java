package Utilities;

import Model.GroundColor;

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

    /** Taille d'image d'un ground **/
    private static final int TILE_IMG_SIZE = 60;

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
            img = new ImageIcon(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream(  filename)).readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static ImageIcon getImage(GroundColor color){
        //Met l'image correspondant à la couleur de la case
        if(color == null){
            return IMGReader.getImage("empty.png");
        }else if(color == GroundColor.GREY){
            return IMGReader.getImage("castle.png");
        }else if(color == GroundColor.YELLOW){
            return IMGReader.getImage("champs.png");
        }else if(color == GroundColor.DARK_GREEN){
            return IMGReader.getImage("foret.png");
        }else if(color == GroundColor.LIGHT_GREEN){
            return IMGReader.getImage("prairie.png");
        }else if(color == GroundColor.BLACK){
            return IMGReader.getImage("mines.png");
        }else if(color == GroundColor.BLUE){
            return IMGReader.getImage("mer.png");
        }else if(color == GroundColor.BROWN){
            return IMGReader.getImage("montagne.png");
        }
        return null;
    }


    public static Icon getImage(GroundColor color, int crown) {
        String directory = "groundImage/";
        //Met l'image correspondant à la couleur de la case
        if(color == null){
            return IMGReader.getImage(directory+"empty.png");
        }else if(color == GroundColor.GREY){
            return IMGReader.getImage(directory+"castle.png");
        }else if(color == GroundColor.YELLOW){
            return IMGReader.getImage(directory+"champs" + crown +".png");
        }else if(color == GroundColor.DARK_GREEN){
            return IMGReader.getImage(directory+"foret"+ crown +".png");
        }else if(color == GroundColor.LIGHT_GREEN){
            return IMGReader.getImage(directory+"prairie"+ crown +".png");
        }else if(color == GroundColor.BLACK){
            return IMGReader.getImage(directory+"mines"+ crown +".png");
        }else if(color == GroundColor.BLUE){
            return IMGReader.getImage(directory+"mer"+ crown +".png");
        }else if(color == GroundColor.BROWN){
            return IMGReader.getImage(directory+"montagne"+ crown +".png");
        }
        return null;
    }

    public static int getTileImgSize(){
        return IMGReader.TILE_IMG_SIZE;
    }
}
