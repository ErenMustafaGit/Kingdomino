package View;

import Model.GameObserver;

import javax.swing.*;
import java.awt.*;

public class MyWindow extends JFrame implements GameObserver
{
    public MyWindow() {
        setTitle( "Kingdomino" );
        setSize( 900, 500 );
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        MainMenu mainMenu = new MainMenu(this);
        setContentPane( mainMenu.getMainPanel() );

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
        MainMenu mainMenu = new MainMenu(this);
        setContentPane( mainMenu.getMainPanel() );
        this.setVisible(true);
    }


}
