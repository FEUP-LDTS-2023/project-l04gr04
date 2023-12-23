package org.example.MonsterBehaviorTest;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.groupcdg.pitest.annotations.DoNotMutate;
import org.example.Monster.Monsters.BlueMonster;
import org.example.Monster.MonsterStates.inCage;
import org.example.Others.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class InCageTest {
    private BlueMonster mockMonster;
    @BeforeEach
    public void setUp() {
        mockMonster = mock(BlueMonster.class);
        mockMonster.setPosition(new Position(75, 42));
    }
    @Test
    public void testModeOn() {
        inCage inCageState = new inCage(mockMonster);
        String mode = inCageState.modeOn();
        assertEquals("inCage", mode);
    }
    @Test
    public void testDraw() {
        TextGraphics mockTextGraphics = mock(TextGraphics.class);
        inCage inCageState = new inCage(mockMonster);
        inCageState.draw(mockTextGraphics, "#FFFFFF");
        verify(mockMonster).normalDraw(eq(mockTextGraphics), eq("#FFFFFF"));
    }
    @Test
    public void testMove() {
        inCage inCageState = new inCage(mockMonster);
        inCageState.move(new Position(0, 0),true, false, true, false);
        verify(mockMonster).targetMove(any(Position.class),eq(true), eq(false), eq(true), eq(false));
    }
    @DoNotMutate
    @Test
    public void testFrightHourStarted() {
        inCage inCageState = new inCage(mockMonster);
        inCageState.FrightHourStarted();
        verify(mockMonster, never()).changeState(any());
    }
    @DoNotMutate
    @Test
    public void testFrightHourEnded() {
        inCage inCageState = new inCage(mockMonster);
        inCageState.FrightHourEnded();
        verify(mockMonster, never()).changeState(any());
    }
}
