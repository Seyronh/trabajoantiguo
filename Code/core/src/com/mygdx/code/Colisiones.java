package com.mygdx.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import java.util.ArrayList;

public class Colisiones implements ContactListener{
	static int danioBaseBarcos = 1;
	int dificultad;
	ArrayList<Body> borrar;
	Code code;
	public Colisiones(ArrayList<Body> borrar,int dificultad,Code code) {
		this.code = code;
		this.borrar = borrar;
		this.dificultad = dificultad;
	}
	
	@Override
	public void beginContact(Contact contact) {
		Body Cuerpo1 = contact.getFixtureA().getBody();
		Body Cuerpo2 = contact.getFixtureB().getBody();
		UserData Data1 = (UserData) Cuerpo1.getUserData();
		UserData Data2 = (UserData) Cuerpo2.getUserData();
		if((Data1.tipo == 1 && Data2.tipo == 2)||(Data2.tipo == 1 && Data1.tipo == 2)) { //TOCAMOS POWERUP
			if(Data1.tipo == 1) {
				if(Data1.barco.poder == null) {
					Data1.barco.guardarPowerUp(Data2.poder);
				}
				this.borrar.add(Cuerpo2);
			} else {
				if(Data2.barco.poder == null) {
					Data2.barco.guardarPowerUp(Data1.poder);
				}
				this.borrar.add(Cuerpo1);
			}
		}
		if((Data1.tipo == 1 && Data2.tipo == 1) || (Data2.tipo ==1 && Data1.tipo == 1)) { //CHOQUE DE BARCOS
			boolean muerto = Data1.barco.recibirDanio(Colisiones.danioBaseBarcos*this.dificultad);
			boolean muerto2 = Data2.barco.recibirDanio(Colisiones.danioBaseBarcos*this.dificultad);
			if(muerto) {
				this.borrar.add(Cuerpo1);
			}
			if(muerto2) {
				this.borrar.add(Cuerpo2);
			}
		}
		if((Data1.tipo == 1 && Data2.tipo == 3) || (Data1.tipo == 3 && Data2.tipo == 1)) { //CHOQUE OBSTACULO
			if(Data1.tipo == 1) {
				this.borrar.add(Cuerpo2);
				Vector2 vel = Cuerpo1.getLinearVelocity();
				Vector2 velco = new Vector2(vel.x,vel.y);
				velco.rotateDeg(180);
				velco.scl(1.5f);
				Cuerpo1.applyLinearImpulse(velco, Cuerpo1.getPosition(),true);
				boolean muerto = Data1.barco.recibirDanio(Data2.obstaculo.danio*this.dificultad);
				if(muerto) {
					if(!Data1.barco.ia) {
						this.code.music.stop();
						this.code.music = this.code.manager.get("musica/fuerapartida.ogg");
						this.code.music.setVolume((float)Math.pow(this.code.volumen, 2));
						this.code.music.play();
						this.code.setScreen(new MainMenuScreen(this.code));
					}
					this.borrar.add(Cuerpo1);
				}
			} else {
				this.borrar.add(Cuerpo1);
				Vector2 vel = Cuerpo1.getLinearVelocity();
				Vector2 velco = new Vector2(vel.x,vel.y);
				velco.rotateDeg(180);
				velco.scl(1.5f);
				Cuerpo1.applyLinearImpulse(velco, Cuerpo1.getPosition(),true);
				boolean muerto = Data2.barco.recibirDanio(Data1.obstaculo.danio*this.dificultad);
				if(muerto) {
					if(!Data2.barco.ia) {
						this.code.music.stop();
						this.code.music = this.code.manager.get("musica/fuerapartida.ogg");
						this.code.music.setVolume((float)Math.pow(this.code.volumen, 2));
						this.code.music.play();
						this.code.setScreen(new MainMenuScreen(this.code));
					}
					this.borrar.add(Cuerpo2);
				}
			}
		}
		if((Data1.tipo == 4 && Data2.tipo == 1) || (Data1.tipo == 1 && Data2.tipo==4)) {
			if(Data1.tipo == 4) {
				if(Data2.barco.ia) {
					this.code.terminados++;
				} else {
					this.code.ganadorj = true;
					this.code.ganadas++;
				}
			} else {
				if(Data1.barco.ia) {
					this.code.terminados++;
				} else {
					this.code.ganadorj = true;
					this.code.ganadas++;
				}
			}
		}
	}

	@Override
	public void endContact(Contact contact) {
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}
	
	
}