package View;

import Controller.BoardGame;
import Model.*;
import Utilities.IMGReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView  {
    private BoardGame boardGame;
    private MyWindow mainFrame;
    private JPanel panel = new JPanel();

    public GameView(MyWindow MyWindow){
        this.mainFrame = MyWindow;

        JLabel header = new JLabel("Kingdomino Game");
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setVerticalAlignment(JLabel.CENTER);


        //A SUPPR -> l'ajout d'un playerboard ne se fait pas ici!! (JUSTE POUR TESTER)
        Castle caste = new Castle();
        PlayerBoard playerBoard = new PlayerBoard(caste);
        Ground yellow = new Ground( GroundColor.YELLOW , 1);
        Ground blue = new Ground( GroundColor.BLUE , 2);
        Ground black = new Ground( GroundColor.BLACK, 0 );
        Ground light = new Ground( GroundColor.LIGHT_GREEN, 0 );
        Ground dark = new Ground( GroundColor.DARK_GREEN, 0 );
        Ground brown = new Ground( GroundColor.BROWN, 0 );

        Tile lb = new Tile(1,light, blue);
        Tile blbr = new Tile(2,blue, brown);
        Tile ll = new Tile(3,light, light);
        Tile ly = new Tile(4,light, yellow);
        Tile bdark = new Tile(4,blue, black);
        Tile yb = new Tile(1,yellow, blue);
        Tile ybr = new Tile(3,yellow, brown);
        Tile brbr = new Tile(2,brown, brown);

        /********TABLEAU SUR FIGMA*********/
        playerBoard.setTile( 3, 2, Direction.EAST, lb );
        playerBoard.setTile( 4, 3, Direction.SOUTH, blbr );
        playerBoard.setTile( 2, 3, Direction.EAST, ll );
        playerBoard.setTile( 3, 1, Direction.NORTH, ly );
        playerBoard.setTile( 0, 2, Direction.EAST, bdark );
        playerBoard.setTile( 0, 3, Direction.SOUTH, blbr );
        playerBoard.setTile( 3, 4, Direction.WEST, lb );
        playerBoard.setTile( 1, 4, Direction.NORTH, yb );
        playerBoard.setTile( 2, 1, Direction.WEST, ybr );
        playerBoard.setTile( 0, 0 , Direction.EAST, brbr );
        playerBoard.setTile( 4, 1 , Direction.NORTH, blbr );

        //SUPP EN HAUT !


        //Panel qui va contenir tout les élements de jeu
        JPanel gameBoard = new JPanel();
        GridLayout grid = new GridLayout(2, 4);
        grid.setHgap(10);
        grid.setVgap(15);
        gameBoard.setLayout( grid);

        gameBoard.add( createPlayerBoardPanel(playerBoard));
        gameBoard.add( new JLabel("deck") );
        //gameBoard.add( createPlayerBoardPanel(playerBoard2));



        JButton button3 = new JButton("Go back to the menu");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMainMenu();
            }
        });

        panel.setLayout( new BorderLayout() );
        panel.add( header, BorderLayout.NORTH );
        panel.add( gameBoard, BorderLayout.CENTER );
        panel.add( button3, BorderLayout.SOUTH );
    }
    public JPanel getMainPanel(){
        return this.panel;
    }

    /** Fonction qui renvoie un JPanel contenant l'affichage du playerboard passé en parametre
     *
     * @param playerBoard : PlayerBoard qui doit être affiché
     * @return JPanel : Rendu en panel du playerBoard
     */
    private JPanel createPlayerBoardPanel(PlayerBoard playerBoard){
        JPanel boardPnl = new JPanel();
        GridLayout grid = new GridLayout(playerBoard.BOARD_SIZE, playerBoard.BOARD_SIZE);
        boardPnl.setLayout( grid );

        //Place chaque image correspondantes
        for(int j = 0; j<playerBoard.BOARD_SIZE; j++){
            for(int i = 0; i<playerBoard.BOARD_SIZE; i++){
                //Met l'image correspondant à la couleur de la case
                if(playerBoard.getPositionnable(i,j) == null){
                    boardPnl.add( IMGReader.getImagePnl("empty.jpg") );
                }else if(playerBoard.getPositionnable(i,j).getColor() == GroundColor.GREY){
                    boardPnl.add(IMGReader.getImagePnl("castle.png"));
                }else if(playerBoard.getPositionnable(i,j).getColor() == GroundColor.YELLOW){
                    boardPnl.add(IMGReader.getImagePnl("champs.png"));
                }else if(playerBoard.getPositionnable(i,j).getColor() == GroundColor.DARK_GREEN){
                    boardPnl.add(IMGReader.getImagePnl("foret.png"));
                }else if(playerBoard.getPositionnable(i,j).getColor() == GroundColor.LIGHT_GREEN){
                    boardPnl.add(IMGReader.getImagePnl("prairie.png"));
                }else if(playerBoard.getPositionnable(i,j).getColor() == GroundColor.BLACK){
                    boardPnl.add(IMGReader.getImagePnl("mines.png"));
                }else if(playerBoard.getPositionnable(i,j).getColor() == GroundColor.BLUE){
                    boardPnl.add(IMGReader.getImagePnl("mer.png"));
                }else if(playerBoard.getPositionnable(i,j).getColor() == GroundColor.BROWN){
                    boardPnl.add(IMGReader.getImagePnl("montagne.png"));
                }
            }
        }
        return boardPnl;
    }



}
