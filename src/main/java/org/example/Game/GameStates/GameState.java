package org.example.Game.GameStates;

import com.googlecode.lanterna.input.KeyStroke;
import org.example.Game.Game;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public abstract class GameState {
    protected Game game;
    public GameState(Game g){
        game = g;
    }
    public abstract String name();
    public void changeState(GameState newState) {
        game.changeState(newState);
    }
    public abstract void draw() throws IOException;
    public abstract void input(KeyStroke key) throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException;
}
