package Model;

import java.util.ArrayList;

public class Player
{
    private KingColor playerColor;
    private PlayerBoard board;
    private String playerName;

    public Player(KingColor color, String pName, PlayerBoard board)
    {
        this.playerColor = color;
        this.board = board;
        this.playerName = pName;
    }



    public KingColor getPlayerColor() {
        return playerColor;
    }

    public String getPlayerName()
    {
        return this.playerName;
    }

    public void setPlayerColor(KingColor playerColor) {
        this.playerColor = playerColor;
    }





    public PlayerBoard getBoard() {
        return board;
    }

    public void setBoard(PlayerBoard board) {
        this.board = board;
    }
}
