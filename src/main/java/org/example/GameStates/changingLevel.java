package org.example.GameStates;

import com.googlecode.lanterna.input.KeyStroke;
import org.example.Game;
import org.example.Monster.Monster;
import org.example.Monster.States.scatter;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class changingLevel extends ApplicationState{
    boolean yellowBack = false;
    public changingLevel(Game g) {
        super(g);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    changeState(new playingState(game));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedAudioFileException e) {
                    throw new RuntimeException(e);
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 2000);
    }

    @Override
    public void draw() throws IOException {
        yellowBack = !yellowBack;
        game.changingLevelDraw(yellowBack);
    }

    @Override
    public void input(KeyStroke key) throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {}
}
