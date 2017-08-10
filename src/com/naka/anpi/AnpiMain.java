package com.naka.anpi;

import com.naka.anpi.runner.BuildErrorWatchRunner;

public class AnpiMain {

	public static void main(String[] args) {

		(new BuildErrorWatchRunner()).runBuildWatch();
	}
}
