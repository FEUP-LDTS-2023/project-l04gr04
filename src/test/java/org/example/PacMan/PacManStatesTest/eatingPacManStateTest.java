package org.example.PacMan.PacManStatesTest;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.PacMan.PacManStates.eatingPacManState;
import org.example.PacMan.PacManStates.pacManState;
import org.example.PacMan.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class eatingPacManStateTest {
    private Player playerMock;

    @BeforeEach
    public void setPlayerMock() {
        playerMock = mock(Player.class);
    }
    @Test
    public void testDrawEatingPacManState() {
        pacManState eatingState = new eatingPacManState(playerMock);
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
    public void testMoveEatingPacManState() {
        pacManState eatingState = new eatingPacManState(playerMock);

        eatingState.move("down");

        verify(playerMock, never()).move(anyString());
    }
}
