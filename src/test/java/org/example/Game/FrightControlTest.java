package org.example.Game;
import org.example.PacMan.Player;
import org.example.Sounds.soundTrack;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Timer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FrightControlTest {
    @Test
    public void iniciate() throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        soundTrack bgMock = mock(soundTrack.class);
        Timer timerMock = mock(Timer.class);
        FrightControl frightControl = new FrightControl(5);
        frightControl.setBg(bgMock);
        frightControl.setTimer(timerMock);
        frightControl.FrightClockIniciate();
        Thread.sleep(5000);
        verify(bgMock, Mockito.times(1)).play();
        Mockito.verify(bgMock, Mockito.times(1)).loop();
    }
    @Test
    public void testHour() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        FrightControl frightControl = new FrightControl(5);
        assertEquals(false,frightControl.isFrightHour());
        frightControl.startFrightHour();
        assertEquals(true,frightControl.isFrightHour());
        frightControl.endFrightHour();
        assertEquals(false,frightControl.isFrightHour());
    }
    @Test
    public void notificationToObservers() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        FrightControl frightControl = new FrightControl(5);
        Player player = mock(Player.class);
        frightControl.addObserver(player);
        frightControl.notifyObservers();
        verify(player).FrightHourEnded();
    }
}
