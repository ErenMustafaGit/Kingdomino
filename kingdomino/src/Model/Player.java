package Model;

import java.util.ArrayList;

public class Player
{
    public KingColor playerColor;
    public ArrayList<King> playerKings;
    public PlayerBoard board;
    public Player(KingColor color, ArrayList<King> kings, PlayerBoard board)
    {
        playerColor = color;
        playerKings = kings;
    }



}
