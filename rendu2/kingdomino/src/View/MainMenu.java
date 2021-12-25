package View;

import Utilities.IMGReader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private MyWindow mainFrame;
    private ImageIcon img;


    public MainMenu(MyWindow MyWindow){
        this.mainFrame = MyWindow;
        this.img = IMGReader.getImage("main.jpg");
        setOpaque(false);


        Color mycolor = new Color(174,135,0);

        JButton playBtn = new JButton("JOUER");
        playBtn.setFont(new Font("Algerian", Font.BOLD, 30));
        playBtn.setBackground(mycolor);
        playBtn.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        playBtn.setPreferredSize(new Dimension(60,60));
        this.setBorder(new EmptyBorder(10, 550, 200, 550));

        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setChoosingMenu();

            }
        });


        this.setLayout( new BorderLayout() );

        this.add(playBtn, BorderLayout.SOUTH);

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


