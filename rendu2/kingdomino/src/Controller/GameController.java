package Controller;
import Model.*;

import java.util.ArrayList;
import java.util.Random;


public class GameController
{
    /**/
    private GameContext game;

    public GameController(GameContext game)
    {
        this.game = game;
    }

    public void initializeGame( int nbPlayers, ArrayList<KingColor> colors, int gameMode ) {
        this.game.setPlayerStrategy(nbPlayers);
        this.game.setGameStrategy(gameMode);
        this.game.initGame(colors);
    }

    public void placeCastle(Player player, int pos_x, int pos_y)
    {
        game.setCastle(player, pos_x, pos_y);
    }

    public boolean placeTile(int pos_x, int pos_y)
    {
        return game.setTile(pos_x, pos_y);
    }

    public void pickTiles()
    {
        game.pickTiles();
    }


    public void chooseTile(Tile tile) {
        game.chooseTile(tile);
    }

    public void rotate() {
        game.rotateCurrentTile();

    }

    public void reverse() {
        game.reverseCurrentTile();
    }
}
