package org.example.Monster.GameStateTest;

import org.example.Game;
import org.example.GameStates.changingLevel;
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

public class ChangingLevelStateTest {
    @Test
    void testDrawAndUpdateState() throws IOException, UnsupportedAudioFileException, LineUnavailableException, NoSuchFieldException, IllegalAccessException {
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
}
