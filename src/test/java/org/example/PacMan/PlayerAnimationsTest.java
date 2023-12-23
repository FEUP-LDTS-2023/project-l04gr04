package org.example.PacMan;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class PlayerAnimationsTest {
    private Player player;
    @BeforeEach
    public void setPlayer() {
        player = new Player(74, 42);
    }
    @Test
    public void testPacmanAnimationUp() {
        player.setFacingDirection("up");
        player.draw(mock(TextGraphics.class));

        char[][] pacManUp1 = new char[][]{
                {'#','#','#','#','#','#','#','#','#','#','#','#','#','#'},
                {'#','#',' ',' ','#','#','#','#','#','#',' ',' ','#','#'},
                {'#',' ',' ',' ','#','#','#','#','#','#',' ',' ',' ','#'},
                {'#',' ',' ',' ',' ','#','#','#','#',' ',' ',' ',' ','#'},
                {' ',' ',' ',' ',' ','#','#','#','#',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ','#','#','#','#',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ','#','#',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ','#','#',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#'},
                {'#','#','#','#',' ',' ',' ',' ',' ',' ','#','#','#','#'}
        };

        char[][] actualPacManUp1 = player.getPacManUp1();

        assertEquals(pacManUp1.length, actualPacManUp1.length, "Number of rows doesn't match");
        for (int i = 0; i < pacManUp1.length; i++) {
            assertEquals(pacManUp1[i].length, actualPacManUp1[i].length, "Number of columns in row " + i + " doesn't match");
        }

        for (int i = 0; i < pacManUp1.length; i++) {
            for (int j = 0; j < pacManUp1[i].length; j++) {
                assertEquals(pacManUp1[i][j], actualPacManUp1[i][j], "Element at position (" + i + ", " + j + ") doesn't match");
            }
        }
    }
    @Test
    public void testPacmanAnimationDown() {
        player.setFacingDirection("down");
        player.draw(mock(TextGraphics.class));

        char[][] pacManDown1 = new char[][]{
                {'#','#','#','#',' ',' ',' ',' ',' ',' ','#','#','#','#'},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ','#','#',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ','#','#',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ','#','#','#','#',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ','#','#','#','#',' ',' ',' ',' ',' '},
                {'#',' ',' ',' ',' ','#','#','#','#',' ',' ',' ',' ','#'},
                {'#',' ',' ',' ','#','#','#','#','#','#',' ',' ',' ','#'},
                {'#','#',' ',' ','#','#','#','#','#','#',' ',' ','#','#'},
                {'#','#','#','#','#','#','#','#','#','#','#','#','#','#'}
        };

        char[][] actualPacManDown1 = player.getPacManDown1();

        assertEquals(pacManDown1.length, actualPacManDown1.length, "Number of rows doesn't match");
        for (int i = 0; i < pacManDown1.length; i++) {
            assertEquals(pacManDown1[i].length, actualPacManDown1[i].length, "Number of columns in row " + i + " doesn't match");
        }

        for (int i = 0; i < pacManDown1.length; i++) {
            for (int j = 0; j < pacManDown1[i].length; j++) {
                assertEquals(pacManDown1[i][j], actualPacManDown1[i][j], "Element at position (" + i + ", " + j + ") doesn't match");
            }
        }
    }
    @Test
    public void testPacmanAnimationLeft() {
        player.setFacingDirection("left");
        player.draw(mock(TextGraphics.class));

        char[][] pacManLeft1 = new char[][]{
                {'#','#','#','#',' ',' ',' ',' ',' ',' ','#','#','#','#'},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#','#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {'#','#','#','#','#','#',' ',' ',' ',' ',' ',' ',' ',' '},
                {'#','#','#','#','#','#','#','#',' ',' ',' ',' ',' ',' '},
                {'#','#','#','#','#','#','#','#',' ',' ',' ',' ',' ',' '},
                {'#','#','#','#','#','#',' ',' ',' ',' ',' ',' ',' ',' '},
                {'#','#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#'},
                {'#','#','#','#',' ',' ',' ',' ',' ',' ','#','#','#','#'}
        };

        char[][] actualPacManLeft1 = player.getPacManLeft1();

        assertEquals(pacManLeft1.length, actualPacManLeft1.length, "Number of rows doesn't match");
        for (int i = 0; i < pacManLeft1.length; i++) {
            assertEquals(pacManLeft1[i].length, actualPacManLeft1[i].length, "Number of columns in row " + i + " doesn't match");
        }

        for (int i = 0; i < pacManLeft1.length; i++) {
            for (int j = 0; j < pacManLeft1[i].length; j++) {
                assertEquals(pacManLeft1[i][j], actualPacManLeft1[i][j], "Element at position (" + i + ", " + j + ") doesn't match");
            }
        }
    }
    @Test
    public void testPacmanAnimationRight() {
        player.setFacingDirection("right");
        player.draw(mock(TextGraphics.class));

        char[][] pacManRight1 = new char[][]{
                {'#','#','#','#',' ',' ',' ',' ',' ',' ','#','#','#','#'},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#'},
                {' ',' ',' ',' ',' ',' ',' ',' ','#','#','#','#','#','#'},
                {' ',' ',' ',' ',' ',' ','#','#','#','#','#','#','#','#'},
                {' ',' ',' ',' ',' ',' ','#','#','#','#','#','#','#','#'},
                {' ',' ',' ',' ',' ',' ',' ',' ','#','#','#','#','#','#'},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#'},
                {'#','#','#','#',' ',' ',' ',' ',' ',' ','#','#','#','#'}
        };

        char[][] actualPacManRight1 = player.getPacManRight1();

        assertEquals(pacManRight1.length, actualPacManRight1.length, "Number of rows doesn't match");
        for (int i = 0; i < pacManRight1.length; i++) {
            assertEquals(pacManRight1[i].length, actualPacManRight1[i].length, "Number of columns in row " + i + " doesn't match");
        }

        for (int i = 0; i < pacManRight1.length; i++) {
            for (int j = 0; j < pacManRight1[i].length; j++) {
                assertEquals(pacManRight1[i][j], actualPacManRight1[i][j], "Element at position (" + i + ", " + j + ") doesn't match");
            }
        }
    }
    @Test
    public void testPacmanAnimationMouthClosed() {
        player.mouthOpen = 0;
        player.draw(mock(TextGraphics.class));

        char[][] pacManClosed = new char[][]{
                {'#','#','#','#',' ',' ',' ',' ',' ',' ','#','#','#','#'},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#'},
                {'#','#','#','#',' ',' ',' ',' ',' ',' ','#','#','#','#'}
        };

        char[][] actualPacManClosed = player.getPacManClosed();

        assertEquals(pacManClosed.length, actualPacManClosed.length, "Number of rows doesn't match");
        for (int i = 0; i < pacManClosed.length; i++) {
            assertEquals(pacManClosed[i].length, actualPacManClosed[i].length, "Number of columns in row " + i + " doesn't match");
        }

        for (int i = 0; i < pacManClosed.length; i++) {
            for (int j = 0; j < pacManClosed[i].length; j++) {
                assertEquals(pacManClosed[i][j], actualPacManClosed[i][j], "Element at position (" + i + ", " + j + ") doesn't match");
            }
        }
    }
}
