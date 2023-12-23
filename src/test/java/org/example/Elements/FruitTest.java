package org.example.Elements;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Elements.Fruit;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.Mockito.*;

public class FruitTest {
    @Test
    public void drawTestKey(){
        TextGraphics graphics = mock(TextGraphics.class);
        Fruit fruit = new Fruit(0,0,"key");
        fruit.draw(graphics);
        verify(graphics,times(44)).fillRectangle(any(), any(), anyChar());
        verify(graphics, times(240)).setBackgroundColor(any());
    }
    @Test
    public void drawTestCherry(){
        TextGraphics graphics = mock(TextGraphics.class);
        Fruit fruit = new Fruit(0,0,"cherry");
        fruit.draw(graphics);
        verify(graphics,times(71)).fillRectangle(any(), any(), anyChar());
        verify(graphics, times(267)).setBackgroundColor(any());
    }
    @Test
    public void drawTestStrawberry(){
        TextGraphics graphics = mock(TextGraphics.class);
        Fruit fruit = new Fruit(0,0,"strawberry");
        fruit.draw(graphics);
        verify(graphics,times(92)).fillRectangle(any(), any(), anyChar());
        verify(graphics, times(288)).setBackgroundColor(any());
    }
    @Test
    public void drawTestOrange(){
        TextGraphics graphics = mock(TextGraphics.class);
        Fruit fruit = new Fruit(0,0,"orange");
        fruit.draw(graphics);
        verify(graphics,times(104)).fillRectangle(any(), any(), anyChar());
        verify(graphics, times(300)).setBackgroundColor(any());
    }
    @Test
    public void drawTestApple(){
        TextGraphics graphics = mock(TextGraphics.class);
        Fruit fruit = new Fruit(0,0,"apple");
        fruit.draw(graphics);
        verify(graphics,times(112)).fillRectangle(any(), any(), anyChar());
        verify(graphics, times(308)).setBackgroundColor(any());
    }
    @Test
    public void drawTestGalaxian(){
        TextGraphics graphics = mock(TextGraphics.class);
        Fruit fruit = new Fruit(0,0,"galaxian");
        fruit.draw(graphics);
        verify(graphics,times(63)).fillRectangle(any(), any(), anyChar());
        verify(graphics, times(259)).setBackgroundColor(any());
    }
    @Test
    public void drawTestBell(){
        TextGraphics graphics = mock(TextGraphics.class);
        Fruit fruit = new Fruit(0,0,"bell");
        fruit.draw(graphics);
        verify(graphics,times(112)).fillRectangle(any(), any(), anyChar());
        verify(graphics, times(308)).setBackgroundColor(any());
    }
    @Test
    public void drawTestMelon(){
        TextGraphics graphics = mock(TextGraphics.class);
        Fruit fruit = new Fruit(0,0,"melon");
        fruit.draw(graphics);
        verify(graphics,times(96)).fillRectangle(any(), any(), anyChar());
        verify(graphics, times(292)).setBackgroundColor(any());
    }
}
