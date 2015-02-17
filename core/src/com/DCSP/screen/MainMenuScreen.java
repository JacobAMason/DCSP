package com.DCSP.screen;

import com.DCSP.game.GameRoot;
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
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class MainMenuScreen extends ScreenInterface{
    private int WIDTH, HEIGHT;
    
    private SpriteBatch batch;
    private Sprite background;
    private SpriteDrawable settings;
    private Stage menuStage;
    private Table menuTable,gear;
    private Skin ourSkin;
    private Label nameLbl, passLbl;
    private TextField nameTxt,passTxt;
    private TextButton playBtn, quitBtn;
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
        
        ourSkin = new Skin(Gdx.files.internal("uiskin.json"));
        
        nameLbl = new Label("Username", ourSkin);
        nameTxt = new TextField("", ourSkin);
        passLbl = new Label("Password", ourSkin);
        passTxt = new TextField("", ourSkin);
        
        playBtn = new TextButton("Play Game",ourSkin);
        settingsBtn = new ImageButton(settings);
        quitBtn = new TextButton("Exit",ourSkin);
        
        
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
        
        gear.add(settingsBtn).pad(15);
        gear.top().right();
        menuTable.defaults().padBottom(10).padRight(5);
        menuTable.add(nameLbl);
        menuTable.add(nameTxt).width(100);
        menuTable.row();
        menuTable.add(passLbl);
        menuTable.add(passTxt).width(100);
        menuTable.row();
        menuTable.add(playBtn).colspan(2).fillX();
        menuTable.row();
        menuTable.add(quitBtn).colspan(2).fillX();
        menuStage.addActor(menuTable);
        menuStage.addActor(gear);
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
