package com.naka.anpi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class AnpiMain {
	private GpioController gpio;

	/** Red LED : Raspi PIN 29 */
	private GpioPinDigitalOutput pinRed;

	private boolean status = false;

	public static void main(String[] args) {
		AnpiMain anpi = new AnpiMain();

		for (int i = 0; i < 100; i++) {
			if (anpi.status) {
				anpi.redOff();
				anpi.status = false;
			} else {
				anpi.redOn();
				anpi.status = true;
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	public AnpiMain() {
		initilize();
	}

	/**
	 * GPIO PIN Initialize
	 */
	private void initilize() {
		if (gpio == null) {
			gpio = GpioFactory.getInstance();
			pinRed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "Red", PinState.LOW);
		}

		pinRed.low();

	}

	/**
	 * Raspi Red LED On
	 */
	public void redOn() {
		initilize();
		pinRed.high();
	}

	/**
	 * Raspi Red LED Off
	 */
	public void redOff() {
		initilize();
		pinRed.low();
	}
}
