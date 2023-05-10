package com.mygdx.code;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Carriles {
	private Vector2 carril1;
	private Vector2 carril2;
	private Vector2 carril3;
	private Vector2 carril4;
	private Vector2 carril5;
	OrthographicCamera camara;
	Code code;
	
	public Carriles(Code code,OrthographicCamera camara) {
		this.code = code;
		this.camara = camara;
		float ancho = camara.viewportWidth/4;
		carril1 = new Vector2(-ancho*2,camara.position.y);
		carril2 = new Vector2(-ancho,camara.position.y);
		carril3 = new Vector2(0,camara.position.y);
		carril4 = new Vector2(ancho,camara.position.y);
		carril5 = new Vector2(ancho*2,camara.position.y);
	}
	public void update() {
		carril1 = new Vector2(carril1.x,camara.position.y);
		carril2 = new Vector2(carril2.x,camara.position.y);
		carril3 = new Vector2(carril3.x,camara.position.y);
		carril4 = new Vector2(carril4.x,camara.position.y);
		carril5 = new Vector2(carril5.x,camara.position.y);
	}
	public Vector2 obtenerMedio(int carril) {
		Vector2 pos = new Vector2(0,0);
		switch(carril) {
		case 1:
			pos = new Vector2((carril1.x+carril2.x)/2,carril1.y);
			break;
		case 2:
			pos = new Vector2((carril2.x+carril3.x)/2,carril1.y);
			break;
		case 3:
			pos = new Vector2((carril3.x+carril4.x)/2,carril1.y);
			break;
		case 4:
			pos = new Vector2((carril4.x+carril5.x)/2,carril1.y);
			break;
		}
		return pos;
	}
	public void draw(SpriteBatch batch) {
		Texture text = this.code.manager.get("carril.jpg",Texture.class);
		Sprite barrera1 = new Sprite(text,29,1079);
		Sprite barrera2 = new Sprite(text,29,1079);
		Sprite barrera3 = new Sprite(text,29,1079);
		Sprite barrera4 = new Sprite(text,29,1079);
		Sprite barrera5 = new Sprite(text,29,1079);
		
		
		barrera1.setScale(0.1f);
		barrera2.setScale(0.1f);
		barrera3.setScale(0.1f);
		barrera4.setScale(0.1f);
		barrera5.setScale(0.1f);
		
		
		barrera1.setCenter(carril1.x, carril1.y);
		barrera2.setCenter(carril2.x, carril2.y);
		barrera3.setCenter(carril3.x, carril3.y);
		barrera4.setCenter(carril4.x, carril4.y);
		barrera5.setCenter(carril5.x, carril5.y);
		
		
		barrera1.draw(batch);
		barrera2.draw(batch);
		barrera3.draw(batch);
		barrera4.draw(batch);
		barrera5.draw(batch);
	}
}
