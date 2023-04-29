package com.mygdx.code;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.code.Code;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(90);
		config.useVsync(true);
		config.setFullscreenMode(config.getDisplayMode());
		config.setTitle("Dragon Boat Racing");
		config.setWindowIcon("Icono.png");
		new Lwjgl3Application(new Code(), config);
	}
}
