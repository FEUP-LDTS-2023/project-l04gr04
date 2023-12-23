package org.example;

import com.googlecode.lanterna.graphics.TextGraphics;


public interface GenericMonster extends GameObserver {
    void draw(TextGraphics graphics);
    Position target(Position position, String direction, Position redPosition);
}
