package View;

import Model.Player;
import Utilities.FontReader;
import Utilities.IMGReader;
import View.Components.MyLabel;
import View.Components.PlayerBoardView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

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



        //Ajout des plateaux de chaques joueurs -- Gauche
        JPanel leftPnl = new JPanel();
        leftPnl.setOpaque(false);
        leftPnl.setLayout( new GridBagLayout() );
        GridBagConstraints boardC = new GridBagConstraints();
        boardC.insets = new Insets(10, 10 , 10 , 10);
        boardC.gridy = 0;
        boardC.gridx = 0;
        leftPnl.add( new PlayerBoardView( this.mainFrame.getGame().getPlayer(0), this.mainFrame.getGame()), boardC);

        if(this.mainFrame.getGame().getPlayersNb() >= 3){
            boardC.insets = new Insets(50, 10 , 10 , 10);
            boardC.gridy = 1;
            boardC.gridx = 0;
            leftPnl.add( new PlayerBoardView( this.mainFrame.getGame().getPlayer(2), this.mainFrame.getGame()), boardC);
        }

        //Ajout des plateaux de chaques joueurs -- Droit
        JPanel rightPnl = new JPanel();
        rightPnl.setOpaque(false);
        rightPnl.setLayout( new GridBagLayout() );
        boardC.insets = new Insets(10, 10 , 10 , 10);
        boardC.gridy = 0;
        boardC.gridx = 0;
        rightPnl.add( new PlayerBoardView( this.mainFrame.getGame().getPlayer(1), this.mainFrame.getGame()), boardC);
        if(this.mainFrame.getGame().getPlayersNb() >= 4){
            boardC.insets = new Insets(50, 10 , 10 , 10);
            boardC.gridy = 1;
            boardC.gridx = 0;
            rightPnl.add( new PlayerBoardView( this.mainFrame.getGame().getPlayer(3), this.mainFrame.getGame()), boardC);
        }

        //Panel du milieu (contient le classement et le bouton pour quitter)
        JPanel midPnl = new JPanel();
        midPnl.setOpaque(false);
        midPnl.setLayout(new GridBagLayout());
        GridBagConstraints playerC = new GridBagConstraints();
        int i = 0;
        for (Map.Entry<Player, Integer> ranking : this.mainFrame.getGame().getPlayersRank().entrySet()) {
            JPanel playerPnl = new JPanel(){
                Image img = IMGReader.getImage("badgePoint/badgePoint"+ ranking.getKey().getPlayerColor()+".png").getImage();
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

            playerC.insets = new Insets(40, -200, 5, 65);
            MyLabel positionLbl = new MyLabel((i+1) + "°");
            positionLbl.setFont( FontReader.getInstance().getShowcard().deriveFont(Font.BOLD ).deriveFont(100f));
            positionLbl.setForeground(Color.white);
            positionLbl.setOutlineColor(Color.DARK_GRAY);
            positionLbl.setStroke(new BasicStroke(6f));
            playerC.gridx = 0;
            playerC.gridy = 0;
            playerPnl.add( positionLbl,playerC );

            playerC.insets = new Insets(40, 5, 5, 10);
            int point = ranking.getValue();
            MyLabel pointNbLbl = new MyLabel(Integer.toString(point) );
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
            System.out.println(this.mainFrame.getGame().getGameMode().hasHarmony()  + " harmony");
            if( this.mainFrame.getGame().getGameMode().hasHarmony() ){

                playerC.insets = new Insets(-80, 0, 5, -250);
                MyLabel harmonyLbl = new MyLabel();
                if(this.mainFrame.getGame().getGameMode().isHarmony( ranking.getKey().getBoard() )){
                    harmonyLbl.setText("+5 Harmony");
                }else{
                    harmonyLbl.setText("-5 Harmony");
                }
                harmonyLbl.setFont( FontReader.getInstance().getShowcard().deriveFont(16f));
                harmonyLbl.setForeground(Color.white);
                harmonyLbl.setOutlineColor(Color.DARK_GRAY);
                harmonyLbl.setStroke(new BasicStroke(2f));
                playerC.gridx = 3;
                playerC.gridy = 0;
                playerPnl.add( harmonyLbl,playerC );
            }

            if( this.mainFrame.getGame().getGameMode().isKingdomMiddle( ranking.getKey().getBoard() ) ){
                System.out.println("Middled");

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
            midPnl.add( playerPnl, c );


            //Ajout de chaque panel secondaire dans le panel principal
            this.add(midPnl, c);
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
                mainFrame.getGameController().destroyGame();
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
        midPnl.add(backBtn, c);


        GridBagConstraints mainC = new GridBagConstraints();
        mainC.gridx = 0;
        mainC.gridy = 0;
        mainC.insets = new Insets(5,-20,5,20);
        this.add( leftPnl, mainC );


        mainC.gridx = 1;
        mainC.gridy = 0;
        mainC.insets = new Insets(5,50,5,50);
        this.add( midPnl, mainC);

        mainC.gridx = 2;
        mainC.gridy = 0;
        mainC.insets = new Insets(5,20,5,-20);
        this.add( rightPnl, mainC );
    }

    public void paint(Graphics g){
        g.drawImage( this.img.getImage(), 0 , 0,mainFrame.getWidth(), mainFrame.getHeight(), null);
        super.paint(g);
    }


}


