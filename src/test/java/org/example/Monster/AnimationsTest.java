package org.example.Monster;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import org.example.Game;
import org.example.Level;
import org.example.Monster.States.fright;
import org.example.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimationsTest {
    private Terminal terminalMock;
    private TextGraphics graphicsMock;
    private Game gameMock;
    private Level levelMock;
    private Player player;
    private RedMonster redmonster;

    @BeforeEach
    public void setGame() throws IOException, FontFormatException {
        gameMock = new Game(220,270, terminalMock, levelMock, graphicsMock);
        gameMock.initialize();
    }

    @BeforeEach
    public void setPlayer() {
        player = new Player(74, 42);
    }

    @BeforeEach
    public void setMonster() {
        redmonster = new RedMonster(75, 42);
    }

    // Player animations
    @Test
    public void testPacmanAnimationUp() {
        player.setFacingDirection("up");
        player.draw(graphicsMock);

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

        char[][] actualPacManUp1 = player.pacManUp1;

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
        player.draw(graphicsMock);

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

        char[][] actualPacManDown1 = player.pacManDown1;

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
        player.draw(graphicsMock);

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

        char[][] actualPacManLeft1 = player.pacManLeft1;

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
        player.draw(graphicsMock);

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

        char[][] actualPacManRight1 = player.pacManRight1;

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
        player.draw(graphicsMock);

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

        char[][] actualPacManClosed = player.pacManClosed;

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

    // Monster animations
    @Test
    public void testMonsterAnimationLeft() {
        redmonster.setMovingDirection("left");
        redmonster.draw(graphicsMock);

        char[][] monsterLeft1 = new char[][]{
                {'#','#','#','#','#',' ',' ',' ',' ','#','#','#','#','#'},
                {'#','#','#',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#'},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#'},
                {'#',' ','W','W',' ',' ',' ',' ','W','W',' ',' ',' ','#'},
                {'#','W','W','W','W',' ',' ','W','W','W','W',' ',' ','#'},
                {'#','0','0','W','W',' ',' ','0','0','W','W',' ',' ','#'},
                {' ','0','0','W','W',' ',' ','0','0','W','W',' ',' ',' '},
                {' ',' ','W','W',' ',' ',' ',' ','W','W',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ','#',' ',' ',' ','#','#',' ',' ',' ','#',' ',' '},
                {' ','#','#','#',' ',' ','#','#',' ',' ','#','#','#',' '}
        };

        char[][] actualMonsterLeft1 = redmonster.monsterLeft1;

        assertEquals(monsterLeft1.length, actualMonsterLeft1.length, "Number of rows doesn't match");
        for (int i = 0; i < monsterLeft1.length; i++) {
            assertEquals(monsterLeft1[i].length, actualMonsterLeft1[i].length, "Number of columns in row " + i + " doesn't match");
        }

        for (int i = 0; i < monsterLeft1.length; i++) {
            for (int j = 0; j < monsterLeft1[i].length; j++) {
                assertEquals(monsterLeft1[i][j], actualMonsterLeft1[i][j], "Element at position (" + i + ", " + j + ") doesn't match");
            }
        }
    }
    @Test
    public void testMonsterAnimationRight() {
        redmonster.setMovingDirection("right");
        redmonster.draw(graphicsMock);

        char[][] monsterRight1 = new char[][]{
                {'#','#','#','#','#',' ',' ',' ',' ','#','#','#','#','#'},
                {'#','#','#',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#'},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#'},
                {'#',' ',' ',' ','W','W',' ',' ',' ',' ','W','W',' ','#'},
                {'#',' ',' ','W','W','W','W',' ',' ','W','W','W','W','#'},
                {'#',' ',' ','W','W','0','0',' ',' ','W','W','0','0','#'},
                {' ',' ',' ','W','W','0','0',' ',' ','W','W','0','0',' '},
                {' ',' ',' ',' ','W','W',' ',' ',' ',' ','W','W',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ','#',' ',' ',' ','#','#',' ',' ',' ','#',' ',' '},
                {' ','#','#','#',' ',' ','#','#',' ',' ','#','#','#',' '}
        };

        char[][] actualMonsterRight1 = redmonster.monsterRight1;

        assertEquals(monsterRight1.length, actualMonsterRight1.length, "Number of rows doesn't match");
        for (int i = 0; i < monsterRight1.length; i++) {
            assertEquals(monsterRight1[i].length, actualMonsterRight1[i].length, "Number of columns in row " + i + " doesn't match");
        }

        for (int i = 0; i < monsterRight1.length; i++) {
            for (int j = 0; j < monsterRight1[i].length; j++) {
                assertEquals(monsterRight1[i][j], actualMonsterRight1[i][j], "Element at position (" + i + ", " + j + ") doesn't match");
            }
        }
    }
    @Test
    public void testMonsterAnimationUp() {
        redmonster.setMovingDirection("up");
        redmonster.draw(graphicsMock);

        char[][] monsterUp1 = new char[][]{

                {'#','#','#','#','#',' ',' ',' ',' ','#','#','#','#','#'},
                {'#','#','#','0','0',' ',' ',' ',' ','0','0','#','#','#'},
                {'#','#','W','0','0','W',' ',' ','W','0','0','W','#','#'},
                {'#','#','W','W','W','W',' ',' ','W','W','W','W','#','#'},
                {'#',' ','W','W','W','W',' ',' ','W','W','W','W',' ','#'},
                {'#',' ',' ','W','W',' ',' ',' ',' ','W','W',' ',' ','#'},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ','#',' ',' ',' ','#','#',' ',' ',' ','#',' ',' '},
                {' ','#','#','#',' ',' ','#','#',' ',' ','#','#','#',' '}
        };

        char[][] actualMonsterUp1 = redmonster.monsterUp1;

        assertEquals(monsterUp1.length, actualMonsterUp1.length, "Number of rows doesn't match");
        for (int i = 0; i < monsterUp1.length; i++) {
            assertEquals(monsterUp1[i].length, actualMonsterUp1[i].length, "Number of columns in row " + i + " doesn't match");
        }

        for (int i = 0; i < monsterUp1.length; i++) {
            for (int j = 0; j < monsterUp1[i].length; j++) {
                assertEquals(monsterUp1[i][j], actualMonsterUp1[i][j], "Element at position (" + i + ", " + j + ") doesn't match");
            }
        }
    }
    @Test
    public void testMonsterAnimationDown() {
        redmonster.setMovingDirection("Down");
        redmonster.draw(graphicsMock);

        char[][] monsterDown1 = new char[][]{
                {'#','#','#','#','#',' ',' ',' ',' ','#','#','#','#','#',},
                {'#','#','#',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#',},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#',},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#',},
                {'#',' ',' ','W','W',' ',' ',' ',' ','W','W',' ',' ','#',},
                {'#',' ','W','W','W','W',' ',' ','W','W','W','W',' ','#',},
                {' ',' ','W','W','W','W',' ',' ','W','W','W','W',' ',' ',},
                {' ',' ','W','0','0','W',' ',' ','W','0','0','W',' ',' ',},
                {' ',' ',' ','0','0',' ',' ',' ',' ','0','0',' ',' ',' ',},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ','#',' ',' ',' ','#','#',' ',' ',' ','#',' ',' '},
                {' ','#','#','#',' ',' ','#','#',' ',' ','#','#','#',' '}

        };

        char[][] actualMonsterDown1 = redmonster.monsterDown1;

        assertEquals(monsterDown1.length, actualMonsterDown1.length, "Number of rows doesn't match");
        for (int i = 0; i < monsterDown1.length; i++) {
            assertEquals(monsterDown1[i].length, actualMonsterDown1[i].length, "Number of columns in row " + i + " doesn't match");
        }

        for (int i = 0; i < monsterDown1.length; i++) {
            for (int j = 0; j < monsterDown1[i].length; j++) {
                assertEquals(monsterDown1[i][j], actualMonsterDown1[i][j], "Element at position (" + i + ", " + j + ") doesn't match");
            }
        }
    }
    @Test
    public void testMonsterAnimationRun() {
        redmonster.ms = new fright(redmonster);
        redmonster.draw(graphicsMock);

        char[][] monsterRun1 = new char[][]{
                {'#','#','#','#','#',' ',' ',' ',' ','#','#','#','#','#',},
                {'#','#','#',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#',},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#',},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#',},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#',},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#',},
                {' ',' ',' ',' ','W','W',' ',' ','W','W',' ',' ',' ',' ',},
                {' ',' ',' ',' ','W','W',' ',' ','W','W',' ',' ',' ',' ',},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',},
                {' ',' ',' ',' ','W',' ','W',' ','W',' ','W',' ',' ',' ',},
                {' ',' ',' ','W',' ','W',' ','W',' ','W',' ',' ',' ',' ',},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ','#',' ',' ',' ','#','#',' ',' ',' ','#',' ',' '},
                {' ','#','#','#',' ',' ','#','#',' ',' ','#','#','#',' '}
        };

        char[][] actualMonsterRun1 = redmonster.monsterRun1;

        assertEquals(monsterRun1.length, actualMonsterRun1.length, "Number of rows doesn't match");
        for (int i = 0; i < monsterRun1.length; i++) {
            assertEquals(monsterRun1[i].length, actualMonsterRun1[i].length, "Number of columns in row " + i + " doesn't match");
        }

        for (int i = 0; i < monsterRun1.length; i++) {
            for (int j = 0; j < monsterRun1[i].length; j++) {
                assertEquals(monsterRun1[i][j], actualMonsterRun1[i][j], "Element at position (" + i + ", " + j + ") doesn't match");
            }
        }
    }

    // Character animations

}
