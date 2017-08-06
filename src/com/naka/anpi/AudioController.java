package com.naka.anpi;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioController {

	public AudioController() {
	}

	public void playAudioByFile(String fileName)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
		if (fileName == null) {
			return;
		}

		AudioInputStream audioInputStream = null;
		Clip clip = null;

		try {

			URL url = getClass().getResource(fileName);
			audioInputStream = AudioSystem.getAudioInputStream(url);

			clip = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));

			clip.open(audioInputStream);
			clip.loop(0);
			Thread.sleep(3000);

			while (clip.isRunning()) {
				Thread.sleep(100);
			}

			clip.stop();

		} finally {
			clip.close();
		}
	}
}
