package com.naka.anpi.business;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class LampController {
	
	private GpioController gpio;
	
	/** Pat Lamp  : Raspi PIN 29(21) */
	private GpioPinDigitalOutput pitLamp;
	
	private boolean isPatLampFlashed;

	public LampController() {
		initilize();
	}

	/**
	 * GPIO PIN Initialize
	 */
	public void initilize() {
		if (gpio == null) {
			gpio = GpioFactory.getInstance();
			pitLamp = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "Red", PinState.LOW);
		}
		patLampOff();
		pitLamp.low();
	}

	/**
	 * Thins Method is flashing Lamp
	 */
	public void flashLamp() {
		if (!isPatLampFlashed) {
			patLampOn();
		} else {
			patLampOff();
		}
	}

	/**
	 * Raspi Pat Lamp On
	 */
	private void patLampOn() {
		pitLamp.high();
		isPatLampFlashed = true;
	}

	/**
	 * Raspi Pat Lamp Off
	 */
	private void patLampOff() {
		pitLamp.low();
		isPatLampFlashed = false;
	}

}