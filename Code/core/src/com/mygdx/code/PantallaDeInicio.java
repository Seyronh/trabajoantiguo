package com.mygdx.code;

import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class PantallaDeInicio implements Screen {
	
	private ProgressBar bar;
	private Code code;
	private Sprite splash;
	private Texture texture;

	public PantallaDeInicio(Code code) {
		this.code = code;
	}

	@Override
	public void show() {
		
		Skin skin = new Skin();
		Pixmap pixmap = new Pixmap(10, 10, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		TextureRegionDrawable textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("aguaMar.png"))));
		ProgressBarStyle barStyle = new ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBar);
		this.bar = new ProgressBar(0f, 1f, 0.01f, false, barStyle);
		this.bar.setSize(1000f, 0.000000000001f);
		this.bar.setSize(1000f, 0.000001f);
		this.bar.setHeight(0.00001f);
		this.texture = new Texture("Fondo_Inicio.jpg");
		this.splash = new Sprite(texture);
		this.splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1);
		
		this.code.batch.begin();
		this.splash.draw(this.code.batch);
		this.bar.draw(this.code.batch, 1f);
		this.code.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
