package org.example.PacMan;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Monster.Monster;
import org.example.Monster.monsterState;
import org.example.Position;

public abstract class pacManState {
    protected Player player;
    public pacManState(Player p){
        player = p;
    }
    public void changeState(pacManState newState) {
        player.changeState(newState);
    }
    public abstract void draw(TextGraphics graphics);
    public abstract void move(String direction);

}
