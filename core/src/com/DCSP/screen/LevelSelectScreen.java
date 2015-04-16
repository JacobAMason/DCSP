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
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import java.text.DecimalFormat;

/**
 *
 * @author Alex Dodd (wad79)
 */
public class LevelSelectScreen extends ScreenInterface {

    private Stage levelStage;
    private Table levelTable;
    private Skin skin;
    private TextButton levelBtn;
    private ScrollPane levelScroll;

    @Override
    public void show() {
        levelStage = new Stage();
        InputMultiplexer levelInput = new InputMultiplexer();
        levelInput.addProcessor(levelStage);
        levelInput.addProcessor(new InputAdapter() {

            @Override
            public boolean keyUp(int keycode) {
                switch (keycode) {
                    case Keys.ESCAPE:
                    case Keys.BACK:
                        gameParent.setScreen(new GameMenuScreen());
                        break;
                    default:
                        return false;
                }
                return true;
            }

        });
        Gdx.input.setInputProcessor(levelInput);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        levelTable = new Table(skin);
        //levelTable.setFillParent(true);

        levelTable.defaults().pad(10);
        levelTable.add("Level Select").colspan(5);
        levelTable.row();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 5; j++) {
                final int lvl = (i * 5 + j + 1);
                String x = String.valueOf(lvl);
                levelBtn = new TextButton(x, skin);
                
                Double timeNum = gameParent.profile.scoresDict.get(lvl);
                if (timeNum != null) {
                    Label label = new Label(new DecimalFormat("####.##").format(timeNum), skin, "small");
                    levelBtn.add(label);
                } else {
                    levelBtn.getLabel().setColor(Color.RED);
                }

                levelBtn.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        gameParent.setScreen(new MazeScreen(lvl, 1));
                    }
                });

                levelTable.add(levelBtn).size(Gdx.graphics.getWidth() / 5 - 20).expand();
            }
            levelTable.row();
        }
        levelScroll = new ScrollPane(levelTable);
        levelScroll.setFillParent(true);
//        levelTable = new Table(skin);
//        levelTable.setFillParent(true);
//        levelTable.defaults().padBottom(10);
//        levelTable.add("Level Select").colspan(5);
//        levelTable.row();
//        levelTable.add(levelScroll);
        levelStage.addActor(levelScroll);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        levelStage.act(delta);
        levelStage.draw();
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
        levelStage.dispose();
    }

}
