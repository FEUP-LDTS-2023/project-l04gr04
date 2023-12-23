package org.example.MonsterBehaviorTest;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Monster.BlueMonster;
import org.example.Monster.States.fright;
import org.example.Monster.States.hunt;
import org.example.Monster.States.scatter;
import org.example.Monster.monsterState;
import org.example.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ScatterTest {
    private BlueMonster mockMonster;
    @BeforeEach
    public void setUp() {
        mockMonster = mock(BlueMonster.class);
        mockMonster.setPosition(new Position(75, 42));
    }
    @Test
    public void testModeOn() {
        scatter scatterState = new scatter(mockMonster);
        String mode = scatterState.modeOn();
        assertEquals("scatter", mode);
    }
    @Test
    public void testDraw() {
        TextGraphics mockTextGraphics = mock(TextGraphics.class);
        scatter scatterState = new scatter(mockMonster);
        scatterState.draw(mockTextGraphics, "#FFFFFF");
        verify(mockMonster).normalDraw(eq(mockTextGraphics), eq("#FFFFFF"));
    }
    @Test
    public void testMove() {
        scatter scatterState = new scatter(mockMonster);
        scatterState.move(new Position(0, 0),true, false, true, false);
        verify(mockMonster).targetMove(any(Position.class),eq(true), eq(false), eq(true), eq(false));
    }
    @Test
    public void testFrightHourStarted() {
        monsterState monsterState = new scatter(mockMonster);
        monsterState.FrightHourStarted();
        verify(mockMonster).changeState(new fright(any()));
    }
    @Test
    public void testFrightHourEnded() {
        scatter scatter = new scatter(mockMonster);
        scatter.FrightHourEnded();
        verify(mockMonster).changeState(new hunt(any()));
    }
}
