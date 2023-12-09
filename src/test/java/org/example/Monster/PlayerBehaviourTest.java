package org.example.Monster;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlayerBehaviourTest {
    InputStream fontStream = getClass().getClassLoader().getResourceAsStream("square.ttf");
    Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
    Font customFont = font.deriveFont(Font.PLAIN, 2);
    SwingTerminalFontConfiguration fontConfig = new SwingTerminalFontConfiguration(true, SwingTerminalFontConfiguration.BoldMode.EVERYTHING, customFont);
    DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(1, 1)).setTerminalEmulatorFontConfiguration(fontConfig);
    Terminal terminal = terminalFactory.createTerminal();
    public Screen screen = new TerminalScreen(terminal);
    TextGraphics graphicsMock = screen.newTextGraphics();
    private Player playerMock;
    private Mapa mapa;
    private Game game;
    @Mock
    private GameObserver observer;
    private GameState gameState;

    public PlayerBehaviourTest() throws IOException, FontFormatException {
    }

    @BeforeEach
    public void setPlayerMock() {
        playerMock = new Player(74, 42);
    }

    @BeforeEach
    public void setMap() throws IOException {
        mapa = new Mapa(202, 240, graphicsMock, "", 0, 0.0, 0.0, 0.0, 0.0, 0);
    }

    public void setGame() throws IOException, FontFormatException {
        game = new Game(220, 270);
    }

    public void setGameState() {
        MockitoAnnotations.openMocks(this);
        gameState = new GameState(2); // 2 seconds for fright hour
        gameState.addObserver(observer);
    }

    @Test
    public void whichModeTest() {
        assertEquals("hunt", playerMock.getMode());
        playerMock.FrightHourStarted();
        assertEquals("fright", playerMock.getMode());
    }
    @Test
    public void testNearBoundaries() {
        playerMock.setPosition(new Position(180, 50));
        playerMock.draw(graphicsMock);
        playerMock.move("right");
        assertTrue(playerMock.getX() >= 0 && playerMock.getX() < mapa.getWidth());
        playerMock.move("up");
        assertTrue(playerMock.getX() >= 0 && playerMock.getX() < mapa.getWidth());

        playerMock.setPosition(new Position(180, 222));
        playerMock.draw(graphicsMock);
        playerMock.move("right");
        assertTrue(playerMock.getX() >= 0 && playerMock.getX() < mapa.getWidth());
        playerMock.move("down");
        assertTrue(playerMock.getX() >= 0 && playerMock.getX() < mapa.getWidth());

        playerMock.setPosition(new Position(8, 236));
        playerMock.draw(graphicsMock);
        playerMock.move("left");
        assertTrue(playerMock.getX() >= 0 && playerMock.getX() < mapa.getWidth());
        playerMock.move("down");
        assertTrue(playerMock.getX() >= 0 && playerMock.getX() < mapa.getWidth());

        playerMock.setPosition(new Position(8, 27));
        playerMock.draw(graphicsMock);
        playerMock.move("left");
        assertTrue(playerMock.getX() >= 0 && playerMock.getX() < mapa.getWidth());
        playerMock.move("up");
        assertTrue(playerMock.getX() >= 0 && playerMock.getX() < mapa.getWidth());
    }

    @Test// voltar aqui no final
    public void testFrightHourStartAndEnd() throws InterruptedException, IOException, FontFormatException {
        setGame();
        setGameState();
        gameState.startFrightHour();
        gameState.notifyObservers();

        // Wait for the Timer to trigger and end the Fright Hour
        Thread.sleep(2000); // Adjust the time based on your expected delay

        gameState.notifyObservers();

        // Verify that FrightHourStarted was called
        verify(observer).FrightHourStarted();

        // Verify that FrightHourEnded was called after the Timer triggers
        verify(observer, timeout(3000)).FrightHourEnded();
    }
    @Test //Aqui também
    public void testMultipleObservers() {
        // Add another observer
        GameObserver anotherObserver = mock(GameObserver.class);
        gameState.addObserver(anotherObserver);

        // Start fright hour
        gameState.startFrightHour();

        // Verify that both observers receive the notification
        verify(observer).FrightHourStarted();
        verify(anotherObserver).FrightHourStarted();

        // End fright hour
        gameState.endFrightHour();

        // Verify that both observers receive the notification
        verify(observer).FrightHourEnded();
        verify(anotherObserver).FrightHourEnded();
    }

}
