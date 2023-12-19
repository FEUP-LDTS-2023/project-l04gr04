package org.example.Monster.MonsterBehaviorTest;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import com.groupcdg.pitest.annotations.DoNotMutate;
import org.example.Monster.BlueMonster;
import org.example.Monster.RedMonster;
import org.example.Monster.States.inCage;
import org.example.Monster.States.onCollision;
import org.example.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class OnCollisionTest {
    InputStream fontStream = getClass().getClassLoader().getResourceAsStream("square.ttf");
    Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
    Font customFont = font.deriveFont(Font.PLAIN, 2);
    SwingTerminalFontConfiguration fontConfig = new SwingTerminalFontConfiguration(true, SwingTerminalFontConfiguration.BoldMode.EVERYTHING, customFont);
    DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(1, 1)).setTerminalEmulatorFontConfiguration(fontConfig);
    Terminal terminal = terminalFactory.createTerminal();
    public Screen screen = new TerminalScreen(terminal);
    TextGraphics mockTextGraphics = screen.newTextGraphics();
    private BlueMonster mockMonster;

    public OnCollisionTest() throws IOException, FontFormatException {
    }
    @BeforeEach
    public void setUp() {
        mockMonster = mock(BlueMonster.class);
        mockMonster.setPosition(new Position(75, 42));
    }
    @Test
    public void testOnPacManCollision() {
        onCollision onCollisionState = new onCollision(mockMonster);
        onCollisionState.onPacManCollision();
        verify(mockMonster, never()).changeState(any());
        verify(mockMonster, never()).draw(any(), any());
    }
    @Test
    public void testModeOn() {
        onCollision onCollisionState = new onCollision(mockMonster);
        String mode = onCollisionState.modeOn();
        assertEquals("onCollision", mode);
    }

    @Test
    public void testDraw() {
        onCollision onCollisionState = new onCollision(mockMonster);
        onCollisionState.draw(mockTextGraphics, "#FFFFFF");
        verify(mockMonster, never()).darkDraw(mockTextGraphics,"#FFFFFF");
        verify(mockMonster, never()).blueDraw(mockTextGraphics,"#FFFFFF");
        verify(mockMonster, never()).normalDraw(mockTextGraphics,"#FFFFFF");
    }
    @Test
    public void testMove() {
        onCollision onCollisionState = new onCollision(mockMonster);
        onCollisionState.move(new Position(0, 0), new char[5][5], true, false, true, false);
        verify(mockMonster,never()).targetMove(any(Position.class), any(char[][].class), eq(true), eq(false), eq(true), eq(false));
    }
    @DoNotMutate
    @Test
    public void testFrightHourStarted() {
        onCollision onCollisionState = new onCollision(mockMonster);
        onCollisionState.FrightHourStarted();
        verify(mockMonster, never()).changeState(any());
    }
    @DoNotMutate
    @Test
    public void testFrightHourEnded() {
        onCollision onCollisionState = new onCollision(mockMonster);
        onCollisionState.FrightHourEnded();
        verify(mockMonster, never()).changeState(any());
    }
}
