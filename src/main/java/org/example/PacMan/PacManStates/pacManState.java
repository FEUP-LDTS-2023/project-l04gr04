package org.example.PacMan.PacManStates;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.PacMan.Player;

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
