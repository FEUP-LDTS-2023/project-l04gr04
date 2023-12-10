package org.example.Monster;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import org.example.Dot;
import org.example.Mapa;
import org.example.Monster.States.fright;
import org.example.Numbers.Score;
import org.example.Player;
import org.example.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DotTests {
    InputStream fontStream = getClass().getClassLoader().getResourceAsStream("square.ttf");
    Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
    Font customFont = font.deriveFont(Font.PLAIN, 2);
    SwingTerminalFontConfiguration fontConfig = new SwingTerminalFontConfiguration(true, SwingTerminalFontConfiguration.BoldMode.EVERYTHING, customFont);
    DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(1, 1)).setTerminalEmulatorFontConfiguration(fontConfig);
    Terminal terminal = terminalFactory.createTerminal();
    public Screen screen = new TerminalScreen(terminal);
    TextGraphics graphicsMock = screen.newTextGraphics();

    private Mapa mapa;

    public DotTests() throws IOException, FontFormatException {
    }

    @BeforeEach
    public void setMap() throws IOException {
        mapa = new Mapa(202, 240, graphicsMock, "", 0, 0.0, 0.0, 0.0, 0.0, 0);
    }
    @Test
    public void testDotDraw() {
        //test drawing methods for the first normal dot and the first special dot
        for (Dot dot : mapa.getDots()) {
            if (!dot.isSpecialDote()) {
                dot.draw(graphicsMock);
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
                dot.draw(graphicsMock);
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

    @Test
    public void testNormalDotCollision() {
        //Testing a normal dot
        Dot dot = new Dot(5, 5, false);
        mapa.getDots().add(dot);

        Player player = mapa.getPlayer();
        player.setPosition(new Position(5, 5));
        Score score = new Score();
        mapa.checkDotCollisions(score);

        assertEquals(246, mapa.getDots().size());
        assertEquals(1, score.getCurrentScore());
        assertEquals(245, mapa.getDotsCounter());
    }
    @Test
    public void testSpecialDotCollision() {
        Dot dot = new Dot(5, 5, true);
        mapa.getDots().add(dot);

        Player player = mapa.getPlayer();
        player.setPosition(new Position(5, 5));
        Score score = new Score();
        mapa.checkDotCollisions(score);

        assertEquals(246, mapa.getDots().size());
        assertEquals(5, score.getCurrentScore());  // increments by 50 points
        assertEquals(245, mapa.getDotsCounter());
    }

    // descobrir como testar posições dos pontos

    @Test
    public void testDotConsumption() {
        Player player = mapa.getPlayer();
        Monster monster = mapa.getMonsters().get(0);
        Score score = new Score();
        int totalPoints = 0;
        for (Dot dot : mapa.getDots()) {
            player.setPosition(dot.getPosition());
            mapa.checkDotCollisions(score);
            // if current score is incremented, it means the player collided with the dot and it won't be drawn again:
            if (!dot.isSpecialDote()) {
                totalPoints++;
                //assertEquals(totalPoints, score.getCurrentScore());
                break;
            }
            else {
                assertEquals("fright", monster.ms.modeOn());
                totalPoints += 5;
                //assertEquals(totalPoints, score.getCurrentScore());
            }
        }
    }
}
