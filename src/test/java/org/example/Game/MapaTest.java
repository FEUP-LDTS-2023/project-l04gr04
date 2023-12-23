package org.example.Game;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.example.Chars.Score;
import org.example.Elements.Fruit;
import org.example.Elements.Lifes;
import org.example.Monster.MonsterStates.eaten;
import org.example.Monster.MonsterStates.fright;
import org.example.Monster.MonsterStates.hunt;
import org.example.Monster.Monsters.Monster;
import org.example.Monster.Monsters.OrangeMonster;
import org.example.Monster.Monsters.RedMonster;
import org.example.Others.Color;
import org.example.Others.Position;
import org.example.PacMan.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MapaTest {
    private Mapa mapa;
    private Game game;

    @BeforeEach
    public void setMap() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        game = new Game(220, 270);
        mapa = new Mapa(220, 270, "", 0, 0.0, 0.0, 0.0, 0.0, 0, game.loadMapFromFile("map.txt"));
    }

    @Test
    public void testMapInitialization() {
        assertEquals(220, mapa.getWidth());
        assertEquals(270, mapa.getHeight());

        assertEquals("#FFB8FF", Color.getColor("gate"));
        assertEquals("#000000", Color.getColor("background"));
        assertEquals("#2121DE", Color.getColor("walls"));
        assertEquals("#959043", Color.getColor("coins"));

        assertNotNull(mapa.getPlayer());
        assertEquals(mapa.getMonsters().size(), 4);
        assertEquals(mapa.getDots().size(), 246);
    }

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

    @Test
    public void mapaGameTest() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        mapa.setMapaListener(game);
        mapa.getMapaListener().gameLost();
        assertNull(game.getLevel());
        mapa.getMapaListener().levelLost(game.loadMapFromFile("map.txt"));
        assertEquals("playing", game.getApplicationState().name());
    }

    @Test
    public void drawInitialMap(){
        TextGraphics graphics = mock(TextGraphics.class);
        List<Fruit> frutas = new ArrayList<>();
        frutas.add(new Fruit(0,0,"key"));
        frutas.add(new Fruit(0,0,"bell"));
        mapa.drawInicialMap(graphics,frutas, new Lifes(0,0),new Score());
        verify(graphics,times(220*270+1999)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(220*270+4190+15600)).setBackgroundColor(any());
    }
    @Test
    public void draw() throws IOException {
        TextGraphics graphics = mock(TextGraphics.class);
        List<Rectangle> dr = new ArrayList<>();
        dr.add(new Rectangle(50,50,10,10));
        mapa.draw(graphics,dr,new Score(),new Lifes(0,0),new ArrayList<>());
        verify(graphics,times(220*270+1841)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(220*270+4190+15050)).setBackgroundColor(any());
    }
    @Test
    public void draw2() throws IOException {
        TextGraphics graphics = mock(TextGraphics.class);
        List<Rectangle> dr = new ArrayList<>();
        dr.add(new Rectangle(50,50,10,10));
        mapa.setFruta(null);
        mapa.setOffFirstInput();
        mapa.draw(graphics,dr,new Score(),new Lifes(0,0),new ArrayList<>());
        verify(graphics,times(1448)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(2287)).setBackgroundColor(any());
    }
    @Test
    public void drawNormal(){
        List<Rectangle> dr = new ArrayList<>();
        dr.add(new Rectangle(50,50,10,10));
        TextGraphics graphics = mock(TextGraphics.class);
        mapa.drawNormal(graphics,dr,new Score(),new Lifes(0,0));
        verify(graphics,times(1448)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(2287)).setBackgroundColor(any());
    }
    @Test
    public void gameLoop() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        int i = mapa.getPlayer().fps;
        mapa.gameLoop(new ArrayList<>(), new Score(), new Lifes(0, 0));
        assertEquals(i, mapa.getPlayer().fps);
        mapa.setOffFirstInput();
        mapa.gameLoop(new ArrayList<>(), new Score(), new Lifes(0, 0));
        assertEquals(i + 1, mapa.getPlayer().fps);
    }
    @Test
    public void gameLoop2() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        FrightControl frightControl = mock(FrightControl.class);
        Game game = mock(Game.class);
        mapa.setMapaListener(game);
        mapa.setGameState(frightControl);
        mapa.setFruta(null);
        mapa.setDotsCounter(0);
        mapa.setOffFirstInput();
        mapa.gameLoop(new ArrayList<>(), new Score(), new Lifes(0, 0));
        verify(frightControl).stopMusic();
        verify(frightControl).closeMusic();
        verify(game).levelWon();
    }

    @Test
    public void lostOneLife() {
        Lifes lifes = new Lifes(0, 0);
        mapa.lostOneLife(lifes);
        assertEquals(2, lifes.getNumber());

    }

    @Test
    public void movement() {
        assertEquals(true, mapa.canMove("left"));
        assertEquals(true, mapa.canMove("right"));
        assertEquals(false, mapa.canMove("down"));
        assertEquals(false, mapa.canMove("up"));
    }

    @Test
    public void testMonsterCollision() {
        for (Monster monster : mapa.getMonsters()) {
            monster.changeState(new fright(monster));
            monster.setPosition(new Position(75, 42));
        }
        Player player = mapa.getPlayer();
        player.setPosition(new Position(75, 42));
        mapa.checkMonsterCollisions(new Lifes(75, 42));
        for (Monster monster : mapa.getMonsters()) {
            assertEquals(new eaten(monster).modeOn(), monster.ms.modeOn());
        }
    }
    @Test
    public void playerMovement(){
        Player player = mapa.getPlayer();
        player.setPosition(new Position(94, 180));
        player.setFps(1000);
        mapa.setPlayer(player);
        mapa.setLastInputMove(KeyType.ArrowLeft);
        mapa.playerMovement();
        assertEquals(new Position(93,180),mapa.getPlayer().getPosition());
        mapa.setLastInputMove(KeyType.ArrowRight);
        mapa.playerMovement();
        assertEquals(new Position(94,180),mapa.getPlayer().getPosition());
        mapa.setLastInputMove(KeyType.ArrowUp);
        mapa.playerMovement();
        assertEquals(new Position(95,180),mapa.getPlayer().getPosition());
        mapa.setLastInputMove(KeyType.ArrowDown);
        mapa.playerMovement();
        assertEquals(new Position(96,180),mapa.getPlayer().getPosition());
        player = mapa.getPlayer();
        player.setPosition(new Position(6, 222));
        player.setFacingDirection("left");
        mapa.setPlayer(player);
        mapa.setLastInputMove(KeyType.ArrowLeft);
        mapa.playerMovement();
        assertEquals(new Position(6,222),mapa.getPlayer().getPosition());
        player = mapa.getPlayer();
        player.setPosition(new Position(181, 222));
        player.setFacingDirection("right");
        mapa.setPlayer(player);
        mapa.setLastInputMove(KeyType.ArrowRight);
        mapa.playerMovement();
        assertEquals(new Position(181,222),mapa.getPlayer().getPosition());
    }
    @Test
    public void monsterMovement(){
        Player player = mapa.getPlayer();
        player.setPosition(new Position(94, 180));
        player.setFps(1000);
        mapa.setPlayer(player);
        RedMonster redMonster = new RedMonster(92,180);
        redMonster.changeState(new hunt(redMonster));
        redMonster.setMovingDirection("right");
        List<Monster> monstros = new ArrayList<>();
        monstros.add(redMonster);
        mapa.setMonsters(monstros);
        mapa.monsterMovement();
        assertEquals(new Position(93,180), mapa.getMonsters().get(0).getPosition());
    }
    @Test
    public void monsterMovement2(){
        Player player = mapa.getPlayer();
        player.setPosition(new Position(94, 180));
        player.setFps(1000);
        mapa.setPlayer(player);
        RedMonster redMonster = new RedMonster(93,116);
        redMonster.changeState(new eaten(redMonster));
        List<Monster> monstros = new ArrayList<>();
        monstros.add(redMonster);
        mapa.setMonsters(monstros);
        mapa.monsterMovement();
        assertEquals("inCage", mapa.getMonsters().get(0).ms.modeOn());

    }

}
