package org.example;

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
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import org.example.GameStates.ApplicationState;
import org.example.GameStates.changingLevel;
import org.example.GameStates.menuState;
import org.example.Numbers.Score;
import org.example.Sounds.soundTrack;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Game {
    private final String backgroundColor = "#000000";
    private final String wallsColor = "#2121DE";
    private final String gateColor = "#FFB8FF";
    private final String coinsColor = "#959043";
    private final String yellow = "#FFB897";
    private int levelNumber = 100;
    public Screen screen;
    public Terminal terminal;
    private Level level;
    private KeyStroke k = null;
    private Score score = new Score();
    List<Rectangle> dirtyRegions = new ArrayList<>();
    private Timer gameLoopTimer;
    private int gameW;
    private int gameH;
    private TextGraphics graphics;
    private char menu[][];
    private char pausa[][];
    private char map[][];
    public boolean onPause = false;
    public boolean firstInput = true;
    private ApplicationState applicationState;
    public soundTrack st = new soundTrack("Sounds/pacman_beginning.wav");
    public Game(int w,int h) throws IOException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        st.play();
        gameW = w;
        gameH = h;
        menu = loadMapFromFile("menu.txt");
        pausa = loadMapFromFile("pausa.txt");
        map = loadMapFromFile("map.txt");
        InputStream fontStream = getClass().getClassLoader().getResourceAsStream("square.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
        Font customFont = font.deriveFont(Font.PLAIN, 2);
        SwingTerminalFontConfiguration fontConfig = new SwingTerminalFontConfiguration(true, SwingTerminalFontConfiguration.BoldMode.EVERYTHING, customFont);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(w, h)).setTerminalEmulatorFontConfiguration(fontConfig);
        terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        graphics = screen.newTextGraphics();
        applicationState = new menuState(this);
        graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
        for (int row = 0; row < gameH; row++) {
            for (int col = 0; col < gameW; col++) {
                graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
            }
        }
    }
    public void changingLevelDraw(boolean back) throws IOException {
        screen.clear();
        for (int row = 0; row < gameH; row++) {
            for (int col = 0; col < gameW; col++) {
                graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
                if (map[row][col] == '.') {
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                } else if (map[row][col] == 'P' || map[row][col] == 'p') {
                    if(back)graphics.setBackgroundColor(TextColor.Factory.fromString(wallsColor));
                    else graphics.setBackgroundColor(TextColor.Factory.fromString(yellow));
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
                } else {
                    graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                }
            }
        }
        screen.refresh();
    }
    public void drawMenu(int barOn) throws IOException {
        for (int row = 0; row < gameH; row++) {
            for (int col = 0; col < gameW; col++) {
                if (Character.getNumericValue(menu[row][col]) == barOn){
                    graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
                    screen.setCharacter(row, col, new TextCharacter(' ')); // Basically clearing that area
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#CCCC00"));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                }else if (menu[row][col] == '0' || menu[row][col] == '1' || menu[row][col] == '2'){
                    graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
                    screen.setCharacter(row, col, new TextCharacter(' ')); // Basically clearing that area
                    graphics.setBackgroundColor(TextColor.Factory.fromString(wallsColor));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                }
            }
        }
        screen.refresh();
    }
    public void drawPause(int barOn) throws IOException {
        for (int row = 0; row < gameH; row++) {
            for (int col = 0; col < gameW; col++) {
                if (Character.getNumericValue(menu[row][col]) == barOn){
                    graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
                    screen.setCharacter(row, col, new TextCharacter(' ')); // Basically clearing that area
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#CCCC00"));
                    graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(1, 1), ' ');
                }else if (menu[row][col] == '0' || menu[row][col] == '1'){
                    graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));
                    screen.setCharacter(row, col, new TextCharacter(' ')); // Basically clearing that area
                    graphics.setBackgroundColor(TextColor.Factory.fromString(wallsColor));
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
        level.draw(graphics, dirtyRegions,score); // Drawing in that area
        dirtyRegions.clear();
        screen.refresh();
    }
    public void gameLoop(){
        gameLoopTimer = new Timer();
        gameLoopTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                try {
                    KeyStroke keystroke = screen.pollInput();
                    applicationState.input(keystroke);
                    applicationState.draw();
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
        }, 0, 10); // Refresh at each 16 ms ( ~ 60 FPS)
    }
    public void gameplayInput(KeyStroke keystroke) throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        if (keystroke != null){
            firstInput = false;
            k = keystroke;
            KeyType keyType = keystroke.getKeyType();
            if (keyType == KeyType.Escape) {
                screen.close();
                stopGameLoop();
            }
        }
        if (!firstInput){
            if (level.processKey(k))k = null;
            level.gameLoop(dirtyRegions,score);
            firstInput = false;
        }
        if (level.changeLevel())changeLevel();

    }
    public void startGameplay() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        st.stop();
        screen.clear();
        if (!onPause){
            level = new Level(levelNumber,gameW,gameH,graphics);
        }
    }
    private void changeLevel() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        levelNumber++;
        applicationState = new changingLevel(this);
        firstInput = true;
    }
    public void drawInicialMap() throws IOException {level.drawInicialMap(graphics);}
    public void stopGameLoop() throws IOException {
        gameLoopTimer.cancel();
        gameLoopTimer.purge();
        gameLoopTimer = null;
        screen.close();
    }

    public void changeState(ApplicationState newState) {
        applicationState = newState;
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
}
