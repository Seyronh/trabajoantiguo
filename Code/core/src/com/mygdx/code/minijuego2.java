package com.mygdx.code;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;

public class minijuego2 implements Screen{
    final Code code;
	private float anchoPantalla, altoPantalla;
	private Texture fondo, pez, tiburon, meta;
    private SpriteBatch batch;
    private int puntuacion;
    private final int MAX_PUNTOS;
    private BitmapFont fuente;
    private Random rnd;
    private int subir;
    private ProgressBar barraProgreso;
    private Stage stage;
    private boolean estaBajando, hasGanado;
    private float delay;
	
    public minijuego2(final Code code){
        this.code = code;
        anchoPantalla = Gdx.graphics.getWidth();
		altoPantalla = Gdx.graphics.getHeight();
        
        MAX_PUNTOS = 3;
        rnd = new Random();
        subir = 0;
        hasGanado = false;
    }

	@Override
	public void show() {
		// TODO Auto-generated method stub
        //stage = new Stage();
        batch = new SpriteBatch();
        //puntuacion
        puntuacion = 0;
        
        fuente = new BitmapFont();
        fuente.setColor(0, 0, 0, 1);
        fuente.getData().setScale(5, 5);
        
        
        pez = this.code.manager.get("pezHorizontal.png");
        //fondo
        fondo = this.code.manager.get("fondo-mar.png");
        //tiburon
        tiburon = this.code.manager.get("tibu2.png");
        //meta±a
        //meta = this.code.manager.get("meta.png");
        
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		batch.begin();
        batch.draw(fondo, 0, 0, anchoPantalla, altoPantalla);

        batch.draw(pez, anchoPantalla/2 + 225, altoPantalla/2 - 15, 90, 150);
        batch.draw(tiburon,anchoPantalla/2 + 270, altoPantalla/2 - 15, 180, 150);
        //batch.draw(meta, anchoPantalla/2 + 120, altoPantalla/2 - 30, 50, 300);

        batch.end();
        //stage.act();
        //stage.draw();


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
