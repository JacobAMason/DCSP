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
    private GameRoot game;
    private int WIDTH, HEIGHT;
    
    private SpriteBatch batch;
    private Sprite warlock;
    private Stage menuStage;
    private Skin ourSkin;
    private TextButton settingsBtn, playBtn, quitBtn;
    private int btnWidth, btnHeight;
    
    @Override
    public void show() {        
        game = (GameRoot) Gdx.app.getApplicationListener();
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        
        btnWidth = WIDTH/5;
        btnHeight = HEIGHT/11;
        menuStage = new Stage();
        Gdx.input.setInputProcessor(menuStage);
        
        Texture splashTexture = new Texture("img/warlock.jpg");
        warlock = new Sprite(splashTexture);
        warlock.setSize(WIDTH, HEIGHT);
        
        ourSkin = new Skin(Gdx.files.internal("uiskin.json"));
        playBtn = new TextButton("Play Game",ourSkin);
        settingsBtn = new TextButton("Settings",ourSkin);
        quitBtn = new TextButton("Quit Game",ourSkin);
        
        playBtn.setPosition(WIDTH/2-btnWidth/2,HEIGHT/2);
        playBtn.setSize(btnWidth,btnHeight);
       
        settingsBtn.setPosition(WIDTH/2-btnWidth/2,HEIGHT/2-btnHeight-5);
        settingsBtn.setSize(btnWidth,btnHeight);
        settingsBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.settingsScreen);
            }
        });
        
        quitBtn.setPosition(WIDTH/2-btnWidth/2, HEIGHT/2-(2*btnHeight)-2*5);
        quitBtn.setSize(btnWidth, btnHeight);
        quitBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }            
        });
        
        menuStage.addActor(playBtn);
        menuStage.addActor(settingsBtn);
        menuStage.addActor(quitBtn);
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        warlock.draw(batch);
        batch.end();        
        
        menuStage.act(delta);
        menuStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        menuStage.getViewport().update(width,height);       
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
