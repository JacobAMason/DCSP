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

import com.DCSP.http.HttpConnection;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
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
public class InstructionScreen extends ScreenInterface {
    private String instructions;
    private Stage instStage;
    
    InstructionScreen(){
        instructions =
                "Welcome to An AMAZEing Game. The goal of\n"
                + "this game is to make our way to the bottom\n"
                + "right corner of the maze. When playing on\n"
                + "Android, the character will move towards\n"
                + "the point on the screen that you touch.\n"
                + "When playing on Desktop, the arrow keys\n"
                + "and WASD will move the character in their\n"
                + "respective direction. One can also use the\n"
                + "mouse and the character will move towards\n"
                + "where you are clicking on the screen.";
    }

    @Override
    public void show() {
        instStage = new Stage();
        InputMultiplexer instInput = new InputMultiplexer();
        instInput.addProcessor(new InputAdapter(){

            @Override
            public boolean keyUp(int keycode) {
                switch(keycode){
                    case Input.Keys.BACK:
                        gameParent.setScreen(new MainMenuScreen());
                        break;
                    default:
                        return false;                        
                }
                return true;
            }
        });
        
        instInput.addProcessor(instStage);
        Gdx.input.setInputProcessor(instStage);
        
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        
        Table instTable = new Table(skin);
        instTable.setFillParent(true);
        instTable.defaults().pad(10).expand();
        
        instTable.add(instructions,"small").row();
        
        TextButton btn = new TextButton("Continue",skin);
        btn.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
            gameParent.setScreen(new GameMenuScreen());
            }
        });
        
        instTable.add(btn);
        
        instStage.addActor(instTable);
        

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        instStage.act(delta);
        instStage.draw();
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
