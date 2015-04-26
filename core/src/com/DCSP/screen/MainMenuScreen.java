package com.DCSP.screen;

import com.DCSP.http.HttpConnection;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class MainMenuScreen extends ScreenInterface {

    private int WIDTH, HEIGHT;

    private SpriteBatch batch;
    private Sprite background;
    private SpriteDrawable settings;
    private Stage menuStage;
    private Table menuTable, gear;
    private Skin skin;
    private Label nameLbl, passLbl;
    private TextField nameTxt, passTxt;
    private TextButton playBtn, quitBtn, register, login;
    private ImageButton settingsBtn;

    // Windows
    private Window successWindow;

    @Override
    public void show() {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();

        menuStage = new Stage();
        InputMultiplexer menuInput = new InputMultiplexer();
        menuInput.addProcessor(new InputAdapter() {

            @Override
            public boolean keyUp(int keycode) {
                switch (keycode) {
                    case Input.Keys.ENTER:
                        HttpConnection httpCon = new HttpConnection(gameParent);
                        httpCon.login(nameTxt.getText(), passTxt.getText(), successWindow);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

        menuInput.addProcessor(menuStage);

        Gdx.input.setInputProcessor(menuInput);
        Gdx.input.setInputProcessor(menuStage);
        Gdx.input.setCatchBackKey(true);

        menuTable = new Table();
        menuTable.setFillParent(true);
        gear = new Table();
        gear.setFillParent(true);

        Texture splashTexture = new Texture("img/menu.png");
        background = new Sprite(splashTexture);
        background.setSize(WIDTH, HEIGHT);

        Texture settingsTexture = new Texture("img/settings.png");
        Sprite settingsSprite = new Sprite(settingsTexture);
        settingsSprite.setSize(30, 30);
        settings = new SpriteDrawable(settingsSprite);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        nameLbl = new Label("Username", skin);
        nameTxt = new TextField("", skin, "user");

        passLbl = new Label("Password", skin);
        passTxt = new TextField("", skin, "user");
        passTxt.setPasswordMode(true);
        passTxt.setPasswordCharacter('*');

        settingsBtn = new ImageButton(settings);

        playBtn = new TextButton("Play Demo", skin);
        quitBtn = new TextButton("Exit", skin);
        login = new TextButton("Login", skin);
        register = new TextButton("Register", skin);

        playBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameParent.setScreen(new InstructionScreen());
            }
        });

        settingsBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameParent.setScreen(new InstructionScreen());
            }
        });

        quitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        login.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                HttpConnection httpCon = new HttpConnection(gameParent);
                httpCon.login(nameTxt.getText(), passTxt.getText(), successWindow);
            }
        });

        register.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameParent.setScreen(new RegistrationScreen());
            }
        });

        gear.add(settingsBtn).pad(15);
        gear.top().right();
        menuStage.addActor(gear);

        menuTable.defaults().pad(10).minHeight(HEIGHT / 9);
        menuTable.add(nameLbl).minWidth(90);  // 10 difference due to padding
        menuTable.add(nameTxt).minWidth(WIDTH / 2 - 100);
        menuTable.row();
        menuTable.add(passLbl).minWidth(90);
        menuTable.add(passTxt).minWidth(WIDTH / 2 - 100);
        menuTable.row();
        menuTable.add(login).colspan(2).minWidth(WIDTH / 2);
        menuTable.row();
        menuTable.add(register).colspan(2).minWidth(WIDTH / 2);
        menuTable.row();
        menuTable.add(playBtn).colspan(2).minWidth(WIDTH / 2);
        menuTable.row();
        menuTable.add(quitBtn).colspan(2).minWidth(WIDTH / 2);
        menuStage.addActor(menuTable);

        //Check window
        /* 
         * make this method call to put the login failed window appear
         * successWindow.setVisible(true);
         */
        successWindow = new Window("Login Failed", skin);
        successWindow.setMovable(false);
        successWindow.padTop(20);
        Label successWindowLbl = new Label("Incorrect Username or Password.\nPlease try again.\n", skin, "small");
        successWindow.add(successWindowLbl);
        successWindow.setWidth(successWindowLbl.getWidth() + 20);
        successWindow.row();
        TextButton successOK = new TextButton("Ok", skin);
        successOK.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                successWindow.setVisible(false);
            }
        });
        successWindow.add(successOK);
        successWindow.setVisible(false);
        successWindow.setPosition(WIDTH / 2, HEIGHT / 2, Align.center);
        successWindow.pack();
        menuStage.addActor(successWindow);
        //end check window

        // Always add the generic message window
        menuStage.addActor(gameParent.getMessageWindow().getWindow());

        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        background.draw(batch);
        batch.end();

        menuStage.act(delta);
        menuStage.draw();

        if (gameParent.isLoggedIn() && gameParent.profile.isProfileConstructed()) {
            if (gameParent.profile.scoresDict.isEmpty()) {
                gameParent.setScreen(new InstructionScreen());
            } else {
                gameParent.setScreen(new GameMenuScreen());
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        menuStage.getViewport().update(width, height);
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
        batch.dispose();
        background.getTexture().dispose();
        menuStage.dispose();
    }
}
