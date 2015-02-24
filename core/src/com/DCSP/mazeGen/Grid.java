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

/**
 *
 * @author Jacob Mason (jm2232)
 */
public class Grid {
    private final Cell[] cellArray;
    private final int width, height, totalCells;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.totalCells = width * height;
        this.cellArray = new Cell[totalCells];
        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // The following expression creates a cell with borders that cannot be "knocked down"
                cellArray[x + y*width] = new Cell(x, y, y==0, x==width-1, y==height-1, x==0);
            }
        }
    }
    
    public Cell getCell(int x, int y) {
        return cellArray[x + y*width];
    }
    
    public ArrayList<Cell> getNeighbors(Cell currentCell) {
        final Cell neighborN, neighborE, neighborS, neighborW;
        
        if (!currentCell.borderN) {
            neighborN = cellArray[currentCell.X + (currentCell.Y-1)*width];
        } else {
            neighborN = null;
        }

        if (!currentCell.borderE) {
            neighborE = cellArray[currentCell.X+1 + (currentCell.Y)*width];
        } else {
            neighborE = null;
        }

        if (!currentCell.borderS) {
            neighborS = cellArray[currentCell.X + (currentCell.Y+1)*width];
        } else {
            neighborS = null;
        }

        if (!currentCell.borderW) {
            neighborW = cellArray[currentCell.X-1 + (currentCell.Y)*width];
        } else {
            neighborW = null;
        }
        
        // http://stackoverflow.com/questions/1005073/initialization-of-an-arraylist-in-one-line
        return new ArrayList<Cell>() {{add(neighborN); add(neighborE); add(neighborS); add(neighborW);}};
    }
    
    
    public void connect(Cell c1, Cell c2) {
        int diff = (c1.X - c2.X) + 2*(c1.Y - c2.Y);
        
        /*
        -2: c2 is South of c1
        -1: c2 is East of c1
         1: c2 is West of c1
         2: c2 is North of c1
        */
        
        switch(diff) {
            case -2:
                c1.wallS = false;
                c2.wallN = false;
                break;
                
            case -1:
                c1.wallE = false;
                c2.wallW = false;
                break;
                
            case 1:
                c1.wallW = false;
                c2.wallE = false;
                break;
                
            case 2:
                c1.wallN = false;
                c2.wallS = false;
                break;
            
            default:
                assert false; // Done screwed up.
        }
    }

    @Override
    public String toString() {
        String returnStr = "";
        
        for (int i = 0; i < totalCells; i++) {
            if (i % width == 0)
                returnStr += "\n";
            
            Integer cellValue = 0;
            
            if(cellArray[i].wallN)
                cellValue += 1;
            if(cellArray[i].wallE)
                cellValue += 2;
            if(cellArray[i].wallS)
                cellValue += 4;
            if(cellArray[i].wallW)
                cellValue += 8;
            
            returnStr += cellValue.toString() + " ";
        }
    
        
        return returnStr;
    }
 
}
