#!/bin/bash

CLASSPATH=$CLASSPATH:bin/:lib/pi4j-core.jar:lib/pi4j-device.jar:lib/pi4j-example.jar:lib/pi4j-gpio-extension.jar:lib/pi4j-service.jar
export CLASSPATH

javac src/com/naka/anpi/AnpiMain.java -d bin/com/naka/anpi/
java com.naka.anpi.AnpiMain

