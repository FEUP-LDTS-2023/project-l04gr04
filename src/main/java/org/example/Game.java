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

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {
    public Screen screen;
    public Terminal terminal;
    private Level level;
    private KeyStroke k = null;
    private static final int FPS = 144;
    private static final long FRAME_DURATION = 1000 / FPS;
    List<Rectangle> dirtyRegions = new ArrayList<>();
    int gameW;
    int gameH;
    private TextGraphics graphics;
    public Game(int w,int h) throws IOException {
        gameW = w;
        gameH = h;
        try {
            InputStream fontStream = getClass().getClassLoader().getResourceAsStream("square.ttf");
            if (fontStream != null) {
                Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
                Font customFont = font.deriveFont(Font.PLAIN, 2);
                SwingTerminalFontConfiguration fontConfig = new SwingTerminalFontConfiguration(true, SwingTerminalFontConfiguration.BoldMode.EVERYTHING, customFont);
                DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(w, h)).setTerminalEmulatorFontConfiguration(fontConfig);
                terminal = terminalFactory.createTerminal();
                screen = new TerminalScreen(terminal);
                screen.setCursorPosition(null);
                screen.startScreen();
                screen.doResizeIfNecessary();
            } else {
                System.out.println("Font file not found.");
            }
        }catch (IOException | FontFormatException e) {
            e.printStackTrace();
            screen.close();
        }
        graphics = screen.newTextGraphics();
        level = new Level(1,w,h,graphics);
    }
    private void draw() throws IOException {
        for (Rectangle dirtyRegion : dirtyRegions) {
            for (int i = dirtyRegion.y; i < dirtyRegion.y + dirtyRegion.height; i++) {
                for (int j = dirtyRegion.x; j < dirtyRegion.x + dirtyRegion.width; j++) {
                    screen.setCharacter(j, i, new TextCharacter(' '));
                }
            }
            level.draw(graphics, dirtyRegion);
        }
        dirtyRegions.clear();
        screen.refresh();
    }
    public void run() throws IOException {
        long lastFrameTime = System.currentTimeMillis();
        while (true){
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastFrameTime;
            if (elapsedTime >= FRAME_DURATION) {
                draw();
                KeyStroke keystroke = screen.pollInput();
                if (keystroke != null){
                    k = keystroke;
                    KeyType keyType = keystroke.getKeyType();
                    if (keyType == KeyType.Escape) {
                        break;
                    }
                }
                if (level.processKey(k))k = null;
                level.gameLoop(dirtyRegions);
                if (level.changeLevel())changeLevel();
                lastFrameTime = currentTime;
            }else{
                System.out.println("NOT YET");
            }
        }
        screen.close();
    }
    private void changeLevel() throws IOException {
        level = new Level(level.levelNumber + 1, gameW,gameH,graphics);
    }
}
