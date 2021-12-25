package View;

import Model.KingColor;
import Utilities.IMGReader;
import View.Components.MyLabel;

import javax.swing.*;
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
        MyLabel label = new MyLabel("Menu de choix");
        label.setFont(new Font("Showcard Gothic",Font.BOLD, 60));
        label.setOutlineColor(Color.DARK_GRAY);
        label.setStroke(new BasicStroke(5f));
        label.setForeground(Color.WHITE);
        label.setVisible(true);
        c.gridx = 0;
        c.gridy = 0;
        mainPnl.add( label, c );

                    /**Affichage du mode de jeu + des checkbox**/
        MyLabel mode = new MyLabel("Mode de jeu :");
        mode.setForeground(Color.WHITE);
        mode.setFont(new Font("Bookman Old Style", Font.BOLD, 30));
        mode.setOutlineColor(Color.DARK_GRAY);
        mode.setStroke(new BasicStroke(3f));
        c.gridx = 0;
        c.gridy = 1;
        mainPnl.add(mode, c);


        JCheckBox harmonyChk = new JCheckBox("Harmony");
        harmonyChk.setOpaque(false);
        harmonyChk.setForeground(Color.WHITE);
        harmonyChk.setFont(new Font("Algerian", Font.BOLD, 20));

        JCheckBox middleKingdomChk = new JCheckBox("Middle Kingdom");
        middleKingdomChk.setOpaque(false);
        middleKingdomChk.setForeground(Color.WHITE);
        middleKingdomChk.setFont(new Font("Algerian", Font.BOLD, 20));

        c.gridx = 1;
        c.gridy = 1;
        mainPnl.add(harmonyChk, c);
        c.gridx = 2;
        c.gridy = 1;
        mainPnl.add(middleKingdomChk, c);

                    /**Les combobox**/
        //ajout du label de combobox
        MyLabel nbPlayerLbl = new MyLabel("Nombre de joueurs :");
        nbPlayerLbl.setOutlineColor(Color.DARK_GRAY);
        nbPlayerLbl.setStroke(new BasicStroke(3f));
        nbPlayerLbl.setForeground(Color.WHITE);
        nbPlayerLbl.setFont(new Font("Bookman Old Style", Font.BOLD, 30));
        c.gridx = 0;
        c.gridy = 2;
        mainPnl.add(nbPlayerLbl, c);
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



        Color mycolor = new Color(174,135,0);

        JButton playButton = new JButton("COMMENCER");
        playButton.setBackground(mycolor);
        playButton.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        playButton.setPreferredSize(new Dimension(250,60));
        playButton.setFont(new Font("Algerian", Font.BOLD, 25));
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int gameMode = 0;
                if(harmonyChk.isSelected() && middleKingdomChk.isSelected() ){
                    gameMode = 3;
                }else if( harmonyChk.isSelected() ){
                    gameMode = 1;
                }else if (middleKingdomChk.isSelected()){
                    gameMode = 2;
                }
                //Donne les infos de mode de jeu, de nombre de joueur et de couleurs des joueurs
                mainFrame.getGameController().initializeGame( Integer.parseInt(nbPlayerCbo.getSelectedItem().toString()), gameMode );
                //Passe à la vue GameView
                mainFrame.setGamePanel();
            }
        });
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 4;
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
