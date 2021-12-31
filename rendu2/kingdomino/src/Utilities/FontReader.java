package Utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.Font;

public class FontReader {
    private static FontReader instance;
    private Font algerian;

    private Font showcard;
    private Font bookmanold;


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
                algerian = Font.createFont(Font.TRUETYPE_FONT, new File("./kingdomino/font/algerian.ttf"));

            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }

            //enregistrer les Fonts
            GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
            g.registerFont(algerian);
        }

        return algerian;
    }
    public Font getShowcard() throws IOException, FontFormatException{
        if(showcard!=null){
            return showcard;
        }
        else {
            //fichier du font label mode de jeu - Bookman Old Style
            try {
                showcard = Font.createFont(Font.TRUETYPE_FONT, new File("./kingdomino/font/showcard-gothic.ttf"));
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }

            GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
            g.registerFont(showcard);
        }
        return showcard;
    }
    public Font getBookmanold() throws IOException, FontFormatException{
        if(bookmanold!=null){
            return bookmanold;
        }
        else{
            //fichier du font pour label menu du choix | Label Nbre de joueurs | les messages d'erreurs - Showcard Gothic
            try{
            bookmanold = Font.createFont(Font.TRUETYPE_FONT, new File("./kingdomino/font/bookman-old-style.ttf"));
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }
            GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
            g.registerFont(bookmanold);
        }
        return bookmanold;
    }


}
