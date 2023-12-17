package org.example;

import com.googlecode.lanterna.graphics.TextGraphics;


public class Lifes extends Element {
    private String playerColor = "#B5D221";
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
    public void decrementLife(){number--;}
    public boolean isempty(){
        return number == 0;
    }
    public void draw(TextGraphics graphics){
        int initialX = position.getX();
        for (int i = 0 ; i < number ; i++){
            position = new Position(position.getX() + i*15 , position.getY());
            drawTheStyle(pacman,graphics,playerColor);
            position = new Position(position.getX() - i*15 , position.getY());
        }
        position = new Position(initialX , position.getY());
    }


}
