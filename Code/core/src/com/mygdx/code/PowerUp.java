package com.mygdx.code;

public class PowerUp {
	
	
	static PowerUp[] predefinidos = {new PowerUp(2,0,0,0),new PowerUp(0,10,0,0),new PowerUp(0,0,2,0),new PowerUp(0,0,0,10)};
	public float aceleracion = 0f;
	public float curacion = 0f;
	public float movilidad = 0f;
	public boolean disparo = false;
	public float cansancio = 0f;
	
	public PowerUp(float acel, float curacion, float movilidad, float cansancio) {
		this.aceleracion=acel;
		this.curacion=curacion;
		this.movilidad=movilidad;
		this.cansancio=cansancio;
	}
	
}
