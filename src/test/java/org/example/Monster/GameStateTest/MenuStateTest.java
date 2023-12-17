package org.example.Monster.GameStateTest;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import com.groupcdg.pitest.annotations.DoNotMutate;
import org.example.Game;
import org.example.GameStates.menuState;
import org.example.GameStates.playingState;
import org.example.Level;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MenuStateTest {
    InputStream fontStream = getClass().getClassLoader().getResourceAsStream("square.ttf");
    Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
    Font customFont = font.deriveFont(Font.PLAIN, 2);
    SwingTerminalFontConfiguration fontConfig = new SwingTerminalFontConfiguration(true, SwingTerminalFontConfiguration.BoldMode.EVERYTHING, customFont);
    DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(1, 1)).setTerminalEmulatorFontConfiguration(fontConfig);
    Terminal terminal = terminalFactory.createTerminal();
    public Screen mockScreen = new TerminalScreen(terminal);
    TextGraphics graphicsMock =mockScreen.newTextGraphics();
    private Game mockGame;

    public MenuStateTest() throws IOException, FontFormatException {
    }


    @BeforeEach
    void setUp() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        mockGame = new Game(220,270,terminal,new Level(1,220,270,null),graphicsMock);
    }
    @DoNotMutate
    @Test
    void testDraw() throws IOException {
        menuState menuState = new menuState(mockGame);
        menuState.draw();
        verify(mockGame).drawMenu(anyInt());
    }

    @DoNotMutate
    @Test
    void testInputEnter() throws IOException,UnsupportedAudioFileException, LineUnavailableException {
        menuState menuState = new menuState(mockGame);
        KeyStroke enterKey = new KeyStroke(KeyType.Enter);
        mockGame.screen = mockScreen;
        menuState.input(enterKey);
        verify(mockGame).changeState(any(playingState.class));
    }

    @DoNotMutate
    @Test
    void testInputArrowUp() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        menuState menuState = new menuState(mockGame);
        KeyStroke arrowUpKey = new KeyStroke(KeyType.ArrowUp);
        mockGame.screen = mockScreen;
        menuState.input(arrowUpKey);
        assertEquals(mockGame,menuState.getBarOn());
    }

    @DoNotMutate
    @Test
    void testInputArrowDown() throws IOException, UnsupportedEncodingException, UnsupportedAudioFileException, LineUnavailableException {
        menuState menuState = new menuState(mockGame);
        KeyStroke arrowDownKey = new KeyStroke(KeyType.ArrowDown);
        mockGame.screen = mockScreen;

        menuState.input(arrowDownKey);

        verify(mockGame).drawMenu(anyInt());
    }
}
