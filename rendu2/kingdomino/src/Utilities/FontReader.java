package Utilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.Font;

public class FontReader {
    private static FontReader instance;
    private FontReader(){

    }
    public static FontReader getInstance() throws IOException, FontFormatException {


        if(instance == null) {
            /**Création des Fonts**/
            //fichier du font algerian pour les boutons | boutton commencer |harmony et middle kingdom | CBO nbre de joueurs - ALGERIAN
            Font algerian = Font.createFont(Font.TRUETYPE_FONT, new File("./kingdomino/font/algerian.ttf"));
            //fichier du font pour label menu du choix | Label Nbre de joueurs | les messages d'erreurs - Showcard Gothic
            Font bookmanold = Font.createFont(Font.TRUETYPE_FONT, new File("./kingdomino/font/bookman-old-style.ttf"));
            //fichier du font label mode de jeu - Bookman Old Style
            Font showcard = Font.createFont(Font.TRUETYPE_FONT, new File("./kingdomino/font/showcard-gothic.ttf"));

            //enregistrer les Fonts
            GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
            g.registerFont(algerian);
            g.registerFont(bookmanold);
            g.registerFont(showcard);
        }
        return instance;
    }
}
