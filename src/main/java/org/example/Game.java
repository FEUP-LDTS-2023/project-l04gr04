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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public Screen screen;
    public Terminal terminal;
    private Mapa mapa;
    private static final int FPS = 60;
    private static final long FRAME_DURATION = 1000 / FPS;
    public Game(int w,int h) throws IOException {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();

        System.out.println("Fontes disponíveis no sistema:");
        for (String fontName : fontNames) {
            System.out.println(fontName);
        }
        mapa = new Mapa(w,h);
        Font customFont = new Font("Courier New", Font.PLAIN, 10); // Substitua pelo nome da fonte e tamanho desejados
        SwingTerminalFontConfiguration fontConfig = new SwingTerminalFontConfiguration(true,SwingTerminalFontConfiguration.BoldMode.EVERYTHING, customFont);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(w, h)).setTerminalEmulatorFontConfiguration(fontConfig);
        terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
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
