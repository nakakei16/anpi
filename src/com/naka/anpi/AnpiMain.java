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

	/** Green LED : Raspi PIN 28(20) */
	private GpioPinDigitalOutput pinGreen;

	private boolean RED_HIGH = false;
	private boolean GREEN_HIGH = false;

	public static void main(String[] args) {
		AnpiMain anpi = new AnpiMain();
		anpi.initilize();

		try {
			System.out.println("Anpi Start!!");

			for (int i = 0; i < 100; i++) {
//				anpi.flash();
				 anpi.check_flas();
				Thread.sleep(1000);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();

		} finally {
			anpi.initilize();
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
			pinGreen = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28, "Green", PinState.LOW);
		}

		pinRed.low();
		pinGreen.low();

	}

	/**
	 * Raspi Red LED On
	 */
	public void flash() {
		initilize();
		
		if (RED_HIGH) {
			System.out.println("=== RED ON -> OFF ===");
			redOff();
			greenOn();

		} else if (!RED_HIGH) {
			System.out.println("=== RED OFF -> RED ON ===");
			redOn();
			greenOff();
		} else {
			System.out.println("== OTHERS -> ALL OFF");
			redOff();
			greenOff();
		}
	}

	/**
	 * Thins Method is to check for flashin LED
	 */
	private void check_flas() {
		if (!RED_HIGH) {
			redOn();
		} else {
			redOff();
		}

		if (!GREEN_HIGH) {
			greenOn();
		} else {
			greenOff();
		}
	}

	/**
	 * Raspi Green LED On
	 */
	private void greenOn() {
		pinGreen.high();
		GREEN_HIGH = true;
	}

	/**
	 * Raspi Green LED Off
	 */
	private void greenOff() {
		pinGreen.low();
		GREEN_HIGH = false;
	}

	/**
	 * Raspi Red LED On
	 */
	private void redOn() {
		pinRed.high();
		RED_HIGH = true;
	}

	/**
	 * Raspi Red LED Off
	 */
	private void redOff() {
		pinRed.low();
		RED_HIGH = false;
	}

}
