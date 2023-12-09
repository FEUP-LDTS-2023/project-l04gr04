package org.example;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.example.Monster.*;
import org.example.Monster.States.eaten;
import org.example.Monster.States.hunt;
import org.example.Numbers.Character;
import org.example.Numbers.Score;

import java.awt.*;
import java.io.*;


import java.io.IOException;
import java.util.*;
import java.util.List;

public class Mapa {
    private int width;
    private int height;
    public boolean level_running = true;
    private final String gateColor = "#FFB8FF";
    private final String backgroundColor = "#000000";
    private final String wallsColor = "#2121DE";
    private final String coinsColor = "#959043";
    private Double baseFrequency = 1.5; // Base velocity
    private final int timeInScout = 10;
    private GameState gameState ;
    private char[][] map;
    private List<Monster> monsters = new ArrayList<>();
    private Player player = new Player(10,241);
    private Fruit cherry = new Fruit(63,241);
    private Fruit orange = new Fruit(50,241);
    private Fruit strawberry = new Fruit(80,241);
    private Fruit melon = new Fruit(25,241);
    private Fruit apple = new Fruit(37,241);
    private Fruit galaxianFlagShip = new Fruit(100,241);
    private Fruit bell = new Fruit(120,241);
    private Fruit key = new Fruit(140,241);
    private Character scoreText = new Character(50,10);
    private Character ready = new Character(79,141);
    private List<Dot> dots = new ArrayList<>();
    private int dotsCounter = 246;
    String yellow = "#FFB897";
    private KeyType lastInputMove ;

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public String getGateColor() {
        return gateColor;
    }
    public String getBackgroundColor() {
        return backgroundColor;
    }
    public String getWallsColor() {
        return wallsColor;
    }
    public String getCoinsColor() {
        return coinsColor;
    }
    public Player getPlayer() {
        return player;
    }
    public List<Monster> getMonsters() {
        return monsters;
    }
    public List<Dot> getDots() {
        return dots;
    }
    public KeyType getLastInputMove() {
        return lastInputMove;
    }
    public int getDotsCounter() {
        return dotsCounter;
    }

    public Mapa(int w , int h, TextGraphics graphics, String bonusSymbol, Integer bonusPoints,
                Double ps, Double pfs, Double gs, Double gfs, int tInF) throws IOException {
        Double monstersFrightF = baseFrequency + baseFrequency * (1 - gfs);
        Double playerFrightF = baseFrequency + baseFrequency * (1 - pfs);
        Double monstersF = baseFrequency + baseFrequency * (1 - gs);
        Double playerF = baseFrequency + baseFrequency * (1 - ps);
        player.atmF = playerF;
        player.playerF = playerF;
        player.playerFrightF = playerFrightF;
        width = w;
        height = h;
        gameState = new GameState(tInF);
        map = loadMapFromFile("map.txt");
        monsters.add(new RedMonster(134,126));
        monsters.add(new OrangeMonster(134,126));
        monsters.add( new BlueMonster(134,126));
        monsters.add(new PinkMonster(134,126));
        for (Monster m : monsters){
            m.monsterF = monstersF;
            m.atmF = monstersF;
            m.monsterFrightF = monstersFrightF;
            gameState.addObserver(m);
        }
        gameState.addObserver(player);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
                if (map[row][col] == '.') {
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                } else if (map[row][col] == 'P' || map[row][col] == 'p') {
                    graphics.setBackgroundColor(TextColor.Factory.fromString(wallsColor));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                } else if (map[row][col] == 'A') {
                    graphics.setBackgroundColor(TextColor.Factory.fromString(gateColor));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                } else if (map[row][col] == 'R') {
                    graphics.setBackgroundColor(TextColor.Factory.fromString(gateColor));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                } else if (map[row][col] == 'a') {
                    graphics.setBackgroundColor(TextColor.Factory.fromString(yellow));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                } else if (map[row][col] == '0') {
                    graphics.setBackgroundColor(TextColor.Factory.fromString(coinsColor));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                }else if (map[row][col] == 'd'){
                    dots.add(new Dot(col,row,false));
                }else if (map[row][col] == 'D'){
                    dots.add(new Dot(col,row,true));
                } else {
                    graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                }
            }
        }
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameState.endFrightHour();
            }
        }, timeInScout * 1000);
    }
    public void gameLoop(List<Rectangle> dirtyRegions,Score score){
        dirtyRegions.add(new Rectangle(player.getX(),player.getY(),14,14));
        for (Monster m : monsters){
            dirtyRegions.add(new Rectangle(m.getX(),m.getY(),14,14));
        }
        playerMovement();
        monsterMovement();
        checkDotCollisions(score);
        checkMonsterCollisions();
        if (dotsCounter == 0)level_running = false;
        player.fps++;
    }
    void monsterMovement(){
        for (Monster m : monsters){
            if ((player.fps > m.atmF * m.monsterM ) || m.ms.modeOn().equals("eaten")){ // Control the frequency of movement
                m.monsterM++;
                Position rp = monsters.get(0).getPosition(); // Red monster position
                Position mt = m.target(player.position, player.facingDirection, rp);
                m.move(mt,map);
                if (mt.equals(m.getPosition()) && m.ms.modeOn().equals("eaten")) m.changeState(new hunt(m)); // If he is eaten and gets to the cage, he comes back to hunt state
            }
        }
    }
    void playerMovement(){
        if ((player.fps > player.atmF * player.playerM )){ // Control the frequency of movement
            player.playerM++;
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
    public void checkDotCollisions(Score score){
        Iterator<Dot> iterator = dots.iterator();
        while (iterator.hasNext()) {
            Dot dot = iterator.next();
            int dx = dot.getX();
            int dy = dot.getY();
            int px = player.getX();
            int py = player.getY();
            if (px <= dx && px + 14 >= dx && py <= dy && py + 14 >= dy) {
                dotsCounter--;
                if (dot.SpecialDote) {
                    gameState.startFrightHour();
                    score.increment(5);
                }else score.increment(1);
                iterator.remove();
            }
        }
    }
    void checkMonsterCollisions(){
        for (Monster m : monsters){
            int mx = m.getX();
            int my = m.getY();
            int px = player.getX();
            int py = player.getY();
            if ((px <= mx && px + 14 - 8 >= mx && py <= my && py + 14 - 8 >= my) || (mx <= px && mx + 14 - 8 >= px && my <= py && my + 14 - 8 >= py)) {
                if (m.ms.modeOn().equals("fright"))m.changeState(new eaten(m));
            }
        }
    }
    public boolean readInput(KeyStroke keyStroke) {
        if (keyStroke == null || (keyStroke.getKeyType() != KeyType.ArrowRight && keyStroke.getKeyType() != KeyType.ArrowLeft &&
                keyStroke.getKeyType() != KeyType.ArrowUp
                && keyStroke.getKeyType() != KeyType.ArrowDown
                ||(player.getPosition().getX() < 0 || player.getPosition().getX() > 181))){
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
    public void draw(TextGraphics graphics, List<Rectangle> dirtyRegions,Score score) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
        for (Rectangle dirtyRegion : dirtyRegions){
            int startX = Math.max(dirtyRegion.x, 0);
            int startY = Math.max(dirtyRegion.y, 0);
            int endX = Math.min(dirtyRegion.x + dirtyRegion.width, width);
            int endY = Math.min(dirtyRegion.y + dirtyRegion.height, height);
            for (int row = startY; row < endY; row++) {
                for (int col = startX; col < endX; col++) {
                        graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
                        if (map[row][col] == '.') {
                            graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                        } else if (map[row][col] == 'P' || map[row][col] == 'p') {
                            graphics.setBackgroundColor(TextColor.Factory.fromString(wallsColor));
                            graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                        } else if (map[row][col] == 'A') {
                            graphics.setBackgroundColor(TextColor.Factory.fromString(gateColor));
                            graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                        } else if (map[row][col] == 'R') {
                            graphics.setBackgroundColor(TextColor.Factory.fromString(gateColor));
                            graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                        } else if (map[row][col] == '0') {
                            graphics.setBackgroundColor(TextColor.Factory.fromString(coinsColor));
                            graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                        } else if (map[row][col] == 'a') {
                            graphics.setBackgroundColor(TextColor.Factory.fromString(yellow));
                            graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                        } else {
                            graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
                            graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                    }
                }
            }
        }

        for (Dot dot : dots){
            dot.draw(graphics);
        }
        for (Monster m : monsters)m.draw(graphics);
        scoreText.drawscore(graphics);
        ready.drawready(graphics);
        score.draw(graphics);
        player.draw(graphics);
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(201, 117), new TerminalSize(14, 14), ' ');
        cherry.drawCherry(graphics);
        strawberry.drawStrawberry(graphics);
        orange.drawOrange(graphics);
        apple.drawApple(graphics);
        melon.drawMelon(graphics);
        galaxianFlagShip.drawFlagShip(graphics);
        key.drawKey(graphics);
        bell.drawBell(graphics);

    }
    public boolean canMove(String direction){
        int x = player.getX();
        int y = player.getY();
        switch (direction){
            case "up":
                boolean t = true;
                for (int i = 0 ; i < 14 ; i++){
                    if (y-1 >= 0 && y-1 <= height && x+i >= 0 && x+i <= width){
                        if(map[y-1][x+i] == 'P' || map[y-1][x+i] == 'c')t = false;
                    }
                }
                return t;
            case "down":
                boolean b = true;
                for (int i = 0 ; i < 14 ; i++){
                    if (y+14 >= 0 && y+14 <= height && x+i >= 0 && x+i <= width){
                        if (map[y+14][x+i] == 'P'||map[y+14][x+i] == 'c')b = false;
                    }
                }
                return b;
            case "left":
                boolean e = true;
                for (int i = 0 ; i < 14 ; i++){
                    if (y+i >= 0 && y+i <= height && x-1 >= 0 && x-1 <= width){
                        if (map[y+i][x-1] == 'P'||map[y+i][x-1] == 'c')e = false;
                    }
                }
                return e;
            case "right":
                boolean d = true;
                for (int i = 0 ; i < 14 ; i++){
                    if (y+i >= 0 && y+i <= height && x+14 >= 0 && x+14 <= width){
                        if (map[y+i][x+14] == 'P'||map[y+i][x+14] == 'c')d = false;
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
