package View;

import Controller.GameController;
import Model.*;
import Utilities.FontReader;
import Utilities.IMGReader;
import View.Components.ErrorMessage;
import View.Components.MyLabel;
import View.Components.PlayerBoardView;
import View.Components.TileView;

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
    //Couleur des boutons en hover (orangé plus clair)
    private final Color btnHoverColor = new Color(198, 159, 22);

    //Couleur des boutons skip (rouge)
    private final Color btnSkipColor = new Color(255, 88, 88);
    //Couleur des boutons skip en hover (rouge plus clair)
    private final Color btnSkipHoverColor = new Color(255, 140, 140);



    //Label de message d'erreur
    private ErrorMessage errorMessageLbl;


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


        /***HEADER***/
        JPanel header = new JPanel();
        header.setLayout( new BorderLayout() );
        //headerLbl.setFont(new Font("Bookman Old Style", Font.BOLD, 42));
        headerLbl.setFont(FontReader.getInstance().getBookmanold().deriveFont(Font.BOLD).deriveFont(70f));
        headerLbl.setOutlineColor(Color.DARK_GRAY);
        headerLbl.setStroke(new BasicStroke(4f));
        headerLbl.setForeground( Color.white );
        header.add( headerLbl );
        header.setOpaque(false);
        c.fill = GridBagConstraints.BOTH;

        c.insets = new Insets(1, 1, -50, 1);
        // colonne 0
        c.gridx = 0;
        // ligne 0
        c.gridy = 0;
        // Ajouter les contraintes
        this.add( header, c );

        c.insets = new Insets(1, 1, 1, 1);


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
                mainFrame.getGameController().destroyGame();
                mainFrame.setMainMenu();
            }
        });
        //HOVER BUTTON
        btnQuit.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btnQuit.setBackground(btnHoverColor);
            }

            public void mouseExited(MouseEvent evt) {
                btnQuit.setBackground(btnColor);
            }
        });
        btnQuit.setFont(FontReader.getInstance().getBookmanold().deriveFont(Font.CENTER_BASELINE).deriveFont(18f));
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




    public void modifyErrorMessage(String msg){
        this.errorMessageLbl.setText(msg);
        this.revalidate();
        this.repaint();
    }





    //Renvoie un Bouton qui represente un chateau
    public JButton createCastle(){
        JButton btn = new JButton();
        btn.setPreferredSize(new Dimension(IMG_SIZE, IMG_SIZE));
        btn.setIcon( IMGReader.getImage(GroundColor.GREY, 0) );
        return btn;
    }

    private void updateInteractionBoard(){
        gameInterac.removeAll();

        /** Creation des boutons (dans gameInterac) **/
        GridBagConstraints interacC = new GridBagConstraints();
        interacC.insets = new Insets(0, 20, 20, 20);

        /** Message d'erreur **/
        this.errorMessageLbl =  new ErrorMessage(this);
        interacC.gridx = 0;
        interacC.gridy = 0;
        gameInterac.add( this.errorMessageLbl , interacC );

        JPanel infoTurnPnl = new JPanel();
        infoTurnPnl.setOpaque(false);
        infoTurnPnl.setLayout( new GridBagLayout() );
        tourLbl.setFont(FontReader.getInstance().getShowcard().deriveFont(Font.CENTER_BASELINE).deriveFont(27f));
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
        interacC.insets = new Insets(5, 20, 0, 20);

        //Si on est au tour 1 pour tout les joueurs
        if(turn / nbPlayers < 1){
            /** Creation des boutons chateau (dans gameInterac) **/
            for (int i = 0; i< nbPlayers - turn ; i++){
                interacC.gridx = 0;
                interacC.gridy = i;
                currentTilesPnl.add( createCastle() , interacC);
                KingColor playerTurnColor = this.mainFrame.getGame().getPlayerCastleTurn().getPlayerColor();
                String pName = this.mainFrame.getGame().getPlayerCastleTurn().getPlayerName();
                if (!pName.isEmpty()) {
                    tourLbl.setText("Tour de " + pName);
                }
                else
                {
                    tourLbl.setText("Tour du joueur " + playerTurnColor);
                }
                tourLbl.setForeground(GameView.getColor(playerTurnColor));

            }
        }
        else{
            int i = 0;
            for (Tile tile : mainFrame.getGame().getCurrentTiles().keySet()) {
                interacC.gridx = 0;
                interacC.gridy = i;
                //Creation d'un Jpanel qui affiche la tuile (gameController,Tile,King)
                TileView tileView = new TileView(this.mainFrame.getGameController(), tile, mainFrame.getGame().getCurrentTiles().get(tile), false );
                currentTilesPnl.add( tileView , interacC);
                i++;
                KingColor playerTurnColor = this.mainFrame.getGame().getKingTurn().getColor();
                String pName = this.mainFrame.getGame().getKingTurn().getPlayer().getPlayerName();
                if (!pName.isEmpty()) {
                    tourLbl.setText("Tour de " + pName);
                }
                else
                {
                    tourLbl.setText("Tour du joueur " + playerTurnColor);
                }
                tourLbl.setForeground( GameView.getColor( playerTurnColor ) );
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
            choosenTilePnl.add(new TileView( this.mainFrame.getGameController(), currentTile, this.mainFrame.getGame().getKingTurn(), true ) );
            interacC.insets = new Insets( 0, 20, 0, 20 );

            JButton btnRotate = new JButton("PIVOTER");
            btnRotate.addActionListener(actionEvent -> {
                this.mainFrame.getGameController().rotate();
            });

            //HOVER BUTTON
            btnRotate.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnRotate.setBackground(btnHoverColor);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnRotate.setBackground(btnColor);
                }
            });
            btnRotate.setFont(FontReader.getInstance().getBookmanold().deriveFont(Font.CENTER_BASELINE).deriveFont(15f));
            btnRotate.setBackground(btnColor);
            btnRotate.setFocusPainted(false);
            btnRotate.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            btnRotate.setPreferredSize(new Dimension(90,40));
            interacC.gridx = 1;
            interacC.gridy = 0;
            choosenTilePnl.add( btnRotate, interacC );

            interacC.insets = new Insets( 0, 20, 5, 20 );
            JButton btnReverse = new JButton("INVERSER");
            btnReverse.addActionListener(actionEvent -> {
                this.mainFrame.getGameController().reverse();
            });
            //HOVER BUTTON
            btnReverse.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnReverse.setBackground(btnHoverColor);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnReverse.setBackground(btnColor);
                    //btnReverse.setBackground(UIManager.getColor("control"));
                }
            });
            btnReverse.setFocusPainted(false);
            btnReverse.setFont(FontReader.getInstance().getBookmanold().deriveFont(Font.CENTER_BASELINE).deriveFont(14f));
            btnReverse.setBackground(btnColor);
            btnReverse.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            btnReverse.setPreferredSize(new Dimension(90,40));
            interacC.gridx = 1;
            interacC.gridy = 1;
            choosenTilePnl.add( btnReverse, interacC );


            //Si le joueur ne peut pas placer sa tuile
            boolean isPlayableTurn = this.mainFrame.getGame().getKingTurn().getPlayer().getBoard().isPlayable(currentTile);
            if(!isPlayableTurn ){
                JButton btnSkipTurn = new JButton("SAUTER LE TOUR");
                btnSkipTurn.addActionListener(actionEvent -> {
                    this.mainFrame.getGameController().skipTurn();
                });
                //HOVER BUTTON
                btnSkipTurn.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        btnSkipTurn.setBackground(btnSkipHoverColor);
                    }

                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        btnSkipTurn.setBackground(btnSkipColor);
                    }
                });
                btnSkipTurn.setFocusPainted(false);
                btnSkipTurn.setFont(FontReader.getInstance().getBookmanold().deriveFont(Font.CENTER_BASELINE).deriveFont(15f));
                btnSkipTurn.setBackground( btnSkipColor );
                btnSkipTurn.setForeground( Color.white );
                btnSkipTurn.setPreferredSize( new Dimension( 200, 40 ) );
                btnSkipTurn.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                interacC.gridx = 1;
                interacC.gridy = 2;
                choosenTilePnl.add( btnSkipTurn, interacC );
            }
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
        boardsPanel.add( new PlayerBoardView( this.mainFrame.getGameController(), this.mainFrame.getGame().getPlayers().get(0), this.mainFrame.getGame(), this ) , boardC);


        boardC.gridx = 0;
        boardC.gridy = 1;
        boardsPanel.add( new PlayerBoardView( this.mainFrame.getGameController(), this.mainFrame.getGame().getPlayers().get(1), this.mainFrame.getGame(), this ), boardC);

        if( mainFrame.getGame().getNbPlayersStrat().getnbBoard() > 2 ){
            boardC.gridx = 1;
            boardC.gridy = 0;
            boardsPanel.add( new PlayerBoardView( this.mainFrame.getGameController(), this.mainFrame.getGame().getPlayers().get(2), this.mainFrame.getGame(), this ), boardC);
            if( mainFrame.getGame().getNbPlayersStrat().getnbBoard() > 3 ){
                boardC.gridx = 1;
                boardC.gridy = 1;
                boardsPanel.add( new PlayerBoardView( this.mainFrame.getGameController(), this.mainFrame.getGame().getPlayers().get(3), this.mainFrame.getGame(), this ), boardC);
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

    ;

    public static Color getColor(KingColor color){
        switch (color) {
            case PINK -> {
                return new Color( 192, 2, 169 );
            }
            case BLUE -> {
                return new Color( 2, 148, 192 );
            }
            case GREEN -> {
                return new Color( 3, 166, 10 );
            }
            case YELLOW -> {
                return new Color( 178, 173, 19 );
            }
        }
        return Color.DARK_GRAY;
    }


}
