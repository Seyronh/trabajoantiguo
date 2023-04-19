package com.mygdx.code;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Barco {
	public float vida;
	public float cansancio = 100;
	private TipoBarco elegido;
	private Body body;
	
	public Barco(TipoBarco elegido,Body body) {
		this.elegido = elegido;
		this.vida = this.elegido.vidamax;
		this.body = body;
	}
	public void girarDerecha() {
		Vector2 pos = this.body.getPosition();
		if(this.body.getAngularVelocity() < this.elegido.velocidadmax) {
			this.body.applyTorque(-this.elegido.movilidad*(this.cansancio/100), true);
		}
	}
	public void girarIzquierda() {
		Vector2 pos = this.body.getPosition();
		if(this.body.getAngularVelocity() < this.elegido.velocidadmax) {
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
			float x = (float)Math.sin(body.getAngle());
			float y = (float)Math.cos(body.getAngle());
			this.body.applyForceToCenter(-x*this.elegido.aceleracion*(this.cansancio/100), -y*this.elegido.aceleracion*(this.cansancio/100), false);
		}
	}
}
