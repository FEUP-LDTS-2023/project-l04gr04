package org.example;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.awt.*;
import java.io.*;


import java.io.IOException;
import java.util.List;

public class Mapa {
    private int width;
    private int height;
    private final String backgroundColor = "#000000";
    private final String wallsColor = "#385A81";
    private final String coinsColor = "#959043";
    private int monstersF = 5;
    private int playerF = 1;
    private int fpsCount = 0;
    private GameState gameState = new GameState();
    private char[][] map;
    private Player player = new Player(2,1);
    private RedMonster red = new RedMonster(3,25);
    private OrangeMonster orange = new OrangeMonster(62,377);
    private BlueMonster blue = new BlueMonster(300,377);
    private PinkMonster pink = new PinkMonster(241,142);
    private Fruit cherry = new Fruit(100,100);

    private KeyType lastInputMove ;
    public Mapa(int w , int h, TextGraphics graphics) throws IOException {
        width = w;
        height = h;
        map = loadMapFromFile("map.txt");
        gameState.addObserver(player);
        gameState.addObserver(red);
        gameState.addObserver(orange);
        gameState.addObserver(pink);
        gameState.addObserver(blue);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
                if (map[row][col] == '.') {
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                } else if (map[row][col] == 'P') {
                    graphics.setBackgroundColor(TextColor.Factory.fromString(wallsColor));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                } else if (map[row][col] == '0') {
                    graphics.setForegroundColor(TextColor.Factory.fromString(coinsColor));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), '.');
                } else {
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#CC0066"));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                }
            }
        }

    }
    public void gameLoop(List<Rectangle> dirtyRegions){
        dirtyRegions.add(new Rectangle(player.getX(),player.getY(),14,14));
        dirtyRegions.add(new Rectangle(red.getX(),red.getY(),14,14));
        dirtyRegions.add(new Rectangle(orange.getX(),orange.getY(),14,14));
        dirtyRegions.add(new Rectangle(pink.getX(),pink.getY(),14,14));
        dirtyRegions.add(new Rectangle(blue.getX(),blue.getY(),14,14));
        if (fpsCount % monstersF == 0){
            Position redT = red.target(player.position, player.facingDirection, new Position(0,0));
            red.move(redT ,map);
            Position orangeT = orange.target(player.position, player.facingDirection, red.getPosition());
            orange.move(orangeT ,map);
            Position pinkT = pink.target(player.position, player.facingDirection, red.getPosition());
            pink.move(pinkT ,map);
            Position blueT = blue.target(player.position, player.facingDirection, red.getPosition());
            blue.move(blueT ,map);
        }        if (fpsCount % playerF == 0){
            if (lastInputMove == KeyType.ArrowRight){
                if(canMove("right"))player.move("right");
                else if(canMove(player.facingDirection))  player.move(player.facingDirection);
            } else if (lastInputMove == KeyType.ArrowLeft) {
                if(canMove("left"))player.move("left");
                else if(canMove(player.facingDirection))  player.move(player.facingDirection);
            } else if (lastInputMove == KeyType.ArrowUp) {
                if(canMove("up"))player.move("up");
                else if(canMove(player.facingDirection))  player.move(player.facingDirection);
            } else if (lastInputMove == KeyType.ArrowDown) {
                if(canMove("down"))player.move("down");
                else if(canMove(player.facingDirection))  player.move(player.facingDirection);
            }
        }

    }
    public boolean readInput(KeyStroke keyStroke) {
        if (keyStroke == null || (keyStroke.getKeyType() != KeyType.ArrowRight && keyStroke.getKeyType() != KeyType.ArrowLeft &&keyStroke.getKeyType() != KeyType.ArrowUp
                &&keyStroke.getKeyType() != KeyType.ArrowDown)){
            return false;
        }else{
            lastInputMove = keyStroke.getKeyType();
        }
        if (lastInputMove == KeyType.ArrowRight){
            return player.facingDirection == "right";
        } else if (lastInputMove == KeyType.ArrowLeft) {
            return player.facingDirection == "left";
        } else if (lastInputMove == KeyType.ArrowUp) {
            return player.facingDirection == "up";
        } else if (lastInputMove == KeyType.ArrowDown) {
            return player.facingDirection == "down";
        }
        return false;
    }
    public void draw(TextGraphics graphics, Rectangle dirtyRegion) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
        int startX = Math.max(dirtyRegion.x, 0);
        int startY = Math.max(dirtyRegion.y, 0);
        int endX = Math.min(dirtyRegion.x + dirtyRegion.width, width);
        int endY = Math.min(dirtyRegion.y + dirtyRegion.height, height);
        for (int row = startY; row < endY; row++) {
            for (int col = startX; col < endX; col++) {
                graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
                if (map[row][col] == '.') {
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                } else if (map[row][col] == 'P') {
                    graphics.setBackgroundColor(TextColor.Factory.fromString(wallsColor));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                } else if (map[row][col] == '0') {
                    graphics.setForegroundColor(TextColor.Factory.fromString(coinsColor));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), '.');
                } else {
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#CC0066"));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                }
            }
        }
        cherry.draw(graphics);
        red.draw(graphics);
        orange.draw(graphics);
        pink.draw(graphics);
        blue.draw(graphics);
        player.draw(graphics);
        fpsCount++;
    }

    private boolean canMove(String direction){
        int x = player.getX();
        int y = player.getY();
        switch (direction){
            case "up":
                boolean t = true;
                for (int i = 0 ; i < 14 ; i++){
                    if (y-1 >= 0 && y-1 <= 391 && x+i >= 0 && x+i <= 367){
                        if(map[y-1][x+i] == 'P')t = false;
                    }
                }
                return t;
            case "down":
                boolean b = true;
                for (int i = 0 ; i < 14 ; i++){
                    if (y+14 >= 0 && y+14 <= 391 && x+i >= 0 && x+i <= 367){
                        if (map[y+14][x+i] == 'P')b = false;
                    }
                }
                return b;
            case "left":
                boolean e = true;
                for (int i = 0 ; i < 14 ; i++){
                    if (y+i >= 0 && y+i <= 391 && x-1 >= 0 && x-1 <= 367){
                        if (map[y+i][x-1] == 'P')e = false;
                    }
                }
                return e;
            case "right":
                boolean d = true;
                for (int i = 0 ; i < 14 ; i++){
                    if (y+i >= 0 && y+i <= 391 && x+14 >= 0 && x+14 <= 367){
                        if (map[y+i][x+14] == 'P')d = false;
                    }
                }
                return d;
        }
        return true;
    }

    public char[][] loadMapFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        int numRows = height;
        int numCols = width;
        char[][] map = new char[numRows][numCols];
        int row = 0;
        boolean readingFile = true;
        while (readingFile) {
            line = reader.readLine();
            char[] chars = line.toCharArray();
            for (int col = 0; col < chars.length; col++) {
                if (chars[col] == '!') {
                    readingFile = false;
                    break;
                } else if (chars[col] == '#') {
                    break;
                } else {
                    map[row][col] = chars[col];
                }
            }
            row++;
        }
        reader.close();
        return map;
    }
}

