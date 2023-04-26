package com.mygdx.code;

public class Power_Up {
	
	public float PUaceleracion = 0f;
	public float PUcuracion = 0f;
	public float PUmovilidad = 0f;
	public boolean PUdisparo = false;
	public float PUcansancio = 0f;
	
	public Power_Up(float acel, float curacion, float movilidad, boolean bala, float cansancio) {
		this.PUaceleracion=acel;
		this.PUcuracion=curacion;
		this.PUmovilidad=movilidad;
		this.PUdisparo=bala;
		this.PUcansancio=cansancio;
	}
	
}
