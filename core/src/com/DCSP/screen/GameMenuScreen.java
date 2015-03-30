/*
 * The MIT License
 *
 * Copyright 2015 Alex Dodd (wad79).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.DCSP.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 *
 * @author Alex Dodd (wad79)
 */
public class GameMenuScreen extends ScreenInterface{
    private Stage gameStage;
    private Table gameTable;
    private Skin skin;
    private TextButton lvlBtn, frndBtn, challBtn, scorebtn, gameBtn;

    @Override
    public void show() {
        gameStage = new Stage();
        Gdx.input.setInputProcessor(gameStage);
        
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        
        gameTable = new Table(skin);
        gameTable.setFillParent(true);
        gameTable.defaults().pad(10).fill();
        gameTable.add("Welcome To The Game").colspan(2).row();
        
        gameBtn = new TextButton("Levels",skin);
        gameBtn.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameParent.setScreen(new LevelSelectScreen());
            }
        });
        
        gameTable.add(gameBtn).expand();
        
        gameBtn = new TextButton("Friends",skin);
        gameBtn.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameParent.setScreen(new FriendsScreen());
            }
        });
        
        gameTable.add(gameBtn).expand();
        gameTable.row();
        
        gameBtn = new TextButton("Challenges",skin);
        gameBtn.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameParent.setScreen(new ChallengesScreen());
            }
        });
        
        gameTable.add(gameBtn).colspan(2).expand();
        gameTable.row();
        
        gameBtn = new TextButton("High Scores",skin);
        gameBtn.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameParent.setScreen(new HighScoresScreen());
            }
        });
        
        gameTable.add(gameBtn).colspan(2).expand();
        gameStage.addActor(gameTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        gameStage.act(delta);
        gameStage.draw();
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
    }
    
}