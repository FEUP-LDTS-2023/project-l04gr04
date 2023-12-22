package org.example.Monster.States;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Monster.Monster;
import org.example.Monster.monsterState;
import org.example.Monster.Position;

public class onCollision extends monsterState {
    public onCollision(Monster m) {
        super(m);
    }

    @Override
    public void onPacManCollision() {}

    @Override
    public String modeOn() {
        return "onCollision";
    }

    @Override
    public void draw(TextGraphics graphics, String monsterColor) {}

    @Override
    public void move(Position p,boolean t, boolean b, boolean d, boolean e) {}

    @Override
    public void FrightHourStarted() {

    }

    @Override
    public void FrightHourEnded() {

    }
}
