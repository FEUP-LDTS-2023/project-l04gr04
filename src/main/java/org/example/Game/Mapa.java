package org.example.Game;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.example.Others.Color;
import org.example.Elements.Dot;
import org.example.Elements.Fruit;
import org.example.Elements.Lifes;
import org.example.Interfaces.MapaListener;
import org.example.Monster.MonsterStates.*;
import org.example.Monster.Monsters.*;
import org.example.Chars.Character;
import org.example.Chars.Score;
import org.example.PacMan.Player;
import org.example.PacMan.PacManStates.eatingPacManState;
import org.example.Others.Position;
import org.example.Sounds.soundTrack;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;


import java.io.IOException;
import java.util.*;
import java.util.List;

public class Mapa {
    private int width;
    private int height;
    private int bonusP;
    private int dotsCounter;
    private char[][] map;
    private boolean firstInput = true;
    private boolean losted = false;
    private MapaListener mapaListener;
    private FrightControl gameState;
    private List<Monster> monsters = new ArrayList<>();
    public Player player = new Player(94,180);
    private Fruit fruta;
    private Character scoreText = new Character(50,10);
    private Character ready = new Character(79,141);
    private List<Dot> dots = new ArrayList<>();
    private soundTrack eatingDotsSound = new soundTrack("Sounds/pacmanEating.wav");
    private soundTrack eatingGhost= new soundTrack("Sounds/pacman_eatghost.wav");
    private soundTrack death= new soundTrack("Sounds/pacman_death.wav");
    private KeyType lastInputMove = KeyType.ArrowLeft; // By default, the PacMan start by moving left
    public Mapa(int w , int h, String bonusSymbol, Integer bonusPoints, Double ps, Double pfs, Double gs,
                Double gfs,int tInF,char[][]mapa ) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        width = w;
        height = h;
        map = mapa;
        dotsCounter = countDots();
        bonusP = bonusPoints;
        fruta = new Fruit(90,138,bonusSymbol);
        double baseFrequency = 2.0; // Base velocity
        // Frequency of movement calculated based on a Base velocity
        double monstersFrightF = baseFrequency + baseFrequency * (1 - gfs) + 0.2;
        double playerFrightF = baseFrequency + baseFrequency * (1 - pfs) - 0.2;
        double monstersF = baseFrequency + baseFrequency * (1 - gs);
        double playerF = baseFrequency + baseFrequency * (1 - ps);
        player.atmF = playerF;
        player.playerF = playerF;
        player.playerFrightF = playerFrightF;
        //Setting the monsters
        monsters.add(new RedMonster(90,115));
        monsters.add(new OrangeMonster(82,115));
        monsters.add( new BlueMonster(98,115));
        monsters.add(new PinkMonster(106,115));
        gameState = new FrightControl(tInF);
        gameState.FrightClockIniciate();
        for (Monster m : monsters){
            m.monsterF = monstersF;
            m.atmF = monstersF;
            m.monsterFrightF = monstersFrightF;
            gameState.addObserver(m);
        }
        gameState.addObserver(player);
        soundTrack st = new soundTrack("Sounds/pacman_beginning.wav");
        st.play();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                st.stop();
                firstInput = false;
                actions();
            }
        }, 4500);
    }
    private void actions(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for(Monster m : monsters)m.changeState(new scatter(m));
            }
        }, 2000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (Monster m : monsters)m.changeState(new hunt(m));
            }
        }, 10000);
    }
    ////////////////////////////////////////////////////
    // Draws                                          //
    ////////////////////////////////////////////////////
    public void draw(TextGraphics graphics, List<Rectangle> dirtyRegions, Score score, Lifes lifes, List<Fruit> frutas) throws IOException {
        if (firstInput)drawInicialMap(graphics,frutas,lifes,score);
        else drawNormal(graphics,dirtyRegions,score,lifes);
    }
    public void drawInicialMap(TextGraphics graphics, List<Fruit> frutas,Lifes lifes,Score score){
        dots.clear();
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                graphics.setBackgroundColor(TextColor.Factory.fromString(Color.getColor("background")));
                if (map[row][col] == 'd'){
                    dots.add(new Dot(col,row,false));
                }else if (map[row][col] == 'D'){
                    dots.add(new Dot(col,row,true));
                }else if (Color.getColor(String.valueOf(map[row][col])) != null){
                   graphics.setBackgroundColor(TextColor.Factory.fromString(Color.getColor(String.valueOf(map[row][col]))));
                }
                graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
            }
        }
        drawFrutas(graphics,frutas);
        drawGeneralObjects(graphics,score,lifes);
        ready.drawready(graphics);
    }
    public void drawNormal(TextGraphics graphics, List<Rectangle> dirtyRegions,Score score,Lifes lifes){
        ready.cleanReady(graphics);
        for (Rectangle dirtyRegion : dirtyRegions){
            int startX = Math.max(dirtyRegion.x, 0);
            int startY = Math.max(dirtyRegion.y, 0);
            int endX = Math.min(dirtyRegion.x + dirtyRegion.width, width);
            int endY = Math.min(dirtyRegion.y + dirtyRegion.height, height);
            for (int row = startY; row < endY; row++) {
                for (int col = startX; col < endX; col++) {
                    graphics.setBackgroundColor(TextColor.Factory.fromString(Color.getColor("background")));
                    if (Color.getColor(String.valueOf(map[row][col])) != null){
                        graphics.setBackgroundColor(TextColor.Factory.fromString(Color.getColor(String.valueOf(map[row][col]))));
                    }
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                }
            }
        }
        if (!firstInput && fruta != null)fruta.draw(graphics);
        drawGeneralObjects(graphics,score,lifes);
        // Black box so you don't see the Player/Monster when they travel from right to left
        graphics.setBackgroundColor(TextColor.Factory.fromString(Color.getColor("background")));
        graphics.fillRectangle(new TerminalPosition(201, 117), new TerminalSize(14, 14), ' ');
    }
    private void drawFrutas(TextGraphics graphics,List<Fruit> frutas){
        int i = 0;
        for(Fruit f : frutas){
            graphics.setBackgroundColor(TextColor.Factory.fromString(Color.getColor("background")));
            int initialX = f.getX();
            f.setPosition(new Position(f.getX() + i*15 , f.getY()));
            graphics.fillRectangle(new TerminalPosition(f.getX(), f.getY()), new TerminalSize(14, 14), ' ');
            f.draw(graphics);
            f.setPosition(new Position(initialX , f.getY()));
            i++;
        }
    }
    private void drawGeneralObjects(TextGraphics graphics,Score score,Lifes lifes){
        for (Dot dot : dots){
            dot.draw(graphics);
        }
        lifes.draw(graphics);
        scoreText.drawscore(graphics);
        score.draw(graphics);
        for (Monster m : monsters)m.draw(graphics);
        player.draw(graphics);
    }
    ////////////////////////////////////////////////////
    // Game Loop                                      //
    ////////////////////////////////////////////////////
    public void gameLoop(List<Rectangle> dirtyRegions,Score score,Lifes lifes) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (!firstInput ){
            dirtyRegions.add(new Rectangle(player.getX(),player.getY(),14,14));
            for (Monster m : monsters){
                dirtyRegions.add(new Rectangle(m.getX(),m.getY(),14,14));
            }
            playerMovement();
            monsterMovement();
            checkDotCollisions(score);
            checkMonsterCollisions(lifes);
            if (fruta != null)checkFruitCollision(score);
            if (dotsCounter == 0 && fruta == null){
                gameState.stopMusic();
                gameState.closeMusic();
                mapaListener.levelWon();
            }
            if (player.allMonsterseaten())lifes.incrementLife();
            player.fps++;
        }
    }
    ////////////////////////////////////////////////////
    // Input                                          //
    ////////////////////////////////////////////////////
    public boolean readInput(KeyStroke keyStroke) {
        if (firstInput)return false;
        if (keyStroke == null || (keyStroke.getKeyType() != KeyType.ArrowRight && keyStroke.getKeyType() != KeyType.ArrowLeft &&
                keyStroke.getKeyType() != KeyType.ArrowUp && keyStroke.getKeyType() != KeyType.ArrowDown
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
    ////////////////////////////////////////////////////
    // Movement                                       //
    ////////////////////////////////////////////////////
    public void monsterMovement(){
        for (Monster m : monsters){
            if ((player.fps > m.atmF * m.monsterM ) || m.ms.modeOn().equals("eaten")){ // Control the frequency of movement
                m.monsterM++;
                Position rp = monsters.get(0).getPosition(); // Red monster position
                Position mt = m.target(player.getPosition(), player.facingDirection, rp);
                m.move(mt,map);
                if (mt.equals(m.getPosition()) && m.ms.modeOn().equals("eaten")){
                    m.changeState(new inCage(m)); // If he is eaten and gets to the cage, he comes back to hunt state
                    player.incrementCount();
                }
                else if (mt.equals(m.getPosition()) && m.ms.modeOn().equals("inCage")) m.changeState(new hunt(m));
            }
        }
    }
    public void playerMovement(){
        if (player.fps > player.atmF * player.playerM ){ // Control the frequency of movement
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
    ////////////////////////////////////////////////////
    // Collisions                                     //
    ////////////////////////////////////////////////////
    public void checkDotCollisions(Score score){
        Iterator<Dot> iterator = dots.iterator();
        while (iterator.hasNext()) {
            Dot dot = iterator.next();
            int dx = dot.getX();
            int dy = dot.getY();
            int px = player.getX();
            int py = player.getY();
            if (px <= dx && px + 10 >= dx && py <= dy && py + 10 >= dy) {
                eatingDotsSound.play();
                map[dy][dx] = '.';
                dotsCounter--;
                if (dot.isSpecialDote()) {
                    gameState.startFrightHour();
                    score.increment(5);
                }else score.increment(1);
                iterator.remove();
            }
        }
    }
    public void checkMonsterCollisions(Lifes lifes){
        for (Monster m : monsters){
            int mx = m.getX();
            int my = m.getY();
            int px = player.getX();
            int py = player.getY();
            if ((px <= mx && px + 14 - 8 >= mx && py <= my && py + 14 - 8 >= my) || (mx <= px && mx + 14 - 8 >= px && my <= py && my + 14 - 8 >= py)) {
                if (m.ms.modeOn().equals("fright")){
                    eatingGhost.play();
                    m.changeState(new eaten(m));
                }else if(!m.ms.modeOn().equals("eaten") && !m.ms.modeOn().equals("onCollision")){
                    if (!losted){
                        lostOneLife(lifes);
                        losted = true;
                    }
                    break;
                }
            }
        }
    }
    public void checkFruitCollision(Score score){
        int dx = fruta.getX();
        int dy = fruta.getY();
        int px = player.getX();
        int py = player.getY();
        if (px <= dx && px + 10 >= dx && py <= dy && py + 10 >= dy){
            fruta = null;
            score.increment(bonusP);
        }
    }
    ////////////////////////////////////////////////////
    // Others                                         //
    ////////////////////////////////////////////////////
    public void lostOneLife(Lifes lifes){
        warnMapStopMusic();
        lifes.decrementLife();
        for (Monster m : monsters)m.changeState(new onCollision(m));
        player.ps.changeState(new eatingPacManState(player));
        death.play();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    if(lifes.isempty())mapaListener.gameLost();
                    else mapaListener.levelLost(map);
                } catch (UnsupportedAudioFileException e) {
                    throw new RuntimeException(e);
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 2000);
    }

    public boolean canMove(String direction){
        int x = player.getX();
        int y = player.getY();
        switch (direction){
            case "up":
                boolean t = true;
                for (int i = 0 ; i < 14 ; i++){
                    if (y-1 >= 0 && y-1 <= height && x+i >= 0 && x+i < width){
                        if(map[y-1][x+i] == 'P' || map[y-1][x+i] == 'c')t = false;
                    }
                }
                return t;
            case "down":
                boolean b = true;
                for (int i = 0 ; i < 14 ; i++){
                    if (y+14 >= 0 && y+14 <= height && x+i >= 0 && x+i < width){
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
    public void warnMapStopMusic() {
        gameState.stopMusic();
        gameState.closeMusic();
    }
    private int countDots(){
        int c = 0;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (map[row][col] == 'd' || map[row][col] == 'D')c++;
            }
        }
        return c;
    }
    public void setMapaListener(MapaListener listener) {
        this.mapaListener = listener;
    }
    ////////////////////////////////////////////////////
    // Getters e Setters                              //
    ////////////////////////////////////////////////////
    public List<Dot> getDots() {
        List<Dot> dots = new ArrayList<>();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (map[row][col] == 'd')dots.add(new Dot(row,col,false));
                else if(map[row][col] == 'D')dots.add(new Dot(row,col,true));
            }
        }
        return dots;
    }
    public void setDots(List<Dot> d){
        dots = d;
    }
    public void setOffFirstInput(){
        firstInput = false;
    }

    public void setFruta(Fruit fruta) {
        this.fruta = fruta;
    }

    public void setLastInputMove(KeyType lastInputMove) {
        this.lastInputMove = lastInputMove;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public void setGameState(FrightControl gameState) {
        this.gameState = gameState;
    }

    public void setDotsCounter(int dotsCounter) {
        this.dotsCounter = dotsCounter;
    }

    public int getDotsCounter() {
        return dotsCounter;
    }
    public Player getPlayer() {
        return player;
    }
    public List<Monster> getMonsters() {
        return monsters;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public KeyType getLastInputMove() {
        return lastInputMove;
    }
    public MapaListener getMapaListener() {
        return mapaListener;
    }
}