package org.example.Game.GameStates;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.example.Game.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MenuStateTest {
    private Game mockGame;

    @BeforeEach
    void setUp(){
        mockGame = mock(Game.class);
    }
    @Test
    void testDraw() throws IOException {
        menuState menuState = new menuState(mockGame);
        menuState.draw();
        verify(mockGame).drawMenu(anyInt());
    }

    @Test
    void testInputEnter() throws IOException,UnsupportedAudioFileException, LineUnavailableException {
        menuState menuState = new menuState(mockGame);
        KeyStroke enterKey = new KeyStroke(KeyType.Enter);
        menuState.input(enterKey);
        verify(mockGame).changeState(any(playingState.class));
    }
    @Test
    void testInputEnter2() throws IOException,UnsupportedAudioFileException, LineUnavailableException {
        menuState menuState = new menuState(mockGame);
        KeyStroke arrowDownKey = new KeyStroke(KeyType.ArrowDown);
        KeyStroke enterKey = new KeyStroke(KeyType.Enter);
        menuState.input(arrowDownKey);
        menuState.input(enterKey);
        verify(mockGame).changeState(any(infoState.class));
    }
    @Test
    void testInputEnter3() throws IOException,UnsupportedAudioFileException, LineUnavailableException {
        menuState menuState = new menuState(mockGame);
        KeyStroke arrowDownKey = new KeyStroke(KeyType.ArrowDown);
        KeyStroke enterKey = new KeyStroke(KeyType.Enter);
        menuState.input(arrowDownKey);
        menuState.input(arrowDownKey);
        menuState.input(enterKey);
        verify(mockGame).stopGameLoop();
    }

    @Test
    void testInputArrowUp() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        menuState menuState = new menuState(mockGame);
        KeyStroke arrowUpKey = new KeyStroke(KeyType.ArrowUp);
        menuState.input(arrowUpKey);
        assertEquals(2,menuState.getBarOn());
    }

    @Test
    void testInputArrowDown() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        menuState menuState = new menuState(mockGame);
        KeyStroke arrowDownKey = new KeyStroke(KeyType.ArrowDown);
        menuState.input(arrowDownKey);
        assertEquals(1,menuState.getBarOn());
    }
    @Test
    void testName() {
        menuState menuState = new menuState(mockGame);
        assertEquals("menu",menuState.name());
    }
}
