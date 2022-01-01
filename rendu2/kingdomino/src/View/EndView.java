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
        this.img = IMGReader.getImage("wallpaperDark.png");
        setOpaque(false);
        this.setLayout( new GridBagLayout() );
        GridBagConstraints c = new GridBagConstraints();
        // Spécifie le margin de tous les composants
        c.insets = new Insets(5, 1, 5, 1);


        GridBagConstraints playerC = new GridBagConstraints();
        int i = 0;
        for (Player player : this.mainFrame.getGame().getPlayers()){
            JPanel playerPnl = new JPanel(){
                Image img = IMGReader.getImage("badgePoint/badgePointGREEN.png").getImage();
                // initialiseur d'instance
                {setOpaque(false);}
                public void paintComponent(Graphics graphics)
                {
                    graphics.drawImage(img, 0, 0, this);
                    super.paintComponent(graphics);
                }
            };
            playerPnl.setPreferredSize( new Dimension(750, 200) );
            playerPnl.setLayout( new GridBagLayout() );

            //TODO : METTRE IMAGE DE FOND par rapport à la couleur du player + PREFERRED SIZE
            //TODO : METTRE LES vraies points obtenu par player
            // TODO : Bonus : Indiquer si gain de point grace à harmony/middle

            playerC.insets = new Insets(40, -200, 5, 65);
            MyLabel positionLbl = new MyLabel("2°");
            positionLbl.setFont( FontReader.getInstance().getShowcard().deriveFont(Font.BOLD ).deriveFont(100f));
            positionLbl.setForeground(Color.white);
            positionLbl.setOutlineColor(Color.DARK_GRAY);
            positionLbl.setStroke(new BasicStroke(6f));
            playerC.gridx = 0;
            playerC.gridy = 0;
            playerPnl.add( positionLbl,playerC );

            playerC.insets = new Insets(40, 5, 5, 10);
            MyLabel pointNbLbl = new MyLabel("22");
            pointNbLbl.setFont( FontReader.getInstance().getShowcard().deriveFont(Font.BOLD ).deriveFont(80f));
            pointNbLbl.setForeground(Color.white);
            pointNbLbl.setOutlineColor(Color.DARK_GRAY);
            pointNbLbl.setStroke(new BasicStroke(6f));
            playerC.gridx = 1;
            playerC.gridy = 0;
            playerPnl.add( pointNbLbl,playerC );

            playerC.insets = new Insets(60, 5, 5, 10);
            MyLabel pointLbl = new MyLabel("Points");
            pointLbl.setFont( FontReader.getInstance().getShowcard().deriveFont(Font.BOLD ).deriveFont(36f));
            pointLbl.setForeground(Color.white);
            pointLbl.setOutlineColor(Color.DARK_GRAY);
            pointLbl.setStroke(new BasicStroke(4f));
            playerC.gridx = 2;
            playerC.gridy = 0;
            playerPnl.add( pointLbl,playerC );

            if(player.getBoard().isHarmony()){
                playerC.insets = new Insets(-80, 0, 5, -250);
                MyLabel harmonyLbl = new MyLabel("+5 Harmony");
                harmonyLbl.setFont( FontReader.getInstance().getShowcard().deriveFont(16f));
                harmonyLbl.setForeground(Color.white);
                harmonyLbl.setOutlineColor(Color.DARK_GRAY);
                harmonyLbl.setStroke(new BasicStroke(2f));
                playerC.gridx = 3;
                playerC.gridy = 0;
                playerPnl.add( harmonyLbl,playerC );
            }

            if(player.getBoard().isKingdomMiddle()){
                playerC.insets = new Insets(100, 0, 5, -200);
                MyLabel midKingLbl = new MyLabel("+10 Middle Kingdom");
                midKingLbl.setFont( FontReader.getInstance().getShowcard().deriveFont(16f));
                midKingLbl.setForeground(Color.white);
                midKingLbl.setOutlineColor(Color.DARK_GRAY);
                midKingLbl.setStroke(new BasicStroke(2f));
                playerC.gridx = 4;
                playerC.gridy = 0;
                playerPnl.add( midKingLbl,playerC );
            }


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


