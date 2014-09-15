package com.brndn.platformgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.brndn.platformgame.PlatformGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//new LwjglApplication(new PlatformGame(), config);
        new LwjglApplication(new PlatformGame(),"Platformer",480,320);
	}
}
