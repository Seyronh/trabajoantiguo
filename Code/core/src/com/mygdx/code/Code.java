package com.mygdx.code;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Code extends Game {
	SpriteBatch batch;
	Texture img;
	Sprite barquito;
	Barco boat;
	TipoBarco elegido;
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PantallaPartida(this));
		/*
		Vector2 pos = new Vector2(100,100);
		img = new Texture("barquito.png");
		barquito = new Sprite(img,1024,1024);
		barquito.setScale(0.3f);
		elegido = new TipoBarco(10f,10f,"Neutro",10f,20f);
		boat = new Barco(elegido,pos);
		*/
	}
/*
	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 1);
		if(Gdx.input.isKeyPressed(Keys.A)) {
			boat.girarIzquierda();
		}
		if(Gdx.input.isKeyPressed(Keys.D)) {
			boat.girarDerecha();
		}
		if(Gdx.input.isKeyPressed(Keys.W)) {
			boat.acelerar();
		}
		if(Gdx.input.isKeyPressed(Keys.S)) {
			boat.frenar(false);
		} else {
			boat.frenar(true);
		}
		boat.actualizarposicion();
		barquito.setPosition(boat.posicion.x,boat.posicion.y);
		barquito.setRotation(boat.angulo-90);
		batch.begin();
		barquito.draw(batch);
		batch.end();
	}
	*/
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}
}
