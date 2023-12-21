package org.example.Monster.States;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Monster.Monster;
import org.example.Monster.monsterState;
import org.example.Monster.Position;

public class fright extends monsterState {
    public fright(Monster m) {
        super(m);
    }

    @Override
    public void onPacManCollision() {
        changeState(new eaten(monster));
    }
    @Override
    public String modeOn() {
        return "fright";
    }

    @Override
    public void draw(TextGraphics graphics, String monsterColor) {
        monster.blueDraw(graphics,monsterColor);
    }

    @Override
    public void move(Position p, char[][] map, boolean t, boolean b, boolean d, boolean e) {
        monster.frightMove(t,b,d,e);
    }

    @Override
    public void FrightHourStarted() {}

    @Override
    public void FrightHourEnded() {
        changeState(new hunt(monster));
    }
}
