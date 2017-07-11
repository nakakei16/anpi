#!/bin/bash

CLASSPATH=$CLASSPATH:bin/:lib/pi4j-core.jar:lib/pi4j-device.jar:lib/pi4j-example.jar:lib/pi4j-gpio-extension.jar:lib/pi4j-service.jar
export CLASSPATH

java com.naka.anpi.AnpiMain

