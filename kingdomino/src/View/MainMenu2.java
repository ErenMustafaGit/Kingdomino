package View;

import Utilities.IMGReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu2 extends JPanel {
    private MyWindow mainFrame;
    private Image img;


    public MainMenu2(MyWindow MyWindow){
        this.mainFrame = MyWindow;
        this.img = IMGReader.getImage("main.jpg");
        setOpaque(false);


        JLabel header = new JLabel("Kingdomino Menu");
        header.setSize(MyWindow.getWidth(),700);
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setVerticalAlignment(JLabel.CENTER);


        JButton button2 = new JButton("Play");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setChoosingMenu();

            }
        });


        this.setLayout( new BorderLayout() );
        this.add( header, BorderLayout.NORTH );

        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout( new GridLayout(10, 2) );
        center.add(button2);

        this.add( center, BorderLayout.CENTER );

    }

    public void paint(Graphics g){
        g.drawImage(this.img, 0 , 0,mainFrame.getWidth(), mainFrame.getHeight(), null);
        super.paint(g);
    }


}


