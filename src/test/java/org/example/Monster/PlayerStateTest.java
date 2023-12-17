package org.example.Monster;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import org.example.Fruit;
import org.example.Mapa;
import org.example.PacMan.Player;
import org.example.PacMan.eatingPacMan;
import org.example.PacMan.normalState;
import org.example.PacMan.pacManState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PlayerStateTest {
    private Player playerMock;

    @BeforeEach
    public void setPlayerMock() {
        playerMock = mock(Player.class);
    }
    @Test
    public void testNormalStateTransition() {
        pacManState initialState = new normalState(playerMock);
        pacManState eatingState = new eatingPacMan(playerMock);

        assertTrue(initialState instanceof normalState);

        initialState.changeState(eatingState);

        verify(playerMock).changeState(eatingState);
    }
    @Test
    public void testDrawNormalState() {
        pacManState normalState = new normalState(playerMock);

        normalState.draw(mock(TextGraphics.class));

        verify(playerMock).drawNormal(any(TextGraphics.class));
    }
    @Test
    public void testDrawEatingPacManState() {
        pacManState eatingState = new eatingPacMan(playerMock);

        eatingState.draw(mock(TextGraphics.class));

        verify(playerMock).drawDead(any(TextGraphics.class));
    }
    @Test
    public void testMoveNormalState() {
        pacManState normalState = new normalState(playerMock);

        normalState.move("up");

        verify(playerMock).moveNormal("up");
    }
    @Test
    public void testMoveEatingPacManState() {
        pacManState eatingState = new eatingPacMan(playerMock);

        eatingState.move("down");

        verify(playerMock, never()).move(anyString());
    }

}
