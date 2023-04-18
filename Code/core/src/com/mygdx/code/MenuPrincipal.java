package com.mygdx.code;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuPrincipal extends Game{
    private Stage mainMenuStage;
    private TextButton buttonJugar;
    private TextButton buttonSalir;
    private TextButton buttonConfig;
    private TextButtonStyle tbs;
    private Skin skinButton;
    private TextureAtlas atlas;
    private BitmapFont font;



    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resize'");
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pause'");
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resume'");
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dispose'");
    }

    @Override
    public void create() {
        mainMenuStage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(mainMenuStage);
        skinButton = new Skin();
        font = new BitmapFont();
        atlas = new TextureAtlas(Gdx.files.internal("flat-earth-ui.atlas"));
        skinButton.addRegions(atlas);
        tbs = new TextButtonStyle();
        tbs.font = font;
        tbs.up = skin.getDrawable("up-button");
        tbs.down = skin.getDrawable("down-button");
;
        

        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }
    
    @Override
    public void render(){
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainMenuStage.act();
        mainMenuStage.draw();
    }
}
