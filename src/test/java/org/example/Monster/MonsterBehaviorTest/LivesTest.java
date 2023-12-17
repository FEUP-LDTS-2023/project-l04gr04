package org.example.Monster.MonsterBehaviorTest;

import org.example.Lifes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LivesTest {
    @Test
    public void testLives(){
        Lifes lifes = new Lifes(0,0);
        lifes.incrementLife();
        assertEquals(4,lifes.getNumber());
        lifes.decrementLife();
        assertEquals(3,lifes.getNumber());
        assertEquals(false,lifes.isempty());
    }
}
