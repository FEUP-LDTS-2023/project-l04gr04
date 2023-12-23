package org.example.GameStates;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.groupcdg.pitest.annotations.DoNotMutate;
import org.example.Game;
import org.example.Sounds.soundTrack;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class menuState extends ApplicationState{
    private int barOn = 0;
    public menuState(Game g) {
        super(g);
    }
    @Override
    public String name() {
        return "menu";
    }
    @Override
    public void draw() throws IOException {
        game.drawMenu(barOn);
    }
    @Override
    public void input(KeyStroke key) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        if (key == null)return;
        if (key.getKeyType() == KeyType.Enter){
            switch (barOn){
                case 0:
                    game.createLevel();
                    changeState(new playingState(game));
                    break;
                case 2:
                    game.stopGameLoop();
                    break;

            }
        }
        if (key.getKeyType() == KeyType.ArrowUp)up();
        else if (key.getKeyType() == KeyType.ArrowDown)down();
    }
    public void up(){
        barOn--;
        if(barOn < 0)barOn = 2;
    }
    public void down(){
        barOn++;
        if(barOn > 2)barOn = 0;
    }

    public int getBarOn() {
        return barOn;
    }
}
