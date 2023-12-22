package org.example.Monster.States;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Monster.Monster;
import org.example.Monster.monsterState;
import org.example.Monster.Position;

public class hunt extends monsterState {

    public hunt(Monster m) {
        super(m);
    }

    @Override
    public void onPacManCollision() {
        monster.pacManLost();
    }

    @Override
    public String modeOn() {
        return "hunt";
    }

    @Override
    public void draw(TextGraphics graphics, String monsterColor) {
        monster.normalDraw(graphics,monsterColor);
    }

    @Override
    public void move(Position p,boolean t, boolean b, boolean d, boolean e) {
        monster.targetMove(p,t,b,d,e);
    }

    @Override
    public void FrightHourStarted() {
        changeState(new fright(monster));
    }

    @Override
    public void FrightHourEnded() {}
}
