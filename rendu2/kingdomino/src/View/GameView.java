package View;

import Model.*;
import Utilities.IMGReader;
import View.Components.MyLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JPanel {
    private MyWindow mainFrame;

    /******Boards panel (plateau des joueurs)*******/
    JPanel boardsPanel;

    /******Game interaction (Info tour, les 4 tuiles etc)*******/
    JPanel gameInterac;

    //Label d'info sur l'action à faire du joueur (placez chateau, choisir tuile..)
    MyLabel headerLbl = new MyLabel("Placez vos châteaux", SwingConstants.CENTER);

    //Label d'info sur le tour du joueur
    MyLabel tourLbl = new MyLabel("Tour du joueur ??", SwingConstants.CENTER);


    public GameView(MyWindow MyWindow)  {
        this.mainFrame = MyWindow;
        this.setLayout( new GridBagLayout() );

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


        /******Game interaction (info tour, les 4 tuiles etc)*******/
        gameInterac = new JPanel();
        gameInterac.setLayout( new GridBagLayout());
        gameInterac.setBackground( Color.gray );
        //Placement du panel d'interaction
        // augmente la largeur des composants de 100 pixels
        boardC.ipadx = 100;
        boardC.fill = GridBagConstraints.VERTICAL;
        boardC.gridx = 2;
        boardC.gridy = 0;
        gameBoard.add( gameInterac, boardC );

        this.updateInteractionBoard();



        this.setLayout( new GridBagLayout());
        // Crée un objet de contraintes
        GridBagConstraints c = new GridBagConstraints();

        // Spécifie le padding externe de tous les composants
        c.insets = new Insets(1, 1, 1, 1);

        /***HEADER***/
        JPanel header = new JPanel();
        header.setLayout( new BorderLayout() );
        headerLbl.setFont(new Font("Bookman Old Style", Font.BOLD, 30));
        header.add( headerLbl );
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
        JButton backButton = new JButton("QUITTER");
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

                /**Action de click sur une case**/
                btn.addActionListener(actionEvent -> {
                    //Placer le chateau seulement on est dans les 1er tours pour le joueur
                    if(mainFrame.getGame().getTurn() < mainFrame.getGame().getPlayers().size() ){
                        mainFrame.getGameController().placeCastle(player,  finalI, finalJ);
                    }else{

                        if(!mainFrame.getGame().allTilesChoosen()){
                            //TODO : Error message : "Toutes les tuiles doivent être choisi !"
                        }else{
                            if(!mainFrame.getGameController().placeTile( finalI, finalJ, Direction.EAST)){
                                //TODO : Error message : "Cette tuile ne peut pas être placé ici !"
                            }
                        }
                    }
                });

                btn.setRolloverEnabled(false);
                if(player != mainFrame.getGame().getPlayerTurn()){ //Si c'est pas son tour
                    btn.setEnabled(false);
                }
                btn.setRolloverEnabled(false);
                //Met l'image correspondant à la couleur de la case
                if(playerBoard.getPositionnable(i,j) == null){
                    btn.setIcon( IMGReader.getImage("empty.jpg") );
                    grid.add(btn);
                    if(player == mainFrame.getGame().getPlayerTurn()){ //Si c'est son tour
                        //btn.setEnabled(enable);
                        btn.setRolloverEnabled(enable);
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


    /** Fonction qui renvoie un JPanel contenant l'affichage d'une tuile
     *
     * @param tile : PlayerBoard qui doit être affiché
     * @param player
     * @return JPanel : Rendu en panel du playerBoard
     */
    private JPanel createTile(Tile tile, Player player){
        JPanel tilePanel = new JPanel();
        tilePanel.setLayout( new BorderLayout() );
        tilePanel.setPreferredSize( new Dimension(100, 50));

        JButton left = new JButton("left");
        JButton right = new JButton("right");
        left.setPreferredSize(new Dimension(50, 50));
        right.setPreferredSize(new Dimension(50, 50));

        left.addActionListener(actionEvent -> {
            if(mainFrame.getGame().getCurrentTiles().get(tile) == null){
                mainFrame.getGameController().chooseTile(tile);
            }
        });

        right.addActionListener(actionEvent -> {
            if(mainFrame.getGame().getCurrentTiles().get(tile) == null){
                mainFrame.getGameController().chooseTile(tile);
            }
        });



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
        if(player != null){
            tilePanel.add( new JLabel(player.getPlayerColor().toString()), BorderLayout.SOUTH );
        }

        return tilePanel;
    }

    //Renvoie un Bouton qui represente un chateau
    public JButton createCastle(){
        JButton btn = new JButton();
        btn.setPreferredSize(new Dimension(50, 50));
        btn.setIcon( IMGReader.getImage("castle.png") );
        return btn;
    }

    private void updateInteractionBoard(){
        gameInterac.removeAll();

        /** Creation des boutons (dans gameInterac) **/
        GridBagConstraints interacC = new GridBagConstraints();
        interacC.insets = new Insets(5, 20, 5, 20);

        JPanel infoTurnPnl = new JPanel();
        infoTurnPnl.setLayout( new GridBagLayout() );
        tourLbl.setFont(new Font("Bookman Old Style", Font.CENTER_BASELINE, 30));
        tourLbl.setText( "Tour du joueur " + this.mainFrame.getGame().getPlayerTurn().getPlayerColor());

        interacC.gridx = 0;
        interacC.gridy = 0;
        infoTurnPnl.add( tourLbl, interacC );
        gameInterac.add( infoTurnPnl, interacC );




        JPanel currentTilesPnl = new JPanel();
        currentTilesPnl.setLayout( new GridBagLayout() );

        double nbPlayers = mainFrame.getGame().getNbPlayersStrat().getnbBoard();
        double turn = mainFrame.getGame().getTurn();

        //Si on est au tour 1 pour tout les joueurs
        if(turn / nbPlayers < 1){
            /** Creation des boutons chateau (dans gameInterac) **/
            for (int i = 0; i< nbPlayers - turn ; i++){
                interacC.gridx = 0;
                interacC.gridy = i;
                currentTilesPnl.add( createCastle() , interacC);
            }
        }else{
            int i = 0;
            for (Tile tile : mainFrame.getGame().getCurrentTiles().keySet()) {
                interacC.gridx = 0;
                interacC.gridy = i;
                currentTilesPnl.add( createTile( tile, mainFrame.getGame().getCurrentTiles().get(tile) ) , interacC);
                i++;
            }
        }

        interacC.gridx = 0;
        interacC.gridy = 1;
        gameInterac.add( currentTilesPnl, interacC );

        JPanel choosenTilePnl = new JPanel();
        choosenTilePnl.setLayout( new GridBagLayout() );

        //Si les joueurs doivent placer leur tuile
        if( this.mainFrame.getGame().allTilesChoosen() ){
            //Couleur des 2 boutons
            Color btnColor = new Color(174,135,0);


            GridBagConstraints tempC = new GridBagConstraints();
            tempC.insets = new Insets(0,0,0,0);

            //Ajout du Panel invisible pour permettre la rotation de la tuile à l'interieur
            JPanel tempPnl = new JPanel();
            tempPnl.setLayout( new GridBagLayout() );

            //Ajout de la 1ère tuile choisi
            Tile currentTile = this.mainFrame.getGame().getPlayerTurn().getTile();
            interacC.gridx = 0;
            interacC.gridy = 1;
            choosenTilePnl.add( this.createTile( currentTile, this.mainFrame.getGame().getPlayerTurn() ) );

            JButton btnRotate = new JButton("PIVOTER");
            btnRotate.setFont(new Font("Algerian", Font.CENTER_BASELINE, 12));
            btnRotate.setBackground(btnColor);
            btnRotate.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            btnRotate.setPreferredSize(new Dimension(80,40));
            interacC.gridx = 1;
            interacC.gridy = 0;
            choosenTilePnl.add( btnRotate, interacC );

            JButton btnReverse = new JButton("INVERSER");
            btnReverse.setFont(new Font("Algerian", Font.CENTER_BASELINE, 12));
            btnReverse.setBackground(btnColor);
            btnReverse.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            btnReverse.setPreferredSize(new Dimension(80,40));
            interacC.gridx = 1;
            interacC.gridy = 2;
            choosenTilePnl.add( btnReverse, interacC );
        }
        interacC.gridx = 0;
        interacC.gridy = 2;
        gameInterac.add( choosenTilePnl, interacC );


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
        //Update all players boards
        this.updatePlayersBoards();
        //Update game interaction board (right panel)
        this.updateInteractionBoard();

        //Mise à jour du message d'action
        if( mainFrame.getGame().getTurn() >= mainFrame.getGame().getPlayers().size() ){
            if(mainFrame.getGame().allTilesChoosen()){
                this.headerLbl.setText( "Placez vos tuiles" );
            }else{
                this.headerLbl.setText( "Choisissez vos tuiles" );
            }
        }

        this.revalidate();
        this.repaint();
    }



}
