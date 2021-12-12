import Controller.BoardGame;
import Model.Deck;
import Utilities.CSVReader;
import View.MyWindow;

import java.util.List;

public class Kingodomino {
    public static void main(String[] args) {
        //MyWindow myWindow = new MyWindow();

        Deck myDeck = new Deck(32);
        for (int i = 0; i<32; i++){
            System.out.println( myDeck.getTile().getLeft().getColor() );
        }
    }
}
