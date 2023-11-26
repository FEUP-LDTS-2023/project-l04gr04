package org.example;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    List<GameObservers> observers = new ArrayList<>();
    boolean huntH = false;
    public boolean isHuntHour(){return huntH;}
    public void startHuntHour(){
        huntH = true;
        notifyObservers();
    }
    public void endHuntHour(){
        huntH = false;
        notifyObservers();
    }
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }
    public void notifyObservers() {
        for (GameObserver observer : observers)
            if (isHuntHour()) observer.HuntHourStarted();
            else observer.HuntHourEnded();
    }
}
