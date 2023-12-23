package org.example.GameStates;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.example.Game;

import java.io.IOException;

public class infoState extends ApplicationState{
    private int barOn = 0;
    public infoState(Game g) {
        super(g);
    }
    @Override
    public String name() {
        return "info";
    }

    public void draw() throws IOException {
        game.drawInfo();
    }
    public void input(KeyStroke key){
        if (key != null && key.getKeyType() == KeyType.Escape){
            changeState(new menuState(game));
        }
    }
}
