package org.example.MonsterBehaviorTest;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.groupcdg.pitest.annotations.DoNotMutate;
import org.example.Monster.BlueMonster;
import org.example.Monster.States.onCollision;
import org.example.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class OnCollisionTest {
    private BlueMonster mockMonster;
    @BeforeEach
    public void setUp() {
        mockMonster = mock(BlueMonster.class);
        mockMonster.setPosition(new Position(75, 42));
    }
    @Test
    public void testModeOn() {
        onCollision onCollisionState = new onCollision(mockMonster);
        String mode = onCollisionState.modeOn();
        assertEquals("onCollision", mode);
    }

    @Test
    public void testDraw() {
        TextGraphics mockTextGraphics = mock(TextGraphics.class);
        onCollision onCollisionState = new onCollision(mockMonster);
        onCollisionState.draw(mockTextGraphics, "#FFFFFF");
        verify(mockMonster, never()).darkDraw(mockTextGraphics,"#FFFFFF");
        verify(mockMonster, never()).blueDraw(mockTextGraphics);
        verify(mockMonster, never()).normalDraw(mockTextGraphics,"#FFFFFF");
    }
    @Test
    public void testMove() {
        onCollision onCollisionState = new onCollision(mockMonster);
        onCollisionState.move(new Position(0, 0),true, false, true, false);
        verify(mockMonster,never()).targetMove(any(Position.class),eq(true), eq(false), eq(true), eq(false));
    }
    @DoNotMutate
    @Test
    public void testFrightHourStarted() {
        onCollision onCollisionState = new onCollision(mockMonster);
        onCollisionState.FrightHourStarted();
        verify(mockMonster, never()).changeState(any());
    }
    @DoNotMutate
    @Test
    public void testFrightHourEnded() {
        onCollision onCollisionState = new onCollision(mockMonster);
        onCollisionState.FrightHourEnded();
        verify(mockMonster, never()).changeState(any());
    }
}
