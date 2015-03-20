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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
public class RegistrationScreen extends ScreenInterface {
    private int WIDTH, HEIGHT;
    
    private SpriteBatch batch;
    private Sprite background;
    private Stage menuStage;
    private Table menuTable;
    private Skin skin;
    private Label usernameLbl, pass1Lbl, pass2Lbl, nameLbl, email1Lbl, email2Lbl;
    private TextField usernameTxt, pass1Txt, pass2Txt, nameTxt, email1Txt, email2Txt;
    private TextButton cancel, register;

    @Override
    public void show() {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        
        menuStage = new Stage();
        Gdx.input.setInputProcessor(menuStage);
        
        menuTable = new Table();
        menuTable.setFillParent(true);
        
        Texture splashTexture = new Texture("img/menu.png");
        background = new Sprite(splashTexture);
        background.setSize(WIDTH, HEIGHT);
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        
        
        usernameLbl = new Label("Username", skin);
        usernameTxt = new TextField("", skin);
        
        pass1Lbl = new Label("Password", skin);
        pass1Txt = new TextField("", skin);
        pass1Txt.setPasswordMode(true);
        
        pass2Lbl = new Label("Re-enter Password", skin);
        pass2Txt = new TextField("", skin);
        pass2Txt.setPasswordMode(true);
        
        nameLbl = new Label("Name", skin);
        nameTxt = new TextField("", skin);
        
        email1Lbl = new Label("Email", skin);
        email1Txt = new TextField("", skin);
        
        email2Lbl = new Label("Re-enter Email", skin);
        email2Txt = new TextField("", skin);
        
        
        cancel = new TextButton("Cancel", skin);
        register = new TextButton("Register", skin);
        
        cancel.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameParent.setScreen(gameParent.mainMenuScreen);
            }            
        });
        
        register.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!pass1Txt.getText().equals(pass2Txt.getText())) {
                    Gdx.app.log("RegistrationScreen", "Passwords don't match.");
                    return;
                }
                
                if (!email1Txt.getText().equals(email2Txt.getText())) {
                    Gdx.app.log("RegistrationScreen", "Emails don't match.");
                    return;
                }
                
                HttpConnection httpCon = new HttpConnection();
                httpCon.register(usernameTxt.getText(), pass1Txt.getText(),
                        nameTxt.getText(), email1Txt.getText());
            }
        });
        
        
        menuTable.defaults().padBottom(10).padRight(5).expand().fillX();
        menuTable.add(usernameLbl);
        menuTable.add(usernameTxt);
        menuTable.add(nameLbl);
        menuTable.add(nameTxt);
        menuTable.row();
        menuTable.add(pass1Lbl);
        menuTable.add(pass1Txt);
        menuTable.add(email1Lbl);
        menuTable.add(email1Txt);
        menuTable.row();
        menuTable.add(pass2Lbl);
        menuTable.add(pass2Txt);
        menuTable.add(email2Lbl);
        menuTable.add(email2Txt);
        menuTable.row();
        menuTable.add(cancel).colspan(2).fill();
        menuTable.add(register).colspan(2).fill();
        menuStage.addActor(menuTable);
        
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        background.draw(batch);
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
        background.getTexture().dispose();
    }
    
}
