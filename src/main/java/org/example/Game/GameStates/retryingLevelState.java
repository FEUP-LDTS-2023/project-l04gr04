package org.example.Game.GameStates;

import com.googlecode.lanterna.input.KeyStroke;
import org.example.Game.Game;

import java.io.IOException;

public class retryingLevelState extends GameState {
    public retryingLevelState(Game g) {
        super(g);
    }
    @Override
    public String name() {
        return "retryingLevel";
    }
    @Override
    public void draw() throws IOException {}

    @Override
    public void input(KeyStroke key) {}
}
