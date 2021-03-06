package com.naka.anpi.runner;

import java.io.IOException;
import java.net.URI;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import com.naka.anpi.IConst;
import com.naka.anpi.business.AudioController;
import com.naka.anpi.business.LampController;
import com.naka.anpi.business.WebSocketClientController;
import com.naka.anpi.listener.ErrorListener;

public class BuildErrorWatchRunner {
	
	public void runBuildWatch() {

		LampController lampController = new LampController();
		AudioController audio = new AudioController();

		
		try {
		
			Session session = createSession(lampController, audio);
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

	private Session createSession(LampController lampController, AudioController audio)
			throws DeploymentException, IOException {

		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		URI uri = URI.create(IConst.WS_TARGET_URL);
		
		WebSocketClientController wsController = new WebSocketClientController(new ErrorListener() {
			@Override
			public void errorOccured() {
				System.out.println("Build Error Occured");
				lampController.initilize();

				try {

					lampController.flashLamp();
					audio.playAudioByFile(IConst.AUDIO_FILE);
					Thread.sleep(IConst.ALERT_INTERVAL);

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
		
		return container.connectToServer(wsController, uri);
	}

}
