package Model;

import java.util.ArrayList;

public class Player
{
    private KingColor playerColor;
    private ArrayList<King> playerKings;
    private PlayerBoard board;
    private ArrayList<Tile> choosenTile = new ArrayList<>();

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

    public void setChoosenTile(Tile tile){
        this.choosenTile.add(tile);
    }

    public void removeTile(){
        this.choosenTile.remove(0);
    }

    public Tile getTile(){
        System.out.println( this + " " + this.choosenTile );
        return this.choosenTile.get(0);
    }

    public PlayerBoard getBoard() {
        return board;
    }

    public void setBoard(PlayerBoard board) {
        this.board = board;
    }
}
