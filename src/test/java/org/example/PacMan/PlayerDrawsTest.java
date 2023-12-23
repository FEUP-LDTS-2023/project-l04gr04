package org.example.PacMan;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Monster.Monsters.BlueMonster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.Mockito.*;

public class PlayerDrawsTest {
    Player player;
    @BeforeEach
    public void set(){
        player = new Player(94,180);
    }
    @Test
    public void drawDead(){
        TextGraphics graphics = mock(TextGraphics.class);
        player.drawDead(graphics);
        verify(graphics,times(94)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(196)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawDead(graphics);
        verify(graphics,times(170)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(392)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawDead(graphics);
        verify(graphics,times(234)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(588)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawDead(graphics);
        verify(graphics,times(284)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(784)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawDead(graphics);
        verify(graphics,times(336)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(980)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawDead(graphics);
        verify(graphics,times(394)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(1176)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawDead(graphics);
        verify(graphics,times(454)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(1372)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawDead(graphics);
        verify(graphics,times(496)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(1568)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawDead(graphics);
        verify(graphics,times(522)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(1764)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawDead(graphics);
        verify(graphics,times(538)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(1960)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawDead(graphics);
        verify(graphics,times(538)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(2156)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawDead(graphics);
        verify(graphics,times(558)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(2352)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawDead(graphics);
        verify(graphics,times(558)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(2548)).setBackgroundColor(any());
    }
    @Test
    public void normalDrawRight(){
        TextGraphics graphics = mock(TextGraphics.class);
        player.setFacingDirection("right");
        player.drawNormal(graphics);
        verify(graphics,times(164)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(196)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawNormal(graphics);
        verify(graphics,times(294)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(392)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawNormal(graphics);
        verify(graphics,times(400)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(588)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawNormal(graphics);
        verify(graphics,times(530)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(784)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawNormal(graphics);
        verify(graphics,times(858)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(1176)).setBackgroundColor(any());
    }
    @Test
    public void normalDrawLeft(){
        TextGraphics graphics = mock(TextGraphics.class);
        player.setFacingDirection("left");
        player.drawNormal(graphics);
        verify(graphics,times(164)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(196)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawNormal(graphics);
        verify(graphics,times(294)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(392)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawNormal(graphics);
        verify(graphics,times(400)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(588)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawNormal(graphics);
        verify(graphics,times(530)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(784)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawNormal(graphics);
        verify(graphics,times(694)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(980)).setBackgroundColor(any());
    }
    @Test
    public void normalDrawDown(){
        TextGraphics graphics = mock(TextGraphics.class);
        player.setFacingDirection("down");
        player.drawNormal(graphics);
        verify(graphics,times(164)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(196)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawNormal(graphics);
        verify(graphics,times(294)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(392)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawNormal(graphics);
        verify(graphics,times(400)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(588)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawNormal(graphics);
        verify(graphics,times(530)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(784)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawNormal(graphics);
        verify(graphics,times(694)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(980)).setBackgroundColor(any());
    }
    @Test
    public void normalDrawUp(){
        TextGraphics graphics = mock(TextGraphics.class);
        player.setFacingDirection("up");
        player.drawNormal(graphics);
        verify(graphics,times(164)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(196)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawNormal(graphics);
        verify(graphics,times(294)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(392)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawNormal(graphics);
        verify(graphics,times(400)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(588)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawNormal(graphics);
        verify(graphics,times(530)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(784)).setBackgroundColor(any());
        player.mouthOpen += 15;
        player.drawNormal(graphics);
        verify(graphics,times(694)).fillRectangle(any(), any(), anyChar());
        verify(graphics,times(980)).setBackgroundColor(any());
    }
}
