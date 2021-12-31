package View;

import Utilities.FontReader;
import Utilities.IMGReader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private MyWindow mainFrame;
    private ImageIcon img;

    //Couleur des boutons (orangé)
    private final Color btnColor = new Color(174,135,0);
    //Couleur des boutons en hover (orangé plus clair)
    private final Color btnHoverColor = new Color(198, 159, 22);

    public MainMenu(MyWindow MyWindow){
        this.mainFrame = MyWindow;
        this.img = IMGReader.getImage("main.jpg");
        setOpaque(false);



        JButton playBtn = new JButton("JOUER");
        //playBtn.setFont(new Font("Algerian", Font.BOLD, 30));
        playBtn.setFont(FontReader.getInstance().getAlgerian().deriveFont(Font.BOLD).deriveFont(40f));
        playBtn.setBackground(btnColor);
        playBtn.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        playBtn.setPreferredSize(new Dimension(70,70));
        playBtn.setFocusPainted(false);
        //HOVER BUTTON
        playBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playBtn.setBackground(btnHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                playBtn.setBackground(btnColor);
                //btnReverse.setBackground(UIManager.getColor("control"));
            }
        });
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


