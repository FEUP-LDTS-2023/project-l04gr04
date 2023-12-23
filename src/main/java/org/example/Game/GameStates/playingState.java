package org.example.Game.GameStates;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.example.Game.Game;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class playingState extends GameState {
    public playingState(Game g) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        super(g);
    }
    @Override
    public String name() {
        return "playing";
    }
    @Override
    public void draw() throws IOException {
        game.drawLevel();
    }
    @Override
    public void input(KeyStroke key) throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        if (key != null && key.getKeyType() == KeyType.Escape){
            changeState(new pauseState(game));
            return;
        }
        game.gameplayInput(key);
    }
}
