package org.example.Numbers;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Element;

public class Character extends Element {
    char[][] score;
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
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        drawTheStyle(score, graphics, "#FFFFFF");
    }
}
