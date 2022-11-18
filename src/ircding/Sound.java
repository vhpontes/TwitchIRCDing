package ircding;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    
    Clip clip;
    URL soundURL[] = new URL[20];
    FloatControl fc;
    int volumeScale = 3;
    float volume;
    
    public Sound() {
        
        String userDirectory = Paths.get("").toAbsolutePath().toString();
        String psep = File.separator;
        
        File aDirectory = new File(userDirectory+"/sounds/");
        String[] filesInDir = aDirectory.list();
        Arrays.sort(filesInDir);
        for ( int i = 0; i < filesInDir.length; i++ ) {
            try {
                soundURL[i] = new URL("file:///"+userDirectory+psep+"sounds"+psep+filesInDir[i]);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(i+":"+userDirectory+psep+"sounds"+psep+filesInDir[i]);
        }   
     
//        soundURL[0] = getClass().getResource("/res/sound/ding.wav");
//        soundURL[1] = getClass().getResource("/res/sound/cuttree.wav");
//        soundURL[2] = getClass().getResource("/res/sound/Chat.wav");
//        soundURL[3] = getClass().getResource("/res/sound/Message.wav");
    }
    
    public void setFile(int soundIndex) {
        System.out.println("SoundIndex:"+soundIndex);
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[soundIndex]);
            clip = AudioSystem.getClip(); 
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void play() {
        clip.start();
    }
    
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void stop() {
        clip.stop();
    }
    
    public void checkVolume() {
        switch(volumeScale){
            case 0: volume = -80f; break;
            case 1: volume = -20f; break;
            case 2: volume = -12f; break;
            case 3: volume = -5f; break;
            case 4: volume = 1f; break;
            case 5: volume = 6f; break;
        }
        if(volumeScale >= 0 && volumeScale < 6) {
            fc.setValue(volume);
        }
    }
}