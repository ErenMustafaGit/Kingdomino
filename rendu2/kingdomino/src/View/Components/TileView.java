package View.Components;

import Controller.GameController;
import Model.GameContext;
import Model.King;
import Model.Tile;
import Utilities.IMGReader;
import View.GameView;

import javax.swing.*;
import java.awt.*;

public class TileView extends JPanel {

    /** Fonction qui renvoie un JPanel contenant l'affichage du playerboard passé en parametre
     *
     * @param gameController : Le controlleur du jeu
     * @param tile : La tuile à afficher
     * @param king : le roi à qui la tuile appartient (null si à aucun)
     * @param rotateMode : si la tuile est en mode rotation ou non
     */
    public TileView(GameController gameController, Tile tile, King king, boolean rotateMode){

        this.setLayout( new BorderLayout() );
        this.setPreferredSize( new Dimension(IMGReader.getTileImgSize()*2, IMGReader.getTileImgSize()));
        if(rotateMode){
            this.setPreferredSize( new Dimension(IMGReader.getTileImgSize()*2, IMGReader.getTileImgSize()*2));
            this.setOpaque(false);
        }

        JButton left = new JButton();
        JButton right = new JButton();
        left.setPreferredSize(new Dimension(IMGReader.getTileImgSize(), IMGReader.getTileImgSize()));
        right.setPreferredSize(new Dimension(IMGReader.getTileImgSize(), IMGReader.getTileImgSize()));

        //Met les bouttons en transparent (que les images des tuiles seront visible)
        left.setOpaque(false);
        left.setContentAreaFilled(false);
        left.setBorderPainted(false);
        right.setOpaque(false);
        right.setContentAreaFilled(false);
        right.setBorderPainted(false);

        left.addActionListener(actionEvent -> {
            if(king == null){
                gameController.chooseTile(tile);
            }
        });

        right.addActionListener(actionEvent -> {
            if( king == null){
                gameController.chooseTile(tile);
            }
        });



        //Met l'image correspondant à la couleur de la case
        left.setIcon( IMGReader.getImage(tile.getLeft().getColor(), tile.getLeft().getCrownNumber()) );
        right.setIcon( IMGReader.getImage(tile.getRight().getColor(), tile.getRight().getCrownNumber())  );


        this.add(left, BorderLayout.LINE_START);
        this.add(right, BorderLayout.LINE_END);

        if(rotateMode){
            switch(tile.getDirection()){
                case NORTH:
                    this.add(left, BorderLayout.SOUTH);
                    this.add(right, BorderLayout.NORTH);
                    break;

                case SOUTH:
                    this.add(left, BorderLayout.NORTH);
                    this.add(right, BorderLayout.SOUTH);
                    break;

                case WEST:
                    this.add(left, BorderLayout.LINE_END);
                    this.add(right, BorderLayout.LINE_START);
                    break;
                default:
                    this.add(left, BorderLayout.LINE_START);
                    this.add(right, BorderLayout.LINE_END);
                    break;
            }
        }


        //Si la tuile appartient à un roi et que la tuile n'est pas en mode rotation (prêt à etre placé)
        if(king != null && !rotateMode){
            this.setBorder( BorderFactory.createLineBorder( GameView.getColor(king.getColor()), 5 ) );
        }
    }





}
