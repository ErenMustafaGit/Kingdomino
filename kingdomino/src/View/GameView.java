package View;

import Model.*;
import Utilities.IMGReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView  {
    private MyWindow mainFrame;
    private JPanel panel = new JPanel();

    public GameView(MyWindow MyWindow){
        this.mainFrame = MyWindow;




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


        /***GAMEBOARD***/
        //Panel qui va contenir tout les élements de jeu
        JPanel gameBoard = new JPanel();
        GridBagLayout grid = new GridBagLayout();
        gameBoard.setLayout( grid);
        // Crée un objet de contraintes
        GridBagConstraints boardC = new GridBagConstraints();


        /******Boards panel (plateau des joueurs)*******/
        JPanel boardsPanel =  new JPanel();
        boardsPanel.setLayout( new GridBagLayout());
        int boardMargin = 20;
        boardC.insets = new Insets(boardMargin, boardMargin, boardMargin, boardMargin);
        boardC.gridx = 0;
        boardC.gridy = 0;
        boardC.fill = GridBagConstraints.BOTH;
        boardsPanel.setBackground( Color.RED );
        gameBoard.add( boardsPanel, boardC );



        /*Les plateau de jeu mis dans le boardsPanel*/
        GridBagConstraints oneBoardC = new GridBagConstraints();
        oneBoardC.insets = new Insets(20, 20, 20, 20);

        boardC.gridx = 0;
        boardC.gridy = 0;
        boardsPanel.add( createPlayerBoardPanel(playerBoard), boardC);

        boardC.gridx = 0;
        boardC.gridy = 1;
        boardsPanel.add( createPlayerBoardPanel(playerBoard), boardC);


        boardC.gridx = 1;
        boardC.gridy = 1;
        boardsPanel.add( createPlayerBoardPanel(playerBoard), boardC);



        /******Game interaction (les 4 tuiles etc)*******/
        JPanel gameInterac =  new JPanel();
        gameInterac.setLayout( new GridBagLayout());
        gameInterac.setBackground( Color.black );

        GridBagConstraints interacC = new GridBagConstraints();
        interacC.insets = new Insets(5, 20, 5, 20);

        // augmente la largeur des composants de 100 pixels
        boardC.ipadx = 100;
        boardC.fill = GridBagConstraints.VERTICAL;
        boardC.gridx = 2;
        boardC.gridy = 0;
        gameBoard.add( gameInterac, boardC );

        /** Creation des boutons (dans gameInterac) **/
        for (int i = 0; i<4; i++){
            interacC.gridx = 0;
            interacC.gridy = i;
            gameInterac.add( createTile(lb) , interacC);
        }





        panel.setLayout( new GridBagLayout());
        // Crée un objet de contraintes
        GridBagConstraints c = new GridBagConstraints();

        // Spécifie le padding externe de tous les composants
        c.insets = new Insets(1, 1, 1, 1);

        /***HEADER***/
        JPanel header = new JPanel();
        header.setLayout( new BorderLayout() );
        header.add( new JLabel("Kingdomino game"), BorderLayout.CENTER );
        c.fill = GridBagConstraints.BOTH;
        // colonne 0
        c.gridx = 0;
        // ligne 0
        c.gridy = 0;
        // Ajouter les contraintes
        panel.add( header, c );

        /**Ajout du gameBoard**/
        // colonne 0
        c.gridx = 0;
        // ligne 1
        c.gridy = 1;
        // augmente la largeur des composants de 90 pixels
        c.ipadx = 20;
        // augmente la hauteur des composants de 40 pixels
        c.ipady = 20;
        // Ajouter les contraintes
        panel.add( gameBoard, c );

        /**Bouton de retourn au menu**/
        JButton backButton = new JButton("Go back to the menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMainMenu();
            }
        });
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 2;
        // Ajouter les contraintes
        panel.add( backButton, c );



        // ajouter le contenu
        mainFrame.getContentPane().add(panel);
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


    /** Fonction qui renvoie un JPanel contenant l'affichage du playerboard passé en parametre
     *
     * @param tile : PlayerBoard qui doit être affiché
     * @return JPanel : Rendu en panel du playerBoard
     */
    private JPanel createTile(Tile tile){
        JPanel tilePanel = new JPanel();
        tilePanel.setLayout( new BorderLayout() );

        JButton left = new JButton("left");
        JButton right = new JButton("right");

        //Met l'image correspondant à la couleur de la case
        switch(tile.getLeft().getColor()){

            case YELLOW:
                left.add( IMGReader.getImagePnl("champs.png") );
                break;
            case DARK_GREEN:
                left.add( IMGReader.getImagePnl("foret.png") );
                break;
            case LIGHT_GREEN:
                left.add( IMGReader.getImagePnl("prairie.png") );
                break;
            case BLACK:
                left.add( IMGReader.getImagePnl("mines.png") );
                break;
            case BLUE:
                left.add( IMGReader.getImagePnl("mer.png") );
                break;
            case BROWN:
                left.add( IMGReader.getImagePnl("montagne.png") );
                break;
        }

        switch(tile.getRight().getColor()){

            case YELLOW:
                right.add( IMGReader.getImagePnl("champs.png") );
                break;
            case DARK_GREEN:
                right.add( IMGReader.getImagePnl("foret.png") );
                break;
            case LIGHT_GREEN:
                right.add( IMGReader.getImagePnl("prairie.png") );
                break;
            case BLACK:
                right.add( IMGReader.getImagePnl("mines.png") );
                break;
            case BLUE:
                right.add( IMGReader.getImagePnl("mer.png") );
                break;
            case BROWN:
                right.add( IMGReader.getImagePnl("montagne.png") );
                break;
        }




        tilePanel.add(left, BorderLayout.LINE_START);
        tilePanel.add(right, BorderLayout.LINE_END);


        return tilePanel;
    }



}
