package org.example.Monster;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Fruit;
import org.example.Game;
import org.example.Level;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class LevelTest {

    @Test
    public void testInitialization() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Game game = new Game(220, 270,null,null,null);

        // Test level 1 initialization
        Level level1 = new Level(1, 10, 10,game.loadMapFromFile("map.txt"));
        assertEquals(1, level1.levelNumber);
        assertEquals("cherry", level1.fruta);
        assertNotNull(level1.getMap());

        // Test level 5 initialization
        Level level5 = new Level(5, 10, 10,game.loadMapFromFile("map.txt"));
        assertEquals(5, level5.levelNumber);
        assertEquals("apple", level5.fruta);
        assertNotNull(level5.getMap());
    }
    @Test
    public void testGamefeatures() throws IOException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        Game game = new Game(220, 270,null,null,null);
        game.initialize();
        game.createLevel();
        game.gameLoop();
        game.warnMapStopMusic();
    }

}
