package com.mygdx.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Minijuego3 implements Screen{
	final Code code;
	private float anchoPantalla, altoPantalla;
	private Texture fondo, dragon, fuego, pez;
	private SpriteBatch batch;
	private BitmapFont fuente;
	private boolean hasGanado;
	private float delay, pezX;
	    
	public Minijuego3(final Code code) {
		this.code = code;
		anchoPantalla = Gdx.graphics.getWidth();
		altoPantalla = Gdx.graphics.getHeight();
        hasGanado = false;
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
        batch = new SpriteBatch();
        //puntuacion
        
        fuente = new BitmapFont();
        fuente.setColor(0, 0, 0, 1);
        fuente.getData().setScale(5, 5);     
        
        pez = this.code.manager.get("minijuego1/pezHorizontal.png");
        //fondo
        fondo = this.code.manager.get("minijuego3/fondo_playa.png");
        //tiburon
        fuego = this.code.manager.get("minijuego3/fuego.png");
        
        dragon = this.code.manager.get("minijuego3/draon.png");


	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
		batch.begin();
        batch.draw(fondo, 0, 0, anchoPantalla, altoPantalla);
        
        batch.draw(pez, pezX, altoPantalla/3 - 50, 150, 90);
        batch.draw(fuego, 15, altoPantalla/3 - 50, 150, 90);
        batch.draw(dragon, 100, altoPantalla/3 - 50, 150, 90);

        
        batch.end();
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
