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
package com.DCSP.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author Alex
 */
public class Player {
    private Vector2 initPos,pos;
    private int cellFactor;
    private Body player;
    private World world;
    private BodyDef playerBody;
    private FixtureDef playerFix;

    private Vector2 speed = new Vector2(0,0);
    
    public Player(World world, int cellFactor){
        this.world = world;
        this.cellFactor = cellFactor;
        initPos = new Vector2(0+cellFactor/2, 0 + cellFactor/2);
        draw();
    }
    
    public Player(World world, int cellFactor, int initPosX, int initPosY){
        this.world = world;
        this.cellFactor = cellFactor;
        initPos = new Vector2(initPosX + cellFactor/2, initPosY + cellFactor/2);
        draw();
    }
    
    private void draw(){
        playerBody = new BodyDef();
        playerBody.position.set(initPos);
        playerBody.type = BodyDef.BodyType.DynamicBody;
        
        CircleShape playerShape = new CircleShape();
        playerShape.setRadius(cellFactor/6f);
        
        playerFix = new FixtureDef();
        playerFix.shape = playerShape;
        playerFix.restitution = .2f;
        
        player = world.createBody(playerBody);
        player.createFixture(playerFix);
    }
    public void setY(int step){
        speed.y = step;
    }
    
    public void setX(int step){
        speed.x = step;
    }
    
    public void update(){
        player.setLinearVelocity(speed);
    }

    public boolean checkWin(int x, int y){
        pos = player.getLocalPoint(initPos);      
        return ((int)(Math.abs(pos.x)/cellFactor + .5f)== x-1) && 
                ((int)(Math.abs(pos.y)/cellFactor + .5f) == y-1);
    }
}
