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
import org.example.Color;
import org.example.Numbers.Score;
import org.junit.jupiter.api.BeforeEach;
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

public class MapInitializationTest {
    private Mapa mapa;
    private Game game;
    @BeforeEach
    public void setMap() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        game = new Game(220, 270);
        mapa = new Mapa(202, 240, "", 0, 0.0, 0.0, 0.0, 0.0, 0,game.loadMapFromFile("map.txt"));
    }
    @DoNotMutate
    @Test
    public void testMapInitialization() {
        assertEquals(202, mapa.getWidth());
        assertEquals(240, mapa.getHeight());

        assertEquals("#FFB8FF", Color.getColor("gate"));
        assertEquals("#000000", Color.getColor("background"));
        assertEquals("#2121DE", Color.getColor("walls"));
        assertEquals("#959043",  Color.getColor("coins"));

        assertNotNull(mapa.getPlayer());
        assertEquals(mapa.getMonsters().size(), 4);
        assertEquals(mapa.getDots().size(), 246);
    }
    @DoNotMutate
    @Test
    public void testPlayerMovement() {
        mapa.readInput(new KeyStroke(KeyType.ArrowRight));
        assertEquals(KeyType.ArrowLeft, mapa.getLastInputMove());
        mapa.setOffFirstInput();
        mapa.readInput(new KeyStroke(KeyType.ArrowRight));
        assertEquals(KeyType.ArrowRight, mapa.getLastInputMove());
        mapa.readInput(new KeyStroke(KeyType.ArrowUp));
        assertEquals(KeyType.ArrowUp, mapa.getLastInputMove());
        mapa.readInput(new KeyStroke(KeyType.ArrowDown));
        assertEquals(KeyType.ArrowDown, mapa.getLastInputMove());
        mapa.readInput(new KeyStroke(KeyType.ArrowLeft));
        assertEquals(KeyType.ArrowLeft, mapa.getLastInputMove());

    }
    @DoNotMutate
    @Test
    public void mapaGameTest() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        mapa.setMapaListener(game);
        mapa.getMapaListener().gameLost();
        assertNull(game.getLevel());
        mapa.getMapaListener().levelLost(game.loadMapFromFile("map.txt"));
        assertEquals("playing",game.getApplicationState().name());
    }
    /*
    @Test
    public void drawInitialMap() throws IOException {
        Lifes lifes = new Lifes(0,0);
        TextGraphics graphics = mock(TextGraphics.class);
        mapa.drawInicialMap(graphics,new ArrayList<>(),mock(Screen.class),lifes);
        verify(graphics, times(24)).fillRectangle(any(), any(), anyChar());
        verify(graphics, times(74)).setBackgroundColor(any());
    }*/
    @Test
    public void gameLoop() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        int i = mapa.getPlayer().fps;
        mapa.gameLoop(new ArrayList<>(),new Score(),new Lifes(0,0));
        assertEquals(i,mapa.getPlayer().fps);
        mapa.setOffFirstInput();
        mapa.gameLoop(new ArrayList<>(),new Score(),new Lifes(0,0));
        assertEquals(i+1,mapa.getPlayer().fps);
    }
    @Test
    public void lostOneLife(){
        Lifes lifes = new Lifes(0,0);
        mapa.lostOneLife(lifes);
        assertEquals(2,lifes.getNumber());

    }
    @Test
    public void movement(){
        assertEquals(true,mapa.canMove("left"));
        assertEquals(true,mapa.canMove("right"));
        assertEquals(false,mapa.canMove("down"));
        assertEquals(false,mapa.canMove("up"));
    }

}