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
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import java.util.regex.Pattern;

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
    
    Pattern usernameRegex = Pattern.compile("^[0-9A-z_]{3,20}$");
    Pattern nameRegex = Pattern.compile("^[A-z '.]{1,20}$");
    Pattern passwordRegex = Pattern.compile("^(?!.*\\^)(?=.*[0-9]+)(?=.*[a-z]+)[A-z0-9!@#$&*]{6,30}$");
    Pattern emailRegex = Pattern.compile("^[A-z0-9._%+-]+@[A-z0-9.-]+\\.[A-z]{2,4}$");
    
    private Window connectionFailWindow;
    private Window registerErrorWindow;
    private Label registerErrorLbl;

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
        
        
        usernameLbl = new Label("Username", skin, "small");
        usernameTxt = new TextField("", skin,"user");
        
        pass1Lbl = new Label("Password", skin, "small");
        pass1Txt = new TextField("", skin, "user");
        pass1Txt.setPasswordMode(true);
        pass1Txt.setPasswordCharacter('*');
        
        pass2Lbl = new Label("Re-enter\nPassword", skin, "small");
        pass2Txt = new TextField("", skin, "user");
        pass2Txt.setPasswordMode(true);
        pass2Txt.setPasswordCharacter('*');
        
        nameLbl = new Label("Name", skin, "small");
        nameTxt = new TextField("", skin, "user");
        
        email1Lbl = new Label("Email", skin, "small");
        email1Txt = new TextField("", skin, "user-small");
        
        email2Lbl = new Label("Re-enter\nEmail", skin, "small");
        email2Txt = new TextField("", skin,"user-small");
        
        
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
                
                // Validate fields
                
                if (!pass1Txt.getText().equals(pass2Txt.getText())) {
                    registerErrorLbl.setText("Passwords don't match.");
                } else if (!email1Txt.getText().equals(email2Txt.getText())) {
                    registerErrorLbl.setText("Emails don't match.");
                } else if (!nameRegex.matcher(nameTxt.getText()).matches()) {
                    registerErrorLbl.setText("Names must be between 1 and 20 characters long\n"
                                        + "and can use either letters, periods, or apostrophes.");
                } else if (!usernameRegex.matcher(usernameTxt.getText()).matches()) {
                    registerErrorLbl.setText("Usernames must be between 3 and 20 characters long\n"
                                        + "and can use either letters, numbers, and underscores.");
                } else if (!passwordRegex.matcher(pass1Txt.getText()).matches()) {
                    registerErrorLbl.setText("Password must be between 6 and 30 characters long\n"
                                        + "and must contain at least one letter and one number.");
                } else if (!emailRegex.matcher(email1Txt.getText()).matches()) {
                    registerErrorLbl.setText("Please enter a valid email address.");
                } else {
                    // Fields are good. Regsiter the user.
                    HttpConnection httpCon = new HttpConnection(gameParent);
                    httpCon.register(usernameTxt.getText(), pass1Txt.getText(),
                            nameTxt.getText(), email1Txt.getText(),
                            connectionFailWindow);
                    return;
                }
                
                registerErrorWindow.setWidth(registerErrorLbl.getWidth());
                registerErrorWindow.pad(20);
                registerErrorWindow.pack();
                registerErrorWindow.setPosition(WIDTH/2, HEIGHT/2, Align.center);
                registerErrorWindow.setVisible(true);
            }
        });
        
        
        menuTable.defaults().pad(10).minHeight(HEIGHT/9);
        menuTable.add(usernameLbl);
        menuTable.add(usernameTxt).minWidth(WIDTH/3 - 50);
        menuTable.add(nameLbl);
        menuTable.add(nameTxt).minWidth(WIDTH/3 - 50);
        menuTable.row();
        menuTable.add(pass1Lbl);
        menuTable.add(pass1Txt).minWidth(WIDTH/3 - 50);
        menuTable.add(email1Lbl);
        menuTable.add(email1Txt).minWidth(WIDTH/3 - 50);
        menuTable.row();
        menuTable.add(pass2Lbl);
        menuTable.add(pass2Txt).minWidth(WIDTH/3 - 50);
        menuTable.add(email2Lbl);
        menuTable.add(email2Txt).minWidth(WIDTH/3 - 50);
        menuTable.row();
        menuTable.add(cancel).colspan(2).fill();
        menuTable.add(register).colspan(2).fill();
        menuStage.addActor(menuTable);
        
        
        //  Connection Fail Window
        /* 
         * make this method call to put the connection failed window appear
         * connectionFailWindow.setVisible(true);
         */
        connectionFailWindow = new Window("Connection Error",skin);
        connectionFailWindow.setMovable(false);
        connectionFailWindow.padTop(20);
        Label connectionFailWindowLbl = new Label("Couldn't connect to the interwebz.\nPlease try again.", skin, "small");
        connectionFailWindow.add(connectionFailWindowLbl);
        connectionFailWindow.setWidth(connectionFailWindowLbl.getWidth() + 50);
        connectionFailWindow.row().row();
        TextButton confailOK = new TextButton("Ok", skin);
        confailOK.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                connectionFailWindow.setVisible(false);
            }            
        });
        connectionFailWindow.add(confailOK);
        connectionFailWindow.setVisible(false);
        connectionFailWindow.setPosition(WIDTH/2, HEIGHT/2, Align.center);
        menuStage.addActor(connectionFailWindow);
        //end conFail window
        
        
        //  Register Error Window
        /* 
         * make this method call to make the register Error window appear
         * registerErrorWindow.setVisible(true);
         */
        registerErrorWindow = new Window("Registration", skin);
        registerErrorWindow.setMovable(false);
        registerErrorWindow.padTop(20);
        registerErrorLbl = new Label("", skin, "small");
        registerErrorWindow.add(registerErrorLbl);
        registerErrorWindow.row().row();
        TextButton regexErrorOK = new TextButton("Ok", skin);
        regexErrorOK.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                registerErrorWindow.setVisible(false);
            }            
        });
        registerErrorWindow.add(regexErrorOK);
        registerErrorWindow.setVisible(false);
        menuStage.addActor(registerErrorWindow);
        //end regexError window
        
        
        
        
        // Always add the generic message window
        menuStage.addActor(gameParent.getMessageWindow().getWindow());
                
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
