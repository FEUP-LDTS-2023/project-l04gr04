package org.example.OtherTests;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Lifes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LivesTest {
    @Test
    public void testLives(){
        Lifes lifes = new Lifes(0,0);
        lifes.incrementLife();
        assertEquals(4,lifes.getNumber());
        lifes.decrementLife();
        assertEquals(3,lifes.getNumber());
        assertEquals(false,lifes.isempty());
    }
    @Test
    public void draw(){
        Lifes lifes = new Lifes(0,0);
        TextGraphics graphics = mock(TextGraphics.class);
        lifes.draw(graphics);
        verify(graphics, times(390)).fillRectangle(any(), any(), anyChar());
        verify(graphics, times(588)).setBackgroundColor(any());
    }
}
