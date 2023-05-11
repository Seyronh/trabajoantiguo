package com.mygdx.code;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.steer.behaviors.CollisionAvoidance;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import java.util.Random;

public class IA {
	public static Integer getWeightedRandomString(ArrayList<Integer> acciones, ArrayList<Integer> weights) {
        int totalWeight = 0;
        for (int weight : weights) {
            totalWeight += weight;
        }

        Random rand = new Random();
        int randNum = rand.nextInt(totalWeight);

        int weightSum = 0;
        for (int i = 0; i < acciones.size(); i++) {
            weightSum += weights.get(i);
            if (randNum < weightSum) {
                return acciones.get(i);
            }
        }
        
        // Return null if the input ArrayLists are empty or if there's an error
        return null;
    }
	int dificultad;
	TipoBarco barco;
	float cooldown = 0f;
	public IA(int dificultad,TipoBarco b){
		this.dificultad = dificultad;
		this.barco = b;
	}
	public float[] getAccion(Vector2 posicion,float angulo,ArrayList<Body> obstaculos,Vector2 posicionmediobarreras,Vector2 meta,float velangular) {
		cooldown -= Gdx.graphics.getDeltaTime();
		if(cooldown <0f) {
			cooldown = 0f;
		}
		float accion = -1f;
		float accion2 = -1f;
		float fuerza = 1f;
		/*ACCION
		 * -1 - no hacer nada
		 * 0 - frenar
		 * 1 - acelerar
		*/
		/*ACCION2
		 * -1 - no hacer nada
		 * 0 - derecha
		 * 1 - izquierda
		*/
		switch (this.dificultad) {
		case 1:
			accion = 1;
			break;
		case 2:
			float disp = (float) Math.abs(angulo*velangular);
			if(Math.abs(angulo)>0f && cooldown>0) {
				if(angulo>0) {
					accion2 = 0;
				} else if(angulo<0){
					accion2 = 1;
				}
				cooldown = 0.01f*(float) Math.abs(Math.cos(angulo+0.01));
			}
			if(Math.abs(angulo)<0.5) {
				accion = 1;
			}
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