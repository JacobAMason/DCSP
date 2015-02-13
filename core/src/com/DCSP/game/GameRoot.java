package com.DCSP.game;                                      

import com.DCSP.screen.GameplayScreen;
import com.DCSP.screen.MainMenuScreen;
import com.DCSP.screen.SplashScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;

public class GameRoot extends Game {
    private boolean isFullscreen;
    public MainMenuScreen mainMenuScreen;
    public Screen settingsScreen;

    public GameRoot(Screen settingsScreen) {
        this.settingsScreen = settingsScreen;
        mainMenuScreen = new MainMenuScreen();
    }
    
    @Override
    public void create() {
        isFullscreen = false;
        setScreen(new SplashScreen());
    }

    
    public void toggleFullscreen(){
        isFullscreen = !isFullscreen;
            if(isFullscreen)
            {
                Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode().width, 
                        Gdx.graphics.getDesktopDisplayMode().height, true);
            } else {
                Gdx.graphics.setDisplayMode(1024, 576, false);
            }
    }
}
