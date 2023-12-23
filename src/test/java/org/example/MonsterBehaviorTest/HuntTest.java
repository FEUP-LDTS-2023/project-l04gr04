package org.example.MonsterBehaviorTest;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Monster.Monsters.BlueMonster;
import org.example.Monster.MonsterStates.fright;
import org.example.Monster.MonsterStates.hunt;
import org.example.Monster.MonsterStates.monsterState;
import org.example.Others.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class HuntTest {
    private BlueMonster mockMonster;

    @BeforeEach
    public void setUp() {
        mockMonster = mock(BlueMonster.class);
        mockMonster.setPosition(new Position(75, 42));
    }
    @Test
    public void testModeOn() {
        hunt huntState = new hunt(mockMonster);
        String mode = huntState.modeOn();
        assertEquals("hunt", mode);
    }
    @Test
    public void testDraw() {
        TextGraphics mockTextGraphics = mock(TextGraphics.class);
        hunt huntState = new hunt(mockMonster);
        huntState.draw(mockTextGraphics, "#FFFFFF");
        verify(mockMonster).normalDraw(eq(mockTextGraphics), eq("#FFFFFF"));
    }
    @Test
    public void testMove() {
        hunt huntState = new hunt(mockMonster);
        huntState.move(new Position(0, 0),true, false, true, false);
        verify(mockMonster).targetMove(any(Position.class),eq(true), eq(false), eq(true), eq(false));
    }
    @Test
    public void testFrightHourStarted() {
        monsterState monsterState = new hunt(mockMonster);
        monsterState.FrightHourStarted();
        verify(mockMonster).changeState(new fright(any()));
    }
    @Test
    public void testFrightHourEnded() {
        hunt huntState = new hunt(mockMonster);
        huntState.FrightHourEnded();
        verify(mockMonster, never()).changeState(any());
    }
}
