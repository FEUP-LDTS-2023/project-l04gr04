package org.example.Monster;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Color;
import org.example.Position;

public class PinkMonster extends Monster {
    public PinkMonster(int x,int y){super(x,y);}
    @Override
    public void draw(TextGraphics graphics) {
        super.draw(graphics,Color.getColor("pink"));
    }
    public Position target(Position position, String direction, Position redPosition){
        if (ms.modeOn().equals("eaten")) return cagePosition;
        if (ms.modeOn().equals("inCage")) return new Position(cagePosition.getX(), cagePosition.getY()-19);
        if (ms.modeOn().equals("scatter")) return new Position(2,1);
        if (direction.equals("up")) return new Position(position.getX(), position.getY() - 4 * 14);
        if (direction.equals("down"))return new Position(position.getX(),position.getY() + 4 * 14);
        if (direction.equals("left"))return new Position(position.getX()  - 4 * 14, position.getY());
        if (direction.equals("right"))return new Position(position.getX() + 4 * 14, position.getY());
        return position;
    }
}