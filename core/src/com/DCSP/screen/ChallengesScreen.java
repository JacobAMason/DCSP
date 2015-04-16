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

import com.DCSP.http.ChallengesResponse;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 *
 * @author Alex Dodd (wad79)
 */
public class ChallengesScreen extends ScreenInterface {
    private List challengeList;
    private Table challengeTable;
    private ScrollPane challengeScroll;
    private Stage challengesStage;
    private Skin skin;
    private TextButton challengeBtn;
    private Label challengeLbl;
    private final ChallengesResponse challenges;
    
    public ChallengesScreen(ChallengesResponse challenges) {
        this.challenges = challenges;
    }

    @Override
    public void show() {
        challengesStage = new Stage();
        InputMultiplexer scoreInput = new InputMultiplexer();
        scoreInput.addProcessor(challengesStage);
        scoreInput.addProcessor(new InputAdapter(){

            @Override
            public boolean keyUp(int keycode) {
                switch(keycode){
                    case Input.Keys.ESCAPE:
                    case Input.Keys.BACK:
                        gameParent.setScreen(new GameMenuScreen());
                        break;
                    default:
                        return false;                        
                }
                return true;
            }
            
            
        });
        Gdx.input.setInputProcessor(scoreInput);
        
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        
        challengeTable = new Table(skin);
        challengeTable.defaults().pad(10);
        
        
        int i = 0;       
        for (final ChallengesResponse.ChallengeResultsArray challenge : challenges.challengeResultsArray) {
            challengeBtn = new TextButton(challenge.getUsername(), skin, "user");
            challengeLbl = new Label(String.valueOf(challenge.getLevel()) + ": " +
                    String.valueOf(challenge.getTime()), skin, "user");
            challengeBtn.add(challengeLbl);
            
            challengeBtn.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        gameParent.setScreen(new MazeScreen(challenge.getLevel(),
                                challenge.getSeed(), challenge.getTime()));
                    }
                });
            
            challengeTable.add(challengeBtn).width(Gdx.graphics.getWidth()/3 - 20)
                    .height(Gdx.graphics.getHeight()/5);
            
            i++;
            if (i%3 == 0) challengeTable.row();
        }
        
        challengeScroll = new ScrollPane(challengeTable);
        challengeScroll.setFillParent(true);
        challengeScroll.setX(challengeScroll.getX()+5);
        challengesStage.addActor(challengeScroll);
        
        // Always add the generic message window
        challengesStage.addActor(gameParent.getMessageWindow().getWindow());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        challengesStage.act(delta);
        challengesStage.draw();
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