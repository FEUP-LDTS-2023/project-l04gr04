package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameState {
    List<GameObserver> observers = new ArrayList<>();
    private int timeInFright;
    public GameState(int k){
        timeInFright = k;
    }
    private boolean frightH = false;
    public boolean isFrightHHour(){return frightH;}
    public void startFrightHour(){
        frightH = true;
        notifyObservers();
        Timer timer = new Timer();
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
            if (isFrightHHour()) observer.FrightHourStarted();
            else observer.FrightHourEnded();
    }
}
