package com.naka.anpi;

public class AnpiMain {

	public static void main(String[] args) {
		LampOperator lampOperator = new LampOperator();

		try {
			System.out.println("Anpi Start!!");

			for (int i = 0; i < 100; i++) {
				lampOperator.flashLamp();
				Thread.sleep(1000);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();

		} finally {
			lampOperator.initilize();
		}
	}

}
