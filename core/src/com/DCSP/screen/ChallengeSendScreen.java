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

import com.DCSP.http.HttpConnection;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author Jacob Mason (jm2232)
 */
public class ChallengeSendScreen extends ScreenInterface {
    private final long seed;
    private final int level;
    private final double score;
    private Stage challengeStage;
    private Table challengeTable;
    private Skin skin;
    private List challengeList;
    private ScrollPane challengeScroll;
    private Array friends;
    private TextButton toName;

    public ChallengeSendScreen(long seed, int level, double score) {
        this.seed = seed;
        this.level = level;
        this.score = score;
    }

    @Override
    public void show() {
        if (gameParent.profile != null) {
            this.friends = gameParent.profile.getFriendsArray();
        } else {
            this.friends = new Array(new String[]{"You", "Have", "No", "Friends"});
        }
                
        challengeStage = new Stage();
        
        Gdx.input.setInputProcessor(challengeStage);
        Gdx.input.setCatchBackKey(true);
        
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        
        challengeTable = new Table(skin);
        challengeTable.setFillParent(true);
        challengeTable.defaults().pad(15);
        
        challengeTable.add("Select A Friend").colspan(2);
        challengeTable.row();
        
        challengeList = new List(skin);
        challengeList.setItems(friends);
        challengeScroll = new ScrollPane(challengeList);
        
        challengeTable.add(challengeScroll).expand().align(Align.left);
        
        
        
        toName = new TextButton("Send Challenge", skin, "small");
        toName.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        // Insert useful php hook here that uses challengeList.getSelected()
                        HttpConnection httpCon = new HttpConnection(gameParent);
                        httpCon.sendChallenge(score, level, seed, String.valueOf(challengeList.getSelected()));  // score, level, seed, toID
                        System.out.println(score);
                        System.out.println(level);
                        System.out.println(seed);
                        System.out.println(String.valueOf(challengeList.getSelected()));
                    }
                });
        
        challengeTable.add(toName).width(toName.getWidth()*2)
                .height(toName.getHeight() + 20).align(Align.right);
        
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
