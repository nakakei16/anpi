package com.naka.anpi;

import java.io.IOException;
import java.net.URI;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class AnpiMain {

	public static void main(String[] args) {

		LampController lampController = new LampController();
		AudioController audio = new AudioController();

		try {

			String tagetURL = "ws://54.202.105.112:8081/jenkins";

			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			URI uri = URI.create(tagetURL);

			WebSocketClientController wsController = new WebSocketClientController(new BuildErrorListener() {

				@Override
				public void buildErrorOccured() {

					lampController.initilize();

					try {

						lampController.flashLamp();
						audio.playAudioByFile("audio/Tsukeyakiba.wav");
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
			});
			Session session;
			session = container.connectToServer(wsController, uri);

			for (int i = 0; i < 80 && session.isOpen(); i++) {
				Thread.sleep(1000);
				System.out.println("open:" + i);

			}

			// LampController lampController = new LampController();
			// AudioController audio = new AudioController();
			//
			// lampController.initilize();
			//
			// try {
			//
			// lampController.flashLamp();
			// audio.playAudioByFile("audio/Tsukeyakiba.wav");;
			// Thread.sleep(1000);
			//
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DeploymentException e) {
			e.printStackTrace();
		} finally {
			lampController.initilize();
		}
	}
}
