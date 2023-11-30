package org.example;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Level {
    public int levelNumber;
    public boolean game_running = true;
    Mapa map;
    public Level(int ln,int width,int height,TextGraphics graphics) throws IOException {
        levelNumber = ln;
        map = new Mapa(width,height,graphics);
    }
    public void draw(TextGraphics graphics,Rectangle dirtyRegion) throws IOException {
        map.draw(graphics,dirtyRegion);
    }
    public boolean processKey(KeyStroke key) throws IOException {
        return map.readInput(key);
    }
    public void gameLoop(List<Rectangle> dirtyRegion){
        if (!map.level_running)changeLevel();
        map.gameLoop(dirtyRegion);
    }
    public boolean changeLevel(){
        return !map.level_running;
    }

}
