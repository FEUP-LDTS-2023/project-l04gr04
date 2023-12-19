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
import org.example.Monster.States.eaten;
import org.example.Monster.States.fright;
import org.example.Monster.States.hunt;
import org.example.Monster.States.onCollision;
import org.example.Monster.monsterState;
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

public class HuntTest {
    InputStream fontStream = getClass().getClassLoader().getResourceAsStream("square.ttf");
    Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
    Font customFont = font.deriveFont(Font.PLAIN, 2);
    SwingTerminalFontConfiguration fontConfig = new SwingTerminalFontConfiguration(true, SwingTerminalFontConfiguration.BoldMode.EVERYTHING, customFont);
    DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(1, 1)).setTerminalEmulatorFontConfiguration(fontConfig);
    Terminal terminal = terminalFactory.createTerminal();
    public Screen screen = new TerminalScreen(terminal);
    TextGraphics mockTextGraphics = screen.newTextGraphics();
    private BlueMonster mockMonster;



    public HuntTest() throws IOException, FontFormatException {
    }

    @BeforeEach
    public void setUp() {
        mockMonster = mock(BlueMonster.class);
        mockMonster.setPosition(new Position(75, 42));
    }
    @Test
    public void testOnPacManCollision() {
        monsterState monsterState = new hunt(mockMonster);
        monsterState.onPacManCollision();
        verify(mockMonster).pacManLost();
    }
    @Test
    public void testModeOn() {
        hunt huntState = new hunt(mockMonster);
        String mode = huntState.modeOn();
        assertEquals("hunt", mode);
    }
    @Test
    public void testDraw() {
        hunt huntState = new hunt(mockMonster);
        huntState.draw(mockTextGraphics, "#FFFFFF");
        verify(mockMonster).normalDraw(eq(mockTextGraphics), eq("#FFFFFF"));
    }
    @Test
    public void testMove() {
        hunt huntState = new hunt(mockMonster);
        huntState.move(new Position(0, 0), new char[5][5], true, false, true, false);
        verify(mockMonster).targetMove(any(Position.class), any(char[][].class), eq(true), eq(false), eq(true), eq(false));
    }
    @Test
    public void testFrightHourStarted() {
        monsterState monsterState = new hunt(mockMonster);
        monsterState.FrightHourStarted();
        verify(mockMonster).changeState(new fright(any()));
    }
    @Test
    public void testFrightHourEnded() {
        hunt huntState = new hunt(mockMonster);
        huntState.FrightHourEnded();
        verify(mockMonster, never()).changeState(any());
    }
}
