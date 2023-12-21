package org.example.PacMan;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.groupcdg.pitest.annotations.DoNotMutate;

import java.util.Timer;
import java.util.TimerTask;

public class eatingPacMan extends pacManState {

    public eatingPacMan(Player p) {
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
    @DoNotMutate
    public void draw(TextGraphics graphics) {player.drawDead(graphics);}

    @Override
    public void move(String direction) {}

}
