package View;

import Model.KingColor;
import Utilities.IMGReader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoosingMenu extends JPanel {
    final static boolean shouldFill = true;
    private MyWindow mainFrame;
    private JPanel mainPnl = new JPanel();
    private ButtonGroup group;
    private String[] nbPlayerChoice = {"2","3","4"};
    JComboBox nbPlayerCbo = new JComboBox(nbPlayerChoice);

    /** Panel qui va contenir tout les cbo de couleurs (2 à 4) **/
    JPanel playerColorsPnl = new JPanel();

    private ImageIcon img;

    public ChoosingMenu(MyWindow MyWindow) {
        this.mainFrame = MyWindow;
        this.img = IMGReader.getImage("wallpaper.png");
        this.setOpaque(false);
        this.setLayout( new BorderLayout() );

        mainPnl.setLayout( new GridBagLayout() );
        mainPnl.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();
        this.add( mainPnl, BorderLayout.CENTER );
        int margin = 20;
        c.insets = new Insets(margin, margin, margin, margin);




                /***Affichage du titre de la page***/
        JLabel label = new JLabel("Menu de choix");
        label.setFont(new Font("Showcard Gothic",Font.BOLD, 60));

                /**Centrer le label du haut**/
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setVisible(true);
        c.gridx = 0;
        c.gridy = 0;
        mainPnl.add( label, c );

                    /**Affichage du mode de jeu + des checkbox**/
        JLabel mode = new JLabel("Mode de jeu :");
        mode.setForeground(Color.WHITE);
        mode.setFont(new Font("Bookman Old Style", Font.BOLD, 30));
        mode.setHorizontalTextPosition(SwingConstants.HORIZONTAL);
        c.gridx = 0;
        c.gridy = 1;
        mainPnl.add(mode, c);


        JCheckBox harmony = new JCheckBox("Harmony");
        harmony.setOpaque(false);
        harmony.setForeground(Color.WHITE);
        JCheckBox middle_kingdom = new JCheckBox("Middle Kingdom");
        middle_kingdom.setOpaque(false);
        middle_kingdom.setForeground(Color.WHITE);

        c.gridx = 1;
        c.gridy = 1;
        mainPnl.add(harmony, c);
        c.gridx = 2;
        c.gridy = 1;
        mainPnl.add(middle_kingdom, c);

                    /**Les combobox**/
        //ajout du label de combobox
        JLabel joueurs = new JLabel("Nombre de joueurs:");
        joueurs.setForeground(Color.WHITE);
        joueurs.setFont(new Font("Bookman Old Style", Font.BOLD, 30));
        c.gridx = 0;
        c.gridy = 2;
        mainPnl.add(joueurs, c);
        nbPlayerCbo.setPreferredSize(new Dimension(100,50));
        c.gridx = 1;
        c.gridy = 2;
        mainPnl.add(nbPlayerCbo, c);

        /**Affichage des cbo qui permet de choisir la couleur des rois des joueurs**/
        nbPlayerCbo.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                KingColor[] colors = KingColor.values();
                GridBagConstraints colorsC = new GridBagConstraints();
                int boardMargin = 20;
                colorsC.insets = new Insets(boardMargin, boardMargin, boardMargin, boardMargin);
                int playerNb = Integer.parseInt( nbPlayerCbo.getSelectedItem().toString() );
                playerColorsPnl.removeAll();
                playerColorsPnl.setBackground( Color.ORANGE );

                for (int i = 0; i< playerNb; i++){
                    colorsC.gridx = i;
                    colorsC.gridy = 0;
                    JComboBox cboColor = new JComboBox(colors);
                    playerColorsPnl.add( cboColor, colorsC );
                }
                c.gridwidth = 4;
                c.gridx = 0;
                c.gridy = 3;
                mainPnl.add( playerColorsPnl, c );
                mainPnl.revalidate();
                mainPnl.repaint();
            }
        });

        KingColor[] colors = KingColor.values();

        /** Panel de choix des couleurs **/
        playerColorsPnl.setLayout( new GridBagLayout() );
        playerColorsPnl.setBackground( Color.ORANGE );
        // Crée un objet de contraintes
        GridBagConstraints colorsC = new GridBagConstraints();
        int boardMargin = 20;
        colorsC.insets = new Insets(boardMargin, boardMargin, boardMargin, boardMargin);
        int playerNb = Integer.parseInt( nbPlayerCbo.getSelectedItem().toString() );

        for (int i = 0; i< playerNb; i++){
            colorsC.gridx = i;
            colorsC.gridy = 0;
            JComboBox cboColor = new JComboBox(colors);
            playerColorsPnl.add( cboColor, colorsC );
        }
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 3;
        mainPnl.add( playerColorsPnl, c );



        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 3;
        JButton playButton = new JButton("Play");
        playButton.setPreferredSize(new Dimension(10,10));
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int gameMode = 0;
                if(harmony.isSelected() && middle_kingdom.isSelected() ){
                    gameMode = 3;
                }else if( harmony.isSelected() ){
                    gameMode = 1;
                }else if (middle_kingdom.isSelected()){
                    gameMode = 2;
                }
                //Donne les infos de mode de jeu, de nombre de joueur et de couleurs des joueurs
                mainFrame.getGameController().initializeGame( Integer.parseInt(nbPlayerCbo.getSelectedItem().toString()), gameMode );
                //Passe à la vue GameView
                mainFrame.setGamePanel();
            }
        });
        mainPnl.add(playButton, c);
    }

                    /** Rediriger vers la partie **/
    public JPanel getMainPanel(){
            return this;
    }

                /***permet d'avoir les combobox couleurs***/
//    private JComboBox getColorCbo(){
//
////        if(cboColor.getSelectedItem().toString()=="2"){
////            this.add(cboColor);
////
////        }
////        if(cboColor.getSelectedItem().toString()=="3"){
////            this.add(cboColor);
////            this.add(cboColor);
////        }
////        if(cboColor.getSelectedItem().toString()=="4"){
////            this.add(cboColor);
////            this.add(cboColor);
////            this.add(cboColor);
////        }
//        return cboColor;
//    }
    public void paint(Graphics g){
        g.drawImage( this.img.getImage(), 0 , 0,mainFrame.getWidth(), mainFrame.getHeight(), null);
        super.paint(g);
    }


}
