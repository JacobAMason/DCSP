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

/**
 *
 * @author Jacob Mason (jm2232)
 */
public class Cell {
    protected int X;
    protected int Y;
    protected boolean borderN, borderE, borderS, borderW;
    protected boolean wallN = true, wallE = true, wallS = true, wallW = true;

    public Cell(int positionX, int positionY, boolean borderN, boolean borderE,
            boolean borderS, boolean borderW) {
        this.X = positionX;
        this.Y = positionY;
        this.borderN = borderN;
        this.borderE = borderE;
        this.borderS = borderS;
        this.borderW = borderW;
    }
    
    boolean allWallsIntact() {
        return wallN && wallE && wallS && wallW;
    }
    
}
