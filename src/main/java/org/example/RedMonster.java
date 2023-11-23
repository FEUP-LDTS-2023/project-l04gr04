package org.example;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class RedMonster extends Monster{
    public RedMonster(int x,int y){super(x,y);}
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#E2000E"));
        super.draw(graphics, "#E2000E" );
    }
    public Position target(Position position){
        if (mode.equals("Scatter"))return new Position(82,0);
        return position;
    }
}
