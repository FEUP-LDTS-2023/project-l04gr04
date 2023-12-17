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
import org.example.GameStates.pauseState;
import org.example.GameStates.playingState;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PlayingStateTest {
    InputStream fontStream = getClass().getClassLoader().getResourceAsStream("square.ttf");
    Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
    Font customFont = font.deriveFont(Font.PLAIN, 2);
    SwingTerminalFontConfiguration fontConfig = new SwingTerminalFontConfiguration(true, SwingTerminalFontConfiguration.BoldMode.EVERYTHING, customFont);
    DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(1, 1)).setTerminalEmulatorFontConfiguration(fontConfig);
    Terminal terminal = terminalFactory.createTerminal();
    public Screen screen = new TerminalScreen(terminal);

    public PlayingStateTest() throws IOException, FontFormatException {
    }

    @DoNotMutate
    @Test
    void testDraw() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Game mockGame = mock(Game.class);
        playingState playingState = new playingState(mockGame);

        playingState.draw();
        verify(mockGame).drawLevel();
    }

    @DoNotMutate
    @Test
    void testInputEscape() throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        Game game = new Game(220,270,null,null,null);
        game.screen = screen;
        KeyStroke keyStroke = new KeyStroke(KeyType.Escape);
        playingState playingState = new playingState(game);
        playingState.input(keyStroke);
        assertEquals("pause",game.getApplicationState().name());
    }
}
