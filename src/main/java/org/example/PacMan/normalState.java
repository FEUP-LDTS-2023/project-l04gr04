package org.example.PacMan;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.groupcdg.pitest.annotations.DoNotMutate;
import org.example.Position;

public class normalState extends pacManState{
    public normalState(Player p) {
        super(p);
    }
    @Override
    @DoNotMutate
    public void draw(TextGraphics graphics) {player.drawNormal(graphics);}

    @Override
    @DoNotMutate
    public void move(String direction) {player.moveNormal(direction);}

}
