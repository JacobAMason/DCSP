package com.DCSP.screen;

import com.DCSP.game.GameRoot;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenuScreen implements Screen{
    private final Game game;
        
    private SpriteBatch batch;
    private Sprite warlock;

    
    public MainMenuScreen(Game game) {
        this.game = game;
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
