package org.example.Monster;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Monster.Monster;
import org.example.Position;

public class OrangeMonster extends Monster {
    public OrangeMonster(int x,int y){super(x,y);}
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFB852"));
        super.draw(graphics, "#FFB852");
    }
    public Position target(Position position, String direction, Position redPosition){
        if (ms.modeOn().equals("eaten")) return cagePosition;
        if (ms.modeOn().equals("inCage")) return new Position(cagePosition.getX(), cagePosition.getY()-19);
        if (distance(position,this.position) <= 6 * 14 || ms.modeOn().equals("scatter")) return new Position(1,237);
        else return position;
    }
}