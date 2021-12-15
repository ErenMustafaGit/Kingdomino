import Controller.BoardGame;
import Model.Castle;
import Model.Deck;
import Model.PlayerBoard;
import Utilities.CSVReader;
import View.MainMenu2;
import View.MyWindow;

import java.util.Arrays;
import java.util.List;

public class Kingodomino {
    public static void main(String[] args) {
        MyWindow myWindow = new MyWindow();

        Deck myDeck = new Deck(32);

        Castle castle = new Castle();
        PlayerBoard playerBoard = new PlayerBoard(castle);
    }
}
