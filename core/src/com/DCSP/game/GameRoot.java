package com.DCSP.game;                                      

import com.DCSP.screen.MainMenuScreen;
import com.DCSP.screen.ScreenInterface;
import com.DCSP.screen.SplashScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class GameRoot extends Game {
    private boolean isFullscreen;
    public MainMenuScreen mainMenuScreen;
    public Screen settingsScreen;

    public GameRoot(Screen settingsScreen) {
        this.settingsScreen = settingsScreen;
    }
    
    @Override
    public void create() {
        isFullscreen = false;
        mainMenuScreen = new MainMenuScreen();
        setScreen(new SplashScreen());
    }

    // This function doesn't Override since the function signature is different.
    public void setScreen(ScreenInterface screen) {
        if(screen != null)
        {
            screen.setGameParent(this);
        }
        super.setScreen((Screen)screen);
    }
    

    // TODO: Move this elsewhere
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
