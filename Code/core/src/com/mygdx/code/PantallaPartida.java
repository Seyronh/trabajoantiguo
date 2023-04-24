package com.mygdx.code;



import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PantallaPartida implements Screen {
	static final float relation = 15;
	static final float STEP_TIME = 1f/60f;
	float accumulator = 0;
	World fisicas;
	Sprite barco;
	Barco boat;
	TipoBarco elegido;
	OrthographicCamera camara;
	Box2DDebugRenderer debugRenderer;
	BodyDef bodyDef = new BodyDef();
	BodyDef bodyDef2 = new BodyDef();
	
	Sprite powerUp;
	
	Code code;
	public PantallaPartida(Code code) {
		this.code = code;
	}

	@Override
	public void show() {
		powerUp = new Sprite(new Texture("powerUp.png"),1024,1024);
		camara = new OrthographicCamera(Gdx.graphics.getWidth()/relation,Gdx.graphics.getHeight()/relation);  
		barco = new Sprite(new Texture("barquito.png"),294,886);
		barco.setScale(0.20f/relation);
		powerUp.setScale(0.05f/relation);
		Box2D.init();
		debugRenderer = new Box2DDebugRenderer();
		fisicas = new World(new Vector2(0, 0), true);
		bodyDef.type = BodyType.DynamicBody;
		bodyDef2.type = BodyType.StaticBody;
		
		bodyDef.position.set(0, 0);
		bodyDef2.position.set(0,20);
		
		Body body = fisicas.createBody(bodyDef);
		Body body2 = fisicas.createBody(bodyDef2);
		FixtureDef fixtureDef = new FixtureDef();
		FixtureDef fixtureDef2= new FixtureDef();
		PolygonShape poly = new PolygonShape();
		PolygonShape poly2 = new PolygonShape();
		poly.setAsBox(20/relation, 85/relation);
		fixtureDef.shape = poly;
		fixtureDef.density = 0.5f;
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.6f;
		
		poly2.setAsBox(40/relation, 40/relation);
		fixtureDef2.shape = poly2;
		fixtureDef2.density = 0.01f;
		fixtureDef2.friction = 0.01f;
		fixtureDef2.restitution = 0.5f;
		fixtureDef2.isSensor = true;
		
		body.createFixture(fixtureDef);
		poly.dispose();
		
		body2.createFixture(fixtureDef2);
		poly2.dispose();
				
		body.setUserData(barco);
		body2.setUserData(powerUp);
		boat = new Barco(new TipoBarco(20f,20f,"Neutro",10f,100f),body);
		
		
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(1, 1, 1, 1);
		if(Gdx.input.isKeyPressed(Keys.A)) {
			boat.girarIzquierda();
		}
		if(Gdx.input.isKeyPressed(Keys.D)) {
			boat.girarDerecha();
		}
		if(Gdx.input.isKeyPressed(Keys.W)) {
			boat.acelerar();
		}
		if(Gdx.input.isKeyPressed(Keys.S)) {
			boat.frenar();
		}
		debugRenderer.render(fisicas, camara.combined);
		Array<Body> lista = new Array();
		fisicas.getBodies(lista);
		Iterator<Body> iter = lista.iterator();
	    Body body;
	    Sprite sprite;
	    this.code.batch.setProjectionMatrix(camara.combined);
	    this.code.batch.begin();
	    while (iter.hasNext()) {
	        body = iter.next();
	        sprite = (Sprite) body.getUserData();
	        Vector2 pos = body.getPosition();
	        sprite.setRotation(180f+(float)Math.toDegrees(body.getAngle()));
	        sprite.setCenter(pos.x, pos.y);
	        sprite.draw(this.code.batch);
	    }
	    this.code.batch.end();

	    accumulator += Math.min(delta, 0.25f);

	    if (accumulator >= STEP_TIME) {
	        accumulator -= STEP_TIME;

	        fisicas.step(STEP_TIME, 6, 2);
	    }
	  
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
