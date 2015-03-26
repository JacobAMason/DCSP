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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 *
 * @author Alex Dodd (wad79)
 */
public class MessageWindow {
    private final Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    private Window window;
    private Label label;
    private final int WIDTH = Gdx.graphics.getWidth();
    private final int HEIGHT = Gdx.graphics.getHeight();

    public MessageWindow() {
        window = new Window("Generic Error", skin);
        window.setMovable(false);
        window.padTop(20);
        label = new Label("Something has gone wrong.\nPlease check the error log", skin, "small");
        window.add(label);
        window.setWidth(label.getWidth() + 20);
        window.row();
        TextButton successOK = new TextButton("Ok", skin);
        successOK.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                reset();
            }            
        });
        window.add(successOK);
        window.setVisible(false);
        window.setPosition(WIDTH/2, HEIGHT/2, Align.center);
        window.pack();
    }
    
    public void setTitle(String title){
        window.setTitle(title);
    }
    
    public void setText(String text){
        label.setText(text);
    }
    
    public void setVisible(boolean visible){
        window.setVisible(visible);
    }
    
    public void update(){
        window.setWidth(label.getWidth() + 20);
        window.setPosition(WIDTH/2, HEIGHT/2, Align.center);
        window.pack();
    }
    
    public Window getWindow(){
        return window;
    }
    
    public void reset(){
        setVisible(false);
        setTitle("Generic Error");
        setText("Something has gone wrong.\nPlease check the error log");
        update();
        
    }
    
}
