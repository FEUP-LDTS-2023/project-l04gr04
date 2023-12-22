package org.example.Numbers;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.groupcdg.pitest.annotations.DoNotMutate;
import org.example.Color;
import org.example.Element;

public class Character extends Element {
    public char[][] score;
    public char[][] ready;
    public Character(int x, int y){
        super(x,y);

        score = new char[][]{
                {'#','W','W','W','W','#','#','#','#','#','W','W','W','W','#','#','#','W','W','W','W','W','#','#','W','W','W','W','W','W','#','#','W','W','W','W','W','W','#'},
                {'W','W','#','#','W','W','#','#','#','W','W','#','#','W','W','#','W','W','#','#','#','W','W','#','W','W','#','#','#','W','W','#','W','W','#','#','#','#','#'},
                {'W','W','#','#','#','#','#','#','W','W','#','#','#','#','#','#','W','W','#','#','#','W','W','#','W','W','#','#','#','W','W','#','W','W','#','#','#','#','#'},
                {'#','W','W','W','W','W','#','#','W','W','#','#','#','#','#','#','W','W','#','#','#','W','W','#','W','W','#','#','W','W','W','#','W','W','W','W','W','#','#'},
                {'#','#','#','#','#','W','W','#','W','W','#','#','#','#','#','#','W','W','#','#','#','W','W','#','W','W','W','W','W','#','#','#','W','W','#','#','#','#','#'},
                {'W','W','#','#','#','W','W','#','#','W','W','#','#','W','W','#','W','W','#','#','#','W','W','#','W','W','#','#','W','W','#','#','W','W','#','#','#','#','#'},
                {'#','W','W','W','W','W','#','#','#','#','W','W','W','W','#','#','#','W','W','W','W','W','#','#','W','W','#','#','W','W','W','#','W','W','W','W','W','W','#'},


        };
        ready = new char[][]{
                {'a','a','a','a','a','a','#','#','a','a','a','a','a','a','a','#','#','#','a','a','a','#','#','#','a','a','a','a','a','#','#','#','#','a','a','#','#','a','a','#','#','#','#','a','a','a'},
                {'a','a','#','#','#','a','a','#','a','a','#','#','#','#','#','#','#','a','a','#','a','a','#','#','a','a','#','#','a','a','#','#','#','a','a','#','#','a','a','#','#','#','#','a','a','a'},
                {'a','a','#','#','#','a','a','#','a','a','#','#','#','#','#','#','a','a','#','#','#','a','a','#','a','a','#','#','#','a','a','#','#','a','a','#','#','a','a','#','#','#','a','a','a','#'},
                {'a','a','#','#','a','a','a','#','a','a','a','a','a','a','#','#','a','a','#','#','#','a','a','#','a','a','#','#','#','a','a','#','#','#','a','a','a','a','#','#','#','#','a','a','#','#'},
                {'a','a','a','a','a','#','#','#','a','a','#','#','#','#','#','#','a','a','a','a','a','a','a','#','a','a','#','#','#','a','a','#','#','#','#','a','a','#','#','#','#','#','a','#','#','#'},
                {'a','a','#','a','a','a','#','#','a','a','#','#','#','#','#','#','a','a','#','#','#','a','a','#','a','a','#','#','a','a','#','#','#','#','#','a','a','#','#','#','#','#','#','#','#','#'},
                {'a','a','#','#','a','a','a','#','a','a','a','a','a','a','a','#','a','a','#','#','#','a','a','#','a','a','a','a','a','#','#','#','#','#','#','a','a','#','#','#','#','a','#','#','#','#'},
        };
    }

    @DoNotMutate
    public void drawready(TextGraphics graphics) {
        drawTheStyle(ready, graphics, Color.getColor("white"));
    }
    @DoNotMutate
    public void cleanReady(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString(Color.getColor("background")));
        graphics.fillRectangle(new TerminalPosition(position.getX(), position.getY()),new TerminalSize(46,7),' ');
    }
    @DoNotMutate
    public void drawscore(TextGraphics graphics) {
        drawTheStyle(score, graphics, Color.getColor("white"));
    }
}
