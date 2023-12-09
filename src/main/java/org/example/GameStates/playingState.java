package org.example.GameStates;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.example.Game;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class playingState extends ApplicationState{
    public playingState(Game g) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        super(g);
        game.startGameplay();
    }

    @Override
    public void draw() throws IOException {
        game.drawLevel();
    }

    @Override
    public void input(KeyStroke key) throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        if (key != null && key.getKeyType() == KeyType.Escape){
            game.screen.clear();
            changeState(new pauseState(game));
            return;
        }
        game.gameplayInput(key);
    }
}
