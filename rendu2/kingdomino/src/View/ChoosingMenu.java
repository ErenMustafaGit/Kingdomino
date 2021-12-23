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
    private ButtonGroup group;
    private String[] nbreofplayers = {"2","3","4"};
    JComboBox NbredeJoueurs = new JComboBox(nbreofplayers);

    private ImageIcon img;

    public ChoosingMenu(MyWindow MyWindow) {
        this.mainFrame = MyWindow;
        this.img = IMGReader.getImage("wallpaper.png");
        this.setOpaque(false);

                /***utilisation d'un grid Layout***/
        this.setLayout(new GridLayout(8,3,10,10));

                /***padding entre composant de la fenetre***/
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

                /***Affichage du titre de la page***/
        JLabel label = new JLabel("Mode de jeu");
        label.setFont(new Font("Showcard Gothic",Font.BOLD, 40));

                /**Centrer le label du haut**/
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVisible(true);
        this.add(label);

                    /**Affichage du mode de jeu + des checkbox**/
        JLabel mode = new JLabel("Mode de jeu :");
        mode.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
        mode.setHorizontalTextPosition(SwingConstants.HORIZONTAL);
        this.add(mode);
        JCheckBox harmony = new JCheckBox("Harmony");
        harmony.setOpaque(false);
                    /***Changer la couleur de checkbox1***/
        JCheckBox middle_kingdom = new JCheckBox("Middle Kingdom");
        middle_kingdom.setOpaque(false);
                    /***Changer la couleur de checkbox2***/
        this.add(harmony);
        this.add(middle_kingdom);

                    /**Les combobox**/
        cboplayers();
                    /**Affichage des cbo qui permet de choisir la couleur des rois des joueurs**/
        KingColor[] colors = KingColor.values();
        JComboBox cboColor = new JComboBox(colors);
        /**SELECTIONNE L'ITEM QUI EST DE DEUX JOUEURS**/

        if(cboColor.getSelectedItem().toString()=="2"){
            for( int i = 0; i<3; i++){
                this.add(cboColor);
            }
        }
        if(cboColor.getSelectedItem().toString()=="3"){
            for( int i = 0; i<4; i++){
                this.add(cboColor);
            }
        }
        if(cboColor.getSelectedItem().toString()=="4"){
            for( int i = 0; i<5; i++){
                this.add(cboColor);
            }
        }


        JButton button2 = new JButton("Play");
        button2.setPreferredSize(new Dimension(10,10));
        button2.addActionListener(new ActionListener() {
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
                mainFrame.getGameController().initializeGame( Integer.parseInt(NbredeJoueurs.getSelectedItem().toString()), gameMode );
                mainFrame.setGamePanel();
            }
        });
        button2.setVisible(true);
        this.add(button2);
    }

                    /** Rediriger vers la partie **/
    public JPanel getMainPanel(){
            return this;
    }

                    /** Ajout des combobox**/
    public JComboBox cboplayers(){
        //ajout du label de combobox
        JLabel joueurs = new JLabel("Nombre de joueurs:");
        joueurs.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
        this.add(joueurs);
        NbredeJoueurs.setPreferredSize(new Dimension(0,1));
        this.add(NbredeJoueurs);
        NbredeJoueurs.setVisible(true);
        return NbredeJoueurs;
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
