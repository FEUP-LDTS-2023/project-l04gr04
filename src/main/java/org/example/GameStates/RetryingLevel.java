package org.example.GameStates;

import com.googlecode.lanterna.input.KeyStroke;
import org.example.Game;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class RetryingLevel extends ApplicationState{
    public RetryingLevel(Game g) {
        super(g);
    }

    @Override
    public String name() {
        return "retryingLevel";
    }

    @Override
    public void draw() throws IOException {
        game.drawLevel();
    }

    @Override
    public void input(KeyStroke key) throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {}
}
