package org.example.Monster;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Monster.Monster;
import org.example.Position;

public class RedMonster extends Monster {
    public RedMonster(int x,int y){super(x,y);}
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FF0000"));
        super.draw(graphics, "#FF0000");
    }
    public Position target(Position position, String direction, Position redPosition){
        if (ms.modeOn().equals("eaten")) return cagePosition;
        if (ms.modeOn().equals("scatter")) return new Position(195, 1);
        return position;
    }
}