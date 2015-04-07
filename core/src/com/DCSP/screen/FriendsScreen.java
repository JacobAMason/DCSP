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
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author Alex Dodd (wad79)
 */
public class FriendsScreen extends ScreenInterface {
    private List friendList;
    private ScrollPane friendScroll;
    private Table friendTable;
    private Stage friendStage;
    private Skin skin;
    private TextField friendField;
    private TextButton friendBtn;
    private Array friends;

    public FriendsScreen() {
        friends = new Array(new String[]{"You","Have","No","Friends"});
    }
    
    public FriendsScreen(Array friends){
        this.friends = friends;
    }

    @Override
    public void show() {
        friendStage = new Stage();
        InputMultiplexer friendInput = new InputMultiplexer();
        friendInput.addProcessor(friendStage);
        friendInput.addProcessor(new InputAdapter(){

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
        Gdx.input.setInputProcessor(friendInput);
        
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        
        friendTable = new Table(skin);
        friendTable.setFillParent(true);
        friendTable.defaults().pad(15);
        
        friendTable.add("Your Friends List").colspan(3);
        friendTable.row();
        
        friendList = new List(skin,"name");
        friendList.setItems(friends);
        friendScroll = new ScrollPane(friendList);
        
        friendTable.add(friendScroll).expand().align(Align.left);
        
        friendField = new TextField("",skin,"user");
        
        friendTable.add(friendField).height(friendField.getHeight()+10)
                .width(friendField.getWidth()*2).align(Align.right);
        
        friendBtn = new TextButton("Add Friend",skin,"small");
        friendBtn.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        HttpConnection httpCon = new HttpConnection(gameParent);
                        httpCon.addFriend(gameParent.profile.getID(), friendField.getText());
                    }
                });
        
        friendTable.add(friendBtn).height(friendField.getHeight()).align(Align.right);
        
        friendStage.addActor(friendTable);
        
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        friendStage.act(delta);
        friendStage.draw();
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
