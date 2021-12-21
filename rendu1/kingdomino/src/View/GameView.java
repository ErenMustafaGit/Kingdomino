package View;

import Model.*;
import Utilities.IMGReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JPanel {
    private MyWindow mainFrame;

    /******Boards panel (plateau des joueurs)*******/
    JPanel boardsPanel;

    public GameView(MyWindow MyWindow)  {
        this.mainFrame = MyWindow;


        /***GAMEBOARD***/
        //Panel qui va contenir tout les élements de jeu
        JPanel gameBoard = new JPanel();
        GridBagLayout grid = new GridBagLayout();
        gameBoard.setLayout( grid);
        // Crée un objet de contraintes
        GridBagConstraints boardC = new GridBagConstraints();


        /******Boards panel (plateau des joueurs)*******/
        boardsPanel =  new JPanel();
        boardsPanel.setLayout( new GridBagLayout());
        int boardMargin = 20;
        boardC.insets = new Insets(boardMargin, boardMargin, boardMargin, boardMargin);
        boardC.gridx = 0;
        boardC.gridy = 0;
        boardC.fill = GridBagConstraints.BOTH;
        boardsPanel.setBackground( Color.GRAY );
        gameBoard.add( boardsPanel, boardC );
        this.updatePlayersBoards();





        /******Game interaction (les 4 tuiles etc)*******/
        JPanel gameInterac =  new JPanel();
        gameInterac.setLayout( new GridBagLayout());
        gameInterac.setBackground( Color.gray );

        GridBagConstraints interacC = new GridBagConstraints();
        interacC.insets = new Insets(5, 20, 5, 20);

        // augmente la largeur des composants de 100 pixels
        boardC.ipadx = 100;
        boardC.fill = GridBagConstraints.VERTICAL;
        boardC.gridx = 2;
        boardC.gridy = 0;
        gameBoard.add( gameInterac, boardC );

        /** Creation des boutons (dans gameInterac) **/
        /*
        for (int i = 0; i<4; i++){
            interacC.gridx = 0;
            interacC.gridy = i;
            gameInterac.add( createTile( mainFrame.getGame().getCurrentTiles().get(i) ) , interacC);
        }*/

        /** Creation des boutons chateau (dans gameInterac) **/
        for (int i = 0; i< mainFrame.getGame().getNbPlayersStrat().getnbBoard(); i++){
            interacC.gridx = 0;
            interacC.gridy = i;
            gameInterac.add( createCastle() , interacC);
        }




        this.setLayout( new GridBagLayout());
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
        this.add( header, c );

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
        this.add( gameBoard, c );

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
        this.add( backButton, c );



        // ajouter le contenu
        mainFrame.getContentPane().add(this);
    }
    public JPanel getMainPanel(){
        return this;
    }

    /** Fonction qui renvoie un JPanel contenant l'affichage du playerboard passé en parametre
     *
     * @param player : Le joueur qui va avoir son plateau d'affiché
     * @param enable : Si c'est le tour de ce joueur de jouer
     * @return JPanel : Rendu en panel du playerBoard
     */
    private JPanel createPlayerBoardPanel(Player player, boolean enable){
        JPanel boardPnl = new JPanel();
        boardPnl.setLayout( new GridBagLayout() );

        PlayerBoard playerBoard = player.getBoard();

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(playerBoard.BOARD_SIZE, playerBoard.BOARD_SIZE));
        //Place chaque image correspondantes
        for(int j = 0; j<playerBoard.BOARD_SIZE; j++){
            for(int i = 0; i<playerBoard.BOARD_SIZE; i++){
                JButton btn = new JButton();
                btn.setPreferredSize(new Dimension(50, 50));
                int finalJ = j;
                int finalI = i;
                btn.addActionListener(actionEvent -> {
                    mainFrame.getGameController().placeCastle(player,  finalI, finalJ);
                });
                btn.setEnabled(false);

                //Met l'image correspondant à la couleur de la case
                if(playerBoard.getPositionnable(i,j) == null){
                    btn.setIcon( IMGReader.getImage("empty.jpg") );
                    grid.add(btn);
                    if(player == mainFrame.getGame().getPlayerTurn()){ //Si c'est son tour
                        btn.setEnabled(enable);
                    }
                }else if(playerBoard.getPositionnable(i,j).getColor() == GroundColor.GREY){
                    btn.setIcon( IMGReader.getImage("castle.png") );
                    grid.add(btn);
                }else if(playerBoard.getPositionnable(i,j).getColor() == GroundColor.YELLOW){
                    btn.setIcon( IMGReader.getImage("champs.png") );
                    grid.add(btn);
                }else if(playerBoard.getPositionnable(i,j).getColor() == GroundColor.DARK_GREEN){
                    btn.setIcon( IMGReader.getImage("foret.png") );
                    grid.add(btn);
                }else if(playerBoard.getPositionnable(i,j).getColor() == GroundColor.LIGHT_GREEN){
                    btn.setIcon( IMGReader.getImage("prairie.png") );
                    grid.add(btn);
                }else if(playerBoard.getPositionnable(i,j).getColor() == GroundColor.BLACK){
                    btn.setIcon( IMGReader.getImage("mines.png") );
                    grid.add(btn);
                }else if(playerBoard.getPositionnable(i,j).getColor() == GroundColor.BLUE){
                    btn.setIcon( IMGReader.getImage("mer.png") );
                    grid.add(btn);
                }else if(playerBoard.getPositionnable(i,j).getColor() == GroundColor.BROWN){
                    btn.setIcon( IMGReader.getImage("montagne.png") );
                    grid.add(btn);
                }
            }
        }

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 20, 5, 20);

        JLabel colorLbl = new JLabel( "Joueur " + player.getPlayerColor());
        c.gridx = 0;
        c.gridy = 0;
        boardPnl.add( colorLbl, c );

        c.gridx = 0;
        c.gridy = 1;
        boardPnl.add( grid, c );
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
        left.setPreferredSize(new Dimension(50, 50));
        right.setPreferredSize(new Dimension(50, 50));

        //Met l'image correspondant à la couleur de la case
        switch(tile.getLeft().getColor()){

            case YELLOW:
                left.setIcon( IMGReader.getImage("montagne.png") );
                break;
            case DARK_GREEN:
                left.setIcon(IMGReader.getImage("foret.png") );
                break;
            case LIGHT_GREEN:
                left.setIcon( IMGReader.getImage("prairie.png") );
                break;
            case BLACK:
                left.setIcon( IMGReader.getImage("mines.png") );
                break;
            case BLUE:
                left.setIcon( IMGReader.getImage("mer.png") );
                break;
            case BROWN:
                left.setIcon( IMGReader.getImage("montagne.png") );
                break;
        }

        switch(tile.getRight().getColor()){

            case YELLOW:
                right.setIcon( IMGReader.getImage("champs.png") );
                break;
            case DARK_GREEN:
                right.setIcon( IMGReader.getImage("foret.png") );
                break;
            case LIGHT_GREEN:
                right.setIcon( IMGReader.getImage("prairie.png") );
                break;
            case BLACK:
                right.setIcon( IMGReader.getImage("mines.png") );
                break;
            case BLUE:
                right.setIcon( IMGReader.getImage("mer.png") );
                break;
            case BROWN:
                right.setIcon( IMGReader.getImage("montagne.png") );
                break;
        }
        tilePanel.add(left, BorderLayout.LINE_START);
        tilePanel.add(right, BorderLayout.LINE_END);

        return tilePanel;
    }

    public JButton createCastle(){
        JButton btn = new JButton();
        btn.setPreferredSize(new Dimension(50, 50));
        btn.setIcon( IMGReader.getImage("castle.png") );
        return btn;
    }

    private void updatePlayersBoards(){
        boardsPanel.removeAll();

        /*Les plateau de jeu mis dans le boardsPanel*/
        GridBagConstraints boardC = new GridBagConstraints();
        int boardMargin = 20;
        boardC.insets = new Insets(boardMargin, boardMargin, boardMargin, boardMargin);

        boardC.gridx = 0;
        boardC.gridy = 0;
        boardsPanel.add( createPlayerBoardPanel(mainFrame.getGame().getPlayers().get(0), true ), boardC);


        boardC.gridx = 0;
        boardC.gridy = 1;
        boardsPanel.add( createPlayerBoardPanel(mainFrame.getGame().getPlayers().get(1), true), boardC);

        if( mainFrame.getGame().getNbPlayersStrat().getnbBoard() > 2 ){
            boardC.gridx = 1;
            boardC.gridy = 0;
            boardsPanel.add( createPlayerBoardPanel(mainFrame.getGame().getPlayers().get(2), true), boardC);
            if( mainFrame.getGame().getNbPlayersStrat().getnbBoard() > 3 ){
                boardC.gridx = 1;
                boardC.gridy = 1;
                boardsPanel.add( createPlayerBoardPanel(mainFrame.getGame().getPlayers().get(3), true), boardC);
            }
        }

    }

    public void update (GameContext game){

        this.updatePlayersBoards();
        this.revalidate();
        this.repaint();
    }



}
