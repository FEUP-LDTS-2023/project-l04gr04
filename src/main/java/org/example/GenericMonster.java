package org.example;

import com.googlecode.lanterna.graphics.TextGraphics;

public interface GenericMonster extends GameObservers{

    public void draw(TextGraphics graphics);
    public  Position target(Position position, String direction, Position redPosition);
}
