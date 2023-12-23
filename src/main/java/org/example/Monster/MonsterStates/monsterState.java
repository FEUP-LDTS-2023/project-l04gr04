package org.example.Monster.MonsterStates;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Monster.Monsters.Monster;
import org.example.Others.Position;

public abstract class monsterState {
    protected Monster monster;
    public monsterState(Monster m){
        monster = m;
    }
    public void changeState(monsterState newState) {
        monster.changeState(newState);
    }
    public abstract String modeOn();
    public abstract void draw(TextGraphics graphics,String monsterColor);
    public abstract void move(Position p, boolean t, boolean b, boolean d, boolean e);
    public abstract void FrightHourStarted();
    public abstract void FrightHourEnded();
}
