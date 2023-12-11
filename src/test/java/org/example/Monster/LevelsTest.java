package org.example.Monster;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import org.example.Mapa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class LevelsTest {
    InputStream fontStream = getClass().getClassLoader().getResourceAsStream("square.ttf");
    Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
    Font customFont = font.deriveFont(Font.PLAIN, 2);
    SwingTerminalFontConfiguration fontConfig = new SwingTerminalFontConfiguration(true, SwingTerminalFontConfiguration.BoldMode.EVERYTHING, customFont);
    DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(1, 1)).setTerminalEmulatorFontConfiguration(fontConfig);
    Terminal terminal = terminalFactory.createTerminal();
    public Screen screen = new TerminalScreen(terminal);
    TextGraphics graphicsMock = screen.newTextGraphics();

    private Mapa mapa;

    public LevelsTest() throws IOException, FontFormatException {
    }

    @BeforeEach
    public void setMap() throws IOException {
        mapa = new Mapa(202, 240, graphicsMock, "", 0, 0.0, 0.0, 0.0, 0.0, 0);
    }
    @Test
    public void testLevelChangeSystem() {
        //esperar
    }
}