package View;

import Model.Player;
import Utilities.FontReader;
import Utilities.IMGReader;
import View.Components.MyLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndView extends JPanel {
    private MyWindow mainFrame;
    private ImageIcon img;

    //Couleur des boutons (orangé)
    private final Color btnColor = new Color(174,135,0);
    //Couleur des boutons en hover (orangé plus clair)
    private final Color btnHoverColor = new Color(198, 159, 22);

    public EndView(MyWindow MyWindow){
        this.mainFrame = MyWindow;
        this.img = IMGReader.getImage("main.jpg");
        setOpaque(false);
        this.setLayout( new GridBagLayout() );
        GridBagConstraints c = new GridBagConstraints();
        // Spécifie le margin de tous les composants
        c.insets = new Insets(5, 1, 5, 1);


        GridBagConstraints playerC = new GridBagConstraints();
        playerC.insets = new Insets(5, 5, 5, 5);
        int i = 0;
        for(int j = 0; j<4; j++){
        //for (Player player : this.mainFrame.getGame().getPlayers()){
            JPanel playerPnl = new JPanel();
            playerPnl.setLayout( new GridBagLayout() );

            playerPnl.setBackground( Color.green );
            //TODO : METTRE IMAGE DE FOND par rapport à la couleur du player + PREFERRED SIZE
            //TODO : METTRE LES vraies points obtenu par player
            // TODO : Bonus : Indiquer si gain de point grace à harmony/middle

            MyLabel pointNbLbl = new MyLabel("22");
            pointNbLbl.setFont( FontReader.getInstance().getShowcard().deriveFont(Font.BOLD ).deriveFont(60f));
            pointNbLbl.setForeground(Color.white);
            playerC.gridx = 0;
            playerC.gridy = 0;
            playerPnl.add( pointNbLbl,playerC );

            MyLabel pointLbl = new MyLabel("Points");
            pointLbl.setFont( FontReader.getInstance().getShowcard().deriveFont(Font.BOLD ).deriveFont(36f));
            pointLbl.setForeground(Color.white);
            playerC.gridx = 1;
            playerC.gridy = 0;
            playerPnl.add( pointLbl,playerC );


            c.gridx = 0;
            c.gridy = i;
            this.add( playerPnl, c );
            i++;
        }




        JButton backBtn = new JButton("RETOUR AU MENU PRINCIPAL");
        backBtn.setFont(FontReader.getInstance().getAlgerian().deriveFont(Font.BOLD).deriveFont(CENTER_ALIGNMENT).deriveFont(30f));
        backBtn.setBackground(btnColor);
        backBtn.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        backBtn.setPreferredSize(new Dimension(500,65));
        backBtn.setFocusPainted(false);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMainMenu();

            }
        });

        //HOVER BUTTON
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backBtn.setBackground(btnHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backBtn.setBackground(btnColor);
                //btnReverse.setBackground(UIManager.getColor("control"));
            }
        });

        c.gridx = 0;
        c.gridy = 10;
        this.add(backBtn, c);
    }

    public void paint(Graphics g){
        g.drawImage( this.img.getImage(), 0 , 0,mainFrame.getWidth(), mainFrame.getHeight(), null);
        super.paint(g);
    }


}


