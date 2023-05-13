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

	
	float anchoBoton;
    float altoBoton;
    private float delayi;
    private boolean delay;
   
    
	// Almacena las preferencias (en %UserProfile%/.prefs/PreferencesName)
	//Preferences prefs;
	
	public OpcionesControles(final Code code) {

		this.code = code;
		anchoPantalla = Gdx.graphics.getWidth();
		altoPantalla = Gdx.graphics.getHeight();
		
		
		commandnum = 0;
		
		arrows = code.moverIzquierda == Keys.LEFT;;
		
		anchoBoton = anchoPantalla *15/100;
		altoBoton = altoPantalla *5/100;
		
		seleccionar = this.code.manager.get("Menus/madera.png", Texture.class);
		//cargarPantalla();
		
		
		
	}
	
	
	public void cargarPantalla() {
		
		
		
		float botonX = anchoPantalla * 47 /100;
	    float botonY = altoPantalla * 42/100;
	    
	    
		
		
		//OPCIONES CONTROLES
		
	    stage = new Stage();
   		batch = new SpriteBatch();
		
		fondo = code.manager.get("fondoMenuPrincipal.png", Texture.class);
		tabla = code.manager.get("Menu.png", Texture.class);
		
		
//    	
//		Table table2 = new Table();
//	    table2.setPosition(0,0);
//		// La tabla ocupa toda la pantalla
//	    table2.setFillParent(true);
//	    table2.setHeight(altoPantalla);
//
//
//		//table.addActor(boton);
//	    //stage.addActor(table2);
//	    stage.addActor(table2);
//		
//	    //Estilo boton
//	    
//	    	TextButtonStyle styleb = new TextButtonStyle();
//		
//		
//	    	Texture buttondown = new Texture("botondownplchld.png");
//	    	Texture buttonup = new Texture("botonplchld.png");
//		
//	    	styleb.down = new TextureRegionDrawable(new TextureRegion(buttondown));
//	    	styleb.up = new TextureRegionDrawable(new TextureRegion(buttonup));
//		
//	    	styleb.font = new BitmapFont();
//	    	styleb.fontColor = Color.BLACK;
//	    	
//	    		
//	    //Boton Cambiar
//	    	
//	    	TextButton buttonCambiar = new TextButton("A", styleb); //TEXTURA
//			buttonCambiar.setPosition(BotonX, BotonY);
//			buttonCambiar.setSize(AnchoBoton/3, AltoBoton);
//			buttonCambiar.addListener(new InputListener() {
//				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//					return true;	
//				}
//				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//					
//					arrows = false;
//					
//		
//					
//				}
//			});
//	    
//			
//			table2.addActor(buttonCambiar);
//			
//			
//			TextButton buttonCambiarB = new TextButton("B", styleb); //TEXTURA
//			buttonCambiarB.setPosition(BotonX + AnchoBoton/3*2, BotonY);
//			buttonCambiarB.setSize(AnchoBoton/3, AltoBoton);
//			buttonCambiarB.addListener(new InputListener() {
//				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//					return true;	
//				}
//				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//					
//					arrows = false;
//				}
//			});
//	    
//			
//			table2.addActor(buttonCambiarB);
//			
//			
//			BotonY = BotonY - altoPantalla*)7/100;
//			
//			
//		//Boton Volver
//		
//				
//				
//				TextButton buttonVolver = new TextButton("Volver", styleb); //TEXTURA
//				buttonVolver.setPosition(BotonX, BotonY);
//				buttonVolver.setSize(AnchoBoton, AltoBoton);
//				buttonVolver.addListener(new InputListener() {
//					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//						return true;	
//					}
//					public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//						
//						dispose();
//						
//						game.setScreen(new Opciones(game));
//					}
//				});
//				
//				
//				table2.addActor(buttonVolver);
//		
//		Gdx.input.setInputProcessor(stage);
//		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		cargarPantalla();
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		delayi += delta;
		if(delayi > 0.3f) {
			delayi = 0f;
			delay = false;
		}
		
		
		
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {

			dispose();
			
			code.setScreen(new MainMenuScreen(code));
		}
		
		
		
	    
		stage.act(Gdx.graphics.getDeltaTime());
		
		stage.getBatch().begin();
		
		stage.getBatch().draw(fondo, 0, 0, anchoPantalla, altoPantalla);
		    
		stage.getBatch().draw(tabla, anchoPantalla * 335 / 1000, altoPantalla * 25 / 100, anchoPantalla*32/100, altoPantalla * 65/100);

		    

			float medidax = anchoPantalla*40/100;
		    float mediday =  altoPantalla*70/100;
		    
		    
		    stage.getBatch().draw(code.manager.get("Menus/mapeado.png", Texture.class), medidax, mediday - altoPantalla*25/100, anchoPantalla*20/100, altoPantalla*30/100);	  
			
		    if(!arrows) {
		    	
		    	
		    	stage.getBatch().draw(code.manager.get("Menus/opcion1.png", Texture.class), medidax + anchoPantalla*6/100, altoPantalla*48/100, anchoPantalla*20/100, altoPantalla*7/100*4);
		    	
		    	
		    }else {
	    	
		    	stage.getBatch().draw(code.manager.get("Menus/opcion2.png", Texture.class), medidax + anchoPantalla*14/100, altoPantalla*50/100, anchoPantalla*14/100/367*100,anchoPantalla*14/100 );
		    	
		    	
		    }
			
		    
		    
//			mediday = mediday - altoPantalla*7/100;
//			
//			stage.getBatch().draw(new Texture("Menus/usar.png"), medidax, mediday, anchoPantalla*10/100, altoPantalla*6/100);
//			
//			stage.getBatch().draw(new Texture("Menus/espacio.png"), medidax + anchoPantalla*8/100, mediday, anchoPantalla*13/100, altoPantalla*5/100);
//		    
			mediday = altoPantalla*40/100;
		    
			stage.getBatch().draw(code.manager.get("Menus/1.png", Texture.class), medidax + anchoPantalla*8/100, mediday, anchoPantalla*10/100, altoPantalla*6/100);
			
			stage.getBatch().draw(code.manager.get("Menus/2.png", Texture.class), medidax + anchoPantalla*12/100, mediday, anchoPantalla*10/100, altoPantalla*6/100);
			
			mediday = mediday - altoPantalla*5/100;
			
			stage.getBatch().draw(code.manager.get("Menus/Volver.png", Texture.class), medidax + anchoPantalla*5/100, mediday, anchoPantalla*10/100, altoPantalla*6/100);
			
		    
		    if(Gdx.input.isKeyPressed(Keys.DOWN) && !delay) {
	    		delay = true;
	    		commandnum++;
	    		
	    		if(commandnum > 1) {
	    			
	    			commandnum = 0;
	    		}
	    		
	    	}
		    if(Gdx.input.isKeyPressed(Keys.UP) && !delay) {
	    		delay = true;
	    		commandnum--;
	    		
	    		if(commandnum < 0) {
	    			
	    			commandnum = 1;
	    		}
	    		
	    	}
		    
		    float desplazamiento = 0;
			
			if(arrows) {
				
				desplazamiento = anchoBoton*29/100;
			}
		    
		    if(Gdx.graphics.isFullscreen()) {
			    
		    	if(commandnum == 0) {
		    	
		    		stage.getBatch().draw(seleccionar, anchoPantalla*47/100 + desplazamiento, altoPantalla*41/100 - altoPantalla*18/100*commandnum, anchoBoton/2, altoBoton); //Pantalla completa
		    	
		    	}else {
		    		
		    		stage.getBatch().draw(seleccionar, anchoPantalla*39/100 + 35, altoPantalla*42/100 - altoPantalla*7/100*commandnum, anchoBoton/2, altoBoton); //Pantalla completa
			    	
		    	}
		     
		    }else {
		    	
		    	if(commandnum == 0) {
		    	
		    		
		    		stage.getBatch().draw(seleccionar, anchoPantalla*40/100 + desplazamiento, altoPantalla*42/100 - (altoPantalla*7/100)*commandnum, anchoBoton/3, altoBoton);  // Modo ventana
		    	}else {
		    		
		    		stage.getBatch().draw(seleccionar, anchoPantalla*40/100, altoPantalla*42/100 - (altoPantalla*7/100)*commandnum, anchoBoton/3, altoBoton);  // Modo ventana
			    	
		    	}
		    	
		    }
		    
		//    batch.draw(seleccionar, anchoPantalla*39/100, altoPantalla *42/100 - (altoPantalla*5/100 * commandnum));
	 
		    if(code.moverIzquierda == Keys.A) {
		    	
		    	stage.getBatch().draw(code.manager.get("Menus/circulo.png", Texture.class), anchoPantalla*40/100, altoPantalla*42/100 - (altoPantalla*7/100)*commandnum, anchoBoton/3, altoBoton);  // Modo ventana
		    	
		    }else {
		    	
		    	stage.getBatch().draw(code.manager.get("Menus/circulo.png", Texture.class), anchoPantalla*40/100, altoPantalla*42/100 - (altoPantalla*7/100)*commandnum, anchoBoton/3, altoBoton);  // Modo ventana
		    	
		    }
		    
		    switch(commandnum) {
		    case 0:
		    	
		    	if(Gdx.input.isKeyPressed(Keys.ENTER)) {
	    			
		    		code.moverIzquierda = arrows ? Keys.LEFT : Keys.A;
		    		code.moverDerecha = arrows ? Keys.RIGHT : Keys.D;
		    		code.moverArriba = arrows ? Keys.UP : Keys.W;
		    		code.frenar = arrows ? Keys.DOWN : Keys.S;

		    		 
		    		code.setScreen(new MainMenuScreen(code));
		    		 
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
		    		code.setScreen(new MainMenuScreen(code));
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
