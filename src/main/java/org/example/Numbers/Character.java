package org.example.Numbers;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Element;

public class Character extends Element {
    char[][] score;
    char[][] ready;
    public Character (int x, int y){
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

    public void drawready(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        //drawTheStyle(score, graphics, "#FFFFFF");
        drawTheStyle(ready, graphics, "#FFFFFF");
    }
    public void cleanReady(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(position.getX(), position.getY()),new TerminalSize(46,7),' ');
    }
    public void drawscore(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        drawTheStyle(score, graphics, "#FFFFFF");
        //drawTheStyle(ready, graphics, "#FFFFFF");
    }
}
