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
    
//    public static void main(String[] args) {
//        Maze m = new Maze();
//        m.generateMaze(3, 3);
//        System.out.println(m.cellGrid.toString());
//    }
    
    void generateMaze(int mazeWidth, int mazeHeight) {
        Random rand = new Random();        
        Stack<Cell> cellStack = new Stack<Cell>();
        int totalCells = mazeWidth * mazeHeight;
        cellGrid = new Grid(mazeWidth, mazeHeight);
        
        // Get a random starting location.
        Cell currentCell = cellGrid.getCell(rand.nextInt(mazeWidth), rand.nextInt(mazeHeight));
        int visitedCells = 1;
        
        while (visitedCells < totalCells) {
            ArrayList<Cell> neighbors = cellGrid.getNeighbors(currentCell);
            
            Collections.shuffle(neighbors);

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
}
