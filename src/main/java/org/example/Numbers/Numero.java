package org.example.Numbers;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Element;

public class Numero extends Element {
    char[][] num;
    Score score = new Score();
    public Numero(int x, int y){
        super(x,y);
    }
    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        drawTheStyle(num,graphics,"#FFFFFF");
    }
    public void changeNumber(int k){
        switch (k){
            case 0:
                num = new char[][]{
                        {'#','#','Y','Y','#','#','#'},
                        {'#','Y','Y','Y','Y','#','#'},
                        {'Y','Y','Y','Y','Y','Y','#'},
                        {'Y','Y','Y','Y','Y','Y','#'},
                        {'#','Y','Y','Y','Y','#','#'},
                        {'#','#','Y','Y','#','#','#'},
                        {'#','#','#','#','#','#','#'},
                };
                break;
            case 1:
                num = new char[][]{
                        {'#','#','#','#','Y','#','#'},
                        {'#','#','#','Y','Y','#','#'},
                        {'#','#','Y','Y','Y','#','#'},
                        {'#','Y','Y','Y','Y','#','#'},
                        {'#','Y','Y','Y','Y','#','#'},
                        {'#','#','#','Y','Y','#','#'},
                        {'#','#','#','Y','Y','#','#'},
                };
                break;
        }
    }



}
