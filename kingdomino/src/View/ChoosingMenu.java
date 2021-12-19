package View;

import Model.Harmony;
import Model.KingColor;
import Utilities.IMGReader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChoosingMenu extends JPanel {
    final static boolean shouldFill = true;
    private MyWindow mainFrame;
    private ButtonGroup group;
    private String[] nbreofplayers = {"2","3","4"};
    private ImageIcon img;

    Checkbox harmony = new Checkbox("Harmony");
    Checkbox middle_kingdom = new Checkbox("Middle Kingdom");


    public ChoosingMenu(MyWindow MyWindow) {
        this.mainFrame = MyWindow;
        this.img = IMGReader.getImage("wallpaper.png");
        this.setOpaque(false);

        //utilisation d'un grid Layout
        this.setLayout(new GridLayout(10,3,10,10));

        //padding entre composant de la fenetre
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        //Affichage du titre de la page
        JLabel label = new JLabel("Mode de jeu");
        label.setFont(new Font("Showcard Gothic",Font.BOLD, 40));

        //centrer le label en haut de page
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVisible(true);
        this.add(label);

        //affichage du mode de jeu + des checkbox
        level();

        //affichage du nombre de joueur
        cboplayers();
        //affichage des cbo qui permet de choisir la couleur des rois des joueurs
        getColorCbo();

        JButton button2 = new JButton("Play");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(harmony.getState() && middle_kingdom.getState()){

                }
                mainFrame.setGamePanel();
            }
        });
        button2.setVisible(true);
        this.add(button2);
    }

    //Quand on clique sur le bouton play on est redirigé vers cette fenetre
    public JPanel getMainPanel(){
        return this;
    }

    //fonction qui permet d'ajouter des radiobuttons des niveaux
    public CheckboxGroup level(){
        JLabel mode = new JLabel("Mode de jeu :");
        mode.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
        mode.setHorizontalTextPosition(SwingConstants.HORIZONTAL);
        mode.setVisible(true);
        this.add(mode);
        //en vrai il sert à rien le group
        CheckboxGroup group = new CheckboxGroup();


        this.add(harmony);
        this.add(middle_kingdom);
        harmony.setVisible(true);
        middle_kingdom.setVisible(true);
        return group;
    }

    public JComboBox cboplayers(){
        //taille
        JLabel joueurs = new JLabel("Nombre de joueurs:");
        joueurs.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
        joueurs.setVisible(true);
        this.add(joueurs);
        JComboBox NbredeJoueurs = new JComboBox(nbreofplayers);
        this.add(NbredeJoueurs);
        NbredeJoueurs.setVisible(true);
        return NbredeJoueurs;
    }
    //permet d'avoir les combobox couleurs
    private JComboBox getColorCbo(){
        //mettre un if ici
        KingColor[] colors = KingColor.values();
        JComboBox cboColor = new JComboBox(colors);
//        if(e.getSource()==cboplayers()){
//            for(int i =2; i<cboplayers().getSelectedItem(); i++){
//
//            }
//        }

        cboColor.setSelectedIndex(0);
        this.add(cboColor);
        cboColor.setVisible(true);
        return cboColor;
    }
    public void paint(Graphics g){
        g.drawImage( this.img.getImage(), 0 , 0,mainFrame.getWidth(), mainFrame.getHeight(), null);
        super.paint(g);
    }


}
 /*this.mainFrame = MyWindow;


    JLabel header = new JLabel("Choose your game parameter");

    JPanel cbos = new JPanel();
        cbos.setLayout(new GridLayout(1, 0));

        cbos.add(getColorCbo());
        cbos.add(getColorCbo());
        cbos.add(getColorCbo());



    JButton button2 = new JButton("Play");
        button2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.setGamePanel();
        }
    });

        panel.setLayout( new GridLayout(3,0) );
        panel.add( header );
        panel.add(cbos);
        panel.add( button2 );

}
    public JPanel getMainPanel(){
        return this.panel;
    }


    private JComboBox getColorCbo(){
        KingColor[] colors = KingColor.values();
        JComboBox cboColor = new JComboBox(colors);
        cboColor.setSelectedIndex(0);
        return cboColor;
    }
*/
