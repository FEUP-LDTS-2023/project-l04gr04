package org.example.GameStates;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.example.Game;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class pauseState extends ApplicationState{
    int barOn = 0;
    public pauseState(Game g) {
        super(g);
        game.onPause = true;
    }

    @Override
    public void draw() throws IOException {
        game.drawPause(barOn);
    }
    @Override
    public String name() {
        return "pause";
    }

    @Override
    public void input(KeyStroke key) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        if (key == null)return;
        if (key.getKeyType() == KeyType.Enter){
            game.screen.clear();
            switch (barOn){
                case 0:
                    changeState(new playingState(game));
                    game.drawInicialMap();
                    break;
                case 1:
                    game.warnMapStopMusic();
                    game.resetStructs();
                    changeState(new menuState(game));
                    break;
            }
        }
        if (key.getKeyType() == KeyType.ArrowUp)up();
        else if (key.getKeyType() == KeyType.ArrowDown)down();
    }
    public void up(){
        barOn--;
        if(barOn < 0)barOn = 1;
    }
    public void down(){
        barOn++;
        if(barOn > 1)barOn = 0;
    }
    public int getBarOn(){
        return barOn;
    }

}