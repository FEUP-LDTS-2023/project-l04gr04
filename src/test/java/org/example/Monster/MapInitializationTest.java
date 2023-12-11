package org.example.Monster;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import org.example.Dot;
import org.example.Mapa;
import org.example.Numbers.Score;
import org.example.Player;
import org.example.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MapInitializationTest {
    InputStream fontStream = getClass().getClassLoader().getResourceAsStream("square.ttf");
    Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
    Font customFont = font.deriveFont(Font.PLAIN, 2);
    SwingTerminalFontConfiguration fontConfig = new SwingTerminalFontConfiguration(true, SwingTerminalFontConfiguration.BoldMode.EVERYTHING, customFont);
    DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(1, 1)).setTerminalEmulatorFontConfiguration(fontConfig);
    Terminal terminal = terminalFactory.createTerminal();
    public Screen screen = new TerminalScreen(terminal);
    TextGraphics graphicsMock = screen.newTextGraphics();

    private Mapa mapa;

    public MapInitializationTest() throws IOException, FontFormatException {
    }
    @BeforeEach
    public void setMap() throws IOException {
        mapa = new Mapa(202, 240, graphicsMock, "", 0, 0.0, 0.0, 0.0, 0.0, 0);
    }

    @Test
    public void testMapInitialization() {
        assertEquals(202, mapa.getWidth());
        assertEquals(240, mapa.getHeight());

        assertEquals("#FFB8FF", mapa.getGateColor());
        assertEquals("#000000", mapa.getBackgroundColor());
        assertEquals("#2121DE", mapa.getWallsColor());
        assertEquals("#959043", mapa.getCoinsColor());

        assertNotNull(mapa.getPlayer());
        assertEquals(mapa.getMonsters().size(), 4);
        assertEquals(mapa.getDots().size(), 246);
    }
    @Test
    public void testPlayerMovement() {
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowRight);

        mapa.readInput(keyStroke);
        assertEquals(KeyType.ArrowRight, mapa.getLastInputMove());
    }

}