package Controller;
import Model.*;

import java.util.ArrayList;
import java.util.Random;


public class BoardGame
{
    /**/
    private ArrayList<Player> players;
    ArrayList<King> kingsPlayer;
    private KingColor[] allColors = KingColor.values();
    private GameContext strategy; // les stratégie utilisé

    public BoardGame()
    {
    }

    public BoardGame(int nbKing, GameContext strat)
    {
        strategy = strat;
    }

    public void createPlayer(KingColor color, ArrayList<King> kings, PlayerBoard board)
    {
        Player NewPlayer = new Player(color, kings, board);
        players.add(NewPlayer);
    }

    public void createKing(KingColor color)
    {
        King newKing = new King(color);
        kingsPlayer.add(newKing);
    }

    public void initializeGame() throws InstantiationException {
        if (strategy.nbPlayersStrat instanceof FourPlayers) {
            for (int i = 0; i < ( strategy.nbPlayersStrat).getnbBoard(); i++) // boucle sur les 4 rois différents de la partie
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
        else if(strategy.nbPlayersStrat instanceof ThreePlayers)
        {
            for (int i = 0; i < (strategy.nbPlayersStrat).getnbBoard(); i++) // boucle sur les 3 rois différents de la partie
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
        else if (strategy.nbPlayersStrat instanceof TwoPlayers)
        {
            for (int i = 0; i < ( strategy.nbPlayersStrat).getnbBoard()/2; i++) // boucle sur les 4 rois différents de la partie
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
        }
        else
        {
                throw new InstantiationException("Stratégie inexistante");
        }
    }


    public void placeTile(Player player, Tile tile, int pos_x, int pos_y, Direction dir)
    {
        player.board.setTile(pos_x, pos_y, dir, tile);
    }

    public void newRound()
    {

    }










}
