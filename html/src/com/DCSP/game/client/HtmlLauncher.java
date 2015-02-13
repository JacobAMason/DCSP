package com.DCSP.game.client;

import com.DCSP.game.GameRoot;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.DCSP.game.client.screen.HTMLSettingsScreen;

public class HtmlLauncher extends GwtApplication {
    GameRoot game = new GameRoot(new HTMLSettingsScreen());

    @Override
    public GwtApplicationConfiguration getConfig () {
        return new GwtApplicationConfiguration(768, 432);
    }

    @Override
    public ApplicationListener getApplicationListener ()
    {
        return game;
    }
}