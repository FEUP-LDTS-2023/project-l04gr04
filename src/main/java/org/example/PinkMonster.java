package org.example;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class PinkMonster extends Monster{
    public PinkMonster(int x,int y){super(x,y);}
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFB8FF"));
        super.draw(graphics,"FFB8FF");
    }
    public Position target(Position position, String direction, Position redPosition){
        if (mode.equals("Scatter"))return new Position(7,3);
        if (direction.equals("up"))return new Position(position.getX()-4, position.getY()-4);
        if (direction.equals("down"))return new Position(position.getX(), position.getY()+4+14);
        if (direction.equals("left"))return new Position(position.getX()-4, position.getY());
        if (direction.equals("right"))return new Position(position.getX()+4+14, position.getY());
        return position;
    }
}
