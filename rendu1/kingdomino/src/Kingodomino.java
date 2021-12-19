import Controller.GameController;
import Model.Castle;
import Model.Deck;
import Model.GameContext;
import View.MyWindow;

public class Kingodomino {
    public static void main(String[] args) {
        GameContext gameContext = new GameContext();
        GameController gameController = new GameController(gameContext);
        MyWindow myWindow = new MyWindow(gameController, gameContext);

        gameContext.addObserver( myWindow );

        Deck myDeck = new Deck(32);

        Castle castle = new Castle();
    }
}
