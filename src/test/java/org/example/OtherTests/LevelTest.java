package org.example.OtherTests;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import com.groupcdg.pitest.annotations.DoNotMutate;
import org.example.*;
import org.example.Numbers.Score;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class LevelTest {
    @Test
    public void testInitialization() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
       Game game = new Game(220, 270);
        // Test level 1 initialization
        Level level1 = new Level(1, 10, 10,game.loadMapFromFile("map.txt"));
        assertEquals(1, level1.levelNumber);
        assertEquals("cherry", level1.fruta);
        assertNotNull(level1.getMap());
        // Test level 5 initialization
        Level level5 = new Level(5, 10, 10,game.loadMapFromFile("map.txt"));
        assertEquals(5, level5.levelNumber);
        assertEquals("apple", level5.fruta);
        assertNotNull(level5.getMap());
    }
    /*
    @Test
    public void testGamefeatures() throws IOException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        Game game = new Game(220, 270,null,null,null);
        game.initialize();
        game.createLevel();
        game.gameLoop();
        game.warnMapStopMusic();
    }*/
    @Test
    public void processKey() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Game game = new Game(220,270);
        Mapa mapMock = mock(Mapa.class);
        Level level = new Level(1,220,270,game.loadMapFromFile("map.txt"));
        level.setMap(mapMock);
        KeyStroke keyStroke = mock(KeyStroke.class);
        level.processKey(keyStroke);
        verify(mapMock).readInput(keyStroke);
    }
    @Test
    public void gameLoop() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Game game = new Game(220,270);
        Mapa mapMock = mock(Mapa.class);
        Level level = new Level(1,220,270,game.loadMapFromFile("map.txt"));
        level.setMap(mapMock);
        Score score = new Score();
        Lifes lifes = new Lifes(0,0);
        level.gameLoop(new ArrayList<>(),score,lifes);
        verify(mapMock).gameLoop(anyList(),eq(score),eq(lifes));
    }
    @Test
    public void drawInicialMap() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        TextGraphics graphics = mock(TextGraphics.class);
        Screen screen = mock(Screen.class);
        Game game = new Game(220,270);
        Mapa mapMock = mock(Mapa.class);
        Level level = new Level(1,220,270,game.loadMapFromFile("map.txt"));
        level.setMap(mapMock);
        Lifes lifes = new Lifes(0,0);
        Score score = new Score();
        level.drawInicialMap(graphics,new ArrayList<>(),lifes,score);
        verify(mapMock).drawInicialMap(eq(graphics),anyList(),eq(lifes),eq(score));
    }
    @Test
    public void draw() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        TextGraphics graphics = mock(TextGraphics.class);
        Screen screen = mock(Screen.class);
        Game game = new Game(220,270);
        Mapa mapMock = mock(Mapa.class);
        Level level = new Level(1,220,270,game.loadMapFromFile("map.txt"));
        level.setMap(mapMock);
        Lifes lifes = new Lifes(0,0);
        Score score = new Score();
        level.draw(graphics,new ArrayList<>(),score,lifes,new ArrayList<>());
        verify(mapMock).draw(eq(graphics),anyList(),eq(score),eq(lifes),anyList());
    }
}
