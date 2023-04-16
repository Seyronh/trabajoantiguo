package com.mygdx.code;

import com.badlogic.gdx.math.*;

public class Barco {
	private Vector2 velocidad;
	private Vector2 posicion;
	private float vida;
	private float cansancio = 100;
	private TipoBarco elegido;
	private float frenadanatural = -1;
	
	public Barco(TipoBarco elegido) {
		this.elegido = elegido;
		this.vida = this.elegido.vidamax;
	}
	public void girarDerecha() {
		this.velocidad.rotateDeg(this.elegido.movilidad*(this.cansancio/100));
	}
	public void girarIzquierda() {
		this.velocidad.rotateDeg(-this.elegido.movilidad*(this.cansancio/100));
	}
	public void acelerar() {
		Vector2 acel = new Vector2();
		acel.set(this.elegido.aceleracion*(this.cansancio/100)*MathUtils.asin(this.velocidad.angleRad()), this.elegido.aceleracion*(this.cansancio/100)*MathUtils.acos(this.velocidad.angleRad()));
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
			acel.set(this.frenadanatural*MathUtils.asin(this.velocidad.angleRad()), this.frenadanatural*MathUtils.acos(this.velocidad.angleRad()));
			this.velocidad.add(acel);
			if(this.velocidad.x < -this.elegido.velocidadmax) {
				this.velocidad.x = -this.elegido.velocidadmax;
			}
			if(this.velocidad.y < -this.elegido.velocidadmax) {
				this.velocidad.y = -this.elegido.velocidadmax;
			}
		} else {
			acel.set(-this.elegido.aceleracion*(this.cansancio/100)*MathUtils.asin(this.velocidad.angleRad()), -this.elegido.aceleracion*(this.cansancio/100)*MathUtils.acos(this.velocidad.angleRad()));
			this.velocidad.add(acel);
			if(this.velocidad.x < this.elegido.velocidadmax) {
				this.velocidad.x = -this.elegido.velocidadmax;
			}
			if(this.velocidad.y < this.elegido.velocidadmax) {
				this.velocidad.y = -this.elegido.velocidadmax;
			}
		}
	}
}
