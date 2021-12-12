package Controller;
import Model.*;

import java.util.ArrayList;
import java.util.Random;


public class BoardGame
{
    /**/
    int nbKing;
    private ArrayList<Player> players;
    ArrayList<King> kingsPlayer;
    private KingColor[] allColors = KingColor.values();
    public BoardGame(int nbKing)
    {
        nbKing = nbKing;
    }

    public void createPlayer(KingColor color, ArrayList<King> kings)
    {
        //Player NewPlayer = new Player(color, kings);
        //players.add(NewPlayer);
    }

    public void initializeGame()
    {
        for (int i = 0; i<nbKing; i++)
        {
            int random = new Random().nextInt(allColors.length);
            if (allColors[random] != null) {
                KingColor color = allColors[random];
                kingsPlayer.add(new King(color));
                createPlayer(color, kingsPlayer);
                allColors[random] = null;
            }

        }
    }










}
