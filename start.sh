#!/bin/bash

CLASSPATH=$CLASSPATH:pi4j-core.jar:pi4j-device.jar:pi4j-example.jar:pi4j-gpio-extension.jar:pi4j-service.jar
export CLASSPATH

java com.naka.anpi.AnpiMain

