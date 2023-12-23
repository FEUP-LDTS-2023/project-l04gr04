package org.example.OtherTests;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.groupcdg.pitest.annotations.DoNotMutate;
import org.example.Game;
import org.example.Lifes;
import org.example.Mapa;
import org.example.Monster.Monster;
import org.example.Position;
import org.example.Monster.States.eaten;
import org.example.Monster.States.fright;
import org.example.PacMan.Player;
import org.example.PacMan.eatingPacMan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class PlayerBehaviourTest {InputStream fontStream = getClass().getClassLoader().getResourceAsStream("square.ttf");
    private Mapa mapa;
    private Game game;
    @BeforeEach
    public void setMap() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        game = new Game(220, 270);
        mapa = new Mapa(202, 240, "", 0, 0.0, 0.0, 0.0, 0.0, 0,game.loadMapFromFile("map.txt"));
    }

    @DoNotMutate
    @Test
    public void checkCount() {
        Player player = mapa.getPlayer();
        player.incrementCount();
        assertEquals(1,player.getCountOfEatenInARow());

    }
    @DoNotMutate
    @Test
    public void allMonstersEaten() {
        Player player = mapa.getPlayer();
        player.changeState(new eatingPacMan(player));
        player.incrementCount();
        assertEquals(false,player.allMonsterseaten());
        player.incrementCount();
        player.incrementCount();
        player.incrementCount();
        assertEquals(true,player.allMonsterseaten());

    }
    @DoNotMutate
    @Test
    public void testNearBoundaries() {
        TextGraphics graphicsMock = mock(TextGraphics.class);
        Player playerMock = mapa.getPlayer();
        playerMock.setPosition(new Position(180, 50));
        playerMock.draw(graphicsMock);
        playerMock.move("right");
        assertTrue(playerMock.getX() >= 0 && playerMock.getX() < mapa.getWidth());
        playerMock.move("up");
        assertTrue(playerMock.getX() >= 0 && playerMock.getX() < mapa.getWidth());

        playerMock.setPosition(new Position(180, 222));
        playerMock.draw(graphicsMock);
        playerMock.move("right");
        assertTrue(playerMock.getX() >= 0 && playerMock.getX() < mapa.getWidth());
        playerMock.move("down");
        assertTrue(playerMock.getX() >= 0 && playerMock.getX() < mapa.getWidth());

        playerMock.setPosition(new Position(8, 236));
        playerMock.draw(graphicsMock);
        playerMock.move("left");
        assertTrue(playerMock.getX() >= 0 && playerMock.getX() < mapa.getWidth());
        playerMock.move("down");
        assertTrue(playerMock.getX() >= 0 && playerMock.getX() < mapa.getWidth());

        playerMock.setPosition(new Position(8, 27));
        playerMock.draw(graphicsMock);
        playerMock.move("left");
        assertTrue(playerMock.getX() >= 0 && playerMock.getX() < mapa.getWidth());
        playerMock.move("up");
        assertTrue(playerMock.getX() >= 0 && playerMock.getX() < mapa.getWidth());
    }
    @DoNotMutate
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
}