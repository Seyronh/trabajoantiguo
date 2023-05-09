package com.mygdx.code;
import java.util.ArrayList;

import com.badlogic.gdx.ai.steer.behaviors.CollisionAvoidance;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;


public class IA {
	int dificultad;
	public IA(int dificultad){
		this.dificultad = dificultad;
	}
	public float getAccion(Vector2 posicion,float angulo,ArrayList<Body> obstaculos) {
		float accion = -1f;
		switch (this.dificultad) {
			case 1:
				accion = (float) Math.random();
				break;
			case 2:
				accion = (float) Math.random();
				break;
		} 
		return accion;
	}
}
