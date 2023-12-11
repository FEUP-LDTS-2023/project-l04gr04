package org.example.Monster;

import org.example.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameInitializationTest {
    private Game game;

    @BeforeEach
    public void setGame() throws IOException, FontFormatException {
        //game = new Game(220, 270);
    }
    @Test
    public void testGameInitialization() throws IOException, FontFormatException {
        assertEquals(game.getGameW(), 220);
        assertEquals(game.getGameH(), 270);
        assertEquals(game.level.levelNumber, 1);
        game.level.changeLevel();
        assertEquals(game.level.levelNumber, 2);
    }
}