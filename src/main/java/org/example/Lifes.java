package org.example;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.groupcdg.pitest.annotations.DoNotMutate;
import org.example.Monster.Position;


public class Lifes extends Element {
    private char [][]pacman;
    private int number = 3;
    public Lifes(int x, int y){
        super(x,y);
        pacman = new char[][]{
                {'#','#','#','#',' ',' ',' ',' ',' ',' ','#','#','#','#'},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#'},
                {' ',' ',' ',' ',' ',' ',' ',' ','#','#','#','#','#','#'},
                {' ',' ',' ',' ',' ',' ','#','#','#','#','#','#','#','#'},
                {' ',' ',' ',' ',' ',' ','#','#','#','#','#','#','#','#'},
                {' ',' ',' ',' ',' ',' ',' ',' ','#','#','#','#','#','#'},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#'},
                {'#','#','#','#',' ',' ',' ',' ',' ',' ','#','#','#','#'}
        };
    }
    public void incrementLife(){
        number++;
        if (number > 6)number = 6;
    }
    public int getNumber(){
        return number;
    }
    public void decrementLife(){number--;}
    public boolean isempty(){
        return number <= 0;
    }
    @DoNotMutate
    public void draw(TextGraphics graphics){
        int initialX = position.getX();
        for (int i = 0 ; i < number ; i++){
            position = new Position(position.getX() + i*15 , position.getY());
            drawTheStyle(pacman,graphics,Color.getColor("player"));
            position = new Position(position.getX() - i*15 , position.getY());
        }
        position = new Position(initialX , position.getY());
    }


}
