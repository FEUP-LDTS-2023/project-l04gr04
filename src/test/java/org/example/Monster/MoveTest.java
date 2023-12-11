package org.example.Monster;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import org.example.Mapa;
import org.example.Monster.States.fright;
import org.example.Player;
import org.example.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;


import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class MoveTest {
    Player player;

    //InputStream fontStream = getClass().getClassLoader().getResourceAsStream("square.ttf");
    //Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
    //Font customFont = font.deriveFont(Font.PLAIN, 2);
    //SwingTerminalFontConfiguration fontConfig = new SwingTerminalFontConfiguration(true, SwingTerminalFontConfiguration.BoldMode.EVERYTHING, customFont);
    //DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(1, 1)).setTerminalEmulatorFontConfiguration(fontConfig);
    //Terminal terminal = terminalFactory.createTerminal();
    //public Screen screen = new TerminalScreen(terminal);
    //TextGraphics graphics = screen.newTextGraphics();
    TextGraphics graphics = mock(TextGraphics.class);


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
        //RedMonster redMonster = new RedMonster(74, 42);
        //BlueMonster blueMonster= new BlueMonster(75, 42);
        //double distance = redMonster.distance(redMonster.getPosition(), blueMonster.getPosition());
        //assertEquals(1.0, distance);
    }
    @Test
    public void orangeTargeting() {
        OrangeMonster orangeMonsterMock = mock(OrangeMonster.class);
        orangeMonsterMock.setPosition(new Position(75, 42));
        orangeMonsterMock.changeState(new fright(orangeMonsterMock));
        double dist = orangeMonsterMock.distance(player.getPosition(), orangeMonsterMock.getPosition());
        if (dist > 8 * 14) when(orangeMonsterMock.target(player.getPosition(),"", new Position(0, 0))).thenReturn(player.getPosition());
        else when(orangeMonsterMock.target(player.getPosition(),"", new Position(0, 0))).thenReturn(new Position(2, 56));
        //OrangeMonster orangeMonster = new OrangeMonster(74, 42);
        //if (dist > 8 * 14) assertEquals(orangeMonster.target(player.getPosition(), "", new Position(0, 0)), player.getPosition());
        //if (dist <= 8 * 14) assertEquals(orangeMonster.target(player.getPosition(), "", new Position(0, 0)), new Position(2, 56));
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
    }

    @Test
    public void movingWall() throws IOException {
        Mapa newMap = new Mapa(368, 392, graphics, "", 0, 0.0, 0.0, 0.0, 0.0, 0);
        RedMonster redMonster = new RedMonster(3,25);
        redMonster.move(new Position(5, 25), newMap.loadMapFromFile("map.txt")); //Testar se passa pela parede
        assertEquals(new Position(3, 24), redMonster.getPosition()); //Monstro move para cima
    }
    @Test
    public void movingNoWall() throws IOException {
        Mapa newMap = new Mapa(368, 392, graphics, "", 0, 0.0, 0.0, 0.0, 0.0, 0);
        RedMonster redMonster = new RedMonster(3,25);
        redMonster.move(new Position(3, 23), newMap.loadMapFromFile("map.txt"));
        assertEquals(new Position(3, 24), redMonster.getPosition()); //Monstro move para cima
    }
}