package org.example;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public interface MapaListener {
    void gameLost() throws UnsupportedAudioFileException, LineUnavailableException, IOException;
    void levelLost(char[][] mapa) throws UnsupportedAudioFileException, LineUnavailableException, IOException;
    void levelWon() throws UnsupportedAudioFileException, LineUnavailableException, IOException;
}
