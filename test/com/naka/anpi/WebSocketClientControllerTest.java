package com.naka.anpi;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.junit.Test;

public class WebSocketClientControllerTest {

	private final String tagetURL = "ws://54.202.105.112:8081/jenkins";

	@Test
	public void test() throws DeploymentException, IOException, InterruptedException {

		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		URI uri = URI.create(tagetURL);

		WebSocketClientController wsController = new WebSocketClientController(new BuildErrorListener() {
			
			@Override
			public void buildErrorOccured() {
				System.out.println("BUILD ERROR OCCURED!!");

			}
		});
		Session session = container.connectToServer(wsController, uri);

		while(session.isOpen()){
//		for (int i = 0; i < 80 && session.isOpen(); i++) {
			Thread.sleep(1000);
//			System.out.println("open:" + i);

		}
	}
}
