package org.example.Monster.MonsterBehaviorTest;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import org.example.Mapa;
import org.example.Monster.BlueMonster;
import org.example.Monster.OrangeMonster;
import org.example.Monster.PinkMonster;
import org.example.Monster.RedMonster;
import org.example.Monster.States.eaten;
import org.example.Monster.States.fright;
import org.example.Monster.States.inCage;
import org.example.Monster.States.scatter;
import org.example.PacMan.Player;
import org.example.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class MoveTest {
    Player player;

    InputStream fontStream = getClass().getClassLoader().getResourceAsStream("square.ttf");
    Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
    Font customFont = font.deriveFont(Font.PLAIN, 2);
    SwingTerminalFontConfiguration fontConfig = new SwingTerminalFontConfiguration(true, SwingTerminalFontConfiguration.BoldMode.EVERYTHING, customFont);
    DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(1, 1)).setTerminalEmulatorFontConfiguration(fontConfig);
    Terminal terminal = terminalFactory.createTerminal();

    public Screen screen = new TerminalScreen(terminal);
    TextGraphics graphics = screen.newTextGraphics();


    public MoveTest() throws IOException, FontFormatException {
    }

    @BeforeEach
    public void setPlayer() {
        player = new Player(284, 243);
    }
    @Test
    public void redTargeting() {
        RedMonster redMonsterMock = mock(RedMonster.class);
        BlueMonster blueMonsterMock = mock(BlueMonster.class);
        redMonsterMock.setPosition(new Position(74, 42));
        blueMonsterMock.setPosition(new Position(75, 42));
        when(redMonsterMock.distance(redMonsterMock.getPosition(), blueMonsterMock.getPosition())).thenReturn(1.0);

        redMonsterMock.changeState(new eaten(redMonsterMock));
        Position target = redMonsterMock.target(player.getPosition(), "", new Position(0, 0));
        assertEquals(target, new Position(93, 115));

        redMonsterMock.setMs(new inCage(redMonsterMock));
        target = redMonsterMock.target(new Position(10, 10), "up", new Position(20, 20));
        assertEquals(new Position(93, 115 - 19), target);

        redMonsterMock.setMs(new scatter(redMonsterMock));
        target = redMonsterMock.target(new Position(10, 10), "up", new Position(20, 20));
        assertEquals(new Position(195, 1), target);
    }
    @Test
    public void orangeTargeting() {
        OrangeMonster orangeMonsterMock = mock(OrangeMonster.class);
        orangeMonsterMock.setPosition(new Position(75, 42));
        orangeMonsterMock.changeState(new fright(orangeMonsterMock));
        double dist = orangeMonsterMock.distance(player.getPosition(), orangeMonsterMock.getPosition());
        if (dist > 8 * 14) when(orangeMonsterMock.target(player.getPosition(),"", new Position(0, 0))).thenReturn(player.getPosition());
        else when(orangeMonsterMock.target(player.getPosition(),"", new Position(0, 0))).thenReturn(new Position(2, 56));

        orangeMonsterMock.changeState(new eaten(orangeMonsterMock));
        Position target = orangeMonsterMock.target(player.getPosition(), "", new Position(0, 0));
        assertEquals(target, new Position(93, 115));

        orangeMonsterMock.changeState(new inCage(orangeMonsterMock));
        target = orangeMonsterMock.target(new Position(10, 10), "up", new Position(20, 20));
        assertEquals(new Position(93, 115 - 19), target);

        orangeMonsterMock.changeState(new scatter(orangeMonsterMock));
        target = orangeMonsterMock.target(new Position(10, 10), "up", new Position(20, 20));
        assertEquals(new Position(1, 237), target);

    }
    @Test
    public void pinkTargeting() {
        PinkMonster pinkMonsterMock = mock(PinkMonster.class);
        pinkMonsterMock.setPosition(new Position(75, 42));
        pinkMonsterMock.changeState(new fright(pinkMonsterMock));
        when(pinkMonsterMock.target(player.getPosition(), "up", new Position(0,0))).thenReturn(new Position(player.getX(), player.getY() - 4 * 14));
        when(pinkMonsterMock.target(player.getPosition(), "down", new Position(0,0))).thenReturn(new Position(player.getX(), player.getY() + 4 * 14));
        when(pinkMonsterMock.target(player.getPosition(), "left", new Position(0,0))).thenReturn(new Position(player.getX() - 4 * 14, player.getY()));
        when(pinkMonsterMock.target(player.getPosition(), "right", new Position(0,0))).thenReturn(new Position(player.getX() + 4 * 14, player.getY()));

        pinkMonsterMock.changeState(new eaten(pinkMonsterMock));
        Position target = pinkMonsterMock.target(player.getPosition(), "", new Position(0, 0));
        assertEquals(target, new Position(93, 115));

        pinkMonsterMock.changeState(new inCage(pinkMonsterMock));
        target = pinkMonsterMock.target(new Position(10, 10), "up", new Position(20, 20));
        assertEquals(new Position(93, 115 - 19), target);

        pinkMonsterMock.changeState(new scatter(pinkMonsterMock));
        target = pinkMonsterMock.target(new Position(10, 10), "up", new Position(20, 20));
        assertEquals(new Position(2, 1), target);
    }
    @Test
    public void blueTargeting() {
        BlueMonster blueMonsterMock = mock(BlueMonster.class);
        blueMonsterMock.setPosition(new Position(75, 42));
        blueMonsterMock.changeState(new fright(blueMonsterMock));
        String direction = player.facingDirection;
        Position middleP;
        Position redPosition = new Position(167, 74);
        if (direction.equals("up")) middleP =  new Position(player.getX() - 2 * 14, player.getY() - 2 * 14);
        else if (direction.equals("down")) middleP = new Position(player.getX(), player.getY() + 2 * 14);
        else if (direction.equals("left")) middleP = new Position(player.getX() - 2 * 14, player.getY());
        else middleP = new Position(player.getX() + 2 * 14, player.getY());
        when(blueMonsterMock.target(player.getPosition(), "right", redPosition)).thenReturn(new Position(2 * middleP.getX() - redPosition.getX(), 2 * middleP.getY() - redPosition.getY()));

        blueMonsterMock.changeState(new eaten(blueMonsterMock));
        Position target = blueMonsterMock.target(player.getPosition(), "", new Position(0, 0));
        assertEquals(target, new Position(93, 115));

        blueMonsterMock.changeState(new inCage(blueMonsterMock));
        target = blueMonsterMock.target(new Position(10, 10), "up", new Position(20, 20));
        assertEquals(new Position(93, 115 - 19), target);

        blueMonsterMock.changeState(new scatter(blueMonsterMock));
        target = blueMonsterMock.target(new Position(10, 10), "up", new Position(20, 20));
        assertEquals(new Position(2, 1), target);
    }
    @Test
    public void testModeTransition() {
        RedMonster redMonster = mock(RedMonster.class);
        redMonster.setPosition(new Position(75, 42));
        redMonster.setMs(new inCage(redMonster));

        redMonster.setMs(new fright(redMonster));

        assertEquals("fright", redMonster.ms.modeOn());
    }

    @Test
    public void movingWall() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Mapa newMap = new Mapa(368, 392, graphics, "", 0, 0.0, 0.0, 0.0, 0.0, 0 ,null);
        RedMonster redMonster = new RedMonster(3,25);
        redMonster.move(new Position(5, 25), newMap.loadMapFromFile("map.txt")); //Testar se passa pela parede
        assertEquals(new Position(3, 24), redMonster.getPosition()); //Monstro move para cima
    }
    @Test
    public void movingNoWall() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Mapa newMap = new Mapa(368, 392, graphics, "", 0, 0.0, 0.0, 0.0, 0.0, 0, null);
        RedMonster redMonster = new RedMonster(3,25);
        redMonster.move(new Position(3, 23), newMap.loadMapFromFile("map.txt"));
        assertEquals(new Position(3, 24), redMonster.getPosition()); //Monstro move para cima
    }
}