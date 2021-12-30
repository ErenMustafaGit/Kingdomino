package Model;

import java.util.ArrayList;

public class King
{
    private KingColor color;
    private Player player;
    private ArrayList<Tile> choosenTile = new ArrayList<>();



    public King(KingColor color, Player player)
    {
        this.color = color;
        this.player = player;
    }

    public KingColor getColor(){
        return this.color;
    }

    public void setChoosenTile(Tile tile){
        this.choosenTile.add(tile);
    }

    public void removeTile(){
        this.choosenTile.remove(0);
    }

    public Tile getTile(){
        if(this.choosenTile.isEmpty()){
            //Renvoie une tuile null
            return new Tile( 0,new Ground(null, 0 ), new Ground( null, 0 ) );
        }
        return this.choosenTile.get(0);

    }
    public Player getPlayer(){
        return this.player;
    }
}
