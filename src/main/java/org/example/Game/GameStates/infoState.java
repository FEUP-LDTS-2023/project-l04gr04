package org.example.Game.GameStates;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.example.Game.Game;

import java.io.IOException;

public class infoState extends GameState{
    public infoState(Game g) {
        super(g);
    }
    @Override
    public String name() {
        return "info";
    }
    @Override
    public void draw() throws IOException {
        game.drawInfo();
    }
    public void input(KeyStroke key){
        if (key != null && key.getKeyType() == KeyType.Escape){
            game.screenClear();
            changeState(new menuState(game));
        }
    }
}
