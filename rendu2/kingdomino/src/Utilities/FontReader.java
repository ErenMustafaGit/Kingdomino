package Utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.util.Objects;

public class FontReader {
    private static FontReader instance;
    private Font algerian;
    private Font showcard;
    private Font bookmanold;
    private Font symbola;


    private FontReader(){

    }
    public static FontReader getInstance() {


        if(instance == null) {
            instance = new FontReader();
        }
        return instance;
    }
    public Font getAlgerian(){
        if(algerian !=null ){
            return algerian;
        }
        else {
            /**Création des Fonts**/
            //fichier du font algerian pour les boutons | boutton commencer |harmony et middle kingdom | CBO nbre de joueurs - ALGERIAN
            try {
                algerian = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull( ClassLoader.getSystemClassLoader().getResourceAsStream( "font/algerian.ttf" ) ));

                //algerian = Font.createFont(Font.TRUETYPE_FONT, new File("./kingdomino/font/algerian.ttf"));

            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }

            //enregistrer les Fonts
            GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
            g.registerFont(algerian);
        }

        return algerian;
    }
    public Font getShowcard(){
        if(showcard!=null){
            return showcard;
        }
        else {
            //fichier du font label mode de jeu - Bookman Old Style
            try {
                //new File("./kingdomino/font/showcard-gothic.ttf")
                showcard = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull( ClassLoader.getSystemClassLoader().getResourceAsStream( "font/showcard-gothic.ttf" ) ));
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }

            GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
            g.registerFont(showcard);
        }
        return showcard;
    }
    public Font getBookmanold(){
        if(bookmanold!=null){
            return bookmanold;
        }
        else{
            //fichier du font pour label menu du choix | Label Nbre de joueurs | les messages d'erreurs - Showcard Gothic
            try{
                bookmanold = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull( ClassLoader.getSystemClassLoader().getResourceAsStream( "font/bookman-old-style.ttf" ) ));
                //bookmanold = Font.createFont(Font.TRUETYPE_FONT, new File("./kingdomino/font/bookman-old-style.ttf"));
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }
            GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
            g.registerFont(bookmanold);
        }
        return bookmanold;
    }

    public Font getSymbola(){
        if(symbola!=null){
            return symbola;
        }
        else{
            //fichier du font pour label menu du choix | Label Nbre de joueurs | les messages d'erreurs - Showcard Gothic
            try{
                symbola = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull( ClassLoader.getSystemClassLoader().getResourceAsStream( "font/symbola.ttf" ) ));
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }
            GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
            g.registerFont(symbola);
        }
        return symbola;
    }


}
