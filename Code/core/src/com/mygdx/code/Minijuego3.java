package com.mygdx.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Minijuego3 implements Screen{
	private int cocinado;
	final Code code;
	private float anchoPantalla, altoPantalla;
	private Texture fondo, dragon, fuego, pez, barraP, barraG, pezMedioCocinado, pezMedioCocinado2, pezCocinado;
	private SpriteBatch batch;
	private BitmapFont fuente;
	private boolean hasGanado, lanzandoFuego;
	private float delay, delay2, barraPW, barraGW, barrasX, barrasY, fuegoW, fuegoH, fuegoY, fuegoX;
	    
	public Minijuego3(final Code code) {
		this.code = code;
		anchoPantalla = Gdx.graphics.getWidth();
		altoPantalla = Gdx.graphics.getHeight();
        hasGanado = false;
		barraPW = 0;
		barraGW = 1000;
		barrasX = 450;
		barrasY = altoPantalla/2 +200;
		fuegoY = altoPantalla/3 - 75;
		fuegoH = 120; fuegoW = 300;
		fuegoX = 450;
		delay = 0; delay2 = 0;
		lanzandoFuego = false;
		cocinado = 0;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
        batch = new SpriteBatch();
        //puntuacion
        
        fuente = new BitmapFont();
        fuente.setColor(0, 0, 0, 1);
        fuente.getData().setScale(8, 8);     
        
        pez = this.code.manager.get("minijuego1/pezHorizontal.png");
        pezMedioCocinado = this.code.manager.get("minijuego3/pezMedioCocinado.png");
        pezMedioCocinado2 = this.code.manager.get("minijuego3/pezMedioCocinado2.png");
        pezCocinado = this.code.manager.get("minijuego3/pez_cocinado.png");
        //fondo
        fondo = this.code.manager.get("minijuego3/fondo_playa.png");
        //tiburon
        fuego = this.code.manager.get("minijuego3/fuego.png");
        //dragon
        dragon = this.code.manager.get("minijuego3/dragon.png");
        //barra pequena
        barraP = this.code.manager.get("minijuego3/barra.png");
        //barra grande
        barraG = this.code.manager.get("minijuego3/barraGrande.png");

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		batch.begin();	
		
        batch.draw(fondo, 0, 0, anchoPantalla, altoPantalla);
        if(cocinado==0) {
        	batch.draw(pez, anchoPantalla/2 + 550, altoPantalla/3 - 75, 150, 90);
        } else if (cocinado==1) {
        	batch.draw(pezMedioCocinado, anchoPantalla/2 + 550, altoPantalla/3 - 75, 150, 90);
        } else if (cocinado==2) {
        	batch.draw(pezMedioCocinado2, anchoPantalla/2 + 550, altoPantalla/3 - 75, 150, 90);
        } else if (cocinado>=3) {
        	batch.draw(pezCocinado, anchoPantalla/2 + 550, altoPantalla/3 - 75, 150, 110);
        }
        
        if(lanzandoFuego) {
        	batch.draw(fuego, fuegoX, fuegoY, fuegoW, fuegoH);
        }
        batch.draw(dragon, 100, altoPantalla/4 - 250, 500, 400);
        batch.draw(barraG, barrasX, barrasY, barraGW, 100);
        batch.draw(barraP, barrasX, barrasY, barraPW, 100);
        
        if(!lanzandoFuego && !hasGanado) {
	        if(barraPW < barraGW) {
	        	barraPW+=10;
	        } else {
	        	barraPW = 0;
	        }
        }
        
        if(Gdx.input.isKeyJustPressed(Keys.SPACE) && !lanzandoFuego && !hasGanado) {
    		fuegoX = 450;
    		fuegoY = altoPantalla/3 - 75;
        	lanzandoFuego = true;

        } else if (lanzandoFuego) {
        	delay+=delta;
        	fuegoX+=10;
        	if (delay>=0.55f * barraPW/(1000/3)) {
        		if(fuegoY==altoPantalla/3 - 75 && barraPW>850) {
        			cocinado++;
        		}
        		bajarFuego(delta);
        	}
        }
        
        if(cocinado >= 3) {
        	hasGanado = true;
        	delay += delta;
        	fuente.draw(batch, "¡HAS GANADO!", 650, altoPantalla-700);
        	fuente.draw(batch, "¡QUE APROVECHE!", 650, altoPantalla-850);
        	if(delay>3f) {
        		this.code.setScreen(new PantallaPartida(this.code));
        	}
        }

        
        batch.end();
	}
	
	private void bajarFuego(float delta) {
		fuegoY-= 7;
		if (fuegoY + fuegoH<= 0) {
			delay = 0;
			lanzandoFuego=false;
        	barraPW = 0;
		}
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
