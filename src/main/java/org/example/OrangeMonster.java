package org.example;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class OrangeMonster extends Monster{
    public OrangeMonster(int x,int y){super(x,y);}
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFB852"));
        super.draw(graphics, "#FFB852");
    }
    public Position target(Position position, String direction, Position redPosition){
        if (mode.equals("Scatter") || distance(position,this.position) <= 8 * 14) return new Position(2,392);
        return position;
    }
}
