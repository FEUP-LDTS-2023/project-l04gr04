package org.example.Monster;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.groupcdg.pitest.annotations.DoNotMutate;
import org.example.Color;

public class OrangeMonster extends Monster {
    public OrangeMonster(int x,int y){super(x,y);}
    @DoNotMutate
    @Override
    public void draw(TextGraphics graphics) {
        super.draw(graphics, Color.getColor("orange"));
    }
    public Position target(Position position, String direction, Position redPosition){
        if (ms.modeOn().equals("eaten")) return cagePosition;
        if (ms.modeOn().equals("inCage")) return new Position(cagePosition.getX(), cagePosition.getY()-19);
        if (distance(position,this.position) <= 6 * 14 || ms.modeOn().equals("scatter")) return new Position(1,237);
        else return position;
    }
}