package org.example.Monster.MonsterStates;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Monster.Monsters.Monster;
import org.example.Others.Position;

public class hunt extends monsterState {
    public hunt(Monster m) {
        super(m);
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
