package org.example.Monster;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import org.example.Monster.States.fright;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonsterAnimationTest {
    InputStream fontStream = getClass().getClassLoader().getResourceAsStream("square.ttf");
    Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
    Font customFont = font.deriveFont(Font.PLAIN, 2);
    SwingTerminalFontConfiguration fontConfig = new SwingTerminalFontConfiguration(true, SwingTerminalFontConfiguration.BoldMode.EVERYTHING, customFont);
    DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(1, 1)).setTerminalEmulatorFontConfiguration(fontConfig);
    Terminal terminal = terminalFactory.createTerminal();
    public Screen screen = new TerminalScreen(terminal);
    TextGraphics graphicsMock = screen.newTextGraphics();

    public MonsterAnimationTest() throws IOException, FontFormatException {
    }

    private RedMonster redmonster;

    @BeforeEach
    public void setMonster() {
        redmonster = new RedMonster(75, 42);
    }

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

}
