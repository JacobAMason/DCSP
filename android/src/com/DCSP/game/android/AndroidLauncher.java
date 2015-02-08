package com.DCSP.game.android;

import android.os.Bundle;

import com.DCSP.game.GameRoot;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.DCSP.game.DCSPGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        // Disable unused sensors to conserve battery
        config.useAccelerometer = false;
        config.useCompass = false;

		initialize(new GameRoot(), config);
	}
}
