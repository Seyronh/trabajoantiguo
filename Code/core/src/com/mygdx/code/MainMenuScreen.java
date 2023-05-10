package com.mygdx.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MainMenuScreen implements Screen {


	
	final Code code;
	
	private Stage stage;
	
	private float anchoPantalla;
	private float altoPantalla;
	
	private SpriteBatch batch;
	private Texture fondo;
	private Texture tabla;
	private Texture titulo;

	private Texture seleccionar;
	
	private int commandnum;
	
	private boolean arrows;

	
	float anchoBoton;
    float altoBoton;
	// Almacena las preferencias (en %UserProfile%/.prefs/PreferencesName)
	//Preferences prefs;
	private boolean delay;
	private float delayi;
	public MainMenuScreen(final Code code) {

		this.code = code;
		anchoPantalla = Gdx.graphics.getWidth();
		altoPantalla = Gdx.graphics.getHeight();
		
		
		commandnum = 0;
		
		arrows = true;
		
		anchoBoton = anchoPantalla *25/100;
		altoBoton = altoPantalla *10/100;

		seleccionar = this.code.manager.get("Menus/madera.png",Texture.class);
		this.delay = false;
		this.delayi = 0f;
		//cargarPantalla();
	}
	
	
	public void cargarPantalla() {
		
		
		
		float BotonX = (anchoPantalla * 50 /100) - (anchoBoton/2);
	    float BotonY = altoPantalla * 50/100;
	    
	    
	    
		
		//OPCIONES CONTROLES
		
    	stage = new Stage();
		batch = new SpriteBatch();
		

		fondo = this.code.manager.get("fondoMenuPrincipal.png",Texture.class);
    titulo = this.code.manager.get("Titulo.png", Texture.class);

	//	Tabla = new Texture("opciones.png");
    	
		Table table2 = new Table();
	    table2.setPosition(0,0);
		// La tabla ocupa toda la pantalla
	    table2.setFillParent(true);
	    table2.setHeight(altoPantalla);


		//table.addActor(boton);
//	    stage.addActor(table2);
	    stage.addActor(table2);
		
	    //Estilo boton
	    
	    	TextButtonStyle styleb = new TextButtonStyle();
		
	    	Texture buttondown = this.code.manager.get("botondownplchld.png",Texture.class);
	    	Texture buttonup = this.code.manager.get("botonplchld.png",Texture.class);
		
	    	styleb.down = new TextureRegionDrawable(new TextureRegion(buttondown));
	    	styleb.up = new TextureRegionDrawable(new TextureRegion(buttonup));
		
	    	styleb.font = new BitmapFont();
	    	styleb.fontColor = Color.BLACK;
	    	
	    		

//	    //Boton Jugar
//	    	
//	    	TextButton buttonCambiar = new TextButton("Jugar", styleb); //TEXTURA
//			buttonCambiar.setPosition(BotonX, BotonY );
//			buttonCambiar.setSize(AnchoBoton, AltoBoton);
//			buttonCambiar.addListener(new InputListener() {
//				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//					return true;	
//				}
//				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//					
//					
//					
//		
//					
//				}
//			});
//	    
//			
//			table2.addActor(buttonCambiar);
//			
//			BotonY = BotonY - altoPantalla*15/100;
//			
//			//Boton Opciones
//			
//			TextButton buttonOpciones = new TextButton("Opciones", styleb); //TEXTURA
//			buttonOpciones.setPosition(BotonX, BotonY);
//			buttonOpciones.setSize(AnchoBoton, AltoBoton);
//			buttonOpciones.addListener(new InputListener() {
//				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//					return true;	
//				}
//				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//					
//					dispose();
//					game.setScreen(new Opciones(game));
//				}
//			});
//	    
//			
//			table2.addActor(buttonOpciones);
//			
//			
//			BotonY = BotonY - altoPantalla*15/100;
//			
//			
//		//Boton Salir
//		
//				
//				
//				TextButton buttonSalir = new TextButton("Salir", styleb); //TEXTURA
//				buttonSalir.setPosition(BotonX, BotonY);
//				buttonSalir.setSize(AnchoBoton, AltoBoton);
//				buttonSalir.addListener(new InputListener() {
//					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//						return true;	
//					}
//					public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//						
//						dispose();
//						
//						System.exit(0);
//					}
//				});
//				
//				
//				table2.addActor(buttonSalir);
//		
//		Gdx.input.setInputProcessor(stage);
		
	}
	
	@Override
	public void show() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		cargarPantalla();
	}

	@Override
	public void render(float delta) {
		delayi += delta;
		if(delayi > 0.3f) {
			delayi = 0f;
			delay = false;
		}
		// TODO Auto-generated method stub	
	
//		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
//
//			dispose();
//			
//			System.exit(0);
//		}
	    
		stage.act(delta);
		stage.getBatch().begin();
		stage.getBatch().draw(fondo, 0, 0, anchoPantalla, altoPantalla);
		stage.getBatch().draw(titulo,anchoPantalla*5/100,altoPantalla*70/100,anchoPantalla*95/100/(16/9) , altoPantalla*18/100/(16/9));
	 // batch.draw(Tabla, anchoPantalla*38/100, altoPantalla*3/10, anchoPantalla/4, altoPantalla*6/10); 
		stage.getBatch().draw(new Texture("cuadro.png"), anchoPantalla*49/100 - (anchoBoton/2), altoPantalla*20/100, anchoPantalla*25/100, altoPantalla*40/100);
	    
	    float medidax = anchoPantalla*45/100;
	    float mediday =  altoPantalla*495/1000;
	    
	    stage.getBatch().draw(code.manager.get("Jugar.png", Texture.class), medidax, mediday, anchoPantalla*10/100, altoPantalla*6/100);
	    
	    mediday = mediday - altoPantalla*9/100;
	    
	    stage.getBatch().draw(code.manager.get("Controles.png", Texture.class), medidax, mediday, anchoPantalla*10/100, altoPantalla*6/100);
	    
	    mediday = mediday - altoPantalla*9/100;
	    stage.getBatch().draw(code.manager.get("Sonido.png", Texture.class), medidax, mediday, anchoPantalla*10/100, altoPantalla*6/100);
	    
	    mediday = mediday - altoPantalla*9/100;
	    stage.getBatch().draw(code.manager.get("Salir.png", Texture.class), medidax, mediday+4, anchoPantalla*10/100, altoPantalla*6/100);
	    
	    
	  //medio pantalla  
	   // batch.draw(new Texture("sliderknob.png"), anchoPantalla/2, altoPantalla/2, 2, 200);
	    
	    
	    if(Gdx.input.isKeyPressed(Keys.DOWN) && !delay) {
	    	delay = true;
    		commandnum++;
    		
    		if(commandnum > 3) {
    			
    			commandnum = 0;
    		}
    		
    	}
	    if(Gdx.input.isKeyPressed(Keys.UP)&& !delay) {
    		delay = true;
    		commandnum--;
    		
    		if(commandnum < 0) {
    			
    			commandnum = 3;
    		}
    		
    	}
	    
	    if(Gdx.graphics.isFullscreen()) {
	    	stage.getBatch().draw(seleccionar, anchoPantalla*28/100, (altoPantalla * 48/100) - altoPantalla*9/100*commandnum, altoBoton*32/10, altoBoton); //Pantalla completa
		    
	     
	    }else {
	    	stage.getBatch().draw(seleccionar, anchoPantalla*37/100, altoPantalla*42/100 - altoPantalla*15/100*commandnum);  // Modo ventana
	 	   
	    	
	    }
	    
	    switch(commandnum) {
	    case 0:
	    	
	    	
	    	if(Gdx.input.isKeyPressed(Keys.ENTER)) {
	    		try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		code.setScreen(new SeleccionBarco(code));
	    	}
	    	break;
	    case 1:
	    	
	    	if(Gdx.input.isKeyPressed(Keys.ENTER)) {
	    		try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		code.setScreen(new OpcionesControles(code));
	    	}
	    	break;
	    case 2:
	    	if(Gdx.input.isKeyPressed(Keys.ENTER)) {
	    		try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		code.setScreen(new OpcionesSonido(code));
	    	}
	    	break;
	    case 3:
	    	if(Gdx.input.isKeyPressed(Keys.ENTER)) {
	    		System.exit(0);
	    	}
	    	break;
	    
	    
	    }
		
		stage.getBatch().end();
		
		stage.draw();
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
		stage.dispose();
	}
	
}
