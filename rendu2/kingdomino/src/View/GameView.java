package View;

import Model.*;
import Utilities.IMGReader;
import View.Components.MyLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameView extends JPanel {
    private MyWindow mainFrame;

    /** IMG_SIZE of tile **/
    private static final int IMG_SIZE = 60;

    //Image de fond de toute la fenetre
    private ImageIcon img;
    //Image de fond de la fenetre des plateaux
    private ImageIcon imgBoard;


    /******Boards panel (plateau des joueurs)*******/
    private JPanel boardsPanel;

    /******Game interaction (Info tour, les 4 tuiles etc)*******/
    private JPanel gameInterac;

    //Label d'info sur l'action à faire du joueur (placez chateau, choisir tuile..)
    private MyLabel headerLbl = new MyLabel("Placez vos châteaux", SwingConstants.CENTER);

    //Label d'info sur le tour du joueur
    private MyLabel tourLbl = new MyLabel("Tour du joueur ??", SwingConstants.CENTER);

    //Couleur des boutons (orangé)
    private final Color btnColor = new Color(174,135,0);


    //Label de message d'erreur
    private MyLabel errorMessageLbl;


    public GameView(MyWindow MyWindow)  {
        this.mainFrame = MyWindow;
        this.img = IMGReader.getImage("wallpaperDark.png");
        this.setOpaque(false);
        this.setLayout( new GridBagLayout() );

        /***GAMEBOARD***/
        //Panel qui va contenir tout les élements de jeu
        JPanel gameBoard = new JPanel();
        gameBoard.setOpaque(false);
        GridBagLayout grid = new GridBagLayout();
        gameBoard.setLayout( grid);
        // Crée un objet de contraintes
        GridBagConstraints boardC = new GridBagConstraints();


        /******Boards panel (plateau des joueurs)*******/
        if(this.mainFrame.getGame().getNbPlayersStrat().getnbBoard() == 2){
            this.imgBoard = IMGReader.getImage("gameWood_2.png");
        }else{
            this.imgBoard = IMGReader.getImage("gameWood_34.png");
        }
        final ImageIcon imgBoardsPanel = this.imgBoard;

        boardsPanel =  new JPanel(){
            Image img = imgBoardsPanel.getImage();
            // initialiseur d'instance
            {setOpaque(false);}
            public void paintComponent(Graphics graphics)
            {
                graphics.drawImage(img, 0, 0, this);
                super.paintComponent(graphics);
            }
        };
        if(this.mainFrame.getGame().getNbPlayersStrat().getnbBoard() == 2){
            boardsPanel.setPreferredSize( new Dimension( 425, 770 ) );
        }else{
            boardsPanel.setPreferredSize( new Dimension( 787, 770 ) );
        }

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
        gameInterac = new JPanel(){
            Image img = IMGReader.getImage("gameBoardWood.png").getImage();
            // initialiseur d'instance
            {setOpaque(false);}
            {setPreferredSize( new Dimension(530, 732) );}
            public void paintComponent(Graphics graphics)
            {
                graphics.drawImage(img, 0, 0, this);
                super.paintComponent(graphics);
            }

        };
        gameInterac.setLayout( new GridBagLayout());
        gameInterac.setBackground( Color.gray );
        //Placement du panel d'interaction
        // augmente la largeur des composants de 100 pixels
        boardC.ipadx = 50;
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
        headerLbl.setFont(new Font("Bookman Old Style", Font.BOLD, 42));
        headerLbl.setOutlineColor(Color.DARK_GRAY);
        headerLbl.setStroke(new BasicStroke(4f));
        headerLbl.setForeground( Color.white );
        header.add( headerLbl );
        header.setOpaque(false);
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
        JButton btnQuit = new JButton("QUITTER");
        btnQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setMainMenu();
            }
        });
        btnQuit.setFont(new Font("Algerian", Font.CENTER_BASELINE, 13));
        btnQuit.setBackground(this.btnColor);
        btnQuit.setFocusPainted(false);
        btnQuit.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 3;
        // Ajouter les contraintes
        this.add( btnQuit, c );



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
        boardPnl.setOpaque(false);

        PlayerBoard playerBoard = player.getBoard();
        JButton[][] boardBtns = new JButton[playerBoard.BOARD_SIZE][playerBoard.BOARD_SIZE];

        for(int j = 0; j<playerBoard.BOARD_SIZE; j++) {
            for (int i = 0; i < playerBoard.BOARD_SIZE; i++) {
                boardBtns[i][j] = new JButton();
            }
        }

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(playerBoard.BOARD_SIZE, playerBoard.BOARD_SIZE));
        //Place chaque image correspondantes
        for(int j = 0; j<playerBoard.BOARD_SIZE; j++){
            for(int i = 0; i<playerBoard.BOARD_SIZE; i++){
                JButton btn = boardBtns[i][j];
                btn.setPreferredSize(new Dimension(IMG_SIZE, IMG_SIZE));
                int finalJ = j;
                int finalI = i;

                /**Action de click sur une case**/
                btn.addActionListener(actionEvent -> {
                    //Placer le chateau seulement on est dans les 1er tours pour le joueur
                    if(mainFrame.getGame().getTurn() < mainFrame.getGame().getPlayers().size() ){
                        mainFrame.getGameController().placeCastle(player,  finalI, finalJ);
                    }else{

                        if(!mainFrame.getGame().allTilesChoosen()){
                            errorMessageLbl.setText("Toutes les tuiles doivent être choisi !");
                            this.revalidate();
                            this.repaint();
                        }else{
                            if(!mainFrame.getGameController().placeTile( finalI, finalJ)){
                                //TODO : Error message : "Cette tuile ne peut pas être placé ici !"
                                errorMessageLbl.setText("Cette tuile ne peut pas être placé ici !");
                                this.revalidate();
                                this.repaint();
                            }
                        }
                    }
                });

                //Si c'est le tour de poser le chateau
                if(mainFrame.getGame().getTurn() < mainFrame.getGame().getPlayers().size() ){
                    btn.setRolloverEnabled(false);
                    if(player != mainFrame.getGame().getPlayerCastleTurn()){ //Si c'est pas son tour
                        btn.setEnabled(false);
                    }
                    else{
                        btn.setRolloverEnabled(enable);
                    }
                }else{
                    btn.setRolloverEnabled(false);
                    if(player != mainFrame.getGame().getKingTurn().getPlayer()){ //Si c'est pas son tour
                        btn.setEnabled(false);
                    }else{
                        btn.setRolloverEnabled(enable);
                    }
                }

                //Met l'image correspondant à la couleur de la case
                if(playerBoard.getPositionnable(i,j) == null){
                    btn.setIcon( IMGReader.getImage("empty.png") );
                }else{
                    btn.setIcon( IMGReader.getImage( playerBoard.getPositionnable(i,j).getColor()) );
                }
                grid.add(btn);


                /** Hover effect **/
                //Si on est en tour chateau
                if(mainFrame.getGame().getTurn() < mainFrame.getGame().getPlayers().size() ){
                    btn.setRolloverIcon( IMGReader.getImage("castle.png")   );
                }
                 else if(this.mainFrame.getGame().allTilesChoosen() && btn.isEnabled()){
                    Tile choosenTile = mainFrame.getGame().getKingTurn().getTile();
                    final GroundColor leftColor = choosenTile.getLeft().getColor();
                    final GroundColor rightColor = choosenTile.getRight().getColor();
                    final int[] xyRight = playerBoard.getRightXY(finalI, finalJ,choosenTile.getDirection() );
                    btn.addMouseListener(new MouseAdapter() {

                        public void mouseEntered(MouseEvent evt) {

                            if(playerBoard.isPosable(finalI, finalJ) && playerBoard.isPosable(xyRight[0], xyRight[1])  ){
                                btn.setIcon( IMGReader.getImage( leftColor  ));
                                boardBtns[xyRight[0]][xyRight[1]].setIcon( IMGReader.getImage( rightColor  ) );
                            }
                        }

                        public void mouseExited(java.awt.event.MouseEvent evt) {
                            if(playerBoard.isPosable(finalI, finalJ) && playerBoard.isPosable(xyRight[0], xyRight[1])  ){
                                btn.setIcon( IMGReader.getImage("empty.png") );
                                boardBtns[xyRight[0]][xyRight[1]].setIcon( IMGReader.getImage("empty.png") );
                            }
                        }
                    });
                }



            }
        }

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 20, 0, 20);

        MyLabel colorLbl = new MyLabel("Joueur " + player.getPlayerColor(), SwingConstants.CENTER);
        colorLbl.setFont(new Font("Showcard Gothic",Font.BOLD, 20));
        colorLbl.setOutlineColor(Color.DARK_GRAY);
        colorLbl.setStroke(new BasicStroke(3f));
        colorLbl.setForeground( KingColor.getColor( player.getPlayerColor() ) );
        c.gridx = 0;
        c.gridy = 0;
        boardPnl.add( colorLbl, c );

        c.gridx = 0;
        c.gridy = 1;
        boardPnl.add( grid, c );
        return boardPnl;
    }


    /** Fonction qui renvoie un JPanel contenant l'affichage d'une tuile en mode rotation (choosentile)
     *
     * @param tile : PlayerBoard qui doit être affiché
     * @param king
     * @return JPanel : Rendu en panel du playerBoard
     */
    private JPanel createChoosenTile(Tile tile, King king){
        JPanel tilePanel = new JPanel();
        tilePanel.setLayout( new BorderLayout() );
        tilePanel.setOpaque(false);
        tilePanel.setPreferredSize( new Dimension(IMG_SIZE*2, IMG_SIZE*2));

        JButton left = new JButton();
        JButton right = new JButton();
        left.setPreferredSize(new Dimension(IMG_SIZE, IMG_SIZE));
        right.setPreferredSize(new Dimension(IMG_SIZE, IMG_SIZE));

        //Met les bouttons en transparent (que les images des tuiles seront visible)
        left.setOpaque(false);
        left.setContentAreaFilled(false);
        left.setBorderPainted(false);
        right.setOpaque(false);
        right.setContentAreaFilled(false);
        right.setBorderPainted(false);

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
        left.setIcon( IMGReader.getImage(tile.getLeft().getColor()) );
        right.setIcon( IMGReader.getImage(tile.getRight().getColor()) );


        switch(tile.getDirection()){

            case NORTH:
                tilePanel.add(left, BorderLayout.SOUTH);
                tilePanel.add(right, BorderLayout.NORTH);
                break;

            case SOUTH:
                tilePanel.add(left, BorderLayout.NORTH);
                tilePanel.add(right, BorderLayout.SOUTH);
                break;

            case WEST:
                tilePanel.add(left, BorderLayout.LINE_END);
                tilePanel.add(right, BorderLayout.LINE_START);
                break;
            default:
                tilePanel.add(left, BorderLayout.LINE_START);
                tilePanel.add(right, BorderLayout.LINE_END);
                break;
        }

        return tilePanel;
    }

    /** Fonction qui renvoie un JPanel contenant l'affichage d'une tuile (Affichage de la pioche)
     *
     * @param tile : PlayerBoard qui doit être affiché
     * @param king
     * @return JPanel : Rendu en panel du playerBoard
     */
    private JPanel createTile(Tile tile, King king){
        JPanel tilePanel = new JPanel();
        tilePanel.setLayout( new BorderLayout() );
        tilePanel.setPreferredSize( new Dimension(IMG_SIZE*2, IMG_SIZE));

        JButton left = new JButton();
        JButton right = new JButton();
        left.setPreferredSize(new Dimension(IMG_SIZE, IMG_SIZE));
        right.setPreferredSize(new Dimension(IMG_SIZE, IMG_SIZE));

        //Met les bouttons en transparent (que les images des tuiles seront visible)
        left.setOpaque(false);
        left.setContentAreaFilled(false);
        left.setBorderPainted(false);
        right.setOpaque(false);
        right.setContentAreaFilled(false);
        right.setBorderPainted(false);

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
        left.setIcon( IMGReader.getImage(tile.getLeft().getColor()) );
        right.setIcon( IMGReader.getImage(tile.getRight().getColor()) );

        tilePanel.add(left, BorderLayout.LINE_START);
        tilePanel.add(right, BorderLayout.LINE_END);


        if(king != null){
            tilePanel.setBorder( BorderFactory.createLineBorder( KingColor.getColor(king.getColor()), 5 ) );
        }

        return tilePanel;
    }

    //Renvoie un Bouton qui represente un chateau
    public JButton createCastle(){
        JButton btn = new JButton();
        btn.setPreferredSize(new Dimension(IMG_SIZE, IMG_SIZE));
        btn.setIcon( IMGReader.getImage("castle.png") );
        return btn;
    }

    private void updateInteractionBoard(){
        gameInterac.removeAll();

        /** Creation des boutons (dans gameInterac) **/
        GridBagConstraints interacC = new GridBagConstraints();
        interacC.insets = new Insets(0, 20, 20, 20);

        /** Message d'erreur **/
        errorMessageLbl = new MyLabel(" ");
        errorMessageLbl.setFont(new Font("Showcard Gothic",Font.BOLD, 21));
        errorMessageLbl.setOutlineColor(Color.DARK_GRAY);
        errorMessageLbl.setStroke(new BasicStroke(2f));
        errorMessageLbl.setForeground(new Color(252, 87, 87));
        interacC.gridx = 0;
        interacC.gridy = 0;
        gameInterac.add( errorMessageLbl, interacC );

        JPanel infoTurnPnl = new JPanel();
        infoTurnPnl.setOpaque(false);
        infoTurnPnl.setLayout( new GridBagLayout() );
        tourLbl.setFont(new Font("Bookman Old Style", Font.CENTER_BASELINE, 27));
        tourLbl.setOutlineColor(Color.DARK_GRAY);
        tourLbl.setStroke(new BasicStroke(3f));
        interacC.gridx = 0;
        interacC.gridy = 1;
        infoTurnPnl.add( tourLbl, interacC );
        gameInterac.add( infoTurnPnl, interacC );




        JPanel currentTilesPnl = new JPanel();
        currentTilesPnl.setOpaque(false);
        currentTilesPnl.setLayout( new GridBagLayout() );

        double nbPlayers = mainFrame.getGame().getNbPlayersStrat().getnbBoard();
        double turn = mainFrame.getGame().getTurn();
        interacC.insets = new Insets(5, 20, 5, 20);

        //Si on est au tour 1 pour tout les joueurs
        if(turn / nbPlayers < 1){
            /** Creation des boutons chateau (dans gameInterac) **/
            for (int i = 0; i< nbPlayers - turn ; i++){
                interacC.gridx = 0;
                interacC.gridy = i;
                currentTilesPnl.add( createCastle() , interacC);
                KingColor playerTurnColor = this.mainFrame.getGame().getPlayerCastleTurn().getPlayerColor();
                tourLbl.setText( "Tour du joueur " + playerTurnColor);
                tourLbl.setForeground( KingColor.getColor( playerTurnColor ) );


            }
        }else{
            int i = 0;
            for (Tile tile : mainFrame.getGame().getCurrentTiles().keySet()) {
                interacC.gridx = 0;
                interacC.gridy = i;
                currentTilesPnl.add( createTile( tile, mainFrame.getGame().getCurrentTiles().get(tile) ) , interacC);
                i++;
                KingColor playerTurnColor = this.mainFrame.getGame().getKingTurn().getColor();
                tourLbl.setText( "Tour du joueur " + playerTurnColor );
                tourLbl.setForeground( KingColor.getColor( playerTurnColor ) );

            }
        }

        interacC.gridx = 0;
        interacC.gridy = 2;
        gameInterac.add( currentTilesPnl, interacC );

        JPanel choosenTilePnl = new JPanel();
        choosenTilePnl.setOpaque(false);
        choosenTilePnl.setLayout( new GridBagLayout() );

        //Si les joueurs doivent placer leur tuile
        if( this.mainFrame.getGame().allTilesChoosen() ){


            GridBagConstraints tempC = new GridBagConstraints();
            tempC.insets = new Insets(0,0,0,0);

            //Ajout du Panel invisible pour permettre la rotation de la tuile à l'interieur
            JPanel tempPnl = new JPanel();
            tempPnl.setOpaque(false);
            tempPnl.setLayout( new GridBagLayout() );

            //Ajout de la 1ère tuile choisi
            Tile currentTile = this.mainFrame.getGame().getKingTurn().getTile();
            interacC.gridx = 0;
            interacC.gridy = 1;
            choosenTilePnl.add( this.createChoosenTile( currentTile, this.mainFrame.getGame().getKingTurn() ) );

            JButton btnRotate = new JButton("PIVOTER");
            btnRotate.addActionListener(actionEvent -> {
                this.mainFrame.getGameController().rotate();
            });
            btnRotate.setFont(new Font("Algerian", Font.CENTER_BASELINE, 12));
            btnRotate.setBackground(btnColor);
            btnRotate.setFocusPainted(false);
            btnRotate.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            btnRotate.setPreferredSize(new Dimension(80,40));
            interacC.gridx = 1;
            interacC.gridy = 0;
            choosenTilePnl.add( btnRotate, interacC );

            JButton btnReverse = new JButton("INVERSER");
            btnReverse.addActionListener(actionEvent -> {
                this.mainFrame.getGameController().reverse();
            });
            btnReverse.setFocusPainted(false);
            btnReverse.setFont(new Font("Algerian", Font.CENTER_BASELINE, 12));
            btnReverse.setBackground(btnColor);
            btnReverse.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            btnReverse.setPreferredSize(new Dimension(80,40));
            interacC.gridx = 1;
            interacC.gridy = 2;
            choosenTilePnl.add( btnReverse, interacC );
        }
        interacC.insets = new Insets(10, 20, 10, 20);
        interacC.gridx = 0;
        interacC.gridy = 3;
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
        //Si tout le monde a placer ses chateaux
        if( mainFrame.getGame().getTurn() >= mainFrame.getGame().getPlayers().size() ){

            //Si toutes les tuiles ont été choisis
            if(mainFrame.getGame().allTilesChoosen()){
                this.headerLbl.setText( "Placez vos tuiles" );
            }else{
                this.headerLbl.setText( "Choisissez vos tuiles" );
            }
        }


        this.revalidate();
        this.repaint();
    }

    public void paint(Graphics g){
        g.drawImage( this.img.getImage(), 0 , 0,mainFrame.getWidth(), mainFrame.getHeight(), null);
        super.paint(g);
    }


}
