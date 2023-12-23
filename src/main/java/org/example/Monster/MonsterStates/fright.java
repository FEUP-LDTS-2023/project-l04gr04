package org.example.Monster.MonsterStates;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Monster.Monsters.Monster;
import org.example.Others.Position;

public class fright extends monsterState {
    public fright(Monster m) {
        super(m);
    }
    @Override
    public String modeOn() {
        return "fright";
    }
    @Override
    public void draw(TextGraphics graphics, String monsterColor) {
        monster.blueDraw(graphics);
    }
    @Override
    public void move(Position p,boolean t, boolean b, boolean d, boolean e) {
        monster.frightMove(t,b,d,e);
    }
    @Override
    public void FrightHourStarted() {}
    @Override
    public void FrightHourEnded() {
        changeState(new hunt(monster));
    }
}
