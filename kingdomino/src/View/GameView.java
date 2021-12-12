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
        PlayerBoard playerBoard1 = new PlayerBoard(caste);
        PlayerBoard playerBoard2 = new PlayerBoard();
        Deck myDeck = new Deck(32);
        Tile tile = myDeck.getTile();
        System.out.println(tile.getNumber());
         System.out.println(playerBoard1.setTile(2, 1          , Direction.NORTH, tile ));
        Tile tile2 = myDeck.getTile();
        System.out.println(tile2.getNumber());
        System.out.println(playerBoard1.setTile(3, 2, Direction.EAST, tile2 ));
        System.out.println(playerBoard1.setTile(3, 2, Direction.EAST, tile2 ));
        //SUPP EN HAUT !


        //Panel qui va contenir tout les élements de jeu
        JPanel gameBoard = new JPanel();
        GridLayout grid = new GridLayout(2, 4);
        grid.setHgap(10);
        grid.setVgap(15);
        gameBoard.setLayout( grid);

        gameBoard.add( createPlayerBoardPanel(playerBoard1));
        gameBoard.add( new JLabel("deck") );
        gameBoard.add( createPlayerBoardPanel(playerBoard2));



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
        for(int i = 0; i<playerBoard.BOARD_SIZE; i++){
            for(int j = 0; j<playerBoard.BOARD_SIZE; j++){
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
