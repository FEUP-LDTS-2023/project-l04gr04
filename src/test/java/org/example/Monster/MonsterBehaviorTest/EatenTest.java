package org.example.Monster.MonsterBehaviorTest;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import com.groupcdg.pitest.annotations.DoNotMutate;
import org.example.Monster.RedMonster;
import org.example.Monster.States.eaten;
import org.example.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EatenTest {
    InputStream fontStream = getClass().getClassLoader().getResourceAsStream("square.ttf");
    Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
    Font customFont = font.deriveFont(Font.PLAIN, 2);
    SwingTerminalFontConfiguration fontConfig = new SwingTerminalFontConfiguration(true, SwingTerminalFontConfiguration.BoldMode.EVERYTHING, customFont);
    DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(1, 1)).setTerminalEmulatorFontConfiguration(fontConfig);
    Terminal terminal = terminalFactory.createTerminal();
    public Screen screen = new TerminalScreen(terminal);
    TextGraphics mockTextGraphics = screen.newTextGraphics();
    private RedMonster mockMonster;

    public EatenTest() throws IOException, FontFormatException {
    }
    @BeforeEach
    public void setUp() {
        mockMonster = mock(RedMonster.class);
        mockMonster.setPosition(new Position(75, 42));
    }
    @Test
    public void testOnPacManCollision() {
        eaten eatenState = new eaten(mockMonster);
        eatenState.onPacManCollision();
        // Verify that onPacManCollision does not change the state or perform any action
        verify(mockMonster, never()).changeState(any());
        verify(mockMonster, never()).draw(any(), any());
    }
    @Test
    public void testModeOn() {
        eaten eatenState = new eaten(mockMonster);
        String mode = eatenState.modeOn();
        // Verify that modeOn returns "eaten"
        assertEquals("eaten", mode);
    }

    @Test
    public void testDraw() {
        eaten eatenState = new eaten(mockMonster);
        eatenState.draw(mockTextGraphics, "#FFFFFF");
        // Verify that draw calls the correct monster.darkDraw method
        verify(mockMonster).darkDraw(eq(mockTextGraphics), eq("#000000"));
    }
    @Test
    public void testMove() {
        eaten eatenState = new eaten(mockMonster);

        eatenState.move(new Position(0, 0), new char[5][5], true, false, true, false);

        // Verify that move calls the correct monster.targetMove method
        verify(mockMonster).targetMove(any(Position.class), any(char[][].class), eq(true), eq(false), eq(true), eq(false));
    }
    @Test
    public void testFrightHourStarted() {
        eaten eatenState = new eaten(mockMonster);

        eatenState.FrightHourStarted();

        // Verify that FrightHourStarted does not change the state
        verify(mockMonster, never()).changeState(any());
    }
    @Test
    public void testFrightHourEnded() {
        eaten eatenState = new eaten(mockMonster);

        eatenState.FrightHourEnded();

        // Verify that FrightHourEnded does not change the state
        verify(mockMonster, never()).changeState(any());
    }
}
