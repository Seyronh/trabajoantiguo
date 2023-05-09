package com.mygdx.code;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.plaf.nimbus.State;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

public class Opciones implements Screen {

	final Code game;
	
	private Stage stage;
	
	private float anchoPantalla;
	private float altoPantalla;
	
	private SpriteBatch batch;
	private Texture Fondo;
	private Texture Tabla;
	
	private Texture seleccionar;
	
	private int commandnum;
	
	float AnchoBoton;
    float AltoBoton;

	// Almacena las preferencias (en %UserProfile%/.prefs/PreferencesName)
	//Preferences prefs;
	
	public Opciones(final Code game) {

		this.game = game;
		anchoPantalla = Gdx.graphics.getWidth();
		altoPantalla = Gdx.graphics.getHeight();
		
		
		commandnum = 0;
		
		AnchoBoton = anchoPantalla *15/100;
		AltoBoton = altoPantalla *10/100;
		
		seleccionar = new Texture("seleccionar.png");
		//cargarPantalla();
	}
	
	
	private void cargarPantalla() {
		
		
		
//		textura = new Skin();
//		
//		textura.add("boton",new Texture("bronce.png"));
//		
//		textura.add("botonp",new Texture("botonplchld.png"));
//		textura.add("botonp2",new Texture("botondownplchld.png"));
//		textura.add("botonp3",new Texture("botoncheckedplchld.png"));
		
		
		
		// Crea una tabla, donde añadiremos los elementos de menú
//		Table table2 = new Table();
//		
//		table2.setPosition(0, 300);
//		// La tabla ocupa toda la pantalla
//	    table2.setFillParent(true);
//	    table2.setHeight(altoPantalla);
	    
//	    Label hpLabel = new Label("",new Label.LabelStyle(new BitmapFont(),Color.BLACK));
//	    hpLabel.setFontScale(2, 2);
//	    table2.add(hpLabel);

	    	stage = new Stage();
			batch = new SpriteBatch();
			
			
			Fondo = new Texture("fondoMenuPrincipal.png");
			Tabla = new Texture("Menu.png");
			
			
	    	
			Table table = new Table();
		    table.setPosition(0, 0);
			// La tabla ocupa toda la pantalla
		    table.setFillParent(true);
		    table.setHeight(altoPantalla);


			//table.addActor(boton);
//		    stage.addActor(table2);
		    stage.addActor(table);
		    
		    float BotonX = anchoPantalla * 47 /100;
		    float BotonY = altoPantalla * 70/100;
		    
		    
		    
	    	
	    	TextButtonStyle styleb = new TextButtonStyle();
			
			
			Texture buttondown = new Texture("botondownplchld.png");
			Texture buttonup = new Texture("botonplchld.png");

			
			styleb.down = new TextureRegionDrawable(new TextureRegion(buttondown));
			styleb.up = new TextureRegionDrawable(new TextureRegion(buttonup));
		
			
			styleb.font = new BitmapFont();
			styleb.fontColor = Color.BLACK;
			
			TextButton buttonControles = new TextButton("Controles", styleb); //TEXTURA
			buttonControles.setPosition(BotonX, BotonY);

			
			buttonControles.setSize(AnchoBoton, AltoBoton);
			buttonControles.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;	
				}
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					
			
					dispose();
					
					
					game.setScreen(new OpcionesControles(game));
				}
			});
			table.addActor(buttonControles);
			
			BotonY = BotonY - anchoPantalla*10/100;
			
			
			TextButton buttonSonido = new TextButton("Sonido", styleb); //TEXTURA
			buttonSonido.setPosition(BotonX, BotonY);

			buttonSonido.setSize(AnchoBoton, AltoBoton);
			buttonSonido.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;	
				}
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					
					dispose();
					
					
					game.setScreen(new OpcionesSonido(game));
					
				}
			});
			table.addActor(buttonSonido);
			
			BotonY = BotonY - anchoPantalla*10/100;
			
			//Boton Volver
			
			TextButton buttonMainMenu = new TextButton("Volver", styleb); //TEXTURA
			buttonMainMenu.setPosition(BotonX, BotonY);

			buttonMainMenu.setSize(AnchoBoton, AltoBoton);
			buttonMainMenu.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;	
				}
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					
			//	prefs.flush();
					dispose(); //Volver a MainMenu
					game.setScreen(new MainMenuScreen(game));
				}
			});
			table.addActor(buttonMainMenu);
		    
		//	BotonY = BotonY - anchoPantalla*15/100;
			
//		    BitmapFont font = new BitmapFont();
//		    TextureAtlas  buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack"));
//	        textura.addRegions(buttonAtlas);
//	        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
//	        textButtonStyle.font = font;
//	        textButtonStyle.up = textura.getDrawable("up-button");
//	        textButtonStyle.down = textura.getDrawable("down-button");
//	        textButtonStyle.checked = textura.getDrawable("checked-button");
//	        TextButton button = new TextButton("Button1", textButtonStyle);

	  

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
	//	Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
    		
    		
    		game.setScreen(new MainMenuScreen(game));
    	}
		
		
		batch.begin();
		
			
	    batch.draw(Fondo, 0, 0, anchoPantalla, altoPantalla);
	    batch.draw(Tabla, anchoPantalla*38/100, altoPantalla*3/10, anchoPantalla/4, altoPantalla*6/10);
	    
	//    batch.draw(Tabla, anchoPantalla*35/100, altoPantalla*30/100, anchoPantalla*45/100, altoPantalla*65/100);
	    
	    
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
	    
	    	batch.draw(seleccionar, anchoPantalla*39/100, altoPantalla*70/100 - altoPantalla*18/100*commandnum, AnchoBoton/2, AltoBoton); //Pantalla completa
		    
	     
	    }else {
	    	batch.draw(seleccionar, anchoPantalla*39/100, altoPantalla*70/100 - altoPantalla*13/100*commandnum);  // Modo ventana
	 	   
	    	
	    }
	    
	    switch(commandnum) {
	    case 0:
	    	
	    	
	    	if(Gdx.input.isKeyPressed(Keys.ENTER)) {
	    		
	    		game.setScreen(new OpcionesControles(game));
	    	}
	    	break;
	    case 1:
	    	
	    	if(Gdx.input.isKeyPressed(Keys.ENTER)) {
	    		
	    		game.setScreen(new OpcionesSonido(game));
	    	}
	    	break;
	    case 2:
	    	if(Gdx.input.isKeyPressed(Keys.ENTER)) {
	    		
	    		
	    		game.setScreen(new MainMenuScreen(game));
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
		cargarPantalla();
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
