package com.mygdx.code;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Barco {
	public float vida;
	public float cansancio = 100;
	public TipoBarco elegido;
	public TipoBarco elegidoOriginal;
	public Body body;
	public PowerUp poder = null;
	public float tiempo = 0;
	public boolean aplicado = false;
	
	public Barco(TipoBarco elegido,Body body) {
		this.elegidoOriginal = new TipoBarco(elegido.aceleracion,elegido.movilidad,elegido.barco,elegido.vidamax,elegido.velocidadmax);
		this.elegido = elegido;
		this.vida = this.elegido.vidamax;
		this.body = body;
	}
	public void girarDerecha() {
		Vector2 pos = this.body.getPosition();
		if(Math.abs(this.body.getAngularVelocity()) < this.elegido.velocidadmax) {
			this.body.applyTorque(-this.elegido.movilidad*(this.cansancio/100), true);
		}
	}
	public void girarIzquierda() {
		Vector2 pos = this.body.getPosition();
		if(Math.abs(this.body.getAngularVelocity()) < this.elegido.velocidadmax) {
			this.body.applyTorque(this.elegido.movilidad*(this.cansancio/100), true);
		}
	}
	public void acelerar() {
		Vector2 vel = this.body.getLinearVelocity();
		if(vel.x+vel.y < this.elegido.velocidadmax) {
			float x = (float)Math.sin(body.getAngle()-Math.PI);
			float y = (float)Math.cos(body.getAngle());
			this.body.applyForceToCenter(x*this.elegido.aceleracion*(this.cansancio/100), y*this.elegido.aceleracion*(this.cansancio/100), false);
		}
	}
	public void frenar() {
		Vector2 vel = this.body.getLinearVelocity();
		if(vel.x+vel.y<this.elegido.velocidadmax) {
			float x = (float)Math.sin(body.getAngle()-Math.PI);
			float y = (float)Math.cos(body.getAngle());
			this.body.applyForceToCenter(-x*this.elegido.aceleracion*(this.cansancio/100), -y*this.elegido.aceleracion*(this.cansancio/100), false);
		}
	}
	public void guardarPowerUp(PowerUp poder) {
		this.poder = poder;
	}
	public PowerUp usarPowerUp() {
		return this.poder;
	}
	public void resetStats() {
		this.elegido = new TipoBarco(elegidoOriginal.aceleracion,elegidoOriginal.movilidad,elegidoOriginal.barco,elegidoOriginal.vidamax,elegidoOriginal.velocidadmax);
	}
}
