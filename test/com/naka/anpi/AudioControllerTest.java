package com.naka.anpi;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.junit.Test;

import junit.framework.TestCase;

public class AudioControllerTest extends TestCase {

	@Test
	public void testPlayAudioByFile()
			throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {
	
		AudioController audio = new AudioController();

		audio.playAudioByFile("audio/test.wav");;
		assertTrue(true);
	}

	@Test
	public void testPlayAudioByFile_NullParam() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
		try {

			AudioController audio = new AudioController();
			assertTrue(true);

		} catch (NullPointerException e) {

			fail("not cared null");
		}

	}
}
