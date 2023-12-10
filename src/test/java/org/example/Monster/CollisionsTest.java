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
import org.example.Monster.States.eaten;
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

public class CollisionsTest {
    InputStream fontStream = getClass().getClassLoader().getResourceAsStream("square.ttf");
    Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
    Font customFont = font.deriveFont(Font.PLAIN, 2);
    SwingTerminalFontConfiguration fontConfig = new SwingTerminalFontConfiguration(true, SwingTerminalFontConfiguration.BoldMode.EVERYTHING, customFont);
    DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(1, 1)).setTerminalEmulatorFontConfiguration(fontConfig);
    Terminal terminal = terminalFactory.createTerminal();
    public Screen screen = new TerminalScreen(terminal);
    TextGraphics graphicsMock = screen.newTextGraphics();
    private Mapa mapa;

    public CollisionsTest() throws IOException, FontFormatException {
    }

    @BeforeEach
    public void setMap() throws IOException {
        mapa = new Mapa(202, 240, graphicsMock, "", 0, 0.0, 0.0, 0.0, 0.0, 0);
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
        @Test
        public void testMonsterCollision() {
            for (Monster monster : mapa.getMonsters()) {
                monster.changeState(new fright(monster));
                monster.setPosition(new Position(75, 42));
            }
            Player player = mapa.getPlayer();
            player.setPosition(new Position(75, 42));
            mapa.checkMonsterCollisions();
            for (Monster monster : mapa.getMonsters()) {
                assertEquals(new eaten(monster).modeOn(), monster.ms.modeOn());
            }
        }
}
