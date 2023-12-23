package org.example.OtherTests;

import com.groupcdg.pitest.annotations.DoNotMutate;
import org.example.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameInitializationTest {
    private Game game;

    @BeforeEach
    public void setGame(){
        game = new Game(220, 270);
    }
    @DoNotMutate
    @Test
    public void testGameInitialization() throws IOException,UnsupportedAudioFileException, LineUnavailableException {
        game.createLevel();
        assertEquals(game.getGameW(), 220);
        assertEquals(game.getGameH(), 270);
        assertEquals(game.getLevel().levelNumber, 1);
        game.levelWon();
        game.createLevel();
        assertEquals(game.getLevel().levelNumber, 2);
        game.levelLost(game.loadMapFromFile("map.txt"));
        assertEquals(game.getLevel().levelNumber, 2);
    }
}