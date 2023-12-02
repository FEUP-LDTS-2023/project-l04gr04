package org.example;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Level {
    public int levelNumber;
    private List<String> bonusSymbols = Arrays.asList("Cherries","Strawberry","Peach","Peach",
            "Apple","Apple","Grapes","Grapes","Galaxian","Galaxian","Bell","Bell"); // Above is 'Key'
    private List<Integer> bonusPoints = Arrays.asList(1,3,5,5,7,7,10,10,20,20,30,30); // Above is 50
    private Mapa map;
    public Level(int ln,int width,int height,TextGraphics graphics) throws IOException {
        levelNumber = ln;
        Double pacManSpeed;
        Double ghostSpeed;
        Double pacManFrightSpeed;
        Double ghostFrightSpeed;
        int timeInFright;
        if (levelNumber == 1){
            pacManSpeed = 0.8;
            pacManFrightSpeed = 0.9;
            ghostSpeed = 0.75;
            ghostFrightSpeed = 0.50;
            timeInFright = 6;
            map = new Mapa(width,height,graphics,bonusSymbols.get(0),bonusPoints.get(0)
                    ,pacManSpeed,pacManFrightSpeed,ghostSpeed,ghostFrightSpeed,timeInFright);
            return;
        }
        if ((levelNumber >= 2 && levelNumber <= 4)){
            ghostSpeed = 0.85;
            pacManFrightSpeed = 0.95;
            ghostFrightSpeed = 0.55;
            timeInFright = 4;
        }else{
            ghostSpeed = 0.95;
            pacManFrightSpeed = 1.0;
            ghostFrightSpeed = 0.60;
            timeInFright = 2;
        }
        if ((levelNumber >= 2 && levelNumber <= 4) || levelNumber == 21)pacManSpeed = 0.90;
        else pacManSpeed = 1.0;
        if (levelNumber <= 12)map = new Mapa(width,height,graphics,bonusSymbols.get(levelNumber-1),bonusPoints.get(levelNumber-1),pacManSpeed,pacManFrightSpeed,ghostSpeed,ghostFrightSpeed,timeInFright);
        else map = new Mapa(width,height,graphics,"Key",50,pacManSpeed,pacManFrightSpeed,ghostSpeed,ghostFrightSpeed,1);

    }
    public void draw(TextGraphics graphics,Rectangle dirtyRegion) throws IOException {
        map.draw(graphics,dirtyRegion);
    }
    public boolean processKey(KeyStroke key) throws IOException {
        return map.readInput(key);
    }
    public void gameLoop(List<Rectangle> dirtyRegion) throws InterruptedException {
        map.gameLoop(dirtyRegion);
    }
    public boolean changeLevel(){
        return !map.level_running;
    }

}
