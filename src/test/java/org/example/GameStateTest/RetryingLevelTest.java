package org.example.GameStateTest;

import com.googlecode.lanterna.TerminalSize;
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
import org.example.GameStates.RetryingLevel;
import org.example.GameStates.pauseState;
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

public class RetryingLevelTest {
    @Test
    void testDraw() throws IOException{
        // Tests that draw of retryingLevel does not call any draw of game, is just empty
        Game mockGame = mock(Game.class);
        RetryingLevel retryingLevel = new RetryingLevel(mockGame);
        retryingLevel.draw();
        verify(mockGame, never()).drawLevel();
        verify(mockGame, never()).drawMenu(anyInt());
        verify(mockGame, never()).drawPause(anyInt());

    }
    @Test
    void testInput() throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        // Tests that input of retryingLevel does not call the input in Game class
        Game mockGame = mock(Game.class);
        RetryingLevel retryingLevel = new RetryingLevel(mockGame);
        retryingLevel.input(new KeyStroke(KeyType.ArrowLeft));
        verify(mockGame, never()).gameplayInput(any());
    }
    @Test
    void testName(){
        Game mockGame = mock(Game.class);
        RetryingLevel retryingLevel = new RetryingLevel(mockGame);
        assertEquals("retryingLevel",retryingLevel.name());
    }
}
