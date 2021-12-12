package View;

import Controller.BoardGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView  {
    private BoardGame boardGame;
    private MyWindow mainFrame;
    private JPanel panel = new JPanel();

    public GameView(MyWindow MyWindow){
        this.mainFrame = MyWindow;

        JLabel header = new JLabel("Kingdomino Game");



        JButton button3 = new JButton("Go back to the menu");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMainMenu();
            }
        });

        panel.setLayout( new BorderLayout() );
        panel.add( header, BorderLayout.NORTH );
        panel.add( button3, BorderLayout.WEST );
    }
    public JPanel getMainPanel(){
        return this.panel;
    }

}
