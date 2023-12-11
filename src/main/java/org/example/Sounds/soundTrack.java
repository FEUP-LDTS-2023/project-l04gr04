package org.example.Sounds;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class soundTrack {
    private Clip sound;
    public soundTrack(String soundFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        URL resource = getClass().getClassLoader().getResource(soundFile);
        assert resource != null;
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(resource);
        sound = AudioSystem.getClip();
        sound.open(audioInputStream);

    }
    public void play(){
        if (sound.isRunning()) {
            return;
        }
        sound.setFramePosition(0);  // Define a posição do frame de volta para o início
        sound.start();}
    public void loop(){sound.loop(sound.LOOP_CONTINUOUSLY);}

    public void stop() {
        sound.stop();
        sound.setFramePosition(0);
    }
}
