package org.example.OtherTests;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import com.groupcdg.pitest.annotations.DoNotMutate;
import org.example.Dot;
import org.example.Game;
import org.example.Mapa;
import org.example.Monster.Position;
import org.example.Numbers.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class DotTests {
    private Mapa mapa;
    @BeforeEach
    public void setMap() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Game game = new Game(220,270,null,null,null);
        mapa = new Mapa(202, 240, "", 0, 0.0, 0.0, 0.0, 0.0, 0,game.loadMapFromFile("map.txt"));
    }
    @DoNotMutate
    @Test
    public void testDotDraw() {
        //test drawing methods for the first normal dot and the first special dot
        for (Dot dot : mapa.getDots()) {
            if (!dot.isSpecialDote()) {
                dot.draw(mock(TextGraphics.class));
                char[][] smallDot = new char[][]{
                        {'#','#','#','#','#','#','#'},
                        {'#','#','#','#','#','#','#'},
                        {'#','#','#','#','#','#','#'},
                        {'#','#','#','Y','#','#','#'},
                        {'#','#','#','#','#','#','#'},
                        {'#','#','#','#','#','#','#'},
                        {'#','#','#','#','#','#','#'},

                };
                char[][] actualSmallDot = dot.smallDot;
                assertEquals(smallDot.length, actualSmallDot.length, "Number of rows doesn't match");
                for (int i = 0; i < smallDot.length; i++) {
                    assertEquals(smallDot[i].length, actualSmallDot[i].length, "Number of columns in row " + i + " doesn't match");
                }

                for (int i = 0; i < smallDot.length; i++) {
                    for (int j = 0; j < smallDot[i].length; j++) {
                        assertEquals(smallDot[i][j], actualSmallDot[i][j], "Element at position (" + i + ", " + j + ") doesn't match");
                    }
                }
            }
            else {
                dot.draw(mock(TextGraphics.class));
                char[][] bigDot = new char[][]{
                        {'#','#','Y','Y','#','#','#'},
                        {'#','Y','Y','Y','Y','#','#'},
                        {'Y','Y','Y','Y','Y','Y','#'},
                        {'Y','Y','Y','Y','Y','Y','#'},
                        {'#','Y','Y','Y','Y','#','#'},
                        {'#','#','Y','Y','#','#','#'},
                        {'#','#','#','#','#','#','#'},
                };
                char[][] actualBigDot = dot.bigDot;
                assertEquals(bigDot.length, actualBigDot.length, "Number of rows doesn't match");
                for (int i = 0; i < bigDot.length; i++) {
                    assertEquals(bigDot[i].length, actualBigDot[i].length, "Number of columns in row " + i + " doesn't match");
                }

                for (int i = 0; i < bigDot.length; i++) {
                    for (int j = 0; j < bigDot[i].length; j++) {
                        assertEquals(bigDot[i][j], actualBigDot[i][j], "Element at position (" + i + ", " + j + ") doesn't match");
                    }
                }
            }
        }
    }
    @DoNotMutate
    @Test
    public void testDotInitialization() {
        // Check for correct number of dots
        assertEquals(246, mapa.getDotsCounter());
        int normalDots = 0;
        int specialDots = 0;

        for (Dot dot : mapa.getDots()) {
            if (dot.isSpecialDote()) specialDots++;
            else normalDots++;
        }
        assertEquals(4, specialDots);
        assertEquals(242, normalDots);
    }
    @DoNotMutate
    @Test
    public void testNormalDotCollision() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        //Testing a normal dot
        mapa.setDots(mapa.getDots());
        Score score = new Score();
        assertEquals(246, mapa.getDots().size());
        mapa.player.setPosition(new Position(93, 45));
        mapa.player.move("left");
        mapa.checkDotCollisions(score);
        assertEquals(2, score.getCurrentScore());
        assertEquals(244, mapa.getDotsCounter());
    }
    @DoNotMutate
    @Test
    public void testSpecialDotCollision() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        mapa.setDots(mapa.getDots());
        Score score = new Score();
        assertEquals(246, mapa.getDots().size());
        mapa.player.setPosition(new Position(42, 10));
        mapa.player.move("right");
        mapa.checkDotCollisions(score);
        assertEquals(6, score.getCurrentScore());
        assertEquals(244, mapa.getDotsCounter());
    }

}