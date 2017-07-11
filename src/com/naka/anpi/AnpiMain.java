package com.naka.anpi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class AnpiMain {

	private GpioController gpio;

	/** Red LED : Raspi PIN 29(21) */
	private GpioPinDigitalOutput pinRed;

	/** Red LED : Raspi PIN 28(20) */
	private GpioPinDigitalOutput pinGreen;

	private boolean RED_HIGH = false;
	private boolean GREEN_HIGH = false;

	public static void main(String[] args) {
		AnpiMain anpi = new AnpiMain();

		for (int i = 0; i < 100; i++) {

			try {
				anpi.flash();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				anpi.initilize();
				System.exit(0);
			}
		}
		anpi.initilize();
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
	public void flash() {

		if (RED_HIGH) {
			redOff();
			RED_HIGH = false;
		} else {
			redOn();
			RED_HIGH = true;
		}

	}

	/**
	 * Raspi Red LED On
	 */
	private void redOn() {
		initilize();
		pinRed.high();
		System.out.println("Red On");
	}

	/**
	 * Raspi Red LED Off
	 */
	private void redOff() {
		initilize();
		pinRed.low();
		System.out.println("Red Off");
	}
}
