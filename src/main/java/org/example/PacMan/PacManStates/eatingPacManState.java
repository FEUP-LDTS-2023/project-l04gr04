package org.example.PacMan.PacManStates;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.PacMan.Player;

import java.util.Timer;
import java.util.TimerTask;

public class eatingPacManState extends pacManState {
    public eatingPacManState(Player p) {
        super(p);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                changeState(new normalState(player));
            }
        }, 2010);
    }
    @Override
    public void draw(TextGraphics graphics) {player.drawDead(graphics);}
    @Override
    public void move(String direction) {}
}
