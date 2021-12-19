package Controller;
import Model.*;

import java.util.ArrayList;
import java.util.Random;


public class GameController
{
    /**/
    private GameContext game;

    public GameController(GameContext game)
    {
        this.game = game;
    }

    public void initializeGame( int nbPlayers, int gameMode ) {

        this.game.setPlayerStrategy(nbPlayers);
        this.game.setGameStrategy(gameMode);
        this.game.initGame();
        /*
        if (strategy.getNbPlayersStrat() instanceof FourPlayers) {
            for (int i = 0; i < ( strategy.getNbPlayersStrat()).getnbBoard(); i++) // boucle sur les 4 rois différents de la partie
            {
                int random = new Random().nextInt(allColors.length); // sélection aléatoire de la couleur à assigner au joueur
                if (allColors[random] != null) //teste de la disponibilité de la couleur choisi
                {
                    KingColor color = allColors[random]; // création d'une variable contenant la couleur
                    Castle pCastle = new Castle();
                    createKing(color); //création du roi correspondant à la couleur
                    PlayerBoard PBoard = new PlayerBoard(pCastle); // création d'un plateau de jeu pour un joueur
                    createPlayer(color, kingsPlayer, PBoard); // création du joueur avec la couleur, le roi et le plateau
                    allColors[random] = null; // élimination de la couleur du tableau des couleurs disponible
                }
                else
                {
                    i = i-1;
                }
            }
        }
        else if(strategy.getNbPlayersStrat() instanceof ThreePlayers)
        {
            for (int i = 0; i < (strategy.getNbPlayersStrat()).getnbBoard(); i++) // boucle sur les 3 rois différents de la partie
            {
                int random = new Random().nextInt(allColors.length); // sélection aléatoire de la couleur à assigner au joueur
                if (allColors[random] != null) //teste de la disponibilité de la couleur choisi
                {
                    KingColor color = allColors[random]; // création d'une variable contenant la couleur
                    createKing(color); //création du roi correspondant à la couleur
                    PlayerBoard PBoard = new PlayerBoard(); // création d'un plateau de jeu pour un joueur
                    createPlayer(color, kingsPlayer, PBoard); // création du joueur avec la couleur, le roi et le plateau
                    allColors[random] = null; // élimination de la couleur du tableau des couleurs disponible
                }
                else
                {
                    i = i-1;
                }
            }
        }
        else if (strategy.getNbPlayersStrat() instanceof TwoPlayers)
        {
            for (int i = 0; i < ( strategy.getNbPlayersStrat()).getnbBoard()/2; i++) // boucle sur les 4 rois différents de la partie
            {
                int random = new Random().nextInt(allColors.length); // sélection aléatoire de la couleur à assigner au joueur
                if (allColors[random] != null) //teste de la disponibilité de la couleur choisi
                {
                    KingColor color = allColors[random]; // création d'une variable contenant la couleur
                    createKing(color); //création du roi correspondant à la couleur
                    createKing(color); //création du deuxième roi correspondant à la couleur
                    PlayerBoard PBoard = new PlayerBoard(); // création d'un plateau de jeu pour un joueur
                    createPlayer(color, kingsPlayer, PBoard); // création du joueur avec la couleur, le roi et le plateau
                    allColors[random] = null; // élimination de la couleur du tableau des couleurs disponible
                }
                else
                {
                    i = i-1;
                }
            }
        }*/

    }


    public void placeTile(Player player, Tile tile, int pos_x, int pos_y, Direction dir)
    {
        player.getBoard().setTile(pos_x, pos_y, dir, tile);
    }

    public void newRound()
    {

    }










}
