package com.naka.anpi;

public interface IConst {
	public final String WS_TARGET_URL = "ws://54.202.105.112:8081/jenkins";
	public final String AUDIO_FILE = "audio/Tsukeyakiba.wav";
	public final long WEBSOCKET_SLEEP_INTERVAL = 1000;
	public final String JSON_TARGET_NAME = "status";
	public final String JSON_TARGET_VALUE = "FAILURE";
	public final long ALERT_INTERVAL = 5000;	
	public final long AUDIO_INTERVAL = 3000;
	public final long AUDIO_END_CHECK_TIME = 100;
}
