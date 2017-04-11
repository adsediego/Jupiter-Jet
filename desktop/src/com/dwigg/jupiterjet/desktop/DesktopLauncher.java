package com.dwigg.jupiterjet.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dwigg.jupiterjet.MainGame;
import com.dwigg.jupiterjet.utils.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = Constants.TITLE + " " + Constants.VERSION;
		config.width = Constants.WIDTH;
		config.height = Constants.HEIGHT;

		new LwjglApplication(new MainGame(), config);
	}
}
