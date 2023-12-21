package org.example.PacMan;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.groupcdg.pitest.annotations.DoNotMutate;

public abstract class pacManState {
    protected Player player;
    public pacManState(Player p){
        player = p;
    }
    public void changeState(pacManState newState) {
        player.changeState(newState);
    }
    @DoNotMutate
    public abstract void draw(TextGraphics graphics);
    public abstract void move(String direction);

}
