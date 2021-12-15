package View;

import Model.KingColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoosingMenu {
    private MyWindow mainFrame;
    private JPanel panel = new JPanel();

    public ChoosingMenu(MyWindow MyWindow){
        this.mainFrame = MyWindow;


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

}
