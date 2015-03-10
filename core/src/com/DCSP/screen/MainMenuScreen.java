package com.DCSP.screen;

import com.DCSP.http.HttpConnection;
import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class MainMenuScreen extends ScreenInterface{
    private int WIDTH, HEIGHT;
    
    private SpriteBatch batch;
    private Sprite background;
    private SpriteDrawable settings;
    private Stage menuStage;
    private Table menuTable,gear;
    private Skin skin;
    private Label nameLbl, passLbl;
    private TextField nameTxt,passTxt;
    private TextButton playBtn, quitBtn, register,login;
    private ImageButton settingsBtn;
    
    @Override
    public void show() {        
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        
        menuStage = new Stage();
        Gdx.input.setInputProcessor(menuStage);
        
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
        nameTxt = new TextField("", skin);
        
        passLbl = new Label("Password", skin);
        passTxt = new TextField("", skin);
        passTxt.setPasswordMode(true);
        passTxt.setPasswordCharacter('*');
        
        settingsBtn = new ImageButton(settings);
        
        playBtn = new TextButton("Play Demo", skin);
        quitBtn = new TextButton("Exit", skin);
        login = new TextButton("Login", skin);
        register = new TextButton("Register", skin);
        
        
        
        playBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameParent.setScreen(new MazeScreen(10));
            }
        });
        
        settingsBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameParent.setScreen(gameParent.settingsScreen);
            }
        });
        
        quitBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }            
        });
        
        login.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // This is just an example
                HttpConnection httpCon = new HttpConnection();
                httpCon.login(nameTxt.getText(), passTxt.getText());
            }            
        });
        
        register.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameParent.setScreen(new RegistrationScreen());
            }            
        });
        
        gear.add(settingsBtn).pad(15);
        gear.top().right();
        menuStage.addActor(gear);
        
        menuTable.defaults().padBottom(10).padRight(5);
        menuTable.add(nameLbl);
        menuTable.add(nameTxt).width(100);
        menuTable.row();
        menuTable.add(passLbl);
        menuTable.add(passTxt).width(100);
        menuTable.row();
        menuTable.add(login).colspan(2).fillX();
        menuTable.row();
        menuTable.add(register).colspan(2).fillX();
        menuTable.row();
        menuTable.add(playBtn).colspan(2).fillX();
        menuTable.row();
        menuTable.add(quitBtn).colspan(2).fillX();
        menuStage.addActor(menuTable);
        
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        background.draw(batch);
        batch.end();        
        
        menuStage.act(delta);
        menuStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        menuStage.getViewport().update(width,height);       
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
    }
}
