package org.example;

import com.googlecode.lanterna.terminal.Terminal;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, FontFormatException{
        Game game = new Game(220,270);
        game.initialize();
        game.gameLoop();
    }
}
