package com.mygdx.code;



import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PantallaPartida implements Screen {
	static final float relation = (Gdx.graphics.getWidth()/Gdx.graphics.getHeight())+15;
	static final float STEP_TIME = 1f/60f;
	float accumulator = 0;
	World fisicas;
	Barco boat;
	TipoBarco elegido;
	OrthographicCamera camara;
	Box2DDebugRenderer debugRenderer;
	Code code;
	BackgroundPartida background;
	public PantallaPartida(Code code) {
		this.code = code;
	}
	private Body crearCuerpo(Vector2 posicion, BodyType tipo, float densidad, float friccion, float rebote, boolean sensor,Sprite foto,float escala,Vector2 tamano) {
		foto.setScale(escala/relation);
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = tipo;
		bodyDef.position.set(posicion);
		Body cuerpo = fisicas.createBody(bodyDef);
		FixtureDef fixtureDef = new FixtureDef();
		PolygonShape poly = new PolygonShape();
		poly.setAsBox(tamano.x/relation, tamano.y/relation);
		fixtureDef.shape = poly;
		fixtureDef.density = densidad;
		fixtureDef.friction = friccion;
		fixtureDef.restitution = rebote;
		fixtureDef.isSensor = sensor;
		cuerpo.createFixture(fixtureDef);
		poly.dispose();
		cuerpo.setUserData(foto);
		return cuerpo;
	}

	@Override
	public void show() {
		camara = new OrthographicCamera(Gdx.graphics.getWidth()/relation,Gdx.graphics.getHeight()/relation);  
		background = new BackgroundPartida(relation,camara);
		Box2D.init();
		debugRenderer = new Box2DDebugRenderer();
		fisicas = new World(new Vector2(0, 0), true);
		Body barco = crearCuerpo(new Vector2(0,0),BodyType.DynamicBody,0.5f,0.4f,0.6f,false,new Sprite(new Texture("barquito.png"),294,886),0.20f,new Vector2(20,85)); 
		Body powerup = crearCuerpo(new Vector2(0,20),BodyType.StaticBody,0.01f,0.01f,0.5f,true,new Sprite(new Texture("powerUp.png"),1024,1024),0.05f,new Vector2(40,40));
		boat = new Barco(new TipoBarco(20f,20f,"Neutro",10f,100f),barco);
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
		Vector2 pos2 = boat.body.getPosition();
		camara.position.set(new Vector3(pos2.x,pos2.y,0));
		camara.update();
		debugRenderer.render(fisicas, camara.combined);
		Array<Body> lista = new Array<Body>();
		fisicas.getBodies(lista);
		Iterator<Body> iter = lista.iterator();
	    Body body;
	    Sprite sprite;
	    background.animate(delta);
	    this.code.batch.setProjectionMatrix(camara.combined);
	    this.code.batch.begin();
	    background.draw(this.code.batch);
	    while (iter.hasNext()) {
	        body = iter.next();
	        sprite = (Sprite) body.getUserData();
	        Vector2 pos = body.getPosition();
	        sprite.setRotation((float)Math.toDegrees(body.getAngle()));
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
