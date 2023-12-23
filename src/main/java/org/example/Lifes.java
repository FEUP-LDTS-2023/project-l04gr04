package org.example;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Lifes extends Element {
    private char [][]pacman = new char[][]{
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
    private int number = 3; // PacMan start with 3 lifes
    public Lifes(int x, int y){
        super(x,y);
    }
    ////////////////////////////////////////////////////
    // Draws                                          //
    ////////////////////////////////////////////////////
    public void draw(TextGraphics graphics){
        int initialX = position.getX();
        for (int i = 0 ; i < number ; i++){
            position = new Position(position.getX() + i*15 , position.getY());
            drawTheStyle(pacman,graphics,Color.getColor("player"));
            position = new Position(position.getX() - i*15 , position.getY());
        }
        position = new Position(initialX , position.getY());
    }
    ////////////////////////////////////////////////////
    // Increment/Decrement                            //
    ////////////////////////////////////////////////////
    public void incrementLife(){
        number++;
        if (number > 6)number = 6;
    }
    public void decrementLife(){number--;}
    ////////////////////////////////////////////////////
    // Getters                                        //
    ////////////////////////////////////////////////////
    public boolean isempty(){
        return number <= 0;
    }
    public int getNumber(){
        return number;
    }
}
