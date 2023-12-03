package org.example;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Element {
    protected Position position;
    String red = "#EC2324";
    String white ="#FFFFFF";
    String brown = "#9F6022";
    String yellow = "#FFB897";
    String readyColor = "#B5D221";
    String lightgreen = "#00FD00";
    public String monsterYesColor = " #0000FF";
    public Element(){
        position = new Position(0,0);
    }
    public Element (int x , int y){
        position = new Position(x,y);
    }
    public Position getPosition() {
        return position;
    }
    public int getX() {
        return position.getX();
    }
    public int getY() {
        return position.getY();
    }
    public Position moveUp(){return new Position(position.getX(), position.getY()-1);}
    public Position moveDown(){return new Position(position.getX(), position.getY()+1);}
    public Position moveLeft(){
        if (position.equals(new Position(-20, 117))) return new Position(208, 117);
        return new Position(position.getX()-1, position.getY());
    }
    public Position moveRight(){
        if (position.equals(new Position(205, 117))) return new Position(-20, 117);
        return new Position(position.getX()+1, position.getY());
    }
    public void drawTheStyle(char[][] pacManImage,TextGraphics graphics, String colorm){
        int x = position.getX();
        int y = position.getY();
        for (int row = 0; row < pacManImage.length; row++) {
            for (int col = 0; col < pacManImage[row].length; col++) {
                graphics.setBackgroundColor(TextColor.Factory.fromString(colorm));
                if (pacManImage[row][col] != '#') {
                    if(pacManImage[row][col] == '0'){
                        graphics.setBackgroundColor(TextColor.Factory.fromString(monsterYesColor));
                    } else if (pacManImage[row][col] == 'R') {
                        graphics.setBackgroundColor(TextColor.Factory.fromString(red));
                    } else if (pacManImage[row][col] == 'W') {
                        graphics.setBackgroundColor(TextColor.Factory.fromString(white));
                    } else if (pacManImage[row][col] == 'B') {
                        graphics.setBackgroundColor(TextColor.Factory.fromString(brown));
                    }else if (pacManImage[row][col] == 'Y') {
                        graphics.setBackgroundColor(TextColor.Factory.fromString(yellow));
                    }else if (pacManImage[row][col] == 'g') {
                        graphics.setBackgroundColor(TextColor.Factory.fromString(lightgreen));
                    }else if (pacManImage[row][col] == 'a') {
                         graphics.setBackgroundColor(TextColor.Factory.fromString(readyColor));}
                    graphics.fillRectangle(new TerminalPosition(x+col,y+row),new TerminalSize(1,1),' ');
                }
            }
        }
    }
}
