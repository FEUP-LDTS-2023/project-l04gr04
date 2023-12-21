package org.example.GameStateTest;

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
import org.example.GameStates.playingState;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class PauseStateTest {
    @Test
    void testDraw() throws IOException {
        Game mockGame = mock(Game.class);
        pauseState pauseState = new pauseState(mockGame);

        pauseState.draw();
        verify(mockGame).drawPause(anyInt());
    }

    @Test
    void testInput() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Game mockGame = mock(Game.class);
        mockGame.screen = mock(Screen.class);
        pauseState pauseState = new pauseState(mockGame);
        KeyStroke enterKey = new KeyStroke(KeyType.Enter);
        pauseState.input(enterKey);
        verify(mockGame).changeState(any(ApplicationState.class));
    }
    @Test
    void testName() {
        Game mockGame = mock(Game.class);
        pauseState pauseState = new pauseState(mockGame);
        assertEquals("pause",pauseState.name());
    }
    @Test
    void barUp(){
        Game mockGame = mock(Game.class);
        pauseState pauseState = new pauseState(mockGame);
        pauseState.up();
        assertEquals(1,pauseState.getBarOn());
        pauseState.down();
        assertEquals(0,pauseState.getBarOn());
    }
}
