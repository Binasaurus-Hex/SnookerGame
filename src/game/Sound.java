package game;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	private int framePosition = 0;
	private boolean paused = false;
    private Clip clip;
    private boolean playing = false;
    public Sound(String fileName) {
        // specify the sound to play
        // (assuming the sound can be played by the audio system)
        // from a wave File
        try {
            URL soundUrl = Sound.class.getResource(fileName);
            if (soundUrl != null) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(soundUrl);
             // load the sound into memory (a Clip)
                clip = AudioSystem.getClip();
                clip.open(sound);
            }
            else {
                throw new RuntimeException("Sound: file not found: " + fileName);
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Malformed URL: " + e);
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
        }

    // play, stop, loop the sound clip
    }
    public void play(){
    	playing = true;
    	if(paused){
    		clip.setFramePosition(framePosition);
    		paused = false;
    	}
    	else{
    		clip.setFramePosition(0);	
    	}
        clip.start();
        
    }
    public void loop(){
    	playing = true;
    	if(paused){
    		clip.setFramePosition(framePosition);
    		paused = false;
    	}
    	else{
    		clip.setFramePosition(0);
    	}
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
    	playing = false;
    	paused = false;
    	clip.stop();
    }
    
    public void pause(){
    	playing = false;
    	paused = true;
    	framePosition = clip.getFramePosition();
    	clip.stop();
    }
    
    public boolean isPlaying(){
    	return playing;
    }
    
    public boolean isPaused(){
    	return paused;
    }
}
