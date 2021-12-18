package View;

import Controller.GameController;
import Model.GameObserver;
import Model.GameMode;
import Model.PlayerStrategy;

import javax.swing.*;

public class MyWindow extends JFrame implements GameObserver
{
    GameController gameController;

    public MyWindow(GameController gameController) {
        this.gameController = gameController;
        setTitle( "Kingdomino" );
        setSize( 1280, 720 );
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane( new MainMenu(this));
        setVisible( true );
}


    public void setGamePanel(){
        GameView gameView = new GameView(this);
        this.setContentPane(gameView.getMainPanel());
        this.setVisible(true);
    }

    public void setChoosingMenu(){
        ChoosingMenu choosingMenu = new ChoosingMenu(this);
        this.setContentPane(choosingMenu.getMainPanel());
        this.setVisible(true);
    }

    public void setMainMenu(){
        //MainMenu2 mainMenu = new MainMenu2(this);
        setContentPane( new MainMenu(this) );
        this.setVisible(true);
    }


    @Override
    public void notify(GameMode strategy, PlayerStrategy player) {

    }
}
