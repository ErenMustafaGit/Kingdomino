package View;

import Utilities.IMGReader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndView extends JPanel {
    private MyWindow mainFrame;
    private ImageIcon img;


    public EndView(MyWindow MyWindow){
        this.mainFrame = MyWindow;
        this.img = IMGReader.getImage("main.jpg");
        setOpaque(false);


        Color mycolor = new Color(174,135,0);

        JButton playBtn = new JButton("JOUER");
        playBtn.setFont(new Font("Algerian", Font.BOLD, 30));
        playBtn.setBackground(mycolor);
        playBtn.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        playBtn.setPreferredSize(new Dimension(70,65));
        playBtn.setFocusPainted(false);
        this.setBorder(new EmptyBorder(10, 550, 300, 550));

        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setChoosingMenu();

            }
        });


        this.setLayout( new BorderLayout() );

        this.add(playBtn, BorderLayout.SOUTH);
    }

    public void paint(Graphics g){
        g.drawImage( this.img.getImage(), 0 , 0,mainFrame.getWidth(), mainFrame.getHeight(), null);
        super.paint(g);
    }


}


