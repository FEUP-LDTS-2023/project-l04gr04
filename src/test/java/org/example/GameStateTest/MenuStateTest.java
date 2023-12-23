package org.example.GameStateTest;

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
import org.example.Game;
import org.example.GameStates.menuState;
import org.example.GameStates.pauseState;
import org.example.GameStates.playingState;
import org.example.Level;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

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
