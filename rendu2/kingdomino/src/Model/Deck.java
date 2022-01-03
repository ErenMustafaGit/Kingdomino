package Model;

import Utilities.CSVReader;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {

    private Stack<Tile> tiles = new Stack();

    public Deck(){

    }
    public Deck(int deckSize){
        List<List<String>> lines = CSVReader.read("kingdomino.csv");
        lines.remove(0);
        for(List<String> line : lines  ){
            int tileNumber = Integer.parseInt(line.get(0));
            int leftCrown = Integer.parseInt(line.get(5));
            int rightCrown = Integer.parseInt(line.get(6));
            GroundColor leftColor =  GroundColor.valueOf ( line.get(2).toUpperCase() );
            GroundColor rightColor = GroundColor.valueOf( line.get(4).toUpperCase() );
            Ground left = new Ground(leftColor, leftCrown);
            Ground right = new Ground(rightColor, rightCrown);
            Tile tile = new Tile( tileNumber , left, right  );
            tiles.add(tile);
        }
        Collections.shuffle(tiles);
        int i = 0;
        while (tiles.size() > deckSize){
            tiles.remove(i);
        }
    }

    public Tile getTile(){
        return this.tiles.pop();
    }
    public int getNbTiles(){
        return this.tiles.size();
    }
}
