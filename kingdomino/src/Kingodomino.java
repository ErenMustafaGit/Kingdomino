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
        BoardGame boardgame = new BoardGame();
        MyWindow myWindow = new MyWindow(boardgame);

        Deck myDeck = new Deck(32);

        Castle castle = new Castle();
    }
}
