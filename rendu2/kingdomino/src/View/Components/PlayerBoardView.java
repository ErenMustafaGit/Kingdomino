package View.Components;

import Controller.GameController;
import Model.*;
import Utilities.FontReader;
import Utilities.IMGReader;
import View.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayerBoardView extends JPanel {

    /** Constructeur d'un plateau de joueur
     *
     * @param player : Le joueur qui va avoir son plateau d'affiché
     * @return JPanel : Rendu en panel du playerBoard
     */

    public PlayerBoardView(GameController gameController, Player player, GameContext game, GameView gameView){
        this.setLayout( new GridBagLayout() );
        this.setOpaque(false);

        PlayerBoard playerBoard = player.getBoard();
        JButton[][] boardBtns = new JButton[playerBoard.getBOARD_SIZE()][playerBoard.getBOARD_SIZE()];

        for(int j = 0; j<playerBoard.getBOARD_SIZE(); j++) {
            for (int i = 0; i < playerBoard.getBOARD_SIZE(); i++) {
                boardBtns[i][j] = new JButton();
            }
        }

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(playerBoard.getBOARD_SIZE(), playerBoard.getBOARD_SIZE()));
        //Place chaque image correspondantes
        for(int j = 0; j<playerBoard.getBOARD_SIZE(); j++){
            for(int i = 0; i<playerBoard.getBOARD_SIZE(); i++){
                JButton btn = boardBtns[i][j];
                btn.setPreferredSize(new Dimension( IMGReader.getTileImgSize() , IMGReader.getTileImgSize()));
                int finalJ = j;
                int finalI = i;

                /**Action de click sur une case**/
                btn.addActionListener(actionEvent -> {
                    //Placer le chateau seulement on est dans les 1er tours pour le joueur
                    if(game.getTurn() < game.getPlayersNb() ){
                        gameController.placeCastle(player,  finalI, finalJ);
                    }else{

                        if(!game.allTilesChoosen()){
                            gameView.modifyErrorMessage("Toutes les tuiles doivent être choisi !");
                            //errorMessage.setText("Toutes les tuiles doivent être choisi !");
                        }else{
                            if(!gameController.placeTile( finalI, finalJ)){
                                gameView.modifyErrorMessage("Cette tuile ne peut pas être placé ici !");
                                //errorMessage.setText("Cette tuile ne peut pas être placé ici !");
                            }
                        }
                    }
                });

                //Si c'est le tour de poser le chateau
                if(game.getTurn() < game.getPlayersNb() ){
                    btn.setRolloverEnabled(false);
                    if(player != game.getPlayerCastleTurn()){ //Si c'est pas son tour
                        btn.setEnabled(false);
                    }
                    else{
                        btn.setRolloverEnabled(true);
                    }
                }else{
                    btn.setRolloverEnabled(false);
                    if(player != game.getKingTurn().getPlayer()){ //Si c'est pas son tour
                        btn.setEnabled(false);
                    }else{
                        btn.setRolloverEnabled(true);
                    }
                }

                //Met l'image correspondant à la couleur de la case
                if(playerBoard.getPositionnable(i,j) == null){
                    btn.setIcon( IMGReader.getImage("empty.png") );
                }else{
                    btn.setIcon( IMGReader.getImage( playerBoard.getPositionnable(i,j).getColor(), playerBoard.getPositionnable(i,j).getCrownNumber()) );
                }
                grid.add(btn);


                /** Hover effect **/
                //Si on est en tour chateau
                if(game.getTurn() < game.getPlayersNb() ){
                    btn.setRolloverIcon( IMGReader.getImage(GroundColor.GREY, 0)   );
                }
                else if(game.allTilesChoosen() && btn.isEnabled()){
                    Tile choosenTile = game.getKingTurn().getTile();
                    final GroundColor leftColor = choosenTile.getLeft().getColor();
                    final GroundColor rightColor = choosenTile.getRight().getColor();
                    final int leftCrown = choosenTile.getLeft().getCrownNumber();
                    final int rightCrown = choosenTile.getRight().getCrownNumber();
                    final int[] xyRight = playerBoard.getRightXY(finalI, finalJ,choosenTile.getDirection() );
                    btn.addMouseListener(new MouseAdapter() {

                        public void mouseEntered(MouseEvent evt) {

                            if(playerBoard.isPosable(finalI, finalJ) && playerBoard.isPosable(xyRight[0], xyRight[1])  ){
                                btn.setIcon( IMGReader.getImage( leftColor,leftCrown ));
                                boardBtns[xyRight[0]][xyRight[1]].setIcon( IMGReader.getImage( rightColor, rightCrown  ) );
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
        c.insets = new Insets(0, 20, 0, 20);

        MyLabel colorLbl;
        if (!player.getPlayerName().isEmpty())
        {
            colorLbl = new MyLabel(player.getPlayerName(), SwingConstants.CENTER);
        }
        else
        {
            colorLbl = new MyLabel("Joueur " + player.getPlayerColor(), SwingConstants.CENTER);

        }
        colorLbl.setFont(FontReader.getInstance().getShowcard().deriveFont(Font.BOLD).deriveFont(22f));
        colorLbl.setOutlineColor(Color.DARK_GRAY);
        colorLbl.setStroke(new BasicStroke(3f));
        colorLbl.setForeground( GameView.getColor( player.getPlayerColor() ) );
        c.gridx = 0;
        c.gridy = 0;
        this.add( colorLbl, c );

        c.gridx = 0;
        c.gridy = 1;
        this.add( grid, c );
    }



    public PlayerBoardView(Player player, GameContext game){
        this.setLayout( new GridBagLayout() );
        this.setOpaque(false);

        PlayerBoard playerBoard = player.getBoard();
        JButton[][] boardBtns = new JButton[playerBoard.getBOARD_SIZE()][playerBoard.getBOARD_SIZE()];

        for(int j = 0; j<playerBoard.getBOARD_SIZE(); j++) {
            for (int i = 0; i < playerBoard.getBOARD_SIZE(); i++) {
                boardBtns[i][j] = new JButton();
            }
        }

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(playerBoard.getBOARD_SIZE(), playerBoard.getBOARD_SIZE()));
        //Place chaque image correspondantes
        for(int j = 0; j<playerBoard.getBOARD_SIZE(); j++){
            for(int i = 0; i<playerBoard.getBOARD_SIZE(); i++){
                JButton btn = boardBtns[i][j];
                btn.setPreferredSize(new Dimension( IMGReader.getTileImgSize() , IMGReader.getTileImgSize()));
                btn.setRolloverEnabled(false);

                //Met l'image correspondant à la couleur de la case
                if(playerBoard.getPositionnable(i,j) == null){
                    btn.setIcon( IMGReader.getImage("empty.png") );
                }else{
                    btn.setIcon( IMGReader.getImage( playerBoard.getPositionnable(i,j).getColor(), playerBoard.getPositionnable(i,j).getCrownNumber()) );
                }
                grid.add(btn);

            }
        }

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0, 20, 0, 20);

        MyLabel colorLbl;
        if (!player.getPlayerName().isEmpty())
        {
            colorLbl = new MyLabel(player.getPlayerName(), SwingConstants.CENTER);
        }
        else
        {
            colorLbl = new MyLabel("Joueur " + player.getPlayerColor(), SwingConstants.CENTER);

        }
        colorLbl.setFont(FontReader.getInstance().getShowcard().deriveFont(Font.BOLD).deriveFont(22f));
        colorLbl.setOutlineColor(Color.DARK_GRAY);
        colorLbl.setStroke(new BasicStroke(3f));
        colorLbl.setForeground( GameView.getColor( player.getPlayerColor() ) );
        c.gridx = 0;
        c.gridy = 0;
        this.add( colorLbl, c );

        c.gridx = 0;
        c.gridy = 1;
        this.add( grid, c );
    }
}
