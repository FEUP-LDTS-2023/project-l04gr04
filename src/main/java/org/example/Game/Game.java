package org.example.Game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import org.example.Others.Color;
import org.example.Elements.Fruit;
import org.example.Elements.Lifes;
import org.example.Game.GameStates.*;
import org.example.Interfaces.MapaListener;
import org.example.Numbers.Score;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Game implements MapaListener {
    private char menu[][];
    private char pausa[][];
    private char info[][];
    private char map[][];
    private int levelNumber = 1;
    private int gameW;
    private int gameH;
    private Screen screen;
    private Terminal terminal;
    private TextGraphics graphics;
    private Timer gameLoopTimer;
    private Level level;
    private KeyStroke TemporaryKey = null;
    private GameState gameState;
    private Score score = new Score();
    private List<Fruit> frutas = new ArrayList<>();
    private Lifes lifes = new Lifes(5,243);
    private List<Rectangle> dirtyRegions = new ArrayList<>(); // Regions where the screen should clear and refresh at every frame
    private boolean isGameRunning = true;
    public Game(int w, int h){
        gameW = w;
        gameH = h;
        gameState = new menuState(this);
    }
    public void initialize() throws IOException, FontFormatException {
        menu = loadMapFromFile("menu.txt");
        pausa = loadMapFromFile("pausa.txt");
        map = loadMapFromFile("map.txt");
        info = loadMapFromFile("info.txt");
        InputStream fontStream = getClass().getClassLoader().getResourceAsStream("square.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
        Font customFont = font.deriveFont(Font.PLAIN, 2);
        SwingTerminalFontConfiguration fontConfig = new SwingTerminalFontConfiguration(true, SwingTerminalFontConfiguration.BoldMode.EVERYTHING, customFont);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(gameW, gameH)).setTerminalEmulatorFontConfiguration(fontConfig);
        terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        graphics = screen.newTextGraphics();
    }
    ////////////////////////////////////////////////////
    // Game Loop                                      //
    ////////////////////////////////////////////////////

    public void gameLoop(){
        gameLoopTimer = new Timer();
        gameLoopTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                try {
                    KeyStroke keystroke = screen.pollInput();
                    if (keystroke != null && keystroke.getKeyType() == KeyType.EOF)stopGameLoop();
                    if (isGameRunning) {
                        gameState.input(keystroke);
                        gameState.draw();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedAudioFileException e) {
                    throw new RuntimeException(e);
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 0, 5);
    }
    ////////////////////////////////////////////////////
    // Draws                                          //
    ////////////////////////////////////////////////////
    public void changingLevelDraw(boolean back) throws IOException {
        for (int row = 0; row < gameH; row++) {
            for (int col = 0; col < gameW; col++) {
                graphics.setBackgroundColor(TextColor.Factory.fromString(Color.getColor("background")));
                if (map[row][col] == 'P' || map[row][col] == 'p') {
                    if (back) graphics.setBackgroundColor(TextColor.Factory.fromString(Color.getColor("walls")));
                    else graphics.setBackgroundColor(TextColor.Factory.fromString(Color.getColor("yellow")));
                }else if (Color.getColor(String.valueOf(map[row][col])) != null){
                    graphics.setBackgroundColor(TextColor.Factory.fromString(Color.getColor(String.valueOf(map[row][col]))));
                }
                graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
            }
        }
        screen.refresh();
    }
    public void drawMenu(int barOn) throws IOException {
        for (int row = 0; row < gameH; row++) {
            for (int col = 0; col < gameW; col++) {
                if(menu[row][col] == '5'){
                    graphics.setBackgroundColor(TextColor.Factory.fromString(Color.getColor("background")));
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#d3d3d3"));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                }
                else if (Character.getNumericValue(menu[row][col]) == barOn){
                    graphics.setBackgroundColor(TextColor.Factory.fromString(Color.getColor("background")));
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#CCCC00"));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                }else if (menu[row][col] == '0' || menu[row][col] == '1' || menu[row][col] == '2'){
                    graphics.setBackgroundColor(TextColor.Factory.fromString(Color.getColor("background")));
                    graphics.setBackgroundColor(TextColor.Factory.fromString(Color.getColor("walls")));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                }
            }
        }
        screen.refresh();
    }
    public void drawPause(int barOn) throws IOException {
        for (int row = 0; row < gameH; row++) {
            for (int col = 0; col < gameW; col++) {
                if(pausa[row][col] == '5'){
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#d3d3d3"));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                }
                else if (Character.getNumericValue(pausa[row][col]) == barOn){
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#CCCC00"));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                }else if (pausa[row][col] == '0' || pausa[row][col] == '1'){
                    graphics.setBackgroundColor(TextColor.Factory.fromString(Color.getColor("walls")));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                }
            }
        }
        screen.refresh();
    }
    public void drawInfo() throws IOException {
        for (int row = 0; row < gameH; row++) {
            for (int col = 0; col < gameW; col++) {
                if (info[row][col] == '0') {
                    graphics.setBackgroundColor(TextColor.Factory.fromString(Color.getColor("background")));
                    graphics.setBackgroundColor(TextColor.Factory.fromString(Color.getColor("y")));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                }else if (info[row][col] == '5'|| info[row][col] == '1') {
                    graphics.setBackgroundColor(TextColor.Factory.fromString(Color.getColor("background")));
                    graphics.setBackgroundColor(TextColor.Factory.fromString(Color.getColor("walls")));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                }

            }
        }
        screen.refresh();
    }
    public void drawLevel() throws IOException {
        for (Rectangle dirtyRegion : dirtyRegions) {
            for (int i = dirtyRegion.y; i < dirtyRegion.y + dirtyRegion.height; i++) {
                for (int j = dirtyRegion.x; j < dirtyRegion.x + dirtyRegion.width; j++) {
                    screen.setCharacter(j, i, new TextCharacter(' ')); // Basically clearing that area
                }
            }
        }
        if (level != null)level.draw(graphics, dirtyRegions,score,lifes,frutas); // Drawing in that area
        dirtyRegions.clear();
        screen.refresh();
    }
    public void drawInicialMap(){
        if (level != null)level.drawInicialMap(graphics,frutas,lifes,score);
    }
    ////////////////////////////////////////////////////
    // Input                                          //
    ////////////////////////////////////////////////////
    public void gameplayInput(KeyStroke keystroke) throws IOException,UnsupportedAudioFileException, LineUnavailableException {
        if (keystroke != null){
            TemporaryKey = keystroke;
        }
        if (level != null){
            if (level.processKey(TemporaryKey))TemporaryKey = null;
            level.gameLoop(dirtyRegions,score,lifes);
        }
    }
    ////////////////////////////////////////////////////
    // Mapa Listener                                  //
    ////////////////////////////////////////////////////
    @Override
    public void gameLost(){
        level = null;
        resetStructs();
        gameState.changeState(new menuState(this));
    }
    @Override
    public void levelLost(char[][] mapa) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        gameState.changeState(new retryingLevelState(this));
        level = null;
        level = new Level(levelNumber,gameW,gameH,mapa);
        level.setMapaListener(this);
        gameState.changeState(new playingState(this));
    }

    @Override
    public void levelWon() {
        levelNumber++;
        frutas.add(new Fruit(110,243, level.fruta));
        if (frutas.size() > 6)frutas.remove(0);
        level = null;
        gameState.changeState(new changingLevel(this));
    }
    ////////////////////////////////////////////////////
    // Others                                         //
    ////////////////////////////////////////////////////
    public void screenClear(){
        if(screen != null) {
            screen.clear();
        }
    }
    public void createLevel() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        level = new Level(levelNumber,gameW,gameH,loadMapFromFile("map.txt"));
        level.setMapaListener(this);
    }
    public void resetStructs(){
        score = new Score();
        lifes = new Lifes(5,243);
        frutas = new ArrayList<>();
        levelNumber = 1;
        TemporaryKey = null;
    }
    public void stopGameLoop() throws IOException {
        if (level != null) warnMapStopMusic();
        isGameRunning = false;
        gameLoopTimer.cancel();
        gameLoopTimer = null;
        screen.close();
    }
    public void warnMapStopMusic(){
        level.warnMapStopMusic();
    }
    public void changeState(GameState newState) {
        gameState = newState;
    }
    public char[][] loadMapFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        int numRows = gameH;
        int numCols = gameW;
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
    ////////////////////////////////////////////////////
    // Getters                                        //
    ////////////////////////////////////////////////////
    public int getGameW() {
        return gameW;
    }
    public int getGameH() {
        return gameH;
    }
    public Level getLevel() {
        return level;
    }

    public GameState getApplicationState() {
        return gameState;
    }


}