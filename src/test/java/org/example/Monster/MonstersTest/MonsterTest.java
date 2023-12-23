package org.example.Monster.MonstersTest;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Monster.MonsterStates.fright;
import org.example.Monster.Monsters.RedMonster;
import org.example.PacMan.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class MonsterTest {
    private RedMonster redmonster;
    @BeforeEach
    public void setPlayer() {
        redmonster = new RedMonster(75, 42);
    }
    @Test
    public void testMonsterAnimationLeft() {
        redmonster.setMovingDirection("left");
        redmonster.draw(mock(TextGraphics.class));

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

        char[][] actualMonsterLeft1 = redmonster.getMonsterLeft1();

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
        redmonster.draw(mock(TextGraphics.class));

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

        char[][] actualMonsterRight1 = redmonster.getMonsterRight1();

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
        redmonster.draw(mock(TextGraphics.class));

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

        char[][] actualMonsterUp1 = redmonster.getMonsterUp1();

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
        redmonster.draw(mock(TextGraphics.class));

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

        char[][] actualMonsterDown1 = redmonster.getMonsterDown1();

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
        redmonster.draw(mock(TextGraphics.class));

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

        char[][] actualMonsterRun1 = redmonster.getMonsterRun1();

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
