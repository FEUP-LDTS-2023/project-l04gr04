package org.example;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TextColor;

import java.awt.*;
import java.io.*;


import java.io.IOException;

public class Mapa {
    private int width;
    private int height;
    private final String backgroundColor = "#000000";
    private final String wallsColor = "#385A81";
    private final String coinsColor = "#959043";
    private long startTime;

    private int mouthF = 5;
    private int fpsCount = 0;
    private char[][] map;
    private Player player = new Player(0,0);

    private RedMonster red = new RedMonster(10,10);

    public Mapa(int w , int h) throws IOException {
        width = w;
        height = h;
        map = loadMapFromFile("map.txt");
        startTime = System.currentTimeMillis();
    }
    public void draw(TextGraphics graphics, Rectangle dirtyRegion , boolean firstDraw) throws IOException {
        if (firstDraw){
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
            return;
        }
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
        red.draw(graphics);
        player.draw(graphics);
        fpsCount++;
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

