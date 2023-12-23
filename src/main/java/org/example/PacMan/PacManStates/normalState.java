package org.example.PacMan.PacManStates;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.PacMan.Player;

public class normalState extends pacManState {
    public normalState(Player p) {
        super(p);
    }
    @Override
    public void draw(TextGraphics graphics) {player.drawNormal(graphics);}
    @Override
    public void move(String direction) {player.moveNormal(direction);}

}
