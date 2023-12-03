package org.example;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class RedMonster extends Monster{
    public RedMonster(int x,int y){super(x,y);}
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FF0000"));
        super.draw(graphics, "#FF0000");
    }
    public Position target(Position position, String direction, Position redPosition){
        if (mode.equals("scatter")) return new Position(369, 1);
        if (mode.equals("dark")) return new Position(100,115);
        return position;
    }
}