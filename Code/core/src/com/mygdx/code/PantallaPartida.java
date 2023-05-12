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
	//private float tiempo = 0;
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
	Carriles carriles;
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
		carriles = new Carriles(this.code,camara);
		Box2D.init();
		fisicas = new World(new Vector2(0, 0), true);
		fisicas.setContactListener(new Colisiones(borrar,this.code.dificultad,this.code));
		/*
		 * FIN CREACION FISICAS
		 */
		/*
		 * CREACION JUGADOR Y IA
		 */
		Sprite barquito = new Sprite(this.code.manager.get("barquito.png",Texture.class),294,886);
		barquito.setScale(0.20f/relation);
		
		Body barcoj = crearCuerpo(new Vector2(0,0),BodyType.DynamicBody,0.2f,1f,0.6f,false,new Vector2(25,85));
		jugador = new Barco(new TipoBarco(6f,5f,"Neutro",5f,6f),barcoj);
		barcoj.setUserData(new UserData(barquito,ids,jugador));
		ids++;
		
		for(int i = 0;i<1;i++) {
			Body barcoia = crearCuerpo(new Vector2(10+10*i,0),BodyType.DynamicBody,0.2f,1f,0.6f,false,new Vector2(25,85));
			barcoia.setTransform(carriles.obtenerMedio(i+2), barcoia.getAngle());
			TipoBarco iab = new TipoBarco(5f,5f,"Neutro",5f,5f);
			Barco bia = new Barco(iab,barcoia,true);
			IA ia = new IA(this.code.dificultad,iab);
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
			float posx = (float) (Math.random()*camara.viewportWidth/2)-1;
			float posy = camara.viewportHeight+(float)(Math.random()*camara.viewportHeight);
			posy += Gdx.graphics.getHeight()/relation;
			if(Math.random()<0.5f) {
				posx = -posx;
			}
			Obstaculo obstaculo = new Obstaculo((int)Math.floor(Math.random()*10f));
			Sprite obst = new Sprite(this.code.manager.get("roca.png",Texture.class),4000,4000);
			obst.setScale(0.003f*obstaculo.tamanio/relation);
			Body obstaculob = crearCuerpo(new Vector2(posc.x+posx,posc.y+posy),BodyType.StaticBody,0.2f,1f,0.6f,true,new Vector2(obstaculo.tamanio*3,obstaculo.tamanio*3));
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
		barcoj.setTransform(carriles.obtenerMedio(1), barcoj.getAngle());
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
			float[] accion = ia.getAccion(bia.body.getPosition(),bia.body.getAngle(),obstaculos,carriles.obtenerMedio(i+2),new Vector2(10,10),bia.body.getAngularVelocity(),bia.body.getLinearVelocity());
			if(accion[1] == 0) {
				bia.fuerzaia = accion[2];
				bia.girarDerecha();
			}
			if(accion[1] == 1) {
				bia.fuerzaia = accion[2];
				bia.girarIzquierda();
			}
			if(accion[1] == 2) {
				float angle = bia.body.getAngle();
				float nangle = 0;
				if(Math.abs(angle)>0.01f) {
					if(angle>0) {
						nangle = angle-0.007f;
					} else {
						nangle = angle+0.007f;
					}
				} else {
					nangle = angle;
				}
				bia.body.setTransform(bia.body.getPosition(), nangle);
			}
			/*
			if(accion[1] == 3) {
				float angle = bia.body.getAngle();
				float nangle = angle;
				Vector2 vel = bia.body.getLinearVelocity();
				if(vel.x>0f) {
					if(nangle<1.5708f) {
						nangle = angle+0.007f;
					}
				}
				bia.body.setTransform(bia.body.getPosition(), nangle);
			}*/
			if(accion[0] == 1) {
				bia.acelerar();
			}
			if(accion[0] == 0) {
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
		camara.position.set(new Vector3(camara.position.x,pos2.y,0));
		camara.update();
		BitmapFont fuente = new BitmapFont();
		fuente.setColor(Color.GOLDENROD);
		fuente.getData().setScale(5.0f/relation, 5.0f/relation);
		Array<Body> lista = new Array<Body>();
		fisicas.getBodies(lista);
		Iterator<Body> iter = lista.iterator();
	    Body body;
	    Sprite sprite;
	    Vector2 playerposition = new Vector2(0,0);
	    background.animate(delta);
	    carriles.update();
	    this.code.batch.setProjectionMatrix(camara.combined);
	    this.code.batch.begin();
	    background.draw(this.code.batch);
	    carriles.draw(this.code.batch);
	    fuente.draw(this.code.batch,"Vida:"+jugador.vida,camara.position.x,camara.position.y);
	    while (iter.hasNext()) {
	        body = iter.next();
	        UserData data = (UserData) body.getUserData();
	        sprite = (Sprite) data.foto;
	        Vector2 pos = body.getPosition();
	        if(data.tipo == 1 && !data.barco.ia) {
	        	playerposition.set(pos);
	        }
	        if(data.tipo == 1 && data.barco.ia && body.getPosition().y>(camara.viewportHeight+playerposition.y)+camara.viewportHeight/2) {
				Vector2 vel = body.getLinearVelocity();
				Vector2 velco = new Vector2(vel.x,vel.y);
				velco.rotateDeg(180);
				velco.scl(1.5f);
				body.applyForce(velco, body.getPosition(),true);
	        }
	        if(body.getPosition().x<-camara.viewportWidth/2) {
	        	body.setTransform(-camara.viewportWidth/2,body.getPosition().y,body.getAngle());
	        	body.setLinearVelocity(new Vector2(0,body.getLinearVelocity().y));
	        }
	        if(body.getPosition().x>camara.viewportWidth/2) {
	        	body.setTransform(camara.viewportWidth/2,body.getPosition().y,body.getAngle());
	        	body.setLinearVelocity(new Vector2(0,body.getLinearVelocity().y));
	        }
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
	    ArrayList<Body> aux = new ArrayList<Body>();
	    for(int i = 0;i<obstaculos.size();i++) {
	    	Body obs = obstaculos.get(i);
	    	if(!this.borrar.contains(obs)) {
	    	Vector2 pos = obs.getPosition();
	    	if(pos.x<playerposition.x-camara.viewportWidth) {
	    		if(!this.borrar.contains(obs)) {
	    			this.borrar.add(obs);
	    		}
	    		if(!aux.contains(obs)) {
	    			aux.add(obs);
	    		}
	    	} else
	    	if(pos.x>playerposition.x+camara.viewportWidth) {
	    		if(!this.borrar.contains(obs)) {
	    			this.borrar.add(obs);
	    		}
	    		if(!aux.contains(obs)) {
	    			aux.add(obs);
	    		}
	    	} else
	    	if(pos.y<playerposition.y-camara.viewportHeight) {
	    		if(!this.borrar.contains(obs)) {
	    			this.borrar.add(obs);
	    		}
	    		if(!aux.contains(obs)) {
	    			aux.add(obs);
	    		}
	    	}
	    	} else {
	    		aux.add(obs);
	    	}
	    }
	    obstaculos.removeAll(aux);
	    for(int i = 0;i<this.borrar.size();i++) {
	    	fisicas.destroyBody(this.borrar.get(i));
	    }
	    this.borrar.clear();
	    if(obstaculos.size()<PantallaPartida.numObstaculos) {
			for(int i = 0;i<PantallaPartida.numObstaculos-obstaculos.size();i++) {
				Vector3 posc = this.camara.position;
				float posx = (float) (Math.random()*camara.viewportWidth/2)-1;
				float posy = camara.viewportHeight+(float)(Math.random()*camara.viewportHeight);
				posy += Gdx.graphics.getHeight()/relation;
				if(Math.random()<0.5f) {
					posx = -posx;
				}
				Obstaculo obstaculo = new Obstaculo((int)Math.floor(Math.random()*10f));
				Sprite obst = new Sprite(this.code.manager.get("roca.png",Texture.class),4000,4000);
				obst.setScale(0.003f*obstaculo.tamanio/relation);
				Body obstaculob = crearCuerpo(new Vector2(posc.x+posx,posc.y+posy),BodyType.StaticBody,0.2f,1f,0.6f,true,new Vector2(obstaculo.tamanio*3,obstaculo.tamanio*3));
				obstaculob.setUserData(new UserData(obst,ids,obstaculo));
				obstaculos.add(obstaculob);
				ids++;
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
