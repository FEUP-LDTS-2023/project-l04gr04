package org.example.Monster;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.groupcdg.pitest.annotations.DoNotMutate;

public abstract class monsterState {
    protected Monster monster;
    public monsterState(Monster m){
        monster = m;
    }
    public void changeState(monsterState newState) {
        monster.changeState(newState);
    }
    public abstract void onPacManCollision();
    public abstract String modeOn();
    @DoNotMutate
    public abstract void draw(TextGraphics graphics,String monsterColor);
    public abstract void move(Position p,boolean t, boolean b, boolean d, boolean e);
    public abstract void FrightHourStarted();
    public abstract void FrightHourEnded();
}
