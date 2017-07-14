package com.naka.anpi;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

public class AudioController {

	private final static String scheme = "file://";

	public AudioController() {
	}

	public void playAudioByFile(String filePath) throws MalformedURLException {
		if (filePath == null) {
			return;
		}

		URL audioFileUrl = new URL(filePath);
		AudioClip audio = Applet.newAudioClip(audioFileUrl);
		audio.play();

	}

	String createFileUrl(String filePath) {
		if (filePath == null) {
			return null;
		}

		int length = filePath.length();
		StringBuilder dealedFilePath = new StringBuilder(filePath);

		for (int i = 0; i < length; i++) {
			String charact = filePath.substring(i, i + 1);
			if (charact.equals("/")) {
				dealedFilePath.delete(0, 1);
			} else {
				break;
			}
		}

		return scheme + dealedFilePath.toString();
	}
}
