package com.naka.anpi;

import java.net.MalformedURLException;

public class AnpiMain {

	public static void main(String[] args) {
		LampController lampOperator = new LampController();
		AudioController audioOperator = new AudioController();
		
		try {
			audioOperator.playAudioByFile("");
		
			for (int i = 0; i < 30; i++) {
				lampOperator.flashLamp();
				Thread.sleep(1000);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();

		} catch (MalformedURLException e) {
			e.printStackTrace();

		} finally {
			lampOperator.initilize();
		}
	}

}
