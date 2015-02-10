package com.DCSP.game.desktop;

import com.DCSP.game.GameRoot;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.DCSP.game.DCSPGame;
import com.DCSP.game.desktop.screen.DesktopSettingsScreen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "DCSP Project Title";
        config.width = 1024;
        config.height = 576;
        config.resizable = false;
        config.fullscreen = false;
        new LwjglApplication(new GameRoot(new DesktopSettingsScreen()), config);
	}
}
