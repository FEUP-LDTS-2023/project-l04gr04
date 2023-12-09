package org.example;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import org.example.Numbers.Score;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Level {
    public int levelNumber;
    private List<String> bonusSymbols = Arrays.asList("cherry","strawberry","orange","orange",
            "apple","apple","melon","melon","galaxian","galaxian","bell","bell"); // Above is 'Key'
    private List<Integer> bonusPoints = Arrays.asList(1,3,5,5,7,7,10,10,20,20,30,30); // Above is 50
    private Mapa map;
    public Level(int ln,int width,int height,TextGraphics graphics) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
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
        else map = new Mapa(width,height,graphics,"key",50,pacManSpeed,pacManFrightSpeed,ghostSpeed,ghostFrightSpeed,1);

    }
    public void draw(TextGraphics graphics,List<Rectangle> dirtyRegions,Score score) throws IOException {
        map.draw(graphics,dirtyRegions,score);
    }
    public void drawInicialMap(TextGraphics graphics) throws IOException {
        map.drawInicialMap(graphics);
    }
    public boolean processKey(KeyStroke key) throws IOException {
        return map.readInput(key);
    }
    public void gameLoop(List<Rectangle> dirtyRegion, Score score) throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        map.gameLoop(dirtyRegion,score);
    }
    public boolean changeLevel(){
        return !map.level_running;
    }

}
