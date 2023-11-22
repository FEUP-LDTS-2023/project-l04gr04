package org.example;

import com.googlecode.lanterna.TerminalSize;
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
    private Mapa mapa;
    private static final int FPS = 60;
    private static final long FRAME_DURATION = 1000 / FPS;
    public Game(int w,int h) throws IOException, FontFormatException {
        mapa = new Mapa(w,h);
        try {
            InputStream fontStream = getClass().getClassLoader().getResourceAsStream("square.ttf");

            if (fontStream != null) {
                Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);

            Font customFont = font.deriveFont(Font.PLAIN, 10);

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
            System.out.println(1);
            screen.close();
        }
    }
    private void draw() throws IOException {
        screen.clear();
        mapa.draw(screen.newTextGraphics());
        screen.refresh();
    }
    public void run() throws IOException {
        long lastFrameTime = System.currentTimeMillis();
        while (true){
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastFrameTime;
            if (elapsedTime >= FRAME_DURATION) {
                draw();
                KeyStroke keyStroke = screen.pollInput();
                if (keyStroke != null){
                    KeyType keyType = keyStroke.getKeyType();
                    if (keyType == KeyType.Escape) {
                        break;
                    }

                }
                lastFrameTime = currentTime;
            }
        }
        //screen.close();
    }
}
