package com.mygdx.code;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;

public class Colisiones {
	private Object userDataBarco;
	
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		final Fixture Barco = contact.getFixtureA();
		final Fixture powerUp  = contact.getFixtureB();
		
		if (userDataBarco.getValue().equals("jugador") && powerUp.getBody().getUserData().equals("PowerUp")){            
           
	}

	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		final Fixture Barco = contact.getFixtureA();
        final Fixture powerUp = contact.getFixtureB();
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
