package org.example.GameStates;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import org.example.Game;
import org.example.Monster.Monster;
import org.example.Monster.monsterState;

import javax.swing.*;
import java.io.IOException;

public abstract class ApplicationState {
    protected Game game;
    public ApplicationState(Game g){
        game = g;
    }
    public void changeState(ApplicationState newState) {
        game.changeState(newState);
    }
    public abstract void draw() throws IOException;
    public abstract void input(KeyStroke key) throws IOException, InterruptedException;
}
