package com.mygdx.code;



import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PantallaPartida implements Screen {
	static final float relation = (Gdx.graphics.getWidth()/Gdx.graphics.getHeight())+15;
	static final float STEP_TIME = 1f/60f;
	static int numObstaculos = 20;
	static int numPowerUps = 4;
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
	Body meta;
	Vector2 jugadorpos;
	int powerupsa = 0;
	
	/////////
	
	

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
		
	}
	private void generarObstaculo() {
		Vector3 posc = this.camara.position;
		float posx = (float) (Math.random()*camara.viewportWidth/2)-1;
		float posy = camara.viewportHeight+(float)(Math.random()*camara.viewportHeight);
		posy += Gdx.graphics.getHeight()/relation;
		if(Math.random()<0.5f) {
			posx = -posx;
		}
		Obstaculo obstaculo = new Obstaculo((int)Math.floor(Math.random()*10f));
		Sprite obst = new Sprite(this.code.manager.get("pantallapartida/roca.png",Texture.class),4000,4000);
		obst.setScale(0.003f*obstaculo.tamanio/relation);
		Body obstaculob = crearCuerpo(new Vector2(posc.x+posx,posc.y+posy),BodyType.StaticBody,0.2f,1f,0.6f,true,new Vector2(obstaculo.tamanio*3,obstaculo.tamanio*3));
		obstaculob.setUserData(new UserData(obst,ids,obstaculo));
		obstaculos.add(obstaculob);
		ids++;
	}
	private void generarPowerUp() {
		Vector3 posc = this.camara.position;
		float posx = (float) (Math.random()*camara.viewportWidth/2)-1;
		float posy = camara.viewportHeight+(float)(Math.random()*camara.viewportHeight);
		posy += Gdx.graphics.getHeight()/relation;
		if(Math.random()<0.5f) {
			posx = -posx;
		}
		PowerUp powerup = PowerUp.predefinidos[(int)Math.random()*PowerUp.predefinidos.length];
		Sprite power = new Sprite(this.code.manager.get("pantallapartida/powerUp.png",Texture.class),1024,1024);
		power.setScale(0.07f/relation);
		Body powerbod = crearCuerpo(new Vector2(posc.x+posx,posc.y+posy),BodyType.StaticBody,0.2f,1f,0.6f,true,new Vector2(40,40));
		powerbod.setUserData(new UserData(power,ids,powerup));
		ids++;
		powerupsa++;
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
		this.code.music.stop();
		this.code.music = this.code.manager.get("musica/enpartida.ogg");
		this.code.music.setVolume((float)Math.pow(this.code.volumen, 2));
		this.code.music.play();
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
		 * CREAMOS LA META
		 */
		meta = crearCuerpo(new Vector2(0,1200),BodyType.StaticBody,0.2f,1f,0.6f,true,new Vector2(Gdx.graphics.getWidth(),100));
		Sprite metas = new Sprite(this.code.manager.get("pantallapartida/Meta.jpg",Texture.class),745,114);
		metas.setScale(3f/relation);
		meta.setUserData(new UserData(metas,ids));
		ids++;
		/*
		 * CREACION OBSTACULOS INICIALES
		 */
		for(int i = 0;i<PantallaPartida.numObstaculos;i++) {
			this.generarObstaculo();
		}
		
		/*
		 * FINAL CREACION OBSTACULOS INICIALES
		 */
		/*
		 * CREACION PowerUps
		 */
		for(int i = 0;i<PantallaPartida.numPowerUps;i++) {
			this.generarPowerUp();
		}
		
		/*
		 * FINAL CREACION PowerUps
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
		/*
		 * CREACION JUGADOR Y IA
		 */
		Sprite barquito = new Sprite(this.code.manager.get(this.code.tipoBarcoSeleccionado.barco,Texture.class),1024,1024);
		barquito.setScale(0.20f/relation);
		jugadorpos = new Vector2(0,0);
		Body barcoj = crearCuerpo(jugadorpos,BodyType.DynamicBody,0.2f,1f,0.6f,false,new Vector2(25,85));
		jugador = new Barco(this.code.tipoBarcoSeleccionado,barcoj);
		barcoj.setUserData(new UserData(barquito,ids,jugador));
		ids++;
		
		for(int i = 0;i<3;i++) {
			Body barcoia = crearCuerpo(new Vector2(10+10*i,0),BodyType.DynamicBody,0.2f,1f,0.6f,false,new Vector2(25,85));
			barcoia.setTransform(carriles.obtenerMedio(i+2), barcoia.getAngle());
			TipoBarco iab = this.code.tipoBarcos.get((int) (this.code.tipoBarcos.size()*Math.random()));
			Barco bia = new Barco(iab,barcoia,true);
			IA ia = new IA(this.code.dificultad,iab);
			barquito = new Sprite(this.code.manager.get(iab.barco,Texture.class),1024,1024);
			barquito.setScale(0.20f/relation);
			barcoia.setUserData(new UserData(barquito,ids,bia));
			BarcosIA.add(bia);
			IAs.add(ia);
			ids++;
		}
		
		/*
		 * FIN CREACION JUGADOR Y IA
		 */
		barcoj.setTransform(carriles.obtenerMedio(1), barcoj.getAngle());
	}

	@Override
	public void render(float delta) {
		
		switch (state) {
		case RUNNING:
			if(this.code.ganadorj && this.code.ganadas == 4) {
				this.code.terminados = 0;
				this.code.ganadorj = false;
				this.code.dificultad = 1;
				this.code.music.stop();
				this.code.music = this.code.manager.get("musica/fuerapartida.ogg");
				this.code.music.setVolume((float)Math.pow(this.code.volumen, 2));
				this.code.music.play();
				this.code.setScreen(new MainMenuScreen(this.code));
			} else
			if(this.code.ganadorj) {
				this.code.dificultad++;
				this.code.terminados = 0;
				this.code.ganadorj = false;
				this.code.music.stop();
				this.code.music = this.code.manager.get("musica/minijuego1.ogg");
				this.code.music.setVolume((float)Math.pow(this.code.volumen, 2));
				this.code.music.play();
				//this.code.setScreen(new Minijuego(this.code));
			
			  if(Math.random()<0.5f){
			  				this.code.music.stop();
			 				this.code.music = this.code.manager.get("musica/minijuego1.ogg");
			 				this.code.music.setVolume((float)Math.pow(this.code.volumen, 2));
			 				this.code.music.play();
			  		this.code.setScreen(new Minijuego(this.code));
			  } else {
			  				this.code.music.stop();
			 				this.code.music = this.code.manager.get("musica/minijuego2.ogg");
			 				this.code.music.setVolume((float)Math.pow(this.code.volumen, 2));
			 				this.code.music.play();
			  		this.code.setScreen(new minijuego2(this.code));
			  }
			 
			}
			if(this.code.terminados == 3) {
				this.code.terminados = 0;
				this.code.ganadorj = false;
				this.code.dificultad = 1;
				this.code.music.stop();
				this.code.music = this.code.manager.get("musica/fuerapartida.ogg");
				this.code.music.setVolume((float)Math.pow(this.code.volumen, 2));
				this.code.music.play();
				this.code.setScreen(new MainMenuScreen(this.code));
			}

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
		if(Gdx.input.isKeyPressed(Keys.SPACE) && !jugador.aplicado) {
			PowerUp poder = jugador.usarPowerUp();
			if(poder != null) {
				jugador.aplicado = true;
				jugador.elegido.aceleracion += poder.aceleracion;
				jugador.elegido.movilidad += poder.movilidad;
				jugador.vida += poder.curacion;
				if(jugador.vida > jugador.elegido.vidamax*Barco.basevidamax) {
					jugador.vida = jugador.elegido.vidamax*Barco.basevidamax;
				}
				jugador.cansancio += poder.cansancio;
				if(jugador.cansancio > 100) {
					jugador.cansancio = 100;
				}
				jugador.poder = null;
			}
		}
			if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
				
				state = State.PAUSE;
			}
			
			Vector2 pos2 = jugador.body.getPosition();
			camara.position.set(new Vector3(camara.position.x,pos2.y+20,0));
			camara.update();
			Array<Body> lista = new Array<Body>();
			fisicas.getBodies(lista);
			Iterator<Body> iter = lista.iterator();
		    Body body;
		    Sprite sprite;
		    background.animate(delta);
		    carriles.update();
		    this.code.batch.setProjectionMatrix(camara.combined);
		    this.code.batch.begin();
		    background.draw(this.code.batch);
		    carriles.draw(this.code.batch);
		    while (iter.hasNext()) {
		        body = iter.next();
		        UserData data = (UserData) body.getUserData();
		        sprite = (Sprite) data.foto;
		        Vector2 pos = body.getPosition();
		        if(data.tipo == 1 && !data.barco.ia) {
		        	jugadorpos = new Vector2(pos.x,pos.y);
		        }
		        if(data.tipo == 1 && data.barco.ia && pos.y>(camara.viewportHeight+jugadorpos.y)+camara.viewportHeight/2) {
					Vector2 vel = body.getLinearVelocity();
					Vector2 velco = new Vector2(vel.x,vel.y);
					velco.rotateDeg(180);
					velco.scl(0.8f);
					body.applyForce(velco, pos,true);
		        }
		        if(data.tipo == 1 && (data.id == 0 || data.id == 1 || data.id == 2 || data.id == 3)) {
	        		Vector2 poscade = carriles.obtenerCarril(data.id+2);//CARRIL DERECHO
	        		Vector2 poscaiz = carriles.obtenerCarril(data.id+1);//CARRIL IZQUIERDO
		        	if(pos.x>poscade.x||pos.x<poscaiz.x) {
						Vector2 vel = body.getLinearVelocity();
						Vector2 velco = new Vector2(vel.x,vel.y);
						velco.rotateDeg(180);
						velco.scl(0.8f);
						body.applyForce(velco, pos,true);
		        	}
		        }
		        if((data.tipo == 3 || data.tipo == 2) && (pos.x<jugadorpos.x-camara.viewportWidth||pos.x>jugadorpos.x+camara.viewportWidth||pos.y<jugadorpos.y-camara.viewportHeight)) {
		        	if(!this.borrar.contains(body)) {
		        		this.borrar.add(body);
		        	}
		        }
		        if(body.getPosition().x<-camara.viewportWidth/2) {
		        	body.setTransform(-camara.viewportWidth/2,pos.y,body.getAngle());
		        	body.setLinearVelocity(new Vector2(0,body.getLinearVelocity().y));
		        }
		        if(body.getPosition().x>camara.viewportWidth/2) {
		        	body.setTransform(camara.viewportWidth/2,pos.y,body.getAngle());
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
		    ArrayList<Body> auxo = new ArrayList<>();
		    for(int i = 0;i<this.borrar.size();i++) {
		    	Body b = this.borrar.get(i);
		    	UserData data = (UserData) b.getUserData();
		    	if(data.tipo == 3) {
		    		auxo.add(b);
		    	} else if(data.tipo == 2) {
		    		powerupsa--;
		    	}
		    	fisicas.destroyBody(b);
		    }
		    obstaculos.removeAll(auxo);
		    this.borrar.clear();
			for(int i = 0;i<PantallaPartida.numObstaculos-obstaculos.size();i++) {
				generarObstaculo();
			}
			while(powerupsa<PantallaPartida.numPowerUps) {
				this.generarPowerUp();
			}
			
			
			break;
		case PAUSE:
			
			
			
			//Aqui ya las cosas
			
			
			stage.getBatch().begin();
			
			stage.getBatch().draw(code.manager.get("Menus/Menu2.png", Texture.class), anchoPantalla*36/100, altoPantalla*21/100, anchoPantalla*30/100, altoPantalla*60/100);
			
			stage.getBatch().draw(code.manager.get("Menus/Combo.png", Texture.class), anchoPantalla*40/100, altoPantalla*37/100, anchoPantalla*20/100, altoPantalla*30/100);
			
			stage.getBatch().end();
			
			
			
			 if(Gdx.input.isKeyPressed(Keys.ENTER)) {
		    		
		    	state = State.RUNNING;
		    		
		    	}
			  if(Gdx.input.isKeyPressed(Keys.M)) {
		    		stage.dispose();
				  code.setScreen(new MainMenuScreen(code));
		    		
		     }
			  if(Gdx.input.isKeyPressed(Keys.BACKSPACE)) {
					
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
		this.code.music.dispose();
	}
}
