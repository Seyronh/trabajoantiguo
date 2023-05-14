package com.mygdx.code;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.steer.behaviors.CollisionAvoidance;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import java.util.Random;

public class IA {
	int dificultad;
	TipoBarco barco;
	public IA(int dificultad,TipoBarco b){
		this.dificultad = dificultad;
		this.barco = b;
	}
	public float[] getAccion(Vector2 posicion,float angulo,ArrayList<Body> obstaculos,Vector2 posicionmediobarreras,Vector2 meta,float velangular,Vector2 vellinear) {
		float accion = -1f;
		float accion2 = -1f;
		float fuerza = 1f;
		switch (this.dificultad) {
		case 1:
			accion = 1;
			break;
		default:
			if(Math.abs(velangular)>0) {
				if(velangular>0) {
					accion2 = 0;
				} else if(velangular<0){
					accion2 = 1;
				}
			}
			if(Math.abs(angulo)<0.5) {
				accion = 1;
			}
			if(Math.abs(velangular)<0.1f && Math.abs(angulo)>0.02f) {
				accion2 = 2;
			}
			/*
			if(vellinear.x != 0f) {
				accion2 = 3;
			}
			if(posicion.x != posicionmediobarreras.x) {
				accion2 = 4;
			}
			*/
			break;
	} 
		float[] acciones = {accion,accion2,fuerza};
		return acciones;
	}
}
/*
		if(accion<0.1f) {
		bia.girarDerecha();
		}
		if(accion>0.1f&&accion<0.2f) {
		bia.girarIzquierda();
		}
		if(accion>=0.2f&&accion<=0.7f) {
		bia.acelerar();
		}
		if(accion>0.7f) {
		bia.frenar();
		}
*/