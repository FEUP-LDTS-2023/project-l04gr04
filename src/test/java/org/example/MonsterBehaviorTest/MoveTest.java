package org.example.MonsterBehaviorTest;
import com.groupcdg.pitest.annotations.DoNotMutate;
import org.example.Game;
import org.example.Monster.BlueMonster;
import org.example.Monster.OrangeMonster;
import org.example.Monster.PinkMonster;
import org.example.Monster.RedMonster;
import org.example.Monster.States.*;
import org.example.PacMan.Player;
import org.example.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;


public class MoveTest {
    Player player;
    @BeforeEach
    public void setPlayer() {
        player = new Player(284, 243);
    }
    @DoNotMutate
    @Test
    public void redTargeting() {
        RedMonster redMonsterMock = new RedMonster(74,42);
        BlueMonster blueMonsterMock = new BlueMonster(75,42);
        Double d = redMonsterMock.distance(redMonsterMock.getPosition(), blueMonsterMock.getPosition());
        assertEquals(1.0, d);

        redMonsterMock.changeState(new eaten(redMonsterMock));
        Position target = redMonsterMock.target(player.getPosition(), "", new Position(0, 0));
        assertEquals( new Position(93, 115), target);

        redMonsterMock.setMs(new inCage(redMonsterMock));
        target = redMonsterMock.target(new Position(10, 10), "up", new Position(20, 20));
        assertEquals(new Position(93, 115 - 19), target);

        redMonsterMock.setMs(new scatter(redMonsterMock));
        target = redMonsterMock.target(new Position(10, 10), "up", new Position(20, 20));
        assertEquals(new Position(195, 1), target);
    }
    @DoNotMutate
    @Test
    public void orangeTargeting() {
        OrangeMonster orangeMonsterMock = new OrangeMonster(75, 42);
        orangeMonsterMock.changeState(new fright(orangeMonsterMock));
        double dist = orangeMonsterMock.distance(player.getPosition(), orangeMonsterMock.getPosition());
        if (dist > 8 * 14) assertEquals(orangeMonsterMock.target(player.getPosition(),"", new Position(0, 0)), player.getPosition());
        else assertEquals(orangeMonsterMock.target(player.getPosition(),"", new Position(0, 0)), new Position(2, 56));

        orangeMonsterMock.changeState(new eaten(orangeMonsterMock));
        Position target = orangeMonsterMock.target(player.getPosition(), "", new Position(0, 0));
        assertEquals(target, new Position(93, 115));

        orangeMonsterMock.changeState(new inCage(orangeMonsterMock));
        target = orangeMonsterMock.target(new Position(10, 10), "up", new Position(20, 20));
        assertEquals(new Position(93, 115 - 19), target);

        orangeMonsterMock.changeState(new scatter(orangeMonsterMock));
        target = orangeMonsterMock.target(new Position(10, 10), "up", new Position(20, 20));
        assertEquals(new Position(1, 237), target);

    }
    @DoNotMutate
    @Test
    public void pinkTargeting() {
        PinkMonster pinkMonsterMock = new PinkMonster(75, 42);
        pinkMonsterMock.changeState(new fright(pinkMonsterMock));
        assertEquals(pinkMonsterMock.target(player.getPosition(), "up", new Position(0,0)), new Position(player.getX(), player.getY() - 4 * 14));
        assertEquals(pinkMonsterMock.target(player.getPosition(), "down", new Position(0,0)), new Position(player.getX(), player.getY() + 4 * 14));
        assertEquals(pinkMonsterMock.target(player.getPosition(), "left", new Position(0,0)), new Position(player.getX() - 4 * 14, player.getY()));
        assertEquals(pinkMonsterMock.target(player.getPosition(), "right", new Position(0,0)), new Position(player.getX() + 4 * 14, player.getY()));

        pinkMonsterMock.changeState(new eaten(pinkMonsterMock));
        Position target = pinkMonsterMock.target(player.getPosition(), "", new Position(0, 0));
        assertEquals(target, new Position(93, 115));

        pinkMonsterMock.changeState(new inCage(pinkMonsterMock));
        target = pinkMonsterMock.target(new Position(10, 10), "up", new Position(20, 20));
        assertEquals(new Position(93, 115 - 19), target);

        pinkMonsterMock.changeState(new scatter(pinkMonsterMock));
        target = pinkMonsterMock.target(new Position(10, 10), "up", new Position(20, 20));
        assertEquals(new Position(2, 1), target);
    }
    @DoNotMutate
    @Test
    public void blueTargeting() {
        BlueMonster blueMonsterMock = new BlueMonster(75, 42);
        blueMonsterMock.changeState(new fright(blueMonsterMock));
        String direction = player.facingDirection;
        Position middleP;
        Position redPosition = new Position(167, 74);
        if (direction.equals("up")) middleP =  new Position(player.getX() - 2 * 14, player.getY() - 2 * 14);
        else if (direction.equals("down")) middleP = new Position(player.getX(), player.getY() + 2 * 14);
        else if (direction.equals("left")) middleP = new Position(player.getX() - 2 * 14, player.getY());
        else middleP = new Position(player.getX() + 2 * 14, player.getY());
        int Vx = redPosition.getX() - middleP.getX();
        int Vy = redPosition.getY() - middleP.getY();
        assertEquals(blueMonsterMock.target(player.getPosition(), "left", redPosition), new Position( middleP.getX() - Vx, middleP.getY() - Vy));

        blueMonsterMock.changeState(new eaten(blueMonsterMock));
        Position target = blueMonsterMock.target(player.getPosition(), "", new Position(0, 0));
        assertEquals(target, new Position(93, 115));

        blueMonsterMock.changeState(new inCage(blueMonsterMock));
        target = blueMonsterMock.target(new Position(10, 10), "up", new Position(20, 20));
        assertEquals(new Position(93, 115 - 19), target);

        blueMonsterMock.changeState(new scatter(blueMonsterMock));
        target = blueMonsterMock.target(new Position(10, 10), "up", new Position(20, 20));
        assertEquals(new Position(195, 237), target);
    }
    @DoNotMutate
    @Test
    public void testModeTransition() {
        RedMonster redMonster = new RedMonster(75,42);
        redMonster.setMs(new inCage(redMonster));
        redMonster.changeState(new fright(redMonster));

        assertEquals("fright", redMonster.ms.modeOn());
    }
    @DoNotMutate
    @Test
    public void movingWall() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Game game = new Game(220, 270);
        RedMonster redMonster = new RedMonster(94,180);
        redMonster.setMs(new hunt(redMonster));
        redMonster.move(new Position(93,178), game.loadMapFromFile("map.txt")); //Testar se passa pela parede
        assertEquals(new Position(93, 180), redMonster.getPosition()); //Monstro move para cima
    }
    @DoNotMutate
    @Test
    public void movingNoWall() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Game game = new Game(220, 270);
        RedMonster redMonster = new RedMonster(94,180);
        redMonster.setMs(new hunt(redMonster));
        redMonster.move(new Position(97,185), game.loadMapFromFile("map.txt")); //Testar se passa pela parede
        assertEquals(new Position(93, 180), redMonster.getPosition()); //Monstro move para cima
    }
    @Test
    public void moveFright(){
        BlueMonster blueMonster = new BlueMonster(94,180);
        blueMonster.setMovingDirection("left");
        blueMonster.frightMove(true,true,true,true);
        assertEquals(new Position(95,180),blueMonster.getPosition());
        blueMonster = new BlueMonster(94,180);
        blueMonster.setMovingDirection("right");
        blueMonster.frightMove(true,true,true,true);
        assertEquals(new Position(93,180),blueMonster.getPosition());
        blueMonster = new BlueMonster(94,180);
        blueMonster.setMovingDirection("up");
        blueMonster.frightMove(true,true,true,true);
        assertEquals(new Position(94,181),blueMonster.getPosition());
        blueMonster = new BlueMonster(94,180);
        blueMonster.setMovingDirection("down");
        blueMonster.frightMove(true,true,true,true);
        assertEquals(new Position(94,179),blueMonster.getPosition());
        blueMonster.frightMove(true,true,true,true);
        assertNotEquals(new Position(94,179),blueMonster.getPosition());

    }
}