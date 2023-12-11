package org.example.Monster;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Monster.Monster;
import org.example.Position;

public class BlueMonster extends Monster {
    public BlueMonster(int x,int y){super(x,y);}
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#00FFFF"));
        super.draw(graphics, "#00FFFF");
    }
    public Position target(Position position, String direction, Position redPosition){
        if (ms.modeOn().equals("eaten")) return cagePosition;
        if (ms.modeOn().equals("scatter")) return new Position(195,237);
        if (ms.modeOn().equals("inCage")) return new Position(cagePosition.getX(), cagePosition.getY()-19);
        Position middleP;
        if (direction.equals("up")) middleP =  new Position(position.getX() - 2 * 14, position.getY() - 2 * 14);
        else if (direction.equals("down"))middleP = new Position(position.getX(), position.getY() + 2 * 14);
        else if (direction.equals("left"))middleP = new Position(position.getX() - 2 * 14, position.getY());
        else middleP = new Position(position.getX() + 2 * 14, position.getY());
        int Vx = redPosition.getX() - middleP.getX();
        int Vy = redPosition.getY() - middleP.getY();
        return new Position(middleP.getX() - Vx,middleP.getY() - Vy );
    }
}