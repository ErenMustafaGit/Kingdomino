package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu2 {
    private MyWindow mainFrame;
    private JPanel mainPanel = new JPanel();

    public MainMenu2(MyWindow MyWindow){
        this.mainFrame = MyWindow;


        JLabel header = new JLabel("Kingdomino");


        JButton button2 = new JButton("Play");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setChoosingMenu();

            }
        });


        mainPanel.setLayout( new BorderLayout() );
        mainPanel.add( header, BorderLayout.NORTH );
        mainPanel.add( button2, BorderLayout.CENTER );
    }
    public JPanel getMainPanel(){
        return this.mainPanel;
    }

}
