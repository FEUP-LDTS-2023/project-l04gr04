import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveTest {
    Player player;
    Monster monster;
    @BeforeEach
    public void setPlayer() {
        player = new Player(80, 80);
    }
    @Test
    public void redTargeting() {
        RedMonster redMonster = new RedMonster(15, 15);
        assertEquals(redMonster.target(player.getPosition(), "", new Position(0, 0)), player.getPosition());
    }
    @Test
    public void orangeTargeting() {
        OrangeMonster orangeMonster = new OrangeMonster(15, 15);
        double dist = orangeMonster.distance(player.getPosition(), orangeMonster.getPosition());
        if (dist > 8) assertEquals(orangeMonster.target(player.getPosition(), "", new Position(0, 0)), player.getPosition());
        if (dist <= 8) assertEquals(orangeMonster.target(player.getPosition(), "", new Position(0, 0)), new Position(2, 56));
    }
    @Test
    public void pinkTargeting() {
        PinkMonster pinkMonster = new PinkMonster(15, 15);
        assertEquals(pinkMonster.target(player.getPosition(), "up", new Position(0,0)), new Position(player.getX() - 4, player.getY() - 4));
        assertEquals(pinkMonster.target(player.getPosition(), "down", new Position(0,0)), new Position(player.getX(), player.getY() + 4 + 14));
        assertEquals(pinkMonster.target(player.getPosition(), "left", new Position(0,0)), new Position(player.getX() - 4, player.getY()));
        assertEquals(pinkMonster.target(player.getPosition(), "right", new Position(0,0)), new Position(player.getX() + 4 + 14, player.getY()));
    }
    @Test
    public void blueTargeting() {
        BlueMonster blueMonster = new BlueMonster(15,15);
        assertEquals(blueMonster.target(player.getPosition(), "", new Position(0, 0)), player.getPosition());
        String direction = player.facingDirection;
        Position middleP;
        Position redPosition = new Position(30, 30);
        if (direction.equals("up")) middleP =  new Position(player.getX()-2, player.getY()-2);
        else if (direction.equals("down")) middleP = new Position(player.getX(), player.getY()+2+14);
        else if (direction.equals("left")) middleP = new Position(player.getX()-2, player.getY());
        else middleP = new Position(player.getX()+2+14, player.getY());
        assertEquals(blueMonster.target(player.getPosition(), "right", redPosition), new Position(2 * middleP.getX() - redPosition.getX(), 2 * middleP.getY() - redPosition.getY()));
    }

    @Test
    public void moving() throws IOException {
        Mapa newMap = new Mapa(200, 200);
        monster.move(new Position(1, 25), newMap.loadMapFromFile("map.txt"));
        assertEquals(new Position(80, 25), monster.getPosition());
    }
}
