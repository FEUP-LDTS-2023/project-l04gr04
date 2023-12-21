package org.example.OtherTests;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import org.example.Numbers.Numero;
import org.example.Numbers.Score;
import org.example.Numbers.Character;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
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
        verify(graphicsMock, times(480)).setBackgroundColor(any());
    }
    @Test
    public void testDrawScore() {
        Character character = new Character(5, 5);
        TextGraphics graphicsMock = mock(TextGraphics.class);
        character.drawscore(graphicsMock);
        // Verify that the correct drawing methods are called on graphicsMock
        verify(graphicsMock, times(136)).fillRectangle(any(), any(), anyChar());
        verify(graphicsMock, times(410)).setBackgroundColor(any());
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
