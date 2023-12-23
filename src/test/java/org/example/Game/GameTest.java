package org.example.Game;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.groupcdg.pitest.annotations.DoNotMutate;
import org.example.Chars.Score;
import org.example.Elements.Lifes;
import org.example.Game.GameStates.playingState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GameTest {
    private Game game;

    @BeforeEach
    public void setGame(){
        game = new Game(220, 270);
    }
    @Test
    public void testGameInitialization() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        game.createLevel();
        assertEquals(game.getGameW(), 220);
        assertEquals(game.getGameH(), 270);
        assertEquals(game.getLevel().levelNumber, 1);
        game.levelWon();
        game.createLevel();
        assertEquals(game.getLevel().levelNumber, 2);
        game.levelLost(game.loadMapFromFile("map.txt"));
        assertEquals(game.getLevel().levelNumber, 2);
    }
    @Test
    public void input() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Level level = mock(Level.class);
        game.setLevel(level);
        KeyStroke arrowDownKey = new KeyStroke(KeyType.ArrowDown);
        game.gameplayInput(arrowDownKey);
        verify(level).processKey(eq(arrowDownKey));
    }
    @Test
    public void stopMusic(){
        Level level = mock(Level.class);
        game.setLevel(level);
        game.warnMapStopMusic();
        verify(level).warnMapStopMusic();
    }
    @Test
    public void clearScreen(){
        Screen screen = mock(Screen.class);
        game.setScreen(screen);
        game.screenClear();
        verify(screen).clear();
    }
    @Test
    public void stopGameLoop() throws IOException {
        Screen screen = mock(Screen.class);
        Timer timer = mock(Timer.class);
        game.setScreen(screen);
        game.setGameLoopTimer(timer);
        game.stopGameLoop();
        verify(screen).close();
        verify(timer).cancel();
        assertEquals(false,game.isGameRunning());
    }
    @Test
    public void drawChangingLevel() throws IOException {
        TextGraphics graphics = mock(TextGraphics.class);
        Screen screen = mock(Screen.class);
        game.setScreen(screen);
        game.setGraphics(graphics);
        game.setMap(game.loadMapFromFile("map.txt"));
        game.changingLevelDraw(true);
        verify(screen).refresh();
        verify(graphics,times(59400)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(63590)).setBackgroundColor(any());
    }
    @Test
    public void drawChangingLevel2() throws IOException {
        TextGraphics graphics = mock(TextGraphics.class);
        Screen screen = mock(Screen.class);
        game.setScreen(screen);
        game.setGraphics(graphics);
        game.setMap(game.loadMapFromFile("map.txt"));
        game.changingLevelDraw(false);
        verify(screen).refresh();
        verify(graphics,times(59400)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(63590)).setBackgroundColor(any());
    }
    @Test
    public void drawMenu0() throws IOException {
        TextGraphics graphics = mock(TextGraphics.class);
        Screen screen = mock(Screen.class);
        game.setScreen(screen);
        game.setGraphics(graphics);
        game.setMenu(game.loadMapFromFile("menu.txt"));
        game.drawMenu(0);
        verify(screen).refresh();
        verify(graphics,times(1901)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(1901)).setBackgroundColor(any());
    }
    @Test
    public void drawMenu1() throws IOException {
        TextGraphics graphics = mock(TextGraphics.class);
        Screen screen = mock(Screen.class);
        game.setScreen(screen);
        game.setGraphics(graphics);
        game.setMenu(game.loadMapFromFile("menu.txt"));
        game.drawMenu(1);
        verify(screen).refresh();
        verify(graphics,times(1901)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(1901)).setBackgroundColor(any());
    }
    @Test
    public void drawMenu2() throws IOException {
        TextGraphics graphics = mock(TextGraphics.class);
        Screen screen = mock(Screen.class);
        game.setScreen(screen);
        game.setGraphics(graphics);
        game.setMenu(game.loadMapFromFile("menu.txt"));
        game.drawMenu(2);
        verify(screen).refresh();
        verify(graphics,times(1901)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(1901)).setBackgroundColor(any());
    }
    @Test
    public void drawpause0() throws IOException {
        TextGraphics graphics = mock(TextGraphics.class);
        Screen screen = mock(Screen.class);
        game.setScreen(screen);
        game.setGraphics(graphics);
        game.setPausa(game.loadMapFromFile("pausa.txt"));
        game.drawPause(0);
        verify(screen).refresh();
        verify(graphics,times(763)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(763)).setBackgroundColor(any());
    }
    @Test
    public void drawpause1() throws IOException {
        TextGraphics graphics = mock(TextGraphics.class);
        Screen screen = mock(Screen.class);
        game.setScreen(screen);
        game.setGraphics(graphics);
        game.setPausa(game.loadMapFromFile("pausa.txt"));
        game.drawPause(1);
        verify(screen).refresh();
        verify(graphics,times(763)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(763)).setBackgroundColor(any());
    }
    @Test
    public void drawpause2() throws IOException {
        TextGraphics graphics = mock(TextGraphics.class);
        Screen screen = mock(Screen.class);
        game.setScreen(screen);
        game.setGraphics(graphics);
        game.setPausa(game.loadMapFromFile("pausa.txt"));
        game.drawPause(2);
        verify(screen).refresh();
        verify(graphics,times(763)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(763)).setBackgroundColor(any());
    }
    @Test
    public void drawInfo() throws IOException {
        TextGraphics graphics = mock(TextGraphics.class);
        Screen screen = mock(Screen.class);
        Level level = mock(Level.class);
        game.setScreen(screen);
        game.setGraphics(graphics);
        game.setLevel(level);
        game.setInfo(game.loadMapFromFile("info.txt"));
        game.drawInfo();
        verify(screen).refresh();
        verify(graphics,times(1125)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(1125)).setBackgroundColor(any());
    }
    @Test
    public void drawLevel() throws IOException {
        TextGraphics graphics = mock(TextGraphics.class);
        Screen screen = mock(Screen.class);
        Level level = mock(Level.class);
        List<Rectangle> dr = new ArrayList<>();
        dr.add(new Rectangle(50,50,10,10));
        Score score = new Score();
        Lifes lifes = new Lifes(0,0);
        game.setLifes(lifes);
        game.setScore(score);
        game.setDirtyRegions(dr);
        game.setScreen(screen);
        game.setGraphics(graphics);
        game.setLevel(level);
        game.drawLevel();
        verify(screen).refresh();
        verify(screen,times(1)).setCharacter(50,50,new TextCharacter(' '));
        verify(level).draw(eq(graphics),eq(dr),eq(score),eq(lifes),anyList());
    }
    @Test
    public void drawInicialMap() throws IOException {
        TextGraphics graphics = mock(TextGraphics.class);
        Screen screen = mock(Screen.class);
        Level level = mock(Level.class);
        List<Rectangle> dr = new ArrayList<>();
        dr.add(new Rectangle(50,50,10,10));
        Score score = new Score();
        Lifes lifes = new Lifes(0,0);
        game.setLifes(lifes);
        game.setScore(score);
        game.setDirtyRegions(dr);
        game.setScreen(screen);
        game.setGraphics(graphics);
        game.setLevel(level);
        game.drawInicialMap();
        verify(level).drawInicialMap(eq(graphics),anyList(),eq(lifes),eq(score));
    }


}
