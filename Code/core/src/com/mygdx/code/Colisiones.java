package com.mygdx.code;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class Colisiones implements ContactListener{
	
	
	
	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		System.out.println("si");
		 Body Barco = contact.getFixtureA().getBody();
		 Body PowerUp = contact.getFixtureB().getBody();
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		System.out.println("no");
        Body Barco = contact.getFixtureA().getBody();
        Body PowerUp = contact.getFixtureB().getBody();
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
