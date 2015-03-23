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
package com.DCSP.mazeGen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

/**
 * Maze generation from http://www.mazeworks.com/mazegen/mazetut/index.htm
 * @author Jacob Mason (jm2232)
 */
public class Maze {
    private Grid cellGrid;
    private World world;
    private float cellFactor;
    private int width, height;
    
    public Maze(World world, int w, int h, long randomSeed,float cellFactor){
        this.world = world;
        this.cellFactor = cellFactor;
        this.width = w; this.height = h;
        generateMaze(width, height, randomSeed);
        System.out.println(cellGrid.toString());
        drawMaze();
    }
    
//    public static void main(String[] args) {
//        Maze m = new Maze();
//        m.generateMaze(3, 3); 
//        System.out.println(m.cellGrid.toString());
//    }
    
    private void generateMaze(int mazeWidth, int mazeHeight, long randomSeed) {
        Random rand = new Random(randomSeed);
        Stack<Cell> cellStack = new Stack<Cell>();
        int totalCells = mazeWidth * mazeHeight;
        cellGrid = new Grid(mazeWidth, mazeHeight);
        
        // Get a random starting location.
        int randX = rand.nextInt(mazeWidth);
        int randY = rand.nextInt(mazeHeight);
        
        System.out.println("RandX: " + randX + " RandY: " + randY);
        
        Cell currentCell = cellGrid.getCell(randX, randY);
        int visitedCells = 1;
        
        while (visitedCells < totalCells) {
            ArrayList<Cell> neighbors = cellGrid.getNeighbors(currentCell);
            
            Collections.shuffle(neighbors, rand);

            Cell chosenNeighbor = null;
            
            for (Cell neighbor : neighbors) {
                if (neighbor != null && neighbor.allWallsIntact()) {
                    chosenNeighbor = neighbor;
                }
            }
            
            if (chosenNeighbor != null) {
                cellGrid.connect(currentCell, chosenNeighbor);
                cellStack.push(currentCell);
                currentCell = chosenNeighbor;
                visitedCells++;
            } else {
                currentCell = cellStack.pop();
            }
        }
        
    }
    private void drawMaze() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                drawWall(cellGrid.getCell(x, y));   
            }
        }
    }
    
    private void drawWall(Cell cell){
        float X = (cell.X * cellFactor)/2;
        float Y = (cell.Y * cellFactor)/2;
        BodyDef wallBody = new BodyDef();
        wallBody.position.set(X,Y);
        wallBody.type = BodyType.StaticBody;
        
        ChainShape wallShape;
        
        FixtureDef wallFix = new FixtureDef();
        wallFix.restitution = 0;
        
        Body wall = world.createBody(wallBody);
        
        if (cell.wallN){
            wallShape = new ChainShape();
            wallShape.createChain(new Vector2[]{new Vector2(X, Y), new Vector2(X+cellFactor, Y)});
            wallFix.shape = wallShape;
            wall.createFixture(wallFix);
            wallShape.dispose();
        }
        
        if (cell.wallE){
            wallShape = new ChainShape();
            wallShape.createChain(new Vector2[]{new Vector2(X+cellFactor, Y), new Vector2(X+cellFactor, Y+cellFactor)});
            wallFix.shape = wallShape;
            wall.createFixture(wallFix);
            wallShape.dispose();
        }
        
        if (cell.wallS){
            wallShape = new ChainShape();
            wallShape.createChain(new Vector2[]{new Vector2(X, Y+cellFactor), new Vector2(X+cellFactor, Y+cellFactor)});
            wallFix.shape = wallShape;
            wall.createFixture(wallFix);
            wallShape.dispose();
        }
        
        if (cell.wallW){
            wallShape = new ChainShape();
            wallShape.createChain(new Vector2[]{new Vector2(X, Y), new Vector2(X, Y+cellFactor)});
            wallFix.shape = wallShape;
            wall.createFixture(wallFix);
            wallShape.dispose();
        }
        
        
        
    }
    
    
}
