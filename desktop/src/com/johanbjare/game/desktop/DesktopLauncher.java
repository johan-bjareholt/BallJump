package com.johanbjare.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.johanbjare.game.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
		config.title = "name";
		config.width = 1280;
		config.height = 720;
		config.foregroundFPS = 60;
		config.vSyncEnabled = true;
		new LwjglApplication(new Game(), config);
	}
}
