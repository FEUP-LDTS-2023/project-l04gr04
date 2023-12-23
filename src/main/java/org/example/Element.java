package org.example;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Element {
    protected Position position;
    public Element (int x , int y){
        position = new Position(x,y);
    }
    ////////////////////////////////////////////////////
    // Draws                                          //
    ////////////////////////////////////////////////////
    public void drawTheStyle(char[][] pacManImage,TextGraphics graphics, String colorm){ // Standart draw for elements
        int x = position.getX();
        int y = position.getY();
        for (int row = 0; row < pacManImage.length; row++) {
            for (int col = 0; col < pacManImage[row].length; col++) {
                graphics.setBackgroundColor(TextColor.Factory.fromString(colorm));
                if (pacManImage[row][col] != '#') {
                    if (Color.getColor(String.valueOf(pacManImage[row][col])) != null){
                        graphics.setBackgroundColor(TextColor.Factory.fromString(Color.getColor(String.valueOf(pacManImage[row][col]))));
                    }
                    graphics.fillRectangle(new TerminalPosition(x+col,y+row),new TerminalSize(1,1),' ');
                }
            }
        }
    }
    ////////////////////////////////////////////////////
    // Movement                                       //
    ////////////////////////////////////////////////////
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
    ////////////////////////////////////////////////////
    // Getters e Setters                              //
    ////////////////////////////////////////////////////
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public int getX() {
        return position.getX();
    }
    public int getY() {
        return position.getY();
    }


}
