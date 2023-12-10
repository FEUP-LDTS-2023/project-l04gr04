package org.example;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        Game game = new Game(220,270);
        game.gameLoop();
    }
}
