package org.example.Game.GameStates;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.example.Game.Game;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class RetryingLevelStateTest {
    @Test
    void testDraw() throws IOException{
        // Tests that draw of retryingLevel does not call any draw of game, is just empty
        Game mockGame = mock(Game.class);
        retryingLevelState retryingLevelState = new retryingLevelState(mockGame);
        retryingLevelState.draw();
        verify(mockGame, never()).drawLevel();
        verify(mockGame, never()).drawMenu(anyInt());
        verify(mockGame, never()).drawPause(anyInt());

    }
    @Test
    void testInput() throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        // Tests that input of retryingLevel does not call the input in Game class
        Game mockGame = mock(Game.class);
        retryingLevelState retryingLevelState = new retryingLevelState(mockGame);
        retryingLevelState.input(new KeyStroke(KeyType.ArrowLeft));
        verify(mockGame, never()).gameplayInput(any());
    }
    @Test
    void testName(){
        Game mockGame = mock(Game.class);
        retryingLevelState retryingLevelState = new retryingLevelState(mockGame);
        assertEquals("retryingLevel", retryingLevelState.name());
    }
}
