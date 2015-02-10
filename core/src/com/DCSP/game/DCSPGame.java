package com.DCSP.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class DCSPGame extends ApplicationAdapter implements InputProcessor {

    OrthographicCamera camera;
    SpriteBatch batch;
    private Texture img;
    private float stateTime;
    private Animation explosionAnimation;
    private boolean explosionHappening;
    private int touchCoordinateX, touchCoordinateY;
    private Vector3 touchPoint = new Vector3();

    @Override
    public void create() {

        Gdx.input.setInputProcessor(this);

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false);

        batch = new SpriteBatch();
        img = new Texture("img/warlock.jpg");

        int FRAME_COLS = 5;
        int FRAME_ROWS = 3;

        Texture explosionSheet = new Texture(Gdx.files.internal("img/explosion.png")); // explosion image sheet shown above
        TextureRegion[][] textureRegions = TextureRegion.split(explosionSheet, explosionSheet.getWidth() / FRAME_COLS, explosionSheet.getHeight() / FRAME_ROWS);              // #10
        TextureRegion[] explosionFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                explosionFrames[index++] = textureRegions[i][j];
            }
        }
        explosionAnimation = new Animation(0.025f, explosionFrames);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        stateTime += Gdx.graphics.getDeltaTime();
        if (!explosionAnimation.isAnimationFinished(stateTime) && explosionHappening) {
            TextureRegion currentFrame = explosionAnimation.getKeyFrame(stateTime, false);
            camera.unproject(touchPoint.set(touchCoordinateX, touchCoordinateY, 0));
            batch.draw(currentFrame, touchPoint.x - currentFrame.getRegionHeight() / 2, touchPoint.y - currentFrame.getRegionWidth() / 2);
            Gdx.app.log("DCSPGame", "Explosion at point " + touchPoint.x + " " + touchPoint.y);
        }
        batch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log("DCSPGame", "touch at x:" + screenX + " y:" + screenY);
        touchCoordinateX = screenX;
        touchCoordinateY = screenY;
        stateTime = 0;
        explosionHappening = true;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
