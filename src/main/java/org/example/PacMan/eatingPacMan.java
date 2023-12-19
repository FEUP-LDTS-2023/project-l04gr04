package org.example.PacMan;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.groupcdg.pitest.annotations.DoNotMutate;
import org.example.GameStates.playingState;
import org.example.Monster.Monster;
import org.example.Monster.monsterState;
import org.example.Position;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
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
    @DoNotMutate
    public void move(String direction) {}

}
