package Model;

import java.util.ArrayList;

public class Player
{
    private KingColor playerColor;
    private ArrayList<King> playerKings;
    private PlayerBoard board;

    public Player(KingColor color, ArrayList<King> kings, PlayerBoard board)
    {
        this.playerColor = color;
        this.playerKings = kings;
        this.board = board;
    }

    public KingColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(KingColor playerColor) {
        this.playerColor = playerColor;
    }

    public ArrayList<King> getPlayerKings() {
        return playerKings;
    }

    public void setPlayerKings(ArrayList<King> playerKings) {
        this.playerKings = playerKings;
    }

    public PlayerBoard getBoard() {
        return board;
    }

    public void setBoard(PlayerBoard board) {
        this.board = board;
    }
}
