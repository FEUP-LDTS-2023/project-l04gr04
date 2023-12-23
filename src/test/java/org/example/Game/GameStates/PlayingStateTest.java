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

public class PlayingStateTest {
    @Test
    void testDraw() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Game mockGame = mock(Game.class);
        playingState playingState = new playingState(mockGame);
        playingState.draw();
        verify(mockGame).drawLevel();
    }

    @Test
    void testInputEscape() throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        Game game = new Game(220,270);
        KeyStroke keyStroke = new KeyStroke(KeyType.Escape);
        playingState playingState = new playingState(game);
        playingState.input(keyStroke);
        assertEquals("pause",game.getApplicationState().name());
    }
    @Test
    void testInput() throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        // Tests that input of ChangingLevel does not call the input in Game class
        Game mockGame = mock(Game.class);
        playingState playingState = new playingState(mockGame);
        KeyStroke ks = new KeyStroke(KeyType.ArrowRight);
        playingState.input(ks);
        verify(mockGame).gameplayInput(eq(ks));
    }
    @Test
    void testName() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Game mockGame = mock(Game.class);
        playingState playingState = new playingState(mockGame);
        assertEquals("playing",playingState.name());
    }
}
