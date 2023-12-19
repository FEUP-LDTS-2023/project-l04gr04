package org.example;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.groupcdg.pitest.annotations.DoNotMutate;

public class Dot extends Element {
    public boolean SpecialDote;
    public char[][] bigDot;
    public char[][] smallDot;

    public boolean isSpecialDote() {
        return SpecialDote;
    }

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
    @DoNotMutate
    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString(yellow));
        if (SpecialDote)drawTheStyle(bigDot,graphics,yellow);
        else drawTheStyle(smallDot,graphics,yellow);
    }
}