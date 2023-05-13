package com.mygdx.code;



import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PantallaPartida implements Screen {
	static final float relation = (Gdx.graphics.getWidth()/Gdx.graphics.getHeight())+15;
	static final float STEP_TIME = 1f/60f;
	float accumulator = 0;
	private int ids=0;
	private float tiempo = 0;
	World fisicas;
	Barco boat;
	TipoBarco elegido;
	OrthographicCamera camara;
	Box2DDebugRenderer debugRenderer;
	Code code;
	BackgroundPartida background;
	ArrayList<Body> borrar = new ArrayList<Body>();
	
	
	/////////
	
	
	private int commandnum;

	float anchoPantalla = Gdx.graphics.getWidth();
	float altoPantalla = Gdx.graphics.getHeight();
	Stage stage = new Stage();
	
	public enum State{
		
		RUNNING,
		PAUSE
		
	}
	
	private State state;
	
	
	public PantallaPartida(Code code) {
		this.code = code;
		
		/////
		state = State.RUNNING;
		commandnum = 0;
	}

	private Body crearCuerpo(Vector2 posicion, BodyType tipo, float densidad, float friccion, float rebote, boolean sensor,Vector2 tamanio) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = tipo;
		bodyDef.position.set(posicion);
		Body cuerpo = fisicas.createBody(bodyDef);
		FixtureDef fixtureDef = new FixtureDef();
		PolygonShape poly = new PolygonShape();
		poly.setAsBox(tamanio.x/relation, tamanio.y/relation);
		fixtureDef.shape = poly;
		fixtureDef.density = densidad;
		fixtureDef.friction = friccion;
		fixtureDef.restitution = rebote;
		fixtureDef.isSensor = sensor;
		cuerpo.createFixture(fixtureDef);
		poly.dispose();
		return cuerpo;
	}
	@Override
	public void show() {
		camara = new OrthographicCamera(Gdx.graphics.getWidth()/relation,Gdx.graphics.getHeight()/relation);  
		background = new BackgroundPartida(relation,camara, this.code);
		Box2D.init();
		debugRenderer = new Box2DDebugRenderer();
		fisicas = new World(new Vector2(0, 0), true);
		fisicas.setContactListener(new Colisiones(borrar));
		Body barco = crearCuerpo(new Vector2(0,0),BodyType.DynamicBody,0.2f,1f,0.6f,false,new Vector2(20,85)); 
		Body powerup = crearCuerpo(new Vector2(0,20),BodyType.StaticBody,0.01f,0.01f,0.5f,true,new Vector2(20,20));
		Body powerup2 = crearCuerpo(new Vector2(20,20),BodyType.StaticBody,0.01f,0.01f,0.5f,true,new Vector2(20,20));
		boat = new Barco(new TipoBarco(20f,20f,"Neutro",10f,100f),barco);
		Sprite barquito = new Sprite(this.code.manager.get("barcoNormal.png",Texture.class),1024,1024);
		barquito.setScale(0.20f/relation);
		barco.setUserData(new UserData(barquito,ids,boat));
		ids++;
		Sprite power = new Sprite(this.code.manager.get("PowerUp.png",Texture.class),1024,1024);
		power.setScale(0.05f/relation);
		powerup.setUserData(new UserData(power,ids,new PowerUp(20f, 20f, 0f, false, 0f)));
		ids++;
		powerup2.setUserData(new UserData(power,ids,new PowerUp(20f, 20f, 0f, false, 0f)));
		ids++;
	}

	@Override
	public void render(float delta) {
		
		switch (state) {
		case RUNNING:
			

			if(boat.aplicado) {
				boat.tiempo += 100*delta;
				if(boat.tiempo > 1000) {
					boat.tiempo = 0;
					boat.aplicado = false;
					boat.resetStats();
				}
			}
			
		
		ScreenUtils.clear(1, 1, 1, 1);
		if(Gdx.input.isKeyPressed(code.moverIzquierda)) {
			boat.girarIzquierda();
		}
		if(Gdx.input.isKeyPressed(code.moverDerecha)) {
			boat.girarDerecha();
		}
		if(Gdx.input.isKeyPressed(code.moverArriba)) {
			boat.acelerar();
		}
		if(Gdx.input.isKeyPressed(code.frenar)) {
			boat.frenar();
		}
		if(!(Gdx.input.isKeyPressed(code.moverIzquierda)) && 
		   !(Gdx.input.isKeyPressed(code.moverDerecha)) &&
		   !(Gdx.input.isKeyPressed(code.moverArriba)) &&
		   !(Gdx.input.isKeyPressed(code.frenar))){
			
			boat.cansancio += 0.16666f * delta;
		}


			if(Gdx.input.isKeyPressed(Keys.SPACE)) {
				PowerUp poder = boat.usarPowerUp();
				if(poder != null) {
					boat.aplicado = true;
					boat.elegido.aceleracion += poder.aceleracion;
					boat.elegido.movilidad += poder.movilidad;
					boat.vida += poder.curacion;
					if(boat.vida > boat.elegido.vidamax) {
						boat.vida = boat.elegido.vidamax;
					}
					boat.cansancio += poder.cansancio;
					if(boat.cansancio > 100) {
						boat.cansancio = 100;
					}
					boat.poder = null;
				}
			}
			if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				state = State.PAUSE;
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
		        UserData data = (UserData) body.getUserData();
		        sprite = (Sprite) data.foto;
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
		    for(int i = 0;i<this.borrar.size();i++) {
		    	fisicas.destroyBody(this.borrar.get(i));
		    	if(i == this.borrar.size()-1) {
		    		this.borrar = new ArrayList<Body>();
		    	}
		    }
			
			
			break;
		case PAUSE:
			
			//Aqui ya las cosas
			
			
			stage.getBatch().begin();
			
			stage.getBatch().draw(code.manager.get("Menu2.png", Texture.class), anchoPantalla*36/100, altoPantalla*21/100, anchoPantalla*30/100, altoPantalla*60/100);
			
			stage.getBatch().draw(code.manager.get("Menus/Combo.png", Texture.class), anchoPantalla*40/100, altoPantalla*37/100, anchoPantalla*20/100, altoPantalla*30/100);
			
			stage.getBatch().end();
			
			
			
			 if(Gdx.input.isKeyPressed(Keys.ENTER)) {
		    		
		    	state = State.RUNNING;
		    		
		    	}
			  if(Gdx.input.isKeyPressed(Keys.M)) {
		    		stage.dispose();
				  code.setScreen(new MainMenuScreen(code));
		    		
		     }
			  if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
					
					System.exit(0);
				}
			
			

			
			break;
		
		

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
