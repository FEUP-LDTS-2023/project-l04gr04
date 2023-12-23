package org.example.Monster.MonstersTest;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Game.Game;
import org.example.Monster.Monsters.BlueMonster;
import org.example.Others.Position;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.Mockito.*;

public class MonsterGenericTests {
    @Test
    public void normalDrawRight(){
        BlueMonster blueMonster = new BlueMonster(0,0);
        blueMonster.setMovingDirection("right");
        TextGraphics graphics = mock(TextGraphics.class);
        String Color = "#000000";
        blueMonster.normalDraw(graphics,Color);
        int baseF = 158;
        int baseS = 228;
        verify(graphics,times(baseF)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS)).setBackgroundColor(any());
        blueMonster.mouthOpenM+=27;
        blueMonster.normalDraw(graphics,Color);
        verify(graphics,times(baseF*2 + 2)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS*2)).setBackgroundColor(any());
        blueMonster.mouthOpenM+=27;
        blueMonster.normalDraw(graphics,Color);
        verify(graphics,times(baseF*3 + 2)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS*3)).setBackgroundColor(any());
    }
    @Test
    public void normalDrawLeft(){
        BlueMonster blueMonster = new BlueMonster(0,0);
        blueMonster.setMovingDirection("left");
        TextGraphics graphics = mock(TextGraphics.class);
        String Color = "#000000";
        blueMonster.normalDraw(graphics,Color);
        int baseF = 158;
        int baseS = 228;
        verify(graphics,times(baseF)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS)).setBackgroundColor(any());
        blueMonster.mouthOpenM+=27;
        blueMonster.normalDraw(graphics,Color);
        verify(graphics,times(baseF*2 + 2)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS*2)).setBackgroundColor(any());
        blueMonster.mouthOpenM+=27;
        blueMonster.normalDraw(graphics,Color);
        verify(graphics,times(baseF*3 + 2)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS*3)).setBackgroundColor(any());
    }
    @Test
    public void normalDrawDown(){
        BlueMonster blueMonster = new BlueMonster(0,0);
        blueMonster.setMovingDirection("down");
        TextGraphics graphics = mock(TextGraphics.class);
        String Color = "#000000";
        blueMonster.normalDraw(graphics,Color);
        int baseF = 158;
        int baseS = 228;
        verify(graphics,times(baseF)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS)).setBackgroundColor(any());
        blueMonster.mouthOpenM+=27;
        blueMonster.normalDraw(graphics,Color);
        verify(graphics,times(baseF*2 + 2)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS*2)).setBackgroundColor(any());
        blueMonster.mouthOpenM+=27;
        blueMonster.normalDraw(graphics,Color);
        verify(graphics,times(baseF*3 + 2)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS*3)).setBackgroundColor(any());
    }
    @Test
    public void normalDrawUp(){
        BlueMonster blueMonster = new BlueMonster(0,0);
        blueMonster.setMovingDirection("up");
        TextGraphics graphics = mock(TextGraphics.class);
        String Color = "#000000";
        blueMonster.normalDraw(graphics,Color);
        int baseF = 156;
        int baseS = 228;
        verify(graphics,times(baseF)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS)).setBackgroundColor(any());
        blueMonster.mouthOpenM+=27;
        blueMonster.normalDraw(graphics,Color);
        verify(graphics,times(baseF*2 + 2)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS*2)).setBackgroundColor(any());
        blueMonster.mouthOpenM+=27;
        blueMonster.normalDraw(graphics,Color);
        verify(graphics,times(baseF*3 + 2)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS*3)).setBackgroundColor(any());
    }
    @Test
    public void DarkDrawRight(){
        BlueMonster blueMonster = new BlueMonster(0,0);
        blueMonster.setMovingDirection("right");
        TextGraphics graphics = mock(TextGraphics.class);
        String Color = "#000000";
        blueMonster.darkDraw(graphics,Color);
        int baseF = 158;
        int baseS = 228;
        verify(graphics,times(baseF)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS)).setBackgroundColor(any());
        blueMonster.mouthOpenM+=27;
        blueMonster.darkDraw(graphics,Color);
        verify(graphics,times(baseF*2 + 2)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS*2)).setBackgroundColor(any());
        blueMonster.mouthOpenM+=27;
        blueMonster.darkDraw(graphics,Color);
        verify(graphics,times(baseF*3 + 2)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS*3)).setBackgroundColor(any());
    }
    @Test
    public void darkDrawLeft(){
        BlueMonster blueMonster = new BlueMonster(0,0);
        blueMonster.setMovingDirection("left");
        TextGraphics graphics = mock(TextGraphics.class);
        String Color = "#000000";
        blueMonster.darkDraw(graphics,Color);
        int baseF = 158;
        int baseS = 228;
        verify(graphics,times(baseF)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS)).setBackgroundColor(any());
        blueMonster.mouthOpenM+=27;
        blueMonster.darkDraw(graphics,Color);
        verify(graphics,times(baseF*2 + 2)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS*2)).setBackgroundColor(any());
        blueMonster.mouthOpenM+=27;
        blueMonster.darkDraw(graphics,Color);
        verify(graphics,times(baseF*3 + 2)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS*3)).setBackgroundColor(any());
    }
    @Test
    public void darkDrawDown(){
        BlueMonster blueMonster = new BlueMonster(0,0);
        blueMonster.setMovingDirection("down");
        TextGraphics graphics = mock(TextGraphics.class);
        String Color = "#000000";
        blueMonster.darkDraw(graphics,Color);
        int baseF = 158;
        int baseS = 228;
        verify(graphics,times(baseF)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS)).setBackgroundColor(any());
        blueMonster.mouthOpenM+=27;
        blueMonster.darkDraw(graphics,Color);
        verify(graphics,times(baseF*2 + 2)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS*2)).setBackgroundColor(any());
        blueMonster.mouthOpenM+=27;
        blueMonster.darkDraw(graphics,Color);
        verify(graphics,times(baseF*3 + 2)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS*3)).setBackgroundColor(any());
    }
    @Test
    public void darkDrawUp(){
        BlueMonster blueMonster = new BlueMonster(0,0);
        blueMonster.setMovingDirection("up");
        TextGraphics graphics = mock(TextGraphics.class);
        String Color = "#000000";
        blueMonster.darkDraw(graphics,Color);
        int baseF = 156;
        int baseS = 228;
        verify(graphics,times(baseF)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS)).setBackgroundColor(any());
        blueMonster.mouthOpenM+=27;
        blueMonster.darkDraw(graphics,Color);
        verify(graphics,times(baseF*2 + 2)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS*2)).setBackgroundColor(any());
        blueMonster.mouthOpenM+=27;
        blueMonster.darkDraw(graphics,Color);
        verify(graphics,times(baseF*3 + 2)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS*3)).setBackgroundColor(any());
    }
    @Test
    public void BlueDraw(){
        BlueMonster blueMonster = new BlueMonster(0,0);
        blueMonster.setMovingDirection("up");
        TextGraphics graphics = mock(TextGraphics.class);
        blueMonster.blueDraw(graphics);
        int baseF = 158;
        int baseS = 212;
        verify(graphics,times(baseF)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS)).setBackgroundColor(any());
        blueMonster.mouthOpenM+=27;
        blueMonster.blueDraw(graphics);
        verify(graphics,times(baseF*2 + 2)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS*2)).setBackgroundColor(any());
        blueMonster.mouthOpenM+=27;
        blueMonster.blueDraw(graphics);
        verify(graphics,times(baseF*3 + 2)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(baseS*3)).setBackgroundColor(any());
    }
    @Test
    public void movementOnTheEdges() throws IOException {
        Game game = new Game(220,270);
        BlueMonster blueMonster = new BlueMonster(191,100);
        blueMonster.setMovingDirection("right");
        blueMonster.move(null,game.loadMapFromFile("map.txt"));
        assertEquals(new Position(192,100),blueMonster.getPosition());
        blueMonster.setPosition(new Position(4,100));
        blueMonster.setMovingDirection("left");
        blueMonster.move(null,game.loadMapFromFile("map.txt"));
        assertEquals(new Position(3,100),blueMonster.getPosition());

    }
}
