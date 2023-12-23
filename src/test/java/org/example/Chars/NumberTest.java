package org.example.Chars;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.Mockito.*;

public class NumberTest {
    @Test
    public void testDrawNumero() {
        Numero numero = new Numero(5, 5);
        numero.changeNumber(1);
        TextGraphics graphicsMock = mock(TextGraphics.class);
        numero.draw(graphicsMock);
        // Verify that the correct drawing methods are called on graphicsMock
        verify(graphicsMock, times(21)).fillRectangle(any(), any(), anyChar());
        verify(graphicsMock, times(71)).setBackgroundColor(any());
    }

    @Test
    public void testChangeNumber() {
        Numero numero = new Numero(5, 5);
        numero.changeNumber(0);
        assertEquals(0, numero.getNum());
        numero.changeNumber(1);
        assertEquals(1, numero.getNum());
        numero.changeNumber(2);
        assertEquals(2, numero.getNum());
        numero.changeNumber(3);
        assertEquals(3, numero.getNum());
        numero.changeNumber(4);
        assertEquals(4, numero.getNum());
        numero.changeNumber(5);
        assertEquals(5, numero.getNum());
        numero.changeNumber(6);
        assertEquals(6, numero.getNum());
        numero.changeNumber(7);
        assertEquals(7, numero.getNum());
        numero.changeNumber(8);
        assertEquals(8, numero.getNum());
        numero.changeNumber(9);
        assertEquals(9, numero.getNum());
    }
}