package org.example.Monster;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Fruit;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.Mockito.*;

public class FruitTest {
    @Test
    public void drawTest(){
        TextGraphics graphics = mock(TextGraphics.class);
        Fruit fruit = new Fruit(0,0,"key");
        fruit.draw(graphics);
        verify(graphics,times(44)).fillRectangle(any(), any(), anyChar());
        verify(graphics, times(240)).setBackgroundColor(any());
    }
}
