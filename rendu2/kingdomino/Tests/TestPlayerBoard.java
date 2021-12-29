
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
            assertEquals( gLeft, playerBoard.getPositionnable(2, 3)  );
            assertEquals( gRight ,playerBoard.getPositionnable(3, 3) );

            assertTrue( playerBoard.setTile(1, 2, Direction.NORTH, tile) );
            assertEquals(gLeft, playerBoard.getPositionnable(1, 2)  );
            assertEquals(gRight, playerBoard.getPositionnable(1, 1)  );
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
            assertEquals(gLeft, playerBoard.getPositionnable(4, 3) );
            assertEquals(gRight, playerBoard.getPositionnable(4, 4) );
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

            assertFalse( playerBoard.setTile(4, 3, Direction.SOUTH, wheat) );
            assertNull( playerBoard.getPositionnable(4, 3)  );
            assertNull(playerBoard.getPositionnable(4, 4)  );
        }
    }

    @DisplayName("Test d'un plateau rempli")
    @Nested
    public class FullPlayerBoard
    {
        Castle castle;
        NormalMode normalMode;
        Player p;
        @BeforeEach
        public void setUp()
        {
            castle = new Castle();
            playerBoard = new PlayerBoard(castle);
            p = new Player(KingColor.BLUE,  playerBoard);

            normalMode = new NormalMode();


            Ground yellow = new Ground( GroundColor.YELLOW , 1);
            Ground blue = new Ground( GroundColor.BLUE , 2);
            Ground blue1 = new Ground(GroundColor.BLUE, 1);
            Ground black = new Ground( GroundColor.BLACK, 0 );
            Ground light = new Ground( GroundColor.LIGHT_GREEN, 0 );
            Ground dark = new Ground( GroundColor.DARK_GREEN, 0 );
            Ground brown = new Ground( GroundColor.BROWN, 0 );


            Tile lb = new Tile(1,light, blue1);
            Tile blbr = new Tile(2,blue1, brown);
            Tile ll = new Tile(3,light, light);
            Tile ly = new Tile(4,light, yellow);
            Tile bdark = new Tile(4,blue, black);
            Tile yb = new Tile(1,yellow, blue);
            Tile ybr = new Tile(3,yellow, brown);
            Tile brbr = new Tile(2,brown, brown);




            /********TABLEAU SUR FIGMA*********/

            playerBoard.setTile( 3, 2, Direction.EAST, lb );
            playerBoard.setTile( 4, 3, Direction.SOUTH, blbr );
            playerBoard.setTile( 2, 3, Direction.EAST, ll );
            playerBoard.setTile( 3, 1, Direction.NORTH, ly );
            playerBoard.setTile( 0, 2, Direction.EAST, bdark );
            playerBoard.setTile( 0, 3, Direction.SOUTH, blbr );
            playerBoard.setTile( 3, 4, Direction.WEST, lb );
            playerBoard.setTile( 1, 4, Direction.NORTH, yb );
            playerBoard.setTile( 2, 1, Direction.WEST, ybr );
            playerBoard.setTile( 0, 0 , Direction.EAST, brbr );
            playerBoard.setTile( 4, 1 , Direction.NORTH, blbr );
        }

        @Test
        @DisplayName("Toutes les cases sauf le (0,1) & (2,0) sont non-null")
        public void testEstNull()
        {
            assertNull(playerBoard.getPositionnable(0,1));
            for (int i = 0; i< playerBoard.BOARD_SIZE; i++) {
                for (int j = 0; j < playerBoard.BOARD_SIZE; j++) {
                    if(i == 1 && j == 0 || i == 0 && j== 2){
                        assertNull(playerBoard.getPositionnable(j,i));
                    }else{
                        assertNotNull(playerBoard.getPositionnable(j,i));
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

        @Test
        @DisplayName("La party a bien le nombre de point attendu en mode normal")
        public void testNormalPoint()
        {
            assertEquals( 12, normalMode.calculateScore(playerBoard)  );
        }
    }

}





