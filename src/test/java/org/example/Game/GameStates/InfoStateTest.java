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
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

public class InfoStateTest {
    @Test
    void testDraw() throws IOException {
        Game mockGame = mock(Game.class);
        infoState infoState = new infoState(mockGame);
        infoState.draw();
        verify(mockGame).drawInfo();
    }
    @Test
    void testName() {
        Game mockGame = mock(Game.class);
        infoState infoState = new infoState(mockGame);
        assertEquals("info",infoState.name());
    }
    @Test
    void testInput() throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        Game mockGame = mock(Game.class);
        infoState infoState = new infoState(mockGame);
        infoState.input(new KeyStroke(KeyType.ArrowRight));
        // That input does nothing
        verify(mockGame, never()).gameplayInput(any());
        // Just the ESC should work here
        infoState.input(new KeyStroke(KeyType.Escape));
        verify(mockGame).changeState(any(menuState.class));
    }
}
