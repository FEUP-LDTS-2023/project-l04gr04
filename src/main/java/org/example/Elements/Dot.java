package org.example.Elements;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Others.Color;

public class Dot extends Element {
    private boolean SpecialDote;
    private char[][] bigDot = new char[][]{
            {'#','#','Y','Y','#','#','#'},
            {'#','Y','Y','Y','Y','#','#'},
            {'Y','Y','Y','Y','Y','Y','#'},
            {'Y','Y','Y','Y','Y','Y','#'},
            {'#','Y','Y','Y','Y','#','#'},
            {'#','#','Y','Y','#','#','#'},
            {'#','#','#','#','#','#','#'},
    };
    private char[][] smallDot = new char[][]{
            {'#','#','#','#','#','#','#'},
            {'#','#','#','#','#','#','#'},
            {'#','#','#','#','#','#','#'},
            {'#','#','#','Y','#','#','#'},
            {'#','#','#','#','#','#','#'},
            {'#','#','#','#','#','#','#'},
            {'#','#','#','#','#','#','#'},

    };
    public Dot(int x, int y, boolean bd) {
        super(x, y);
        SpecialDote = bd;
    }
    ////////////////////////////////////////////////////
    // Draws                                          //
    ////////////////////////////////////////////////////
    public void draw(TextGraphics graphics){
        if (SpecialDote)drawTheStyle(bigDot,graphics, Color.getColor("yellow"));
        else drawTheStyle(smallDot,graphics,Color.getColor("yellow"));
    }
    ////////////////////////////////////////////////////
    // Others                                         //
    ////////////////////////////////////////////////////
    public boolean isSpecialDote() {
        return SpecialDote;
    }
    ////////////////////////////////////////////////////
    // Getters e Setters                              //
    ////////////////////////////////////////////////////
    public char[][] getBigDot() {
        return bigDot;
    }

    public char[][] getSmallDot() {
        return smallDot;
    }
}