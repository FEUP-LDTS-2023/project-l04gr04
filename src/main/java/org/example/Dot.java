package org.example;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Dot extends Element {
    public boolean SpecialDote;
    char[][] bigDot;
    char[][] smallDot;
    public Dot(int x, int y, boolean bd) {
        super(x, y);
        SpecialDote = bd;

        bigDot = new char[][]{
                {'#','#','Y','Y','#','#','#'},
                {'#','Y','Y','Y','Y','#','#'},
                {'Y','Y','Y','Y','Y','Y','#'},
                {'Y','Y','Y','Y','Y','Y','#'},
                {'#','Y','Y','Y','Y','#','#'},
                {'#','#','Y','Y','#','#','#'},
                {'#','#','#','#','#','#','#'},
        };

        smallDot = new char[][]{
                {'#','#','#','#','#','#','#'},
                {'#','#','#','#','#','#','#'},
                {'#','#','#','#','#','#','#'},
                {'#','#','#','Y','#','#','#'},
                {'#','#','#','#','#','#','#'},
                {'#','#','#','#','#','#','#'},
                {'#','#','#','#','#','#','#'},

        };

    }
    void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString(yellow));
        if (SpecialDote)drawTheStyle(bigDot,graphics,yellow);
        else drawTheStyle(smallDot,graphics,yellow);
    }
}