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
import com.DCSP.http.HttpConnection;
import com.DCSP.mazeGen.Maze;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import java.text.DecimalFormat;

/**
 *
 * @author Alex
 */
public class MazeScreen extends ScreenInterface {

    private World world;
    private Maze maze;
    private Box2DDebugRenderer debugging;
    public OrthographicCamera camera;
    private final int mWidth, mHeight;
    private float cellFactor;
    private Player player;
    private Vector2 pos = new Vector2(0f, 0f);
    private int level;

    private float step, time;

    private SpriteBatch batch;
    private Array<Body> Bodies = new Array();

    //Check window
    private Window endGameWindow;
    private Skin skin;
    private Stage menuStage;
    private Label endGameWindowLbl;
    private int WIDTH;
    private int HEIGHT;

    // The higher the number the higher the zoom.
    public static float zoom = 9;

    public MazeScreen(int level) {
        this.level = level;
        mWidth = level + 15;
        mHeight = (int) Math.floor(9 * mWidth / 16);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        time = 0.0f;
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
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
                switch (keycode) {
                    case Keys.ESCAPE:
                    case Keys.BACK:
                        gameParent.setScreen(new LevelSelectScreen());
                        break;
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

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                player.setMove((float) screenX - WIDTH / 2, (float) screenY - HEIGHT / 2);
                return true;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                player.setMove((float) screenX - WIDTH / 2, (float) screenY - HEIGHT / 2);
                return true;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                player.setX(0);
                player.setY(0);
                return true;
            }

        });

        Gdx.input.setCatchBackKey(true);

        cellFactor = /*(((float)Gdx.graphics.getHeight()/((float)mHeight+0.5f)))/10*/ 8;
        step = cellFactor * cellFactor;
        world = new World(new Vector2(0, 0), true);
        debugging = new Box2DDebugRenderer();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(true);
        camera.translate((float) -WIDTH * 3 / 4, (float) -HEIGHT * 3 / 4);
        camera.zoom /= zoom;
        camera.update();

        maze = new Maze(world, mWidth, mHeight, 42, cellFactor);

        player = new Player(world, cellFactor, camera);

        //Check window
        /* 
         * make this method call to put the login failed window appear
         * endGameWindow.setVisible(true);
         */
        menuStage = new Stage();
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        endGameWindow = new Window("Maze Complete!", skin);
        endGameWindow.setMovable(false);
        endGameWindow.padTop(20);
        endGameWindowLbl = new Label("Would you like to challenge a friend?", skin, "small");
        endGameWindow.add(endGameWindowLbl).colspan(2);
        endGameWindow.setWidth(endGameWindowLbl.getWidth() + 20);
        endGameWindow.row().row();

        TextButton no = new TextButton("Nah", skin);
        no.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                endGameWindow.setVisible(false);
                gameParent.setScreen(new LevelSelectScreen());
            }
        });
        endGameWindow.add(no);

        TextButton yes = new TextButton("Sure", skin);
        yes.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                endGameWindow.setVisible(false);
                gameParent.setScreen(new ChallengeSendScreen(42, new Array(new String[]{"You", "Have", "No", "Friends"})));
            }
        });
        endGameWindow.add(yes);

        endGameWindow.setVisible(false);
        endGameWindow.setPosition(WIDTH / 2, HEIGHT / 2, Align.center);
        menuStage.addActor(endGameWindow);
        //end check window
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.set(player.getPos(), 0);
        camera.update();

        debugging.render(world, camera.combined);

        world.step(delta, 6, 2);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        world.getBodies(Bodies);
        for (Body body : Bodies) {
            if (body.getUserData() instanceof Sprite) {
                Sprite sprite = (Sprite) body.getUserData();
                sprite.setCenter(body.getPosition().x, body.getPosition().y);
                sprite.draw(batch);
            }
        }
        batch.end();

        if (player.checkWin(mWidth, mHeight)) {
            player.setX(0);
            player.setY(0);
            this.pause();
            Gdx.input.setInputProcessor(menuStage);
            String sTime = new DecimalFormat("####.##").format(time);

            HttpConnection httpCon = new HttpConnection(gameParent);
            if (gameParent.profile != null) {
                httpCon.sendScore(gameParent.profile.getID(), level, time);
            }

            endGameWindowLbl.setText("Your time was " + sTime + " seconds."
                    + "\nWould you like to challenge a friend?");
            endGameWindow.setVisible(true);
            // gameParent.setScreen(gameParent.mainMenuScreen);
        } else {
            player.update();
            time += delta;
        }
        menuStage.act(delta);
        menuStage.draw();
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
