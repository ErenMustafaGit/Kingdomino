package Model;

import java.util.ArrayList;

public class Player
{
    private KingColor playerColor;
    private PlayerBoard board;

    public Player(KingColor color, PlayerBoard board)
    {
        this.playerColor = color;
        this.board = board;
    }



    public KingColor getPlayerColor() {
        return playerColor;
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
