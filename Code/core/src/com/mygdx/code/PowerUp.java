package com.mygdx.code;

public class PowerUp {
	
	public float aceleracion = 0f;
	public float curacion = 0f;
	public float movilidad = 0f;
	public boolean disparo = false;
	public float cansancio = 0f;
	
	public PowerUp(float acel, float curacion, float movilidad, boolean bala, float cansancio) {
		this.aceleracion=acel;
		this.curacion=curacion;
		this.movilidad=movilidad;
		this.disparo=bala;
		this.cansancio=cansancio;
	}
	
}
