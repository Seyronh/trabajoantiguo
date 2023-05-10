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
	public IA(int dificultad){
		this.dificultad = dificultad;
	}
	public float getAccion(Vector2 posicion,float angulo,ArrayList<Body> obstaculos,Vector2 posicionmediobarreras,Vector2 meta) {
		float accion = -1f;
		ArrayList<Integer> actions = new ArrayList<Integer>();
		ArrayList<Integer> pesos = new ArrayList<Integer>();
		actions.add(0);
		actions.add(1);
		actions.add(2);
		actions.add(3);
		int piz = 0;
		int pde = 0;
		int pa = 1;
		int pf = 1;
		switch (this.dificultad) {
			case 1:
				piz = 0;
				pde = 0;
				pf = 0;
				break;
			case 2:
				float dist = posicionmediobarreras.x-posicion.x;
				float dista = (float) (Math.toDegrees(angulo));
				float n = (float) Math.floor(dista/180);
				n = (float) Math.abs(n%2);
				if(n==0f) {
					piz += 2*Math.abs(Math.sin(dista));
				} else if(n==1f){
					pde += 2*Math.abs(Math.sin(dista));
				} else {
					piz = 0;
					pde = 0;
				}
				break;
		} 
		pesos.add(pde);
		pesos.add(piz);
		pesos.add(pa);
		pesos.add(pf);
		int a = IA.getWeightedRandomString(actions, pesos);
		accion = (float) a;
		return accion;
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