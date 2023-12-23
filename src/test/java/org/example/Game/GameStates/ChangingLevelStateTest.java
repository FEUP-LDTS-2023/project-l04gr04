package org.example.Game.GameStates;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.example.Game.Game;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ChangingLevelStateTest {

    @Test
    void testDraw() throws IOException {
        Game mockGame = mock(Game.class);
        changingLevel changingLevel = new changingLevel(mockGame);
        changingLevel.draw();
        verify(mockGame).changingLevelDraw(anyBoolean());
    }
    @Test
    void testConstructor() throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        Game mockGame = mock(Game.class);
        changingLevel changingLevel = new changingLevel(mockGame);
        mockGame.changeState(changingLevel);
        Thread.sleep(2500);
        verify(mockGame, times(1)).createLevel();
        verify(mockGame, times(1)).changeState(any(playingState.class));
    }

    @Test
    void testName() {
        Game mockGame = mock(Game.class);
        changingLevel changingLevel = new changingLevel(mockGame);
        assertEquals("changingLevel",changingLevel.name());
    }
    @Test
    void testInput() throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        // Tests that input of ChangingLevel does not call the input in Game class
        Game mockGame = mock(Game.class);
        changingLevel changingLevel = new changingLevel(mockGame);
        changingLevel.input(new KeyStroke(KeyType.ArrowRight));
        verify(mockGame, never()).gameplayInput(any());
    }
}
