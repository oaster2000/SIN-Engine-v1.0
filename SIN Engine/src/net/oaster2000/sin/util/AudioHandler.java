package net.oaster2000.sin.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioHandler {

	private Clip clip;

	public void loadSound(final String url) {
		try {
			clip = AudioSystem.getClip();
			InputStream audioSrc = getClass().getResourceAsStream(url);
			InputStream bufferedIn = new BufferedInputStream(audioSrc);
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
			clip.open(inputStream);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new RuntimeException("Sound: Malformed URL: " + e);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
			throw new RuntimeException("Sound: Unsupported Audio File: " + e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Sound: Input/Output Error: " + e);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
		}
	}

	public void play() {
			clip.start();
	}

	public void rewind(int pos) {
		clip.setFramePosition(pos); // Must always rewind!
		clip.start();
	}

	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {
		clip.stop();
	}

	public void changeVolume(float gain) {
		FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		volume.setValue(gain);
		clip.start();
	}
	
	public void changeRate(float gain) {
		FloatControl rate = (FloatControl) clip.getControl(FloatControl.Type.SAMPLE_RATE);
		rate.setValue(gain);
		clip.start();
	}
}
