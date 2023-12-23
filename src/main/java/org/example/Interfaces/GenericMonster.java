package org.example.Interfaces;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Others.Position;


public interface GenericMonster extends GameObserver {
    void draw(TextGraphics graphics);
    Position target(Position position, String direction, Position redPosition);
}
