package org.example.Monster;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.example.Game;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class UserInputTest {
    @Mock
    private Screen screen;
    @Mock
    private KeyStroke keyStroke;

    @Test
    public void testUserInput() throws IOException, FontFormatException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        Game game = new Game(220, 270, null, null, null);
        //game.runner();
        screen = game.screen;

        // Define the behavior of mocked methods
        when(screen.pollInput()).thenReturn(keyStroke);
        when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowUp); // Example, replace with your desired key type

        // Run your game loop or user input handling method
        //game.runner();

        // Add assertions to verify the expected behavior based on user input
        // For example, assert that the level.processKey() method is called with the expected keyStroke

        // Verify that screen.close() is called when Escape key is pressed
        verify(screen, times(1)).close();
    }
}