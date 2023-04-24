package com.mygdx.code;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Power_Up {

	public Body powerUp;
	private Vector2 posicion;
	
	public Power_Up(Body powerUp, Vector2 posicion) {
		this.powerUp=powerUp;
		this.posicion = new Vector2();
	}
	
	public void setPosicion() {
		posicion = new Vector2(50,50);
	}
	

	
}
