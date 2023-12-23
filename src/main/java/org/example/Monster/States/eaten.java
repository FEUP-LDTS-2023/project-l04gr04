package org.example.Monster.States;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Monster.Monster;
import org.example.Monster.monsterState;
import org.example.Position;

public class eaten extends monsterState {
    public eaten(Monster m) {
        super(m);
    }

    @Override
    public String modeOn() {
        return "eaten";
    }

    @Override
    public void draw(TextGraphics graphics, String monsterColor) {
        monster.darkDraw(graphics,"#000000"); // Black color
    }
    @Override
    public void move(Position p, boolean t, boolean b, boolean d, boolean e) {
        monster.targetMove(p,t,b,d,e);
    }

    @Override
    public void FrightHourStarted() {}

    @Override
    public void FrightHourEnded() {}
}
