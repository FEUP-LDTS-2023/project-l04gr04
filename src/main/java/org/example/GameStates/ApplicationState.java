package org.example.GameStates;

import com.googlecode.lanterna.input.KeyStroke;
import com.groupcdg.pitest.annotations.DoNotMutate;
import org.example.Game;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public abstract class ApplicationState {
    protected Game game;
    public ApplicationState(Game g){
        game = g;
    }
    public abstract String name();
    public void changeState(ApplicationState newState) {
        game.changeState(newState);
    }
    @DoNotMutate
    public abstract void draw() throws IOException;
    public abstract void input(KeyStroke key) throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException;
}
