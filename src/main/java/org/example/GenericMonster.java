package org.example;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Monster.Position;


public interface GenericMonster extends GameObserver {
    public void draw(TextGraphics graphics);
    public Position target(Position position, String direction, Position redPosition);
}
