package Model;

import java.util.ArrayList;

public class Deck {

    ArrayList<Tile> tiles = new ArrayList<>();

    public Deck(){

    }
    public Deck(int deckSize){
        for(int i = 1; i<=deckSize;i++){
            Tile tile = new Tile(i);
            tiles.add(tile);
        }
    }
}
