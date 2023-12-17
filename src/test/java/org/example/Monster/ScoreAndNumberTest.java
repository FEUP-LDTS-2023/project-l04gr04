package org.example.Monster;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import org.example.Mapa;
import org.example.Numbers.Numero;
import org.example.Numbers.Score;
import org.example.Numbers.Character;
import org.example.Position;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ScoreAndNumberTest {
    /*InputStream fontStream = getClass().getClassLoader().getResourceAsStream("square.ttf");
    Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
    Font customFont = font.deriveFont(Font.PLAIN, 2);
    SwingTerminalFontConfiguration fontConfig = new SwingTerminalFontConfiguration(true, SwingTerminalFontConfiguration.BoldMode.EVERYTHING, customFont);
    DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(1, 1)).setTerminalEmulatorFontConfiguration(fontConfig);
    Terminal terminal = terminalFactory.createTerminal();
    public Screen screen = new TerminalScreen(terminal);
    TextGraphics graphicsMock = screen.newTextGraphics();
    TextGraphics graphicsMock = mock(TextGraphics.class);

    private Mapa mapa;
    Character character;
    Score score;
    Numero numero;

    public ScoreAndNumberTest() throws IOException, FontFormatException {
    }
    //voltar aqui e fazer como no player e monster
    /*@Test
    public void testDrawReady() {
        character = new Character(5, 5);

        character.drawready(graphicsMock);
        // Verify that the correct drawing methods are called on graphicsMock
        verify(graphicsMock, times(1)).fillRectangle(any(), any(), anyChar());
        verify(graphicsMock, times(1)).setBackgroundColor(any());
        // Add assertions for the expected calls based on the drawTheStyle method implementation
    }
    @Test
    public void testDrawScore() {
        Character character = new Character(5, 5);
        TextGraphics graphicsMock = mock(TextGraphics.class);

        character.drawscore(graphicsMock);

        // Verify that the correct drawing methods are called on graphicsMock
        verify(graphicsMock, times(1)).fillRectangle(any(), any(), anyChar());
        verify(graphicsMock, times(1)).setBackgroundColor(any());        // Add assertions for the expected calls based on the drawTheStyle method implementation
    }
    @Test
    public void testDrawNumero() {
        Numero numero = new Numero(5, 5);
        TextGraphics graphicsMock = mock(TextGraphics.class);

        numero.draw(graphicsMock);

        // Verify that the correct drawing methods are called on graphicsMock
        verify(graphicsMock, times(1)).fillRectangle(any(), any(), anyChar());
        verify(graphicsMock, times(1)).setBackgroundColor(any());        // Add assertions for the expected calls based on the drawTheStyle method implementation
    }
    @Test
    public void testChangeNumber() {
        Numero numero = new Numero(5, 5);

        // Trigger the changeNumber method with different numbers
        numero.changeNumber(3);

        // Assert that the num array is correctly updated based on the provided cases
        assertEquals(new char[5][5], numero.getNum());
    }
    @Test
    public void testIncrement() {
        Score score = new Score();

        // Trigger the increment method with different increments
        score.increment(10);

        // Assert that the current score and reverseDisplay list are updated correctly
        assertEquals(0, score.getCurrentScore());
        assertEquals(new ArrayList<>(), score.getReverseDisplay());
    }*/




}
