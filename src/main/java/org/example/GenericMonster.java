package org.example;

import com.googlecode.lanterna.graphics.TextGraphics;

public interface GenericMonster {
    public void draw(TextGraphics graphics);
    public  Position target(Position position);

}
