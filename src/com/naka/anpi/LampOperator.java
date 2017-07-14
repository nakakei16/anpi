package com.naka.anpi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class LampOperator {
	private GpioController gpio;
	/** Red LED : Raspi PIN 29(21) */
	private GpioPinDigitalOutput pinRed;
	/** Green LED : Raspi PIN 28(20) */
	private GpioPinDigitalOutput pinGreen;
	private boolean RED_HIGH;
	private boolean GREEN_HIGH;

	public LampOperator() {
		initilize();
	}

	/**
	 * GPIO PIN Initialize
	 */
	public void initilize() {
		if (gpio == null) {
			gpio = GpioFactory.getInstance();
			pinRed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "Red", PinState.LOW);
			pinGreen = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28, "Green", PinState.LOW);
		}

		pinRed.low();
		pinGreen.low();
	}

	/**
	 * Thins Method is flashing Lamp
	 */
	public void flashLamp() {
		if (!RED_HIGH && GREEN_HIGH) {
			redOff();
			greenOff();
		} else if (!RED_HIGH) {
			redOn();
			greenOff();
		} else if (!GREEN_HIGH) {
			greenOn();
			redOff();
		} else {
			redOff();
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