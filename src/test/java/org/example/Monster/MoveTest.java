package org.example.Monster;

import org.example.PacMan.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class MoveTest {
    Player player;

    /*InputStream fontStream = getClass().getClassLoader().getResourceAsStream("square.ttf");
    Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
    Font customFont = font.deriveFont(Font.PLAIN, 2);
    SwingTerminalFontConfiguration fontConfig = new SwingTerminalFontConfiguration(true, SwingTerminalFontConfiguration.BoldMode.EVERYTHING, customFont);
    DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(1, 1)).setTerminalEmulatorFontConfiguration(fontConfig);
    Terminal terminal = terminalFactory.createTerminal();
    public Screen screen = new TerminalScreen(terminal);
    TextGraphics graphics = screen.newTextGraphics();


    public MoveTest() throws IOException, FontFormatException {
    }*/

    @BeforeEach
    public void setPlayer() {
        player = new Player(284, 243);
    }
    @Test
    public void redTargeting() {
        RedMonster redMonster = new RedMonster(74, 42);
        BlueMonster blueMonster= new BlueMonster(75, 42);
        double distance = redMonster.distance(redMonster.getPosition(), blueMonster.getPosition());
        Assertions.assertEquals(1.0,distance );
    }
    /*@Test
    public void orangeTargeting() {
        OrangeMonster orangeMonster = new OrangeMonster(74, 42);
        orangeMonster.mode = "Fright";
        double dist = orangeMonster.distance(player.getPosition(), orangeMonster.getPosition());
        if (dist > 8 * 14) assertEquals(orangeMonster.target(player.getPosition(), "", new Position(0, 0)), player.getPosition());
        if (dist <= 8 * 14) assertEquals(orangeMonster.target(player.getPosition(), "", new Position(0, 0)), new Position(2, 56));
    }
    @Test
    public void pinkTargeting() {
        PinkMonster pinkMonster = new PinkMonster(74, 42);
        pinkMonster.mode = "Fright";
        assertEquals(pinkMonster.target(player.getPosition(), "up", new Position(0,0)), new Position(player.getX(), player.getY() - 4 * 14));
        assertEquals(pinkMonster.target(player.getPosition(), "down", new Position(0,0)), new Position(player.getX(), player.getY() + 4 * 14));
        assertEquals(pinkMonster.target(player.getPosition(), "left", new Position(0,0)), new Position(player.getX() - 4 * 14, player.getY()));
        assertEquals(pinkMonster.target(player.getPosition(), "right", new Position(0,0)), new Position(player.getX() + 4 * 14, player.getY()));
    }
    @Test
    public void blueTargeting() {
        BlueMonster blueMonster = new BlueMonster(74,42);
        blueMonster.mode = "Fright";
        String direction = player.facingDirection;
        Position middleP;
        Position redPosition = new Position(167, 74);
        if (direction.equals("up")) middleP =  new Position(player.getX() - 2 * 14, player.getY() - 2 * 14);
        else if (direction.equals("down")) middleP = new Position(player.getX(), player.getY() + 2 * 14);
        else if (direction.equals("left")) middleP = new Position(player.getX() - 2 * 14, player.getY());
        else middleP = new Position(player.getX() + 2 * 14, player.getY());
        assertEquals(blueMonster.target(player.getPosition(), "right", redPosition), new Position(2 * middleP.getX() - redPosition.getX(), 2 * middleP.getY() - redPosition.getY()));
    }

    @Test
    public void movingWall() throws IOException {
        Mapa newMap = new Mapa(368, 392,graphics);
        RedMonster redMonster = new RedMonster(3,25);
        redMonster.move(new Position(5, 25), newMap.loadMapFromFile("map.txt")); //Testar se passa pela parede
        assertEquals(new Position(3, 24), redMonster.getPosition()); //Monstro move para cima
    }
    @Test
    public void movingNoWall() throws IOException {
        Mapa newMap = new Mapa(368, 392,graphics);
        RedMonster redMonster = new RedMonster(3,25);
        redMonster.move(new Position(3, 23), newMap.loadMapFromFile("map.txt"));
        assertEquals(new Position(3, 24), redMonster.getPosition()); //Monstro move para cima
    }*/
}