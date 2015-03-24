/*
 * The MIT License
 *
 * Copyright 2015 Jacob Mason (jm2232).
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
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 *
 * @author Jacob Mason (jm2232)
 */
class ChallengeSendScreen extends ScreenInterface {
    private int seed;
    private Stage challengeStage;
    private Table challengeTable;
    private Skin skin;
    private TextField toName;

    public ChallengeSendScreen(int seed) {
        this.seed = seed;
    }

    @Override
    public void show() {
        challengeStage = new Stage();
        Gdx.input.setInputProcessor(challengeStage);
        Gdx.input.setCatchBackKey(true);
        
        challengeTable = new Table(skin);
        challengeTable.setFillParent(true);
        
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        
        toName = new TextField("",skin);
        TextButton send = new TextButton("Send",skin);
        send.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }            
        });
        
        challengeTable.add(new Label("Send Challenge",skin)).colspan(2).row();
        challengeTable.add(new Label("Friend's User Name",skin));
        challengeTable.add(toName).width(100).row();
        challengeTable.add(send).colspan(2).width(75);
        
        
        challengeStage.addActor(challengeTable);
        
        
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        challengeStage.act(delta);
        challengeStage.draw();
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
