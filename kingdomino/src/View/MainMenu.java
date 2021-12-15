package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class MainMenu {
    private MyWindow mainFrame;
    private JPanel mainPanel;
    private JButton playButton;

    public MainMenu(MyWindow MyWindow){
        this.mainFrame = MyWindow;


        JLabel header = new JLabel("Kingdomino");


        JButton button2 = new JButton("Play");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        JButton button3 = new JButton("Button 3...");
        JButton button4 = new JButton("Long-Named...");
        JButton button5 = new JButton("5 (LINE_END)");

        mainPanel.setLayout( new BorderLayout() );
        mainPanel.add( header, BorderLayout.NORTH );
        mainPanel.add( button2, BorderLayout.CENTER );

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setChoosingMenu();
            }
        });

    }
    public JPanel getMainPanel(){
        return this.mainPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
