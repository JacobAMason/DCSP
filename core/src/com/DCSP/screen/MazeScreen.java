/*
 * The MIT License
 *
 * Copyright 2015 Alex.
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

import com.DCSP.entities.Player;
import com.DCSP.mazeGen.Maze;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author Alex
 */
public class MazeScreen extends ScreenInterface {
    private World world;
    private Maze maze;
    private Box2DDebugRenderer debugging;
    private OrthographicCamera camera;
    private final int mWidth, mHeight;
    private int cellFactor;
    private Player player;
    
    private int step = 100;
    
    
    
    public MazeScreen(int level){
        mWidth = level + 15;
        mHeight = (int) Math.floor(9*mWidth/16);
    }
    
    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter(){

            @Override
            public boolean keyDown(int keycode) {
                switch (keycode){
                    case Keys.W:
                    case Keys.UP:
                        player.setY(-step);
                        break;
                    case Keys.D:
                    case Keys.RIGHT:
                        player.setX(step);
                        break;
                    case Keys.S:
                    case Keys.DOWN:
                        player.setY(step);
                        break;
                    case Keys.A:
                    case Keys.LEFT:
                        player.setX(-step);
                }
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                switch (keycode){
                    case Keys.W:
                    case Keys.S:
                    case Keys.UP:
                    case Keys.DOWN:
                        player.setY(0);
                        break;
                    case Keys.D:
                    case Keys.A:
                    case Keys.RIGHT:
                    case Keys.LEFT:
                        player.setX(0);
                        break;
                }
                return true;
            }
            
        });
        
        cellFactor = (int)((float)Gdx.graphics.getHeight()/((float)mHeight+0.5f));
        world = new World(new Vector2(0,0),true);
        debugging = new Box2DDebugRenderer();
        
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.setToOrtho(true);
        camera.translate((mWidth*cellFactor - Gdx.graphics.getWidth())/2, (mHeight*cellFactor - Gdx.graphics.getHeight())/2);
        camera.update();
        
        maze = new Maze(world, mWidth, mHeight);
        
        player = new Player(world, cellFactor);
        
        
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        debugging.render(world, camera.combined);
        
        world.step(1/60f, 6, 2);
        player.update();
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
        world.dispose();
        debugging.dispose();
    }
    
}
