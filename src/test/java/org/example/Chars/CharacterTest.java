package org.example.Chars;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.Mockito.*;

public class CharacterTest {
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
}
