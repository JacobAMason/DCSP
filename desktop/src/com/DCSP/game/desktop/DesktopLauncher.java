package com.DCSP.game.desktop;

import com.DCSP.game.GameRoot;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.DCSP.game.desktop.screen.DesktopSettingsScreen;
import com.badlogic.gdx.Files;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "An AMAZEing Game";
        config.width = 1024;
        config.height = 576;
        config.resizable = false;
        config.fullscreen = false;
        config.addIcon("img/pinkyPixel.png", Files.FileType.Internal);

        new LwjglApplication(new GameRoot(new DesktopSettingsScreen()), config);
    }
}
