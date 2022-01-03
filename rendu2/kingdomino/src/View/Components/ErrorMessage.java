package View.Components;

import Utilities.FontReader;

import javax.swing.*;
import java.awt.*;

public class ErrorMessage extends MyLabel {

    JPanel view;

    public ErrorMessage(JPanel view){
        /** Message d'erreur **/
        //errorMessageLbl.setFont(new Font("Showcard Gothic",Font.BOLD, 21));
        this.setFont(FontReader.getInstance().getShowcard().deriveFont(Font.BOLD).deriveFont(21f));
        this.setOutlineColor(Color.DARK_GRAY);
        this.setStroke(new BasicStroke(2f));
        this.setForeground(new Color(252, 87, 87));
        this.view = view;
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        if(this.view != null){
            this.view.repaint();
            this.view.revalidate();

        }
    }
}
