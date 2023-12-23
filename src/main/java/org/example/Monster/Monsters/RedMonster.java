package org.example.Monster.Monsters;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Others.Color;
import org.example.Others.Position;

public class RedMonster extends Monster {
    public RedMonster(int x,int y){super(x,y);}
    @Override
    public void draw(TextGraphics graphics) {
        super.draw(graphics, Color.getColor("red"));
    }
    public Position target(Position position, String direction, Position redPosition){
        if (ms.modeOn().equals("eaten")) return cagePosition;
        if (ms.modeOn().equals("inCage")) return new Position(cagePosition.getX(), cagePosition.getY()-19);
        if (ms.modeOn().equals("scatter")) return new Position(195, 1);
        return position;
    }
}