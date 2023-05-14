package com.mygdx.code;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
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
    private ProgressBar barraProgreso;
    private boolean hasGanado, hasPerdido;
    private float delay,  tiburonX, pezX, accel;
	
    public minijuego2(final Code code){
        this.code = code;
        anchoPantalla = Gdx.graphics.getWidth();
		altoPantalla = Gdx.graphics.getHeight();
        
        MAX_PUNTOS = 3;
        rnd = new Random();
        hasGanado = false;
        hasPerdido = false;
        accel = 1;
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
        tiburon = this.code.manager.get("tiburon.png");
        //meta±a
        //meta = this.code.manager.get("meta.png");
        tiburonX = anchoPantalla/30;
        pezX = anchoPantalla/3 - 100;
	}

	@Override
	public void render(float delta) {
		delay += delta;
		
		batch.begin();
        batch.draw(fondo, 0, 0, anchoPantalla, altoPantalla);
        
        if(!hasPerdido) {
        	batch.draw(pez, pezX, altoPantalla/3 - 50, 150, 90);
        }

        batch.draw(tiburon,tiburonX, altoPantalla/5, 420, 300);
        //batch.draw(meta, anchoPantalla/2 + 120, altoPantalla/2 - 30, 50, 300);
        
        if(!hasPerdido) {
	        //Movimiento pez
		    if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
		       pezX+=15;
		    }
        }
        
	    if(!hasGanado) {
	        //Movimiento tiburon
	        if(delay>3.0f) {
	        	tiburonX = tiburonX + accel;
			    accel+= 0.008;
		    }
	    }
        
        //Colisiones
        //Tiburon-pez
        if(pezX <= tiburonX+250) {
            fuente.draw(batch, "HAS PERDIDO", 100, altoPantalla-70);
            hasPerdido = true;
        }
        //Pez-fin
        if(pezX >= anchoPantalla) {
            fuente.draw(batch, "HAS GANADO", 100, altoPantalla-70);
            hasGanado=true;
        }
        	
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
