package Utilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.Font;

public class FontReader {
    private static FontReader instance;
    private static Font algerian;

    public static Font showcard;
    public static Font bookmanold;


    private FontReader(){

    }
    public static FontReader getInstance() throws IOException, FontFormatException {


        if(instance == null) {
            instance = new FontReader();
        }
        return instance;
    }
    public Font getAlgerian() throws IOException, FontFormatException {

        /**Création des Fonts**/
        //fichier du font algerian pour les boutons | boutton commencer |harmony et middle kingdom | CBO nbre de joueurs - ALGERIAN
        algerian = Font.createFont(Font.TRUETYPE_FONT, new File("./kingdomino/font/algerian.ttf"));


        //enregistrer les Fonts
        GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        g.registerFont(algerian);

        return algerian;
    }
    public Font getShowcard() throws IOException, FontFormatException{

        //fichier du font label mode de jeu - Bookman Old Style
        showcard = Font.createFont(Font.TRUETYPE_FONT, new File("./kingdomino/font/showcard-gothic.ttf"));
        GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        g.registerFont(showcard);
        return showcard;
    }
    public Font getBookmanold() throws IOException, FontFormatException{
        //fichier du font pour label menu du choix | Label Nbre de joueurs | les messages d'erreurs - Showcard Gothic
        bookmanold = Font.createFont(Font.TRUETYPE_FONT, new File("./kingdomino/font/bookman-old-style.ttf"));
        GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        g.registerFont(bookmanold);
        return bookmanold;
    }


}
