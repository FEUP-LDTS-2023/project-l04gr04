package org.example;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import org.example.Numbers.Score;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

public class Game {
    public Screen screen;
    public Terminal terminal;
    public Level level;
    private KeyStroke k = null;
    private Score score = new Score();
    List<Rectangle> dirtyRegions = new ArrayList<>();
    private Timer gameLoopTimer;
    private int gameW;
    private int gameH;
    private TextGraphics graphics;

    public int getGameW() {
        return gameW;
    }
    public int getGameH() {
        return gameH;
    }
    public Game(int w, int h) throws IOException, FontFormatException {
        gameW = w;
        gameH = h;
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
        level = new Level(1,w,h,graphics);
    }
    private void draw() throws IOException {
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
                    runner();
                    draw();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 0, 10); // Refresh at each 16 ms ( ~ 60 FPS)
    }
    public void runner() throws IOException, InterruptedException {
        KeyStroke keystroke = screen.pollInput(); // Listen for input
        if (keystroke != null){
            k = keystroke;
            KeyType keyType = keystroke.getKeyType();
            if (keyType == KeyType.Escape) {
                screen.close();
                stopGameLoop();
            }
        }
        if (level.processKey(k))k = null;
        level.gameLoop(dirtyRegions,score);
        if (level.changeLevel())changeLevel();
    }
    private void changeLevel() throws IOException {
        level = new Level(level.levelNumber + 1, gameW,gameH,graphics);
    }
    public void stopGameLoop() throws IOException {
        gameLoopTimer.cancel();
        gameLoopTimer.purge();
        gameLoopTimer = null;
        screen.close();
    }
}
