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
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author Alex Dodd (wad79)
 */
public class HighScoresScreen extends ScreenInterface {
    private List scoreList;
    private Table scoreTable;
    private ScrollPane scoreScroll;
    private Stage scoreStage;
    private Skin skin;

    public HighScoresScreen() {
    }

    @Override
    public void show() {
        scoreStage = new Stage();
        InputMultiplexer scoreInput = new InputMultiplexer();
        scoreInput.addProcessor(scoreStage);
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
        
        scoreTable = new Table(skin);
        scoreTable.pad(5);
        
        scoreList = new List(skin,"user");
        Array words = new Array(new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p",
            "q","r","s","t","u","v","w","x","y","z"});
        scoreList.setItems(words);
        scoreTable.add(scoreList).padRight(15);
        
        scoreList = new List(skin,"user");
        words = new Array(new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P",
            "Q","R","S","T","U","V","W","X","Y","Z"});
        scoreList.setItems(words);
        scoreTable.add(scoreList);
        
        scoreScroll = new ScrollPane(scoreTable);
        scoreScroll.setFillParent(true);
        scoreScroll.setX(scoreScroll.getX()+5);
        scoreStage.addActor(scoreScroll);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        scoreStage.act(delta);
        scoreStage.draw();
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
