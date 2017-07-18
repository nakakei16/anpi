package com.naka.anpi;

public class AnpiMain {

	public static void main(String[] args) {

		LampController lampController = new LampController();
		lampController.initilize();

		try {
		
			for (int i = 0; i < 10; i++) {
				lampController.flashLamp();
				Thread.sleep(1000);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lampController.initilize();
		}
	}
}
