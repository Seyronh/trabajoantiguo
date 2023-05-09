package com.mygdx.code;



import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PantallaPartida implements Screen {
	static final float relation = (Gdx.graphics.getWidth()/Gdx.graphics.getHeight())+15;
	static final float STEP_TIME = 1f/60f;
	static int numObstaculos = 20;
	float accumulator = 0;
	private int ids=0;
	private float tiempo = 0;
	World fisicas;
	Barco jugador;
	ArrayList<Barco> BarcosIA = new ArrayList<Barco>();
	ArrayList<IA> IAs = new ArrayList<IA>();
	ArrayList<Body> obstaculos = new ArrayList<Body>();
	TipoBarco elegido;
	OrthographicCamera camara;
	Box2DDebugRenderer debugRenderer;
	Code code;
	BackgroundPartida background;
	ArrayList<Body> borrar = new ArrayList<Body>();
	public PantallaPartida(Code code) {
		this.code = code;
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
		/*
		 * CREACION FISICAS
		 */
		camara = new OrthographicCamera(Gdx.graphics.getWidth()/relation,Gdx.graphics.getHeight()/relation);  
		background = new BackgroundPartida(relation,camara, this.code);
		Box2D.init();
		debugRenderer = new Box2DDebugRenderer();
		fisicas = new World(new Vector2(0, 0), true);
		fisicas.setContactListener(new Colisiones(borrar,this.code.dificultad));
		/*
		 * FIN CREACION FISICAS
		 */
		/*
		 * CREACION JUGADOR Y IA
		 */
		Sprite barquito = new Sprite(this.code.manager.get("barquito.png",Texture.class),294,886);
		barquito.setScale(0.20f/relation);
		
		Body barcoj = crearCuerpo(new Vector2(0,0),BodyType.DynamicBody,0.2f,1f,0.6f,false,new Vector2(20,85));
		jugador = new Barco(new TipoBarco(5f,5f,"Neutro",5f,5f),barcoj);
		barcoj.setUserData(new UserData(barquito,ids,jugador));
		ids++;
		
		for(int i = 0;i<3;i++) {
			Body barcoia = crearCuerpo(new Vector2(10+10*i,0),BodyType.DynamicBody,0.2f,1f,0.6f,false,new Vector2(20,85));
			Barco bia = new Barco(new TipoBarco(5f,5f,"Neutro",5f,5f),barcoia);
			IA ia = new IA(this.code.dificultad);
			barcoia.setUserData(new UserData(barquito,ids,bia));
			BarcosIA.add(bia);
			IAs.add(ia);
			ids++;
		}
		
		/*
		 * FIN CREACION JUGADOR Y IA
		 */
		/*
		 * CREACION OBSTACULOS INICIALES
		 */
		for(int i = 0;i<PantallaPartida.numObstaculos;i++) {
			Vector3 posc = this.camara.position;
			float posx = (float) Math.random()*Gdx.graphics.getWidth()/relation;
			float posy = (float) Math.random()*Gdx.graphics.getHeight()/relation;
			posy += Gdx.graphics.getHeight()/relation;
			if(Math.random()<0.5f) {
				posx = -posx;
			}
			Obstaculo obstaculo = new Obstaculo((int)Math.floor(Math.random()*10f));
			Sprite obst = new Sprite(this.code.manager.get("roca.png",Texture.class),4000,4000);
			obst.setScale(0.003f*obstaculo.tamanio/relation);
			Body obstaculob = crearCuerpo(new Vector2(posc.x+posx,posc.y+posy),BodyType.StaticBody,0.2f,1f,0.6f,false,new Vector2(obstaculo.tamanio,obstaculo.tamanio));
			obstaculob.setUserData(new UserData(obst,ids,obstaculo));
			obstaculos.add(obstaculob);
			ids++;
		}
		
		/*
		 * FINAL CREACION OBSTACULOS INICIALES
		 */
		
		
		/*
		Body powerup = crearCuerpo(new Vector2(0,20),BodyType.StaticBody,0.01f,0.01f,0.5f,true,new Vector2(20,20));
		Body powerup2 = crearCuerpo(new Vector2(20,20),BodyType.StaticBody,0.01f,0.01f,0.5f,true,new Vector2(20,20));
		*/
		/*
		Sprite power = new Sprite(this.code.manager.get("PowerUp.png",Texture.class),1024,1024);
		power.setScale(0.05f/relation);
		powerup.setUserData(new UserData(power,ids,new PowerUp(20f, 20f, 0f, false, 0f)));
		ids++;
		powerup2.setUserData(new UserData(power,ids,new PowerUp(20f, 20f, 0f, false, 0f)));
		ids++;
		*/
	}

	@Override
	public void render(float delta) {
		if(jugador.aplicado) {
			jugador.tiempo += 100*delta;
			if(jugador.tiempo > 1000) {
				jugador.tiempo = 0;
				jugador.aplicado = false;
				jugador.resetStats();
			}
		}
		ScreenUtils.clear(1, 1, 1, 1);
		if(Gdx.input.isKeyPressed(code.moverIzquierda)) {
			jugador.girarIzquierda();
		}
		if(Gdx.input.isKeyPressed(code.moverDerecha)) {
			jugador.girarDerecha();
		}
		if(Gdx.input.isKeyPressed(code.moverArriba)) {
			jugador.acelerar();
		}
		if(Gdx.input.isKeyPressed(code.frenar)) {
			jugador.frenar();
		}
		if(!(Gdx.input.isKeyPressed(code.moverIzquierda)) && 
		   !(Gdx.input.isKeyPressed(code.moverDerecha)) &&
		   !(Gdx.input.isKeyPressed(code.moverArriba)) &&
		   !(Gdx.input.isKeyPressed(code.frenar))){
			
			jugador.cansancio += 0.16666f * delta;
		}
		for(int i = 0;i<this.BarcosIA.size();i++) {
			IA ia = this.IAs.get(i);
			Barco bia = this.BarcosIA.get(i);
			float accion = ia.getAccion(bia.body.getPosition(),bia.body.getAngle(),obstaculos);
			if(accion<0.1f) {
				bia.girarDerecha();
			}
			if(accion>0.1f&&accion<0.2f) {
				bia.girarIzquierda();
			}
			if(accion>=0.2f&&accion<=0.7f) {
				bia.acelerar();
			}
			if(accion>0.7f) {
				bia.frenar();
			}
		}
		if(Gdx.input.isKeyPressed(Keys.SPACE)) {
			PowerUp poder = jugador.usarPowerUp();
			if(poder != null) {
				jugador.aplicado = true;
				jugador.elegido.aceleracion += poder.aceleracion;
				jugador.elegido.movilidad += poder.movilidad;
				jugador.vida += poder.curacion;
				if(jugador.vida > jugador.elegido.vidamax) {
					jugador.vida = jugador.elegido.vidamax;
				}
				jugador.cansancio += poder.cansancio;
				if(jugador.cansancio > 100) {
					jugador.cansancio = 100;
				}
				jugador.poder = null;
			}
		}
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			code.setScreen(new MainMenuScreen(code));
		}
		
		Vector2 pos2 = jugador.body.getPosition();
		camara.position.set(new Vector3(pos2.x,pos2.y,0));
		camara.update();
		BitmapFont fuente = new BitmapFont();
		fuente.setColor(Color.GOLDENROD);
		fuente.getData().setScale(5.0f/relation, 5.0f/relation);
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
	    fuente.draw(this.code.batch,"Vida:"+jugador.vida,camara.position.x,camara.position.y);
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
	    		this.borrar.clear();
	    	}
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
