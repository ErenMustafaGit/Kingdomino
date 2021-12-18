package View;

import Controller.BoardGame;
import Model.GameObserver;
import Model.GameMode;
import Model.PlayerStrategy;

import javax.swing.*;

public class MyWindow extends JFrame implements GameObserver
{
    BoardGame boardGame;
    public MyWindow() {
        setTitle( "Kingdomino" );
        setSize( 1280, 720 );
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //MainMenu mainMenu = new MainMenu(this);

        setContentPane( new MainMenu2(this));


        setVisible( true );
    }
    public MyWindow(BoardGame boardGame) {
        this.boardGame = boardGame;
        setTitle( "Kingdomino" );
        setSize( 1280, 720 );
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane( new MainMenu2(this));
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
        setContentPane( new MainMenu2(this) );
        this.setVisible(true);
    }


    @Override
    public void notify(GameMode strategy, PlayerStrategy player) {

    }
}
