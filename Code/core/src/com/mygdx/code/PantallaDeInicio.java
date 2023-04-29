package com.mygdx.code;


import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;

public class PantallaDeInicio implements Screen {
	
	private Code code;
	private Sprite splash;
	private Texture texture;
	private BitmapFont font;
	private float elapsedTime = 0.0f;
	
	
	public PantallaDeInicio(Code code) {
		this.code = code;
	}

	@Override
	public void show() {
	
		this.code.manager.load("aguaMar.png", Texture.class);
		this.code.manager.load("aguaRio.png", Texture.class);
		this.code.manager.load("barquito.png", Texture.class);
		this.code.manager.load("bronce.png", Texture.class);
		this.code.manager.load("oro.png", Texture.class);
		this.code.manager.load("plata.png", Texture.class);
		this.code.manager.load("powerUp.png", Texture.class);
		Skin skin = new Skin();
		Pixmap pixmap = new Pixmap(10, 10, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		
		this.texture = new Texture("Fondo_Inicio.jpg");
		this.splash = new Sprite(texture);
		this.splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render(float delta) {
		
		
		BitmapFont fuente = new BitmapFont();
		fuente.setColor(Color.GOLDENROD);
		fuente.getData().setScale(5.0f, 5.0f);
		this.font = fuente;

		ScreenUtils.clear(0, 0, 0, 1);
		this.code.batch.begin();
	    this.code.batch.draw(this.texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	    if(!this.code.manager.update()) {
	    	elapsedTime += delta;
		    if (elapsedTime < 0.5f) {
	            font.draw(this.code.batch, "Cargando", 75, 75);
	        }else if(elapsedTime < 1f){
	            font.draw(this.code.batch, "Cargando.", 75, 75);
	        }else if(elapsedTime < 1.5f){
	            font.draw(this.code.batch, "Cargando..", 75, 75);
	        }else if(elapsedTime < 2.0f){
	            font.draw(this.code.batch, "Cargando...", 75, 75);
	        }else {
	        	elapsedTime = 0.0f;
	        }
	    }	else {
	    	font.draw(this.code.batch, "Presiona cualquier tecla", 580, 100);
	    }
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
		
	}

}
