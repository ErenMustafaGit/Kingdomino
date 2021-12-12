import Controller.BoardGame;
import Utilities.CSVReader;
import View.MyWindow;

public class Kingodomino {
    public static void main(String[] args) {
        MyWindow myWindow = new MyWindow();
        System.out.println(CSVReader.read("./a31-kingdomino/kingdomino/src/Ressources/kingdomino.csv"));
    }
}
