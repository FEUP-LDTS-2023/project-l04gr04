package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameState {
    List<GameObserver> observers = new ArrayList<>();
    Timer timer;
    private int timeInFright;
    public GameState(int k){
        timeInFright = k;
    }
    private boolean frightH = false;
    public boolean isFrightHour(){return frightH;}
    public void startFrightHour(){
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
                endFrightHour();
            }
        }, timeInFright * 1000);
    }
    public void endFrightHour(){
        frightH = false;
        notifyObservers();
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
