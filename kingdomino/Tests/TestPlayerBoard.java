
import static org.junit.jupiter.api.Assertions.*;

import Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


@DisplayName("TestPlayerBoard: Tests de plateau de joueur")

public class TestPlayerBoard {
    private static PlayerBoard playerBoard;

    @DisplayName("Test d'un plateau vide")
    @Nested
    public class EmptyPlayerBoard
    {
        @BeforeEach
        public void setUp()
        {
            playerBoard = new PlayerBoard();
        }

        @Test
        @DisplayName("Toutes les cases sont null")
        public void testEstNull()
        {
            for (int i = 0; i< playerBoard.BOARD_SIZE; i++) {
                for (int j = 0; j < playerBoard.BOARD_SIZE; j++) {
                    assertNull( playerBoard.getPositionnable(i,j));
                }
            }
        }


    }


    @DisplayName("Test d'un plateau avec uniquement un chateau")
    @Nested
    public class CastlePlayerBoard
    {
        Castle castle;
        @BeforeEach
        public void setUp()
        {
            castle = new Castle();
            playerBoard = new PlayerBoard(castle);
        }

        @Test
        @DisplayName("Toutes les cases sauf le chateau sont null")
        public void testEstNull()
        {
            for (int i = 0; i< playerBoard.BOARD_SIZE; i++) {
                for (int j = 0; j < playerBoard.BOARD_SIZE; j++) {
                    if(i != playerBoard.BOARD_SIZE/2 && j != playerBoard.BOARD_SIZE/2){
                        assertNull( playerBoard.getPositionnable(i,j));
                    }
                }
            }
        }

        @Test
        @DisplayName("La chateau se trouve au milieu")
        public void testCenterCastle()
        {
            assertEquals( castle, playerBoard.getPositionnable(playerBoard.BOARD_SIZE/2, playerBoard.BOARD_SIZE/2 ) );
        }
    }

    @DisplayName("Test de placer des tuiles dans le plateau")
    @Nested
    public class PlayingPlayerBoard
    {
        Castle castle;
        @BeforeEach
        public void setUp()
        {
            castle = new Castle();
            playerBoard = new PlayerBoard(castle);
        }

        @Test
        @DisplayName("Positionné des tuiles autour du chateau")
        public void testAroundCastle()
        {
            Ground gLeft = new Ground(GroundColor.BLUE, 0);
            Ground gRight = new Ground(GroundColor.BLUE, 0);
            Tile tile = new Tile(1, gLeft, gRight );

            assertTrue( playerBoard.setTile(2, 3, Direction.EAST, tile) );
            assertEquals( playerBoard.getPositionnable(2, 3), gLeft  );
            assertEquals( playerBoard.getPositionnable(3, 3), gRight  );

            assertTrue( playerBoard.setTile(1, 2, Direction.NORTH, tile) );
            assertEquals( playerBoard.getPositionnable(1, 2), gLeft  );
            assertEquals( playerBoard.getPositionnable(1, 1), gRight  );
        }

        @Test
        @DisplayName("Posé des tuiles à coté de son même terrain")
        public void testAroundSameGround()
        {
            Ground gLeft = new Ground(GroundColor.BLUE, 0);
            Ground gRight = new Ground(GroundColor.BLUE, 0);
            Tile tile = new Tile(1, gLeft, gRight );

            assertTrue( playerBoard.setTile(2, 3, Direction.EAST, tile) );

            assertTrue( playerBoard.setTile(4, 3, Direction.SOUTH, tile) );
            assertEquals( playerBoard.getPositionnable(4, 3), gLeft  );
            assertEquals( playerBoard.getPositionnable(4, 4), gRight  );
        }

        @Test
        @DisplayName("Posé des tuiles à coté d'une tuile qui n'a pas le meme terrain")
        public void testAroundNotSameGround()
        {
            Ground bLeft = new Ground(GroundColor.BLUE, 0);
            Ground bRight = new Ground(GroundColor.BLUE, 0);
            Tile water = new Tile(1, bLeft, bRight );

            assertTrue( playerBoard.setTile(2, 3, Direction.EAST, water) );

            Ground yLeft = new Ground(GroundColor.YELLOW, 0);
            Ground yRight = new Ground(GroundColor.YELLOW, 0);
            Tile wheat = new Tile(1, yLeft, yRight );

            assertTrue( playerBoard.setTile(4, 3, Direction.SOUTH, wheat) );
            assertEquals( playerBoard.getPositionnable(4, 3), yLeft  );
            assertEquals( playerBoard.getPositionnable(4, 4), yRight  );
        }
    }

    @DisplayName("Test d'un plateau rempli")
    @Nested
    public class FullPlayerBoard
    {
        Castle castle;
        @BeforeEach
        public void setUp()
        {
            castle = new Castle();
            playerBoard = new PlayerBoard(castle);

            Ground yellow = new Ground( GroundColor.YELLOW , 1);
            Ground blue = new Ground( GroundColor.BLUE , 2);
            Ground dark = new Ground( GroundColor.BLACK, 0 );
            Ground light = new Ground( GroundColor.LIGHT_GREEN, 0 );

            Tile by = new Tile(1,yellow, blue);
            Tile db = new Tile(1,dark, blue);
            Tile dy = new Tile(1,dark, yellow);
            Tile ly = new Tile(1,light, yellow);
            Tile ld = new Tile(1,light, dark);
            Tile lb = new Tile(1,light, blue);

            /********TABLEAU SUR FIGMA*********/
            //playerBoard.setTile()
        }

        @Test
        @DisplayName("Toutes les cases sauf le chateau sont null")
        public void testEstNull()
        {
            for (int i = 0; i< playerBoard.BOARD_SIZE; i++) {
                for (int j = 0; j < playerBoard.BOARD_SIZE; j++) {
                    if(i != playerBoard.BOARD_SIZE/2 && j != playerBoard.BOARD_SIZE/2){
                        assertNull( playerBoard.getPositionnable(i,j));
                    }
                }
            }
        }

        @Test
        @DisplayName("La chateau se trouve au milieu")
        public void testCenterCastle()
        {
            assertEquals( castle, playerBoard.getPositionnable(playerBoard.BOARD_SIZE/2, playerBoard.BOARD_SIZE/2 ) );
        }
    }

}





