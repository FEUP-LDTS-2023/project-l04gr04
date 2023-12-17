package org.example.Monster.MonsterBehaviorTest;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import org.example.Monster.RedMonster;
import org.example.Monster.States.eaten;
import org.example.Monster.States.fright;
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

public class FrightTest {
    InputStream fontStream = getClass().getClassLoader().getResourceAsStream("square.ttf");
    Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
    Font customFont = font.deriveFont(Font.PLAIN, 2);
    SwingTerminalFontConfiguration fontConfig = new SwingTerminalFontConfiguration(true, SwingTerminalFontConfiguration.BoldMode.EVERYTHING, customFont);
    DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(1, 1)).setTerminalEmulatorFontConfiguration(fontConfig);
    Terminal terminal = terminalFactory.createTerminal();
    public Screen screen = new TerminalScreen(terminal);
    TextGraphics mockTextGraphics = screen.newTextGraphics();
    private RedMonster mockMonster;


    public FrightTest() throws IOException, FontFormatException {
    }

    @BeforeEach
    public void setUp() {
        mockMonster = mock(RedMonster.class);
        mockMonster.setPosition(new Position(75, 42));
    }
    @Test
    public void testOnPacManCollision() {
        fright frightState = new fright(mockMonster);

        frightState.onPacManCollision();

        // Verify that onPacManCollision does not change the state or perform any action
        verify(mockMonster, never()).changeState(any());
        verify(mockMonster, never()).draw(any(), any());
        //verify(mockMonster, times(0)).move(any(Position.class), any(char[][].class), anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean());
    }

    @Test
    public void testModeOn() {
        fright frightState = new fright(mockMonster);


        String mode = frightState.modeOn();

        // Verify that modeOn returns "eaten"
        assertEquals("fright", mode);
    }

    @Test
    public void testDraw() {
        fright frightState = new fright(mockMonster);


        frightState.draw(mockTextGraphics, "#FFFFFF");

        // Verify that draw calls the correct monster.darkDraw method
        verify(mockMonster).darkDraw(eq(mockTextGraphics), eq("#000000"));
    }

    @Test
    public void testMove() {
        fright frightState = new fright(mockMonster);

        frightState.move(new Position(0, 0), new char[5][5], true, false, true, false);

        // Verify that move calls the correct monster.targetMove method
        verify(mockMonster).targetMove(any(Position.class), any(char[][].class), eq(true), eq(false), eq(true), eq(false));
    }

    @Test
    public void testFrightHourStarted() {
        fright frightState = new fright(mockMonster);

        frightState.FrightHourStarted();

        // Verify that FrightHourStarted does not change the state
        verify(mockMonster, never()).changeState(any());
    }

    @Test
    public void testFrightHourEnded() {
        fright frightState = new fright(mockMonster);

        frightState.FrightHourEnded();

        // Verify that FrightHourEnded does not change the state
        verify(mockMonster, never()).changeState(any());
    }
    ///repetir para os outros todos
}
