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
package com.DCSP.tween;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Based on: http://www.aurelienribon.com/universal-tween-engine/javadoc/aurelienribon/tweenengine/TweenAccessor.html
 * @author Jacob Mason (jm2232)
 */
public class SpriteAccessor implements TweenAccessor<Sprite> {
    
    public static final int ALPHA = 1;
    
    @Override
    public int getValues(Sprite sprite, int type, float[] returnValues) {
        switch (type)
        {
            case ALPHA:
                returnValues[0] = sprite.getColor().a;
                return 1;
            default:
                assert false;
                return 0;
        }
    }

    @Override
    public void setValues(Sprite sprite, int type, float[] newValues) {
        switch(type)
        {
            case ALPHA:
                sprite.setColor(sprite.getColor().r, sprite.getColor().g, sprite.getColor().b, newValues[0]);
                break;
            default:
                assert false;
                break;            
        }
    }



    
    
}
