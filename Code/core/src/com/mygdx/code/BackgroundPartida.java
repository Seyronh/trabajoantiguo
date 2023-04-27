package com.mygdx.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class BackgroundPartida {
	
	Sprite backgroundIzquierdaArriba;
	Sprite backgroundArriba;
	Sprite backgroundDerechaArriba;
	Sprite backgroundIzquierda;
	Sprite backgroundCentro;
	Sprite backgroundDerecha;
	Sprite backgroundIzquierdaAbajo;
	Sprite backgroundAbajo;
	Sprite backgroundDerechaAbajo;
	
	Vector2 posicionIzquierdaArriba;
	Vector2 posicionArriba;
	Vector2 posicionDerechaArriba;
	Vector2 posicionIzquierda;
	Vector2 posicionCentro;
	Vector2 posicionDerecha;
	Vector2 posicionIzquierdaAbajo;
	Vector2 posicionAbajo;
	Vector2 posicionDerechaAbajo;
	
	float h;
	float w;
	
	OrthographicCamera camara;
	
	//float acumulador = 0;
	
	public BackgroundPartida(float relation,OrthographicCamera camara) {
		this.camara = camara;
		
		this.w = Gdx.graphics.getWidth()/relation;
		this.h = Gdx.graphics.getHeight()/relation;
		
		backgroundIzquierdaArriba = new Sprite(new Texture("aguaRio.png"),1024,1024);
		backgroundArriba = new Sprite(new Texture("aguaRio.png"),1024,1024);
		backgroundDerechaArriba = new Sprite(new Texture("aguaRio.png"),1024,1024);
		backgroundIzquierda = new Sprite(new Texture("aguaRio.png"),1024,1024);
		backgroundCentro = new Sprite(new Texture("aguaRio.png"),1024,1024);
		backgroundDerecha = new Sprite(new Texture("aguaRio.png"),1024,1024);
		backgroundIzquierdaAbajo = new Sprite(new Texture("aguaRio.png"),1024,1024);
		backgroundAbajo = new Sprite(new Texture("aguaRio.png"),1024,1024);
		backgroundDerechaAbajo = new Sprite(new Texture("aguaRio.png"),1024,1024);
		
		backgroundIzquierdaArriba.setSize(w, h);
		backgroundArriba.setSize(w, h);
		backgroundDerechaArriba.setSize(w, h);
		backgroundIzquierda.setSize(w, h);
		backgroundCentro.setSize(w, h);
		backgroundDerecha.setSize(w, h);
		backgroundIzquierdaAbajo.setSize(w, h);
		backgroundAbajo.setSize(w, h);
		backgroundDerechaAbajo.setSize(w, h);
		
		posicionIzquierdaArriba = new Vector2(camara.position.x-w,camara.position.y+h);
		posicionArriba = new Vector2(camara.position.x,camara.position.y+h);
		posicionDerechaArriba = new Vector2(camara.position.x+w,camara.position.y+h);
		posicionIzquierda = new Vector2(camara.position.x-w,camara.position.y);
		posicionCentro = new Vector2(camara.position.x,camara.position.y);
		posicionDerecha = new Vector2(camara.position.x+w,camara.position.y);
		posicionIzquierdaAbajo = new Vector2(camara.position.x-w,camara.position.y-h);
		posicionAbajo = new Vector2(camara.position.x,camara.position.y-h);
		posicionDerechaAbajo = new Vector2(camara.position.x+w,camara.position.y-h);
	}
	public void animate(float delta) {
		//acumulador += delta;
		boolean actualizado = false;
		Vector2 pos = new Vector2(this.camara.position.x,this.camara.position.y);
		if(backgroundIzquierdaArriba.getBoundingRectangle().contains(pos)) {
			posicionCentro = new Vector2(posicionIzquierdaArriba.x,posicionIzquierdaArriba.y);
			actualizado = true;
		}else
		if(backgroundArriba.getBoundingRectangle().contains(pos)) {
			posicionCentro = new Vector2(posicionArriba.x,posicionArriba.y);
			actualizado = true;
		}else
		if(backgroundDerechaArriba.getBoundingRectangle().contains(pos)) {
			posicionCentro = new Vector2(posicionDerechaArriba.x,posicionDerechaArriba.y);
			actualizado = true;
		}else
		if(backgroundIzquierda.getBoundingRectangle().contains(pos)) {
			posicionCentro = new Vector2(posicionIzquierda.x,posicionIzquierda.y);
			actualizado = true;
		}else
		if(backgroundDerecha.getBoundingRectangle().contains(pos)) {
			posicionCentro = new Vector2(posicionDerecha.x,posicionDerecha.y);
			actualizado = true;
		}else
		if(backgroundIzquierdaAbajo.getBoundingRectangle().contains(pos)) {
			posicionCentro = new Vector2(posicionIzquierdaAbajo.x,posicionIzquierdaAbajo.y);
			actualizado = true;
		}else
		if(backgroundAbajo.getBoundingRectangle().contains(pos)) {
			posicionCentro = new Vector2(posicionAbajo.x,posicionAbajo.y);
			actualizado = true;
		}else
		if(backgroundDerechaAbajo.getBoundingRectangle().contains(pos)) {
			posicionCentro = new Vector2(posicionDerechaAbajo.x,posicionDerechaAbajo.y);
			actualizado = true;
		}
		if(actualizado) {
			posicionIzquierdaArriba = new Vector2(posicionCentro.x-w,posicionCentro.y+h);
			posicionArriba = new Vector2(posicionCentro.x,posicionCentro.y+h);
			posicionDerechaArriba = new Vector2(posicionCentro.x+w,posicionCentro.y+h);
			posicionIzquierda = new Vector2(posicionCentro.x-w,posicionCentro.y);
			posicionDerecha = new Vector2(posicionCentro.x+w,posicionCentro.y);
			posicionIzquierdaAbajo = new Vector2(posicionCentro.x-w,posicionCentro.y-h);
			posicionAbajo = new Vector2(posicionCentro.x,posicionCentro.y-h);
			posicionDerechaAbajo = new Vector2(posicionCentro.x+w,posicionCentro.y-h);
		}
		backgroundIzquierdaArriba.setCenter(posicionIzquierdaArriba.x, posicionIzquierdaArriba.y);
		backgroundArriba.setCenter(posicionArriba.x, posicionArriba.y);
		backgroundDerechaArriba.setCenter(posicionDerechaArriba.x, posicionDerechaArriba.y);
		backgroundIzquierda.setCenter(posicionIzquierda.x, posicionIzquierda.y);
		backgroundCentro.setCenter(posicionCentro.x, posicionCentro.y);
		backgroundDerecha.setCenter(posicionDerecha.x, posicionDerecha.y);
		backgroundIzquierdaAbajo.setCenter(posicionIzquierdaAbajo.x, posicionIzquierdaAbajo.y);
		backgroundAbajo.setCenter(posicionAbajo.x, posicionAbajo.y);
		backgroundDerechaAbajo.setCenter(posicionDerechaAbajo.x, posicionDerechaAbajo.y);
	}
	public void draw(SpriteBatch batch) {
		backgroundIzquierdaArriba.draw(batch);
		backgroundArriba.draw(batch);
		backgroundDerechaArriba.draw(batch);
		backgroundIzquierda.draw(batch);
		backgroundCentro.draw(batch);
		backgroundDerecha.draw(batch);
		backgroundIzquierdaAbajo.draw(batch);
		backgroundAbajo.draw(batch);
		backgroundDerechaAbajo.draw(batch);
	}
}
