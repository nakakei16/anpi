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

			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			URI uri = URI.create(IConst.WS_TARGET_URL);
			
			WebSocketClientController wsController = new WebSocketClientController(new BuildErrorListener() {

				@Override
				public void buildErrorOccured() {
					System.out.println("Build Error Occured");
					lampController.initilize();

					try {

						lampController.flashLamp();
						audio.playAudioByFile(IConst.AUDIO_FILE);
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

			
			Session session = container.connectToServer(wsController, uri);
			while (session.isOpen()) {
				Thread.sleep(IConst.WEBSOCKET_SLEEP_INTERVAL);
			}

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
