package com.mygdx.code;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.Gdx;
public class Barco {
	private float DegToRad = (float) Math.PI/180;
	private Vector2 velocidad;
	public Vector2 posicion;
	public float vida;
	public float cansancio = 100;
	public float angulo = 90;
	private TipoBarco elegido;
	private float frenadanatural = -1;
	
	public Barco(TipoBarco elegido,Vector2 posicion) {
		this.elegido = elegido;
		this.vida = this.elegido.vidamax;
		this.posicion = posicion;
		this.velocidad = new Vector2();
	}
	public void girarDerecha() {
		this.velocidad.rotateDeg(-this.elegido.movilidad*(this.cansancio/100)*Gdx.graphics.getDeltaTime());
		this.angulo -= this.elegido.movilidad*(this.cansancio/100)*Gdx.graphics.getDeltaTime();
	}
	public void girarIzquierda() {
		this.velocidad.rotateDeg(this.elegido.movilidad*(this.cansancio/100)*Gdx.graphics.getDeltaTime());
		this.angulo += this.elegido.movilidad*(this.cansancio/100)*Gdx.graphics.getDeltaTime();
	}
	public void acelerar() {
		Vector2 acel = new Vector2();
		acel.set(this.elegido.aceleracion*(this.cansancio/100)*MathUtils.cos(this.angulo*this.DegToRad)*Gdx.graphics.getDeltaTime(), this.elegido.aceleracion*(this.cansancio/100)*MathUtils.sin(this.angulo*this.DegToRad)*Gdx.graphics.getDeltaTime());
		this.velocidad.add(acel);
		if(this.velocidad.x > this.elegido.velocidadmax) {
			this.velocidad.x = this.elegido.velocidadmax;
		}
		if(this.velocidad.y > this.elegido.velocidadmax) {
			this.velocidad.y = this.elegido.velocidadmax;
		}
	}
	public void frenar(boolean natural) {
		Vector2 acel = new Vector2();
		if(natural) {
			acel.set(this.frenadanatural*Math.abs(MathUtils.cos(this.angulo*this.DegToRad))*Gdx.graphics.getDeltaTime(), this.frenadanatural*Math.abs(MathUtils.sin(this.angulo*this.DegToRad))*Gdx.graphics.getDeltaTime());
			if(this.velocidad.x < 0) {
				acel.x = -acel.x;
			}
			if(this.velocidad.y < 0) {
				acel.y = -acel.y;
			}
			this.velocidad.add(acel);
		} else {
			acel.set(-this.elegido.aceleracion*(this.cansancio/100)*MathUtils.cos(this.angulo*this.DegToRad)*Gdx.graphics.getDeltaTime(), -this.elegido.aceleracion*(this.cansancio/100)*MathUtils.sin(this.angulo*this.DegToRad)*Gdx.graphics.getDeltaTime());
			this.velocidad.add(acel);
			if(this.velocidad.x < -this.elegido.velocidadmax) {
				this.velocidad.x = -this.elegido.velocidadmax;
			}
			if(this.velocidad.y < -this.elegido.velocidadmax) {
				this.velocidad.y = -this.elegido.velocidadmax;
			}
		}
	}
	public void actualizarposicion() {
		this.posicion.add(velocidad);
	}
}
