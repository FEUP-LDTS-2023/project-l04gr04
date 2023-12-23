package org.example.PacMan;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Game.Game;
import org.example.Game.Mapa;
import org.example.Others.Position;
import org.example.PacMan.PacManStates.eatingPacManState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class PlayerMapTest {
    private Mapa mapa;
    @BeforeEach
    public void setMap() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Game game = new Game(220, 270);
        mapa = new Mapa(202, 240, "", 0, 0.0, 0.0, 0.0, 0.0, 0,game.loadMapFromFile("map.txt"));
    }

    @Test
    public void checkCount() {
        Player player = mapa.getPlayer();
        player.incrementCount();
        assertEquals(1,player.getCountOfEatenInARow());

    }
    @Test
    public void allMonstersEaten() {
        Player player = mapa.getPlayer();
        player.changeState(new eatingPacManState(player));
        player.incrementCount();
        assertEquals(false,player.allMonsterseaten());
        player.incrementCount();
        player.incrementCount();
        player.incrementCount();
        assertEquals(true,player.allMonsterseaten());

    }
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
}
