package com.DCSP.screen;

import com.DCSP.game.GameRoot;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenuScreen implements Screen{
    private final GameRoot game;
    
    
    private int i = 0;    
    private SpriteBatch batch;
    private Sprite warlock;
    private Stage menuStage;
    private final Skin ourSkin;
    private final TextButton settingsBtn, playBtn, quitBtn;
    private final int WIDTH = Gdx.graphics.getWidth(), HEIGHT = Gdx.graphics.getHeight();
    private final int btnWidth = WIDTH/5, btnHeight = HEIGHT/11;

    
    public MainMenuScreen(final GameRoot game) {
        this.game = game;
        menuStage = new Stage();
        Gdx.input.setInputProcessor(menuStage);
        ourSkin = new Skin(Gdx.files.internal("uiskin.json"));
        playBtn = new TextButton("Play Game",ourSkin);
        settingsBtn = new TextButton("Settings",ourSkin);
        quitBtn = new TextButton("Quit Game",ourSkin);
        
        playBtn.setPosition(WIDTH/2-btnWidth/2,HEIGHT/2);
        playBtn.setSize(btnWidth,btnHeight);
        
       
        settingsBtn.setPosition(WIDTH/2,HEIGHT/2-btnHeight-5);
        settingsBtn.setSize(btnWidth,btnHeight);
        settingsBtn.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                i++;
            }
        });
        
        
        quitBtn.setPosition(WIDTH/2, HEIGHT/2-(2*btnHeight)-2*5);
        quitBtn.setSize(btnWidth, btnHeight);
        
        menuStage.addActor(playBtn);
        menuStage.addActor(settingsBtn);
        menuStage.addActor(quitBtn);
        
        
    }

    
    @Override
    public void show() {
        batch = new SpriteBatch();

        Texture splashTexture = new Texture("img/warlock.jpg");
        warlock = new Sprite(splashTexture);
        warlock.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        warlock.draw(batch);
        batch.end();        
        
        menuStage.act(delta);
        menuStage.draw();
        System.out.println(i);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        warlock.getTexture().dispose();
    }
}
