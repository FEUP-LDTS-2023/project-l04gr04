package org.example.Monster;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.GameObserver;
import org.example.Position;


public interface GenericMonster extends GameObserver {
    public void draw(TextGraphics graphics);
    public Position target(Position position, String direction, Position redPosition);
}
