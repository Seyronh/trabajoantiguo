package com.mygdx.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import java.util.ArrayList;

public class Colisiones implements ContactListener{
	ArrayList<Body> borrar = new ArrayList<Body>();
	public Colisiones(ArrayList<Body> borrar) {
		this.borrar = borrar;
	}
	
	@Override
	public void beginContact(Contact contact) {
		Body Cuerpo1 = contact.getFixtureA().getBody();
		Body Cuerpo2 = contact.getFixtureB().getBody();
		UserData Data1 = (UserData) Cuerpo1.getUserData();
		UserData Data2 = (UserData) Cuerpo2.getUserData();
		if((Data1.tipo == 1 && Data2.tipo == 2)||(Data2.tipo == 1 && Data1.tipo == 2)) {
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
