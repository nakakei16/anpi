package com.naka.anpi;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AnpiMain {

	public static void main(String[] args) {

		LampController lampController = new LampController();
		AudioController audio = new AudioController();

		lampController.initilize();

		try {
			
			lampController.flashLamp();
			audio.playAudioByFile("audio/Tsukeyakiba.wav");;
			Thread.sleep(1000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} finally {
			lampController.initilize();
		}
	}
}
