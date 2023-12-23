package org.example.OtherTests;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Chars.Numero;
import org.example.Chars.Score;
import org.example.Chars.Character;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ScoreAndNumberTest {
    @Test
    public void testDrawReady() {
        Character character = new Character(5, 5);
        TextGraphics graphicsMock = mock(TextGraphics.class);
        character.drawready(graphicsMock);
        // Verify that the correct drawing methods are called on graphicsMock
        verify(graphicsMock, times(157)).fillRectangle(any(), any(), anyChar());
        verify(graphicsMock, times(479)).setBackgroundColor(any());
    }
    @Test
    public void testDrawScore() {
        Character character = new Character(5, 5);
        TextGraphics graphicsMock = mock(TextGraphics.class);
        character.drawscore(graphicsMock);
        // Verify that the correct drawing methods are called on graphicsMock
        verify(graphicsMock, times(136)).fillRectangle(any(), any(), anyChar());
        verify(graphicsMock, times(409)).setBackgroundColor(any());
    }
    @Test
    public void testCleanReady() {
        Character character = new Character(5, 5);
        TextGraphics graphicsMock = mock(TextGraphics.class);
        character.cleanReady(graphicsMock);
        // Verify that the correct drawing methods are called on graphicsMock
        verify(graphicsMock, times(1)).fillRectangle(any(), any(), anyChar());
        verify(graphicsMock, times(1)).setBackgroundColor(any());
    }

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
    public void testDrawScore2() {
        TextGraphics graphicsMock = mock(TextGraphics.class);
        Score score = new Score();
        score.draw(graphicsMock);
        verify(graphicsMock, times(24)).fillRectangle(any(), any(), anyChar());
        verify(graphicsMock, times(74)).setBackgroundColor(any());
    }
    @Test
    public void testChangeNumber() {
        Numero numero = new Numero(5, 5);
        numero.changeNumber(3);
        assertEquals(3 ,numero.getNum());
        numero.changeNumber(9);
        assertEquals(9 ,numero.getNum());
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
