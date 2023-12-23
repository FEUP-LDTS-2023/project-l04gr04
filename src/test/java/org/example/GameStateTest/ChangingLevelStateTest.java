package org.example.GameStateTest;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.example.Game.Game;
import org.example.Game.GameStates.changingLevel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ChangingLevelStateTest {
    @Test
    void testDrawAndUpdateState() throws IOException, NoSuchFieldException, IllegalAccessException {
        Game mockGame = Mockito.mock(Game.class);
        changingLevel changingLevelState = new changingLevel(mockGame);

        Field yellowBackField = changingLevel.class.getDeclaredField("yellowBack");
        yellowBackField.setAccessible(true);

        changingLevelState.draw();
        boolean firstDrawState = yellowBackField.getBoolean(changingLevelState);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    assertNotEquals(firstDrawState, yellowBackField.getBoolean(changingLevelState));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 2100);

        assertEquals(firstDrawState, true);
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
