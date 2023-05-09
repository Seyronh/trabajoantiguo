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
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class OpcionesControles implements Screen {

	
	
	final Code code;
	
	private Stage stage;
	
	private float anchoPantalla;
	private float altoPantalla;
	
	private SpriteBatch batch;
	private Texture fondo;
	private Texture tabla;
	
	private Texture seleccionar;
	
	private int commandnum;
	
	private boolean arrows;

	
	float AnchoBoton;
    float AltoBoton;
	// Almacena las preferencias (en %UserProfile%/.prefs/PreferencesName)
	//Preferences prefs;
	
	public OpcionesControles(final Code code) {

		this.code = code;
		anchoPantalla = Gdx.graphics.getWidth();
		altoPantalla = Gdx.graphics.getHeight();
		
		
		commandnum = 0;
		
		arrows = code.moverIzquierda == Keys.LEFT;;
		
		AnchoBoton = anchoPantalla *15/100;
		AltoBoton = altoPantalla *5/100;
		
		seleccionar = code.manager.get("seleccionar.png", Texture.class);
		//cargarPantalla();
	}
	
	
	public void cargarPantalla() {
		
		float BotonX = anchoPantalla * 47 /100;
	    float BotonY = altoPantalla * 42/100;
	    
	    
		
		
		//OPCIONES CONTROLES
		
    	stage = new Stage();
		batch = new SpriteBatch();
		
		fondo = code.manager.get("fondoMenuPrincipal.png", Texture.class);
		tabla = code.manager.get("opciones.png", Texture.class);
    	
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
		
		
	    	Texture buttondown = code.manager.get("botondownplchld.png", Texture.class);
	    	Texture buttonup = code.manager.get("botonplchld.png", Texture.class);
		
	    	styleb.down = new TextureRegionDrawable(new TextureRegion(buttondown));
	    	styleb.up = new TextureRegionDrawable(new TextureRegion(buttonup));
		
	    	styleb.font = new BitmapFont();
	    	styleb.fontColor = Color.BLACK;
	    	
	    		
	    //Boton Cambiar
	    	
	    	TextButton buttonCambiar = new TextButton("A", styleb); //TEXTURA
			buttonCambiar.setPosition(BotonX, BotonY);
			buttonCambiar.setSize(AnchoBoton/3, AltoBoton);
			buttonCambiar.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;	
				}
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					
					arrows = false;
					
		
					
				}
			});
	    
			
			table2.addActor(buttonCambiar);
			
			
			TextButton buttonCambiarB = new TextButton("B", styleb); //TEXTURA
			buttonCambiarB.setPosition(BotonX + AnchoBoton/3*2, BotonY);
			buttonCambiarB.setSize(AnchoBoton/3, AltoBoton);
			buttonCambiarB.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;	
				}
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					
					arrows = false;
				}
			});
	    
			
			table2.addActor(buttonCambiarB);
			
			
			BotonY = BotonY - altoPantalla*7/100;
			
			
		//Boton Volver
		
				
				
				TextButton buttonVolver = new TextButton("Volver", styleb); //TEXTURA
				buttonVolver.setPosition(BotonX, BotonY);
				buttonVolver.setSize(AnchoBoton, AltoBoton);
				buttonVolver.addListener(new InputListener() {
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						return true;	
					}
					public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
						
						dispose();
						
						code.setScreen(new Opciones(code));
					}
				});
				
				
				table2.addActor(buttonVolver);
		
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
			
			code.setScreen(new Opciones(code));
		}
		
		
		batch.begin();
	    batch.draw(fondo, 0, 0, anchoPantalla, altoPantalla);
	    batch.draw(tabla, anchoPantalla*38/100, altoPantalla*3/10, anchoPantalla/4, altoPantalla*6/10);

	    
	    
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
		
		if(arrows) {
			
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
    			
	    		 code.moverIzquierda = arrows ? Keys.LEFT : Keys.A;
	    		 code.moverDerecha = arrows ? Keys.RIGHT : Keys.D;
	    		 code.moverArriba = arrows ? Keys.UP : Keys.W;
	    		 code.frenar = arrows ? Keys.DOWN : Keys.S;

	    		 
	    		 code.setScreen(new Opciones(code));
	    		 
	    	}
	    		
	    	
	    	
	    	if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
	    		code.moverIzquierda = Keys.LEFT;
	    		
	    		
	    		arrows = true;
	    	}
	    	if(Gdx.input.isKeyPressed(Keys.LEFT)) {
	    		code.moverIzquierda = Keys.A;
	    		
	    		arrows = false;
	    	}
	    	
	    	
	    	
	    	
	    	break;
	    case 1:
	    	
	    	if(Gdx.input.isKeyPressed(Keys.ENTER)) {
	    		code.setScreen(new Opciones(code));
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
