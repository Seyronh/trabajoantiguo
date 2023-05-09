package com.mygdx.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PantallaSalir implements Screen{

	final Code game;
	
	private Stage stage;
	
	private float anchoPantalla;
	private float altoPantalla;
	
	private SpriteBatch batch;
	private Texture Fondo;
	private Texture Tabla;
	
	private Texture seleccionar;
	
	private int commandnum;
	
	private boolean salir;

	
	float AnchoBoton;
    float AltoBoton;
	// Almacena las preferencias (en %UserProfile%/.prefs/PreferencesName)
	//Preferences prefs;
	
	public PantallaSalir(final Code game) {

		this.game = game;
		anchoPantalla = Gdx.graphics.getWidth();
		altoPantalla = Gdx.graphics.getHeight();
		
		
		commandnum = 0;
		
		salir = false;
		
		AnchoBoton = anchoPantalla *15/100;
		AltoBoton = altoPantalla *5/100;
		
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
		Tabla = new Texture("opciones.png");
    	
		Table table2 = new Table();
	    table2.setPosition(0,0);
		// La tabla ocupa toda la pantalla
	    table2.setFillParent(true);
	    table2.setHeight(altoPantalla);


		//table.addActor(boton);
	    //stage.addActor(table2);
	    stage.addActor(table2);
		
	    //Estilo boton
	    
	    	TextButtonStyle styleb = new TextButtonStyle();
		
		
	    	Texture buttondown = new Texture("botondownplchld.png");
	    	Texture buttonup = new Texture("botonplchld.png");
		
	    	styleb.down = new TextureRegionDrawable(new TextureRegion(buttondown));
	    	styleb.up = new TextureRegionDrawable(new TextureRegion(buttonup));
		
	    	styleb.font = new BitmapFont();
	    	styleb.fontColor = Color.BLACK;
	    	
	    		
	    //Boton Cambiar
	    	
	    	TextButton buttonCambiar = new TextButton("Si", styleb); //TEXTURA
			buttonCambiar.setPosition(BotonX, BotonY);
			buttonCambiar.setSize(AnchoBoton/3, AltoBoton);
			buttonCambiar.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;	
				}
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					
					salir = false;
					
		
					
				}
			});
	    
			
			table2.addActor(buttonCambiar);
			
			
			TextButton buttonCambiarB = new TextButton("No", styleb); //TEXTURA
			buttonCambiarB.setPosition(BotonX + AnchoBoton/3*2, BotonY);
			buttonCambiarB.setSize(AnchoBoton/3, AltoBoton);
			buttonCambiarB.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;	
				}
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					
					salir = false;
				}
			});
	    
			
			table2.addActor(buttonCambiarB);
			
			
		
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
			
			game.setScreen(new Opciones(game));
		}
		
		
		batch.begin();
	    batch.draw(Fondo, 0, 0, anchoPantalla, altoPantalla);
	    batch.draw(Tabla, anchoPantalla*38/100, altoPantalla*3/10, anchoPantalla/4, altoPantalla*6/10);

	    
	    
	    if(Gdx.input.isKeyPressed(Keys.DOWN)) {
    		
    		commandnum++;
    		
    		if(commandnum > 1) {
    			
    			commandnum = 0;
    		}
    		
    	}
	    if(Gdx.input.isKeyPressed(Keys.UP)) {
    		
    		commandnum--;
    		
    		if(commandnum < 0) {
    			
    			commandnum = 1;
    		}
    		
    	}
	    
	    float desplazamiento = 0;
		
		if(salir) {
			
			desplazamiento = AnchoBoton/3*2+10;
		}
	    
	    if(Gdx.graphics.isFullscreen()) {
		    
	    	if(commandnum == 0) {
	    	
	    		batch.draw(seleccionar, anchoPantalla*39/100 + desplazamiento +35, altoPantalla*42/100 - altoPantalla*18/100*commandnum, AnchoBoton/2, AltoBoton); //Pantalla completa
	    	
	    	}else {
	    		
	    		batch.draw(seleccionar, anchoPantalla*39/100 + 35, altoPantalla*42/100 - altoPantalla*7/100*commandnum, AnchoBoton/2, AltoBoton); //Pantalla completa
		    	
	    	}
	     
	    }else {
	    	
	    	if(commandnum == 0) {
	    	
	    		
	    		batch.draw(seleccionar, anchoPantalla*40/100 + desplazamiento, altoPantalla*42/100 - (altoPantalla*7/100)*commandnum, AnchoBoton/3, AltoBoton);  // Modo ventana
	    	}else {
	    		
	    		batch.draw(seleccionar, anchoPantalla*40/100, altoPantalla*42/100 - (altoPantalla*7/100)*commandnum, AnchoBoton/3, AltoBoton);  // Modo ventana
		    	
	    	}
	    	
	    }
	    
	//    batch.draw(seleccionar, anchoPantalla*39/100, altoPantalla *42/100 - (altoPantalla*5/100 * commandnum));
 
	    switch(commandnum) {
	    case 0:
	    	
	    	if(Gdx.input.isKeyPressed(Keys.ENTER)) {
	    		 
	    		if(salir) {
	    			
	    			game.setScreen(new MainMenuScreen(game));
	    			
	    		}else {
	    			
	    			game.setScreen(new PantallaPartida(game));
	    		}
	    		
	    		 
	    		 
	    	}
	    		
	    	
	    	
	    	if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
	    		game.moverIzquierda = Keys.LEFT;
	    		
	    		
	    		salir = true;
	    	}
	    	if(Gdx.input.isKeyPressed(Keys.LEFT)) {
	    		game.moverIzquierda = Keys.A;
	    		
	    		salir = false;
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
