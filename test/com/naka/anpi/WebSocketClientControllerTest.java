package com.naka.anpi;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.junit.Test;

public class WebSocketClientControllerTest {

	@Test
	public void test() throws DeploymentException, IOException, InterruptedException {

		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		URI uri = URI.create(IConst.WS_TARGET_URL);

		WebSocketClientController wsController = new WebSocketClientController(new ErrorListener() {

			@Override
			public void errorOccured() {
				System.out.println("BUILD ERROR OCCURED!!");

			}
		});

		Session session = container.connectToServer(wsController, uri);
		while (session.isOpen()) {
			Thread.sleep(IConst.WEBSOCKET_SLEEP_INTERVAL);
		}
	}
}
