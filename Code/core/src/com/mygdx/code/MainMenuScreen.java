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


	
	final Code game;
	
	private Stage stage;
	
	private float anchoPantalla;
	private float altoPantalla;
	
	private SpriteBatch batch;
	private Texture Fondo;
	private Texture Tabla;
	
	private Texture seleccionar;
	
	private int commandnum;
	
	private boolean arrows;

	
	float AnchoBoton;
    float AltoBoton;
	// Almacena las preferencias (en %UserProfile%/.prefs/PreferencesName)
	//Preferences prefs;
	
	public MainMenuScreen(final Code game) {

		this.game = game;
		anchoPantalla = Gdx.graphics.getWidth();
		altoPantalla = Gdx.graphics.getHeight();
		
		
		commandnum = 0;
		
		arrows = true;
		
		AnchoBoton = anchoPantalla *25/100;
		AltoBoton = altoPantalla *10/100;
		
		seleccionar = new Texture("seleccionar.png");
		//cargarPantalla();
	}
	
	
	public void cargarPantalla() {
		
		
		
		float BotonX = anchoPantalla * 47 /100;
	    float BotonY = altoPantalla * 42/100;
	    
	    
	    
		
		//OPCIONES CONTROLES
		
    	stage = new Stage();
		batch = new SpriteBatch();
		
		Fondo = new Texture("fondoMenuPrincipal.png");
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
		
		
	    	Texture buttondown = new Texture("botondownplchld.png");
	    	Texture buttonup = new Texture("botonplchld.png");
		
	    	styleb.down = new TextureRegionDrawable(new TextureRegion(buttondown));
	    	styleb.up = new TextureRegionDrawable(new TextureRegion(buttonup));
		
	    	styleb.font = new BitmapFont();
	    	styleb.fontColor = Color.BLACK;
	    	
	    		
	    //Boton Jugar
	    	
	    	TextButton buttonCambiar = new TextButton("Jugar", styleb); //TEXTURA
			buttonCambiar.setPosition(BotonX, BotonY);
			buttonCambiar.setSize(AnchoBoton, AltoBoton);
			buttonCambiar.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;	
				}
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					
					
					
		
					
				}
			});
	    
			
			table2.addActor(buttonCambiar);
			
			BotonY = BotonY - altoPantalla*15/100;
			
			
			//Boton Opciones
			
			TextButton buttonOpciones = new TextButton("Opciones", styleb); //TEXTURA
			buttonOpciones.setPosition(BotonX, BotonY);
			buttonOpciones.setSize(AnchoBoton, AltoBoton);
			buttonOpciones.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;	
				}
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					
					dispose();
					game.setScreen(new Opciones(game));
				}
			});
	    
			
			table2.addActor(buttonOpciones);
			
			
			BotonY = BotonY - altoPantalla*15/100;
			
			
		//Boton Salir
		
				
				
				TextButton buttonSalir = new TextButton("Salir", styleb); //TEXTURA
				buttonSalir.setPosition(BotonX, BotonY);
				buttonSalir.setSize(AnchoBoton, AltoBoton);
				buttonSalir.addListener(new InputListener() {
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						return true;	
					}
					public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
						
						dispose();
						
						System.exit(0);
					}
				});
				
				
				table2.addActor(buttonSalir);
		
		Gdx.input.setInputProcessor(stage);
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		cargarPantalla();
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
		
		
		
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {

			dispose();
			
			System.exit(0);
		}
		
		
		batch.begin();
	    batch.draw(Fondo, 0, 0, anchoPantalla, altoPantalla);
	 //   batch.draw(Tabla, anchoPantalla*38/100, altoPantalla*3/10, anchoPantalla/4, altoPantalla*6/10);

	    
	    
	    if(Gdx.input.isKeyPressed(Keys.DOWN)) {
    		
    		commandnum++;
    		
    		if(commandnum > 2) {
    			
    			commandnum = 0;
    		}
    		
    	}
	    if(Gdx.input.isKeyPressed(Keys.UP)) {
    		
    		commandnum--;
    		
    		if(commandnum < 0) {
    			
    			commandnum = 2;
    		}
    		
    	}
	    
	    if(Gdx.graphics.isFullscreen()) {
	    
	    	batch.draw(seleccionar, anchoPantalla*37/100, altoPantalla*42/100 - altoPantalla*15/100*commandnum, AnchoBoton/4, AltoBoton); //Pantalla completa
		    
	     
	    }else {
	    	batch.draw(seleccionar, anchoPantalla*37/100, altoPantalla*42/100 - altoPantalla*15/100*commandnum);  // Modo ventana
	 	   
	    	
	    }
	    
	    switch(commandnum) {
	    case 0:
	    	
	    	
	    	if(Gdx.input.isKeyPressed(Keys.ENTER)) {
	    		
	    		//game.setScreen(new OpcionesControles(game));
	    	}
	    	break;
	    case 1:
	    	
	    	if(Gdx.input.isKeyPressed(Keys.ENTER)) {
	    		
	    		game.setScreen(new Opciones(game));
	    	}
	    	break;
	    case 2:
	    	if(Gdx.input.isKeyPressed(Keys.ENTER)) {
	    		
	    		System.exit(0);
	    		
	    		//game.setScreen(new OpcionesSonido(game));
	    	}
	    	break;
	    
	    
	    }
	    
	    
	    
	    batch.end();
	    
		stage.act(delta);
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
