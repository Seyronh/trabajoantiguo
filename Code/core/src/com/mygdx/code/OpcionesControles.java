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
	
	public OpcionesControles(final Code game) {

		this.game = game;
		anchoPantalla = Gdx.graphics.getWidth();
		altoPantalla = Gdx.graphics.getHeight();
		
		
		commandnum = 0;
		
		arrows = game.moverIzquierda == Keys.LEFT;;
		
		AnchoBoton = anchoPantalla *15/100;
		AltoBoton = altoPantalla *5/100;
		
		seleccionar = new Texture("Menus/madera.png");
		//cargarPantalla();
		
		
		
	}
	
	
	public void cargarPantalla() {
		
		
		
		float BotonX = anchoPantalla * 47 /100;
	    float BotonY = altoPantalla * 42/100;
	    
	    
		
		
		//OPCIONES CONTROLES
		
	    stage = new Stage();
   		batch = new SpriteBatch();
		
		Fondo = new Texture("fondoMenuPrincipal.png");
		Tabla = new Texture("Menu.png");
		
		
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
//			BotonY = BotonY - altoPantalla*7/100;
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
		
		
		
		
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {

			dispose();
			
			game.setScreen(new MainMenuScreen(game));
		}
		
		
		
	    
		stage.act(Gdx.graphics.getDeltaTime());
		
		stage.getBatch().begin();
		
		stage.getBatch().draw(Fondo, 0, 0, anchoPantalla, altoPantalla);
		    
		stage.getBatch().draw(Tabla, anchoPantalla * 335 / 1000, altoPantalla * 25 / 100, anchoPantalla*32/100, altoPantalla * 65/100);

		    

			float medidax = anchoPantalla*40/100;
		    float mediday =  altoPantalla*70/100;
		    
		    
		    stage.getBatch().draw(new Texture("Menus/mapeado.png"), medidax, mediday - altoPantalla*25/100, anchoPantalla*20/100, altoPantalla*30/100);	  
			
		    if(!arrows) {
		    	
		    	
		    	stage.getBatch().draw(new Texture("Menus/opcion1.png"), medidax + anchoPantalla*6/100, altoPantalla*48/100, anchoPantalla*20/100, altoPantalla*7/100*4);
		    	
		    	
		    }else {
	    	
		    	stage.getBatch().draw(new Texture("Menus/opcion2.png"), medidax + anchoPantalla*14/100, altoPantalla*50/100, anchoPantalla*14/100/367*100,anchoPantalla*14/100 );
		    	
		    	
		    }
			
		    
		    
//			mediday = mediday - altoPantalla*7/100;
//			
//			stage.getBatch().draw(new Texture("Menus/usar.png"), medidax, mediday, anchoPantalla*10/100, altoPantalla*6/100);
//			
//			stage.getBatch().draw(new Texture("Menus/espacio.png"), medidax + anchoPantalla*8/100, mediday, anchoPantalla*13/100, altoPantalla*5/100);
//		    
			mediday = altoPantalla*40/100;
		    
			stage.getBatch().draw(new Texture("Menus/1.png"), medidax + anchoPantalla*8/100, mediday, anchoPantalla*10/100, altoPantalla*6/100);
			
			stage.getBatch().draw(new Texture("Menus/2.png"), medidax + anchoPantalla*12/100, mediday, anchoPantalla*10/100, altoPantalla*6/100);
			
			mediday = mediday - altoPantalla*5/100;
			
			stage.getBatch().draw(new Texture("Menus/Volver.png"), medidax + anchoPantalla*5/100, mediday, anchoPantalla*10/100, altoPantalla*6/100);
			
		    
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
				
				desplazamiento = AnchoBoton*29/100;
			}
		    
		    if(Gdx.graphics.isFullscreen()) {
			    
		    	if(commandnum == 0) {
		    	
		    		stage.getBatch().draw(seleccionar, anchoPantalla*47/100 + desplazamiento, altoPantalla*41/100 - altoPantalla*18/100*commandnum, AnchoBoton/2, AltoBoton); //Pantalla completa
		    	
		    	}else {
		    		
		    		stage.getBatch().draw(seleccionar, anchoPantalla*39/100 + 35, altoPantalla*42/100 - altoPantalla*7/100*commandnum, AnchoBoton/2, AltoBoton); //Pantalla completa
			    	
		    	}
		     
		    }else {
		    	
		    	if(commandnum == 0) {
		    	
		    		
		    		stage.getBatch().draw(seleccionar, anchoPantalla*40/100 + desplazamiento, altoPantalla*42/100 - (altoPantalla*7/100)*commandnum, AnchoBoton/3, AltoBoton);  // Modo ventana
		    	}else {
		    		
		    		stage.getBatch().draw(seleccionar, anchoPantalla*40/100, altoPantalla*42/100 - (altoPantalla*7/100)*commandnum, AnchoBoton/3, AltoBoton);  // Modo ventana
			    	
		    	}
		    	
		    }
		    
		//    batch.draw(seleccionar, anchoPantalla*39/100, altoPantalla *42/100 - (altoPantalla*5/100 * commandnum));
	 
		    if(game.moverIzquierda == Keys.A) {
		    	
		    	stage.getBatch().draw(new Texture("Menus/circulo.png"), anchoPantalla*40/100, altoPantalla*42/100 - (altoPantalla*7/100)*commandnum, AnchoBoton/3, AltoBoton);  // Modo ventana
		    	
		    }else {
		    	
		    	stage.getBatch().draw(new Texture("Menus/circulo.png"), anchoPantalla*40/100, altoPantalla*42/100 - (altoPantalla*7/100)*commandnum, AnchoBoton/3, AltoBoton);  // Modo ventana
		    	
		    }
		    
		    switch(commandnum) {
		    case 0:
		    	
		    	if(Gdx.input.isKeyPressed(Keys.ENTER)) {
	    			
		    		 game.moverIzquierda = arrows ? Keys.LEFT : Keys.A;
		    		 game.moverDerecha = arrows ? Keys.RIGHT : Keys.D;
		    		 game.moverArriba = arrows ? Keys.UP : Keys.W;
		    		 game.frenar = arrows ? Keys.DOWN : Keys.S;

		    		 
		    		 game.setScreen(new MainMenuScreen(game));
		    		 
		    	}
		    		
		    	
		    	
		    	if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
		    		game.moverIzquierda = Keys.LEFT;
		    		
		    		
		    		arrows = true;
		    	}
		    	if(Gdx.input.isKeyPressed(Keys.LEFT)) {
		    		game.moverIzquierda = Keys.A;
		    		
		    		arrows = false;
		    	}
		    	
		    	
		    	
		    	
		    	break;
		    case 1:
		    	
		    	if(Gdx.input.isKeyPressed(Keys.ENTER)) {
		    		game.setScreen(new MainMenuScreen(game));
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
