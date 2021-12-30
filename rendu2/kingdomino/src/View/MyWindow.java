package View;

import Controller.GameController;
import Model.GameContext;
import Model.GameObserver;
import Model.GameMode;
import Model.PlayerStrategy;

import javax.swing.*;

public class MyWindow extends JFrame implements GameObserver
{
    GameController gameController;
    GameContext game;
    GameView gameView;

    public MyWindow(GameController gameController, GameContext game) {
        this.game = game;
        this.gameController = gameController;
        setTitle( "Kingdomino" );
        setSize( 1700, 980 );
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane( new MainMenu(this));
        setVisible( true );
}

    public GameContext getGame(){
        return this.game;
    }

    public GameController getGameController(){
        return this.gameController;
    }
    public void setGamePanel(){
        this.gameView = new GameView(this);
        this.setContentPane( gameView );
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
    public void update(GameContext game) {
        gameView.update(game);
    }

    @Override
    public void updateEnd(GameContext gameContext) {
        setContentPane( new EndView(this) );
        this.setVisible(true);
    }
}
