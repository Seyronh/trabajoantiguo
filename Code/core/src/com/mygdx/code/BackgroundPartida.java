package com.mygdx.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class BackgroundPartida {
	Sprite background;
	Sprite background2;
	Vector2 posicion;
	Vector2 posicion2;
	float h;
	float w;
	OrthographicCamera camara;
	float acumulador = 0;
	public BackgroundPartida(float relation,OrthographicCamera camara) {
		background = new Sprite(new Texture("aguaRio.png"),1024,1024);
		//background2 = new Sprite(new Texture("aguaRio.png"),1024,1024);
		this.w = Gdx.graphics.getWidth()/relation;
		this.h = Gdx.graphics.getHeight()/relation;
		background.setSize(w+50, h+50);

		//background2.setSize(w, h);
		//background2.flip(false, true);
		
		this.camara = camara;
		posicion = new Vector2(camara.position.x,camara.position.y);
		//posicion2 = new Vector2(camara.position.x,camara.position.y+h);
	}
	public void animate(float delta) {
		acumulador += delta;
		posicion.x = camara.position.x;
		posicion.y = camara.position.y;
		posicion.x += 2*Math.sin(acumulador);
	    background.setCenter(posicion.x, posicion.y);
	}
	public void draw(SpriteBatch batch) {
		background.draw(batch);
 
		//background2.draw(batch);
	}
}
