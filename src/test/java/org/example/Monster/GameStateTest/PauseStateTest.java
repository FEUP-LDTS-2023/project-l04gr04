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
import org.example.GameStates.ApplicationState;
import org.example.GameStates.pauseState;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class PauseStateTest {
    InputStream fontStream = getClass().getClassLoader().getResourceAsStream("square.ttf");
    Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
    Font customFont = font.deriveFont(Font.PLAIN, 2);
    SwingTerminalFontConfiguration fontConfig = new SwingTerminalFontConfiguration(true, SwingTerminalFontConfiguration.BoldMode.EVERYTHING, customFont);
    DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(1, 1)).setTerminalEmulatorFontConfiguration(fontConfig);
    Terminal terminal = terminalFactory.createTerminal();
    public Screen screen = new TerminalScreen(terminal);

    public PauseStateTest() throws IOException, FontFormatException {
    }

    @DoNotMutate

    @Test
    void testConstructor() {
        Game mockGame = mock(Game.class);
        pauseState pauseState = new pauseState(mockGame);

        verify(mockGame).onPause = true;
    }

    @DoNotMutate
    @Test
    void testDraw() throws IOException {
        Game mockGame = mock(Game.class);
        pauseState pauseState = new pauseState(mockGame);

        pauseState.draw();
        verify(mockGame).drawPause(anyInt());
    }

    @DoNotMutate
    @Test
    void testInput() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Game mockGame = mock(Game.class);
        mockGame.screen = screen;
        pauseState pauseState = new pauseState(mockGame);

        KeyStroke enterKey = new KeyStroke(KeyType.Enter);
        pauseState.input(enterKey);
        verify(mockGame).changeState(any(ApplicationState.class));
    }
}
