package View;

import Model.KingColor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoosingMenu {
    final static boolean shouldFill = true;
    private MyWindow mainFrame;
    private JPanel panel = new JPanel();
    private ButtonGroup group;
    private String[] nbreofplayers = {"2","3","4"};


    public ChoosingMenu(MyWindow MyWindow) {
        //utilisation d'un grid Layout
        panel.setLayout(new GridLayout(10,3,10,10));

        //padding entre composant de la fenetre
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        //Affichage du titre de la page
        JLabel label = new JLabel("Mode de jeu");
        label.setFont(new Font("Showcard Gothic",0, 40));

        //centrer le label en haut de page
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVisible(true);
        panel.add(label);

        //affichage du mode de jeu + des checkbox
        level();

        //affichage du nombre de joueur
        cboplayers();
        //affichage des cbo qui permet de choisir la couleur des rois des joueurs
        getColorCbo();
    }

    //Quand on clique sur le bouton play on est redirigé vers cette fenetre
    public JPanel getMainPanel(){
        return this.panel;
    }

    //fonction qui permet d'ajouter des radiobuttons des niveaux
    public CheckboxGroup level(){
        JLabel mode = new JLabel("Mode de jeu :");
        mode.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
        mode.setHorizontalTextPosition(SwingConstants.HORIZONTAL);
        mode.setVisible(true);
        panel.add(mode);
        //en vrai il sert à rien le group
        CheckboxGroup group = new CheckboxGroup();
        Checkbox Harmony = new Checkbox("Harmony");
        Checkbox middle_kingdom = new Checkbox("Middle Kingdom");

        panel.add(Harmony);
        panel.add(middle_kingdom);
        Harmony.setVisible(true);
        middle_kingdom.setVisible(true);
        return group;
    }

    public JComboBox cboplayers(){
        //taille
        JLabel joueurs = new JLabel("Nombre de joueurs:");
        joueurs.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
        joueurs.setVisible(true);
        panel.add(joueurs);
        JComboBox NbredeJoueurs = new JComboBox(nbreofplayers);
        panel.add(NbredeJoueurs);
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
        panel.add(cboColor);
        cboColor.setVisible(true);
        return cboColor;
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
