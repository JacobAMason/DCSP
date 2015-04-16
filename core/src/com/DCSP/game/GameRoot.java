package com.DCSP.game;                                      

import com.DCSP.screen.MainMenuScreen;
import com.DCSP.screen.ScreenInterface;
import com.DCSP.screen.SplashScreen;
import com.DCSP.windows.MessageWindow;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;

public class GameRoot extends Game {
    private boolean isFullscreen;
    public MainMenuScreen mainMenuScreen;
    public Screen settingsScreen;
    static private MessageWindow messageWindow;
    public UserProfile profile;
    private boolean userIsLoggedIn;
    
    
    public GameRoot(Screen settingsScreen) {
        this.settingsScreen = settingsScreen;
    }
    
    @Override
    public void create() {
        isFullscreen = false;
        userIsLoggedIn = false;
        mainMenuScreen = new MainMenuScreen();
        messageWindow = new MessageWindow();
        
        Music musicPlayer = Gdx.audio.newMusic(Gdx.files.internal("audio/BGmusic.mp3"));
        musicPlayer.setLooping(true);
        musicPlayer.play();
        
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
    
    public MessageWindow getMessageWindow(){
        return messageWindow;
    }
        
    public boolean isLoggedIn() {
        return userIsLoggedIn;
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
