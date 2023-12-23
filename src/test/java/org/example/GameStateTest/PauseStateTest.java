package org.example.GameStateTest;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.example.Game.Game;
import org.example.Game.GameStates.GameState;
import org.example.Game.GameStates.pauseState;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

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
        pauseState pauseState = new pauseState(mockGame);
        KeyStroke enterKey = new KeyStroke(KeyType.Enter);
        pauseState.input(enterKey);
        verify(mockGame).changeState(any(GameState.class));
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
