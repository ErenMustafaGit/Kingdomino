package View;

import Utilities.IMGReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JPanel {
    private MyWindow mainFrame;
    private ImageIcon img;


    public MainMenu(MyWindow MyWindow){
        this.mainFrame = MyWindow;
        this.img = IMGReader.getImage("main.jpg");
        setOpaque(false);


        JLabel header = new JLabel("MENU");
        header.setFont(new Font("Algerian", Font.BOLD, 48));
        Color mycolor = new Color(174,135,0);
        header.setForeground(mycolor);


        header.setSize(MyWindow.getWidth(),700);
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setVerticalAlignment(JLabel.CENTER);


        JButton button2 = new JButton("PLAY");
        button2.setFont(new Font("Algerian", Font.BOLD, 30));
       // mycolor = new Color(255,228,54);
        button2.setBackground(mycolor);
       // button2.setForeground(Color.black);
        button2.setBorder(BorderFactory.createLineBorder(mycolor, 5));
        button2.setPreferredSize(new Dimension(60,60));
        this.setBorder(new EmptyBorder(10, 550, 200, 550));

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setChoosingMenu();

            }
        });


        this.setLayout( new BorderLayout() );
        this.add( header, BorderLayout.PAGE_START );

        this.add(button2, BorderLayout.SOUTH);

//        JPanel center = new JPanel();
//        center.setOpaque(false);
//        center.setLayout( new GridLayout(10, 2) );
//        center.add(button2, BorderLayout.PAGE_END);
//
//        this.add( center, BorderLayout.CENTER );

    }

    public void paint(Graphics g){
        g.drawImage( this.img.getImage(), 0 , 0,mainFrame.getWidth(), mainFrame.getHeight(), null);
        super.paint(g);
    }


}


