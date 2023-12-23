package org.example;

import org.example.Game.Game;

import java.awt.*;
import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, FontFormatException{
        Game game = new Game(220,270);
        game.initialize();
        game.gameLoop();
    }
}
