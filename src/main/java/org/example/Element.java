package org.example;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Element {
    protected Position position;
    public Element(){
        position = new Position(0,0);
    }
    public Element (int x , int y){
        position = new Position(x,y);
    }
    public int getX() {
        return position.getX();
    }
    public int getY() {
        return position.getY();
    }
    public Position moveUp(){return new Position(position.getX(), position.getY()-1);}
    public Position moveDown(){return new Position(position.getX(), position.getY()+1);}
    public Position moveLeft(){return new Position(position.getX()-1, position.getY());}
    public Position moveRight(){return new Position(position.getX()+1, position.getY());}
    public void drawTheStyle(char[][] pacManImage,TextGraphics graphics){
        int x = position.getX();
        int y = position.getY();
        for (int row = 0; row < pacManImage.length; row++) {
            for (int col = 0; col < pacManImage[row].length; col++) {
                if (pacManImage[row][col] != '#') {
                    graphics.fillRectangle(new TerminalPosition(x+col,y+row),new TerminalSize(1,1),pacManImage[row][col]);
                }
            }
        }
    }

}
