package com.mygdx.code;

public class TipoBarco {
	public float resistencia;
	public float aceleracion;
	public float movilidad;
	public float vidamax;
	public float velocidadmax;
	public String barco;
	public TipoBarco(float resistencia,float aceleracion,float movilidad,String barco,float vidamax,float velocidadmax) {
		this.aceleracion = aceleracion;
		this.resistencia = resistencia;
		this.movilidad = movilidad;
		this.barco = barco;
		this.vidamax = vidamax;
		this.velocidadmax = velocidadmax;
	}
}
