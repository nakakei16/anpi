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

	private final static String scheme = "file://";

	public AudioController() {
	}

	/**
	 * Play sound
	 * 
	 * @param full
	 *            path filePath
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 * @throws InterruptedException
	 */
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
			System.out.println("Open");
			clip.loop(0);
			System.out.println("Play");
			Thread.sleep(3000);

			while (clip.isRunning()) {
				Thread.sleep(100);
			}

			clip.stop();

		} finally {
			clip.close();
			System.out.println("Close");

		}
	}
}
