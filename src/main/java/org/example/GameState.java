package org.example;

import org.example.Sounds.soundTrack;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameState {
    soundTrack bg= new soundTrack("Sounds/gostNormal.wav");
    soundTrack bgScared= new soundTrack("Sounds/gostScared.wav");
    List<GameObserver> observers = new ArrayList<>();
    Timer timer;
    private int timeInFright;
    public GameState(int k) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        timeInFright = k;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                bg.play();
                bg.loop();
            }
        }, 4500);
    }


    private boolean frightH = false;
    public boolean isFrightHour(){return frightH;}
    public void startFrightHour(){
        bg.stop();
        bgScared.play();
        bgScared.loop();
        frightH = true;
        notifyObservers();
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!observers.isEmpty())endFrightHour();
            }
        }, timeInFright * 1000);
    }
    public void endFrightHour(){
        bgScared.stop();
        bg.play();
        bg.loop();
        frightH = false;
        notifyObservers();
    }
    public void stopMusic(){
        bg.stop();
        bgScared.stop();

    }
    public void closeMusic(){
        bg.close();
        bgScared.close();
    }

    public void startMusic(){
        if (isFrightHour()){
            bgScared.play();
            bgScared.loop();
        }else{
            bg.play();
            bg.loop();
        }
    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }
    public void notifyObservers() {
        for (GameObserver observer : observers)
            if (isFrightHour()) observer.FrightHourStarted();
            else observer.FrightHourEnded();
    }
}
