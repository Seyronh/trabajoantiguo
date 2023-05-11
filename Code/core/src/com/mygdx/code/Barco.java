package com.mygdx.code;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
//import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Barco {
	static float basemovilidad = 20f;
	static float baseaceleracion = 10f;
	static float basevidamax = 100f;
	static float basevelocidadmax = 100f;
	static float basecansamiento = 0.5f;
	public float vida;
	public float cansancio = 100;
	public TipoBarco elegido;
	public TipoBarco elegidoOriginal;
	public Body body;
	public PowerUp poder = null;
	public float tiempo = 0;
	public boolean aplicado = false;
	public boolean ia = false;
	public float fuerzaia = 1f;
	
	public Barco(TipoBarco elegido,Body body) {
		this.elegidoOriginal = new TipoBarco(elegido.aceleracion,elegido.movilidad,elegido.barco,elegido.vidamax,elegido.velocidadmax);
		this.elegido = elegido;
		this.vida = Barco.basevidamax*this.elegido.vidamax/5;
		this.body = body;
	}
	public Barco(TipoBarco elegido,Body body,boolean ia) {
		this.ia = ia;
		this.elegidoOriginal = new TipoBarco(elegido.aceleracion,elegido.movilidad,elegido.barco,elegido.vidamax,elegido.velocidadmax);
		this.elegido = elegido;
		this.vida = Barco.basevidamax*this.elegido.vidamax/5;
		this.body = body;
	}
	public boolean recibirDanio(int danio) {
		this.vida -= danio;
		boolean muerto = false;
		if(this.vida <= 0.0f) {
			this.vida = 0.0f;
			muerto = true;
		}
		return muerto;
	}
	public void girarDerecha() {
		//Vector2 pos = this.body.getPosition();
		if(Math.abs(this.body.getAngularVelocity()) < Barco.basevelocidadmax*this.elegido.velocidadmax/5) {
			if(this.ia) {
				this.body.applyTorque(-(Barco.basemovilidad*this.elegido.movilidad/5)*(this.cansancio/100)*this.fuerzaia, true);
			} else {
				this.body.applyTorque(-(Barco.basemovilidad*this.elegido.movilidad/5)*(this.cansancio/100)*this.fuerzaia, true);
			}
		}
		this.cansancio -= Barco.basecansamiento* Gdx.graphics.getDeltaTime();
		if(this.cansancio < 0) this.cansancio = 0;
	}
	public void girarIzquierda() {
		//Vector2 pos = this.body.getPosition();
		if(Math.abs(this.body.getAngularVelocity()) < Barco.basevelocidadmax*this.elegido.velocidadmax/5) {
			if(this.ia) {
				this.body.applyTorque((Barco.basemovilidad*this.elegido.movilidad/5)*(this.cansancio/100)*this.fuerzaia, true);
			} else {
				this.body.applyTorque((Barco.basemovilidad*this.elegido.movilidad/5)*(this.cansancio/100)*this.fuerzaia, true);
			}
		}
		this.cansancio -= Barco.basecansamiento * Gdx.graphics.getDeltaTime();
		if(this.cansancio < 0) this.cansancio = 0;
	}
	public void acelerar() {
		Vector2 vel = this.body.getLinearVelocity();
		if(vel.x+vel.y < Barco.basevelocidadmax*this.elegido.velocidadmax/5) {
			float x = (float)Math.sin(body.getAngle()-Math.PI);
			float y = (float)Math.cos(body.getAngle());
			this.body.applyForceToCenter(x*(Barco.baseaceleracion*this.elegido.aceleracion/5)*(this.cansancio/100), y*(Barco.baseaceleracion*this.elegido.aceleracion/5)*(this.cansancio/100), false);
		}
		this.cansancio -= Barco.basecansamiento * Gdx.graphics.getDeltaTime();
		if(this.cansancio < 0) this.cansancio = 0;
	}
	public void frenar() {
		Vector2 vel = this.body.getLinearVelocity();
		if(vel.x+vel.y<Barco.basevelocidadmax*this.elegido.velocidadmax/5) {
			float x = (float)Math.sin(body.getAngle()-Math.PI);
			float y = (float)Math.cos(body.getAngle());
			this.body.applyForceToCenter(-x*(Barco.baseaceleracion*this.elegido.aceleracion/5)*(this.cansancio/100), -y*(Barco.baseaceleracion*this.elegido.aceleracion/5)*(this.cansancio/100), false);
		}
		this.cansancio -= Barco.basecansamiento * Gdx.graphics.getDeltaTime();
		if(this.cansancio < 0) this.cansancio = 0;
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
