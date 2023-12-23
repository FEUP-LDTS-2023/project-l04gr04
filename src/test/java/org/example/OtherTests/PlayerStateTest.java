package org.example.OtherTests;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.PacMan.Player;
import org.example.PacMan.eatingPacMan;
import org.example.PacMan.normalState;
import org.example.PacMan.pacManState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void testDrawDead() {
        playerMock = new Player(0,0);
        playerMock.mouthOpen = -30;
        TextGraphics graphics = mock(TextGraphics.class);
        playerMock.drawDead(graphics);
        verify(graphics, times(94)).fillRectangle(any(), any(), anyChar());
        verify(graphics, times(196)).setBackgroundColor(any());
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
