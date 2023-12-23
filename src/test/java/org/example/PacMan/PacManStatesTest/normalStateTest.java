package org.example.PacMan.PacManStatesTest;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.PacMan.PacManStates.eatingPacManState;
import org.example.PacMan.PacManStates.normalState;
import org.example.PacMan.PacManStates.pacManState;
import org.example.PacMan.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class normalStateTest {
    private Player playerMock;

    @BeforeEach
    public void setPlayerMock() {
        playerMock = mock(Player.class);
    }
    @Test
    public void testNormalStateTransition() {
        pacManState initialState = new normalState(playerMock);
        pacManState eatingState = new eatingPacManState(playerMock);
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
    public void testMoveNormalState() {
        pacManState normalState = new normalState(playerMock);

        normalState.move("up");

        verify(playerMock).moveNormal("up");
    }
}
