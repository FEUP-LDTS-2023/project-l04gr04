package org.example;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class PinkMonster extends Monster{
    public PinkMonster(int x,int y){super(x,y);}
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFB8FF"));
        super.draw(graphics,"#FFB8FF");
    }
    public Position target(Position position, String direction, Position redPosition){
        if (mode.equals("scatter")) return new Position(2,1);
        if (mode.equals("dark")) return new Position(100,115);
        if (direction.equals("up")) return new Position(position.getX(), position.getY() - 4 * 14);
        if (direction.equals("down"))return new Position(position.getX(),position.getY() + 4 * 14);
        if (direction.equals("left"))return new Position(position.getX()  - 4 * 14, position.getY());
        if (direction.equals("right"))return new Position(position.getX() + 4 * 14, position.getY());
        return position;
    }
}