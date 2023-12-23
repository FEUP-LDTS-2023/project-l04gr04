package org.example.Game;
import org.example.Interfaces.GameObserver;
import org.example.Sounds.soundTrack;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FrightControl {
    private soundTrack bg= new soundTrack("Sounds/gostNormal.wav");
    private soundTrack bgScared= new soundTrack("Sounds/gostScared.wav");
    private List<GameObserver> observers = new ArrayList<>();
    private Timer timer;
    private int timeInFright;
    private boolean frightH = false;
    public FrightControl(int k) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        timeInFright = k;
    }
    public void FrightClockIniciate(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                bg.play();
                bg.loop();
            }
        }, 4500);
    }
    public boolean isFrightHour(){return frightH;}
    public void startFrightHour(){
        bg.stop();
        bgScared.stop();
        bgScared.play();
        bgScared.loop();
        frightH = true;
        notifyObservers();
        if (timer != null) timer.cancel();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                endFrightHour();
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
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }
    public void notifyObservers() {
        for (GameObserver observer : observers)
            if (isFrightHour()) observer.FrightHourStarted();
            else observer.FrightHourEnded();
    }
    ////////////////////////////////////////////////////
    // Getters e Setters                              //
    ////////////////////////////////////////////////////
    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void setBg(soundTrack bg) {
        this.bg = bg;
    }
}
