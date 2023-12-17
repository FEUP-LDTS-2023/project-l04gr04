package org.example.Monster.GameStateTest;

import com.googlecode.lanterna.input.KeyStroke;
import org.example.Game;
import org.example.GameStates.playingState;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PlayingStateTest {
    @Test
    void testConstructor() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Game mockGame = mock(Game.class);

        new playingState(mockGame, true);
        verify(mockGame).startNewGameplay();

        new playingState(mockGame, false);
        verify(mockGame).changeLevelGameplay();
    }

    @Test
    void testDraw() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Game mockGame = mock(Game.class);
        playingState playingState = new playingState(mockGame, true);

        playingState.draw();
        verify(mockGame).drawLevel();
    }

    @Test
    void testInput() throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        Game mockGame = mock(Game.class);
        playingState playingState = new playingState(mockGame, true);

        KeyStroke mockKeyStroke = mock(KeyStroke.class);
        playingState.input(mockKeyStroke);
        verify(mockGame).gameplayInput(mockKeyStroke);
    }
}
