package org.example.Chars;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ScoreTest {
    @Test
    public void testDrawScore2() {
        TextGraphics graphicsMock = mock(TextGraphics.class);
        Score score = new Score();
        score.draw(graphicsMock);
        verify(graphicsMock, times(24)).fillRectangle(any(), any(), anyChar());
        verify(graphicsMock, times(74)).setBackgroundColor(any());
        // To test the update method
        score.increment(5);
        score.draw(graphicsMock);
        verify(graphicsMock, times(76)).fillRectangle(any(), any(), anyChar());
        verify(graphicsMock, times(226)).setBackgroundColor(any());
    }

    @Test
    public void testIncrement() {
        Score score = new Score();
        score.increment(10);
        assertEquals(10, score.getCurrentScore());
        ArrayList<Integer> k = new ArrayList<>();
        k.add(0);
        k.add(1);
        assertEquals(k, score.getReverseDisplay());
    }
}
