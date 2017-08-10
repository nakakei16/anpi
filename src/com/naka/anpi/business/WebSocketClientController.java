package com.naka.anpi.business;

import java.io.IOException;

import javax.websocket.ClientEndpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import com.naka.anpi.listener.ErrorListener;

@ClientEndpoint
public class WebSocketClientController {

	private ErrorListener buildErrorListener = null;
	
    public WebSocketClientController(ErrorListener listener) {
        super();
        this.buildErrorListener = listener;
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig endPointConf) {
    	System.out.println("Seesion is Opened!");
    }

    @OnMessage
    public void receiveMessage(String receivedMessage) throws IOException {
    	if(JsonResultChecker.isBuildSuccess(receivedMessage) == false && buildErrorListener != null){
    		buildErrorListener.errorOccured();
    		System.out.println("BUILD RESULT : FAILURE");
    	}
    }

    @OnClose
    public void onClose(Session session) {
    	System.out.println("Session is Closed");
    }

    @OnError
    public void onError(Throwable t) {
    	System.out.println("[ERROR] " + t.getMessage());
    }
    
    public void addListener(ErrorListener listener){
    	if(this.buildErrorListener != null) return;
    	this.buildErrorListener = listener;
    }
    public void removeListener(){
    	this.buildErrorListener = null;
    }    
}
