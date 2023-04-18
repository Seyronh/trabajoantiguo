package com.mygdx.code;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.plaf.nimbus.State;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;

public class Opciones implements Screen {

	final Code juego;
	
	private Stage stage;
	
	private Skin textura;
	
	private float anchoPantalla;
	private float altoPantalla;
	
	private SpriteBatch batch;
	private Texture img;
	
	// Almacena las preferencias (en %UserProfile%/.prefs/PreferencesName)
	//Preferences prefs;
	
	public Opciones(final Code juego) {

		this.juego = juego;
		anchoPantalla = Gdx.graphics.getWidth();
		altoPantalla = Gdx.graphics.getHeight();
		
		cargarPantalla();
	}
	
	
	private void cargarPantalla() {
		
		stage = new Stage();
		batch = new SpriteBatch();
		img = new Texture("fondoMenuPrincipal.png");
		textura = new Skin();
		textura.add("boton", "bronce.png");
		
		// Crea una tabla, donde añadiremos los elementos de menú
		Table table = new Table();
		table.setPosition(0, 0);
		// La tabla ocupa toda la pantalla
	    table.setFillParent(true);
	    table.setHeight(altoPantalla);
	    
	    Label hpLabel = new Label("PRUEBA: ",new Label.LabelStyle(new BitmapFont(),Color.BLACK));
	    table.add(hpLabel);
	    stage.addActor(table);
		
	    //Texture boton = textura.get("boton", Texture.class);
		//Label label = new Label("Opciones", textura.get("prueba", Texture.class));  //TEXTURA
		//table.addActor(boton);
//		
//		
//		final CheckBox checkSound = new CheckBox(" Sonido", textura); //TEXTURA
//		
//		
//		//checkSound.setChecked(prefs.getBoolean("sound"));
//		
//		
//		checkSound.setPosition(label.getOriginX(), label.getOriginY() - 40);
//		checkSound.addListener(new InputListener() {
//			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//				return true;	
//			}
//			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//				
//			//	prefs.putBoolean("sound", checkSound.isChecked());
//			}
//		});
//		
		
		//Boton Volver
		
//		TextButton buttonMainMenu = new TextButton("Volver", textura); //TEXTURA
//		buttonMainMenu.setPosition(label.getOriginX(), label.getOriginY() - 220);
//		buttonMainMenu.setWidth(200);
//		buttonMainMenu.setHeight(40);
//		buttonMainMenu.addListener(new InputListener() {
//			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//				return true;	
//			}
//			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//				
//			//	prefs.flush();
//				dispose(); //Volver a MainMenu
//			//	game.setScreen(new MainMenuScreen(game));
//			}
//		});
//		table.addActor(buttonMainMenu);
	    
//	    BitmapFont font = new BitmapFont();
//	    TextureAtlas  buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack"));
//        textura.addRegions(buttonAtlas);
//        TextButtonStyle textButtonStyle = new TextButtonStyle();
//        textButtonStyle.font = font;
//        textButtonStyle.up = textura.getDrawable("up-button");
//        textButtonStyle.down = textura.getDrawable("down-button");
//        textButtonStyle.checked = textura.getDrawable("checked-button");
//        TextButton  button = new TextButton("Button1", textButtonStyle);
//        stage.addActor(button);
	   
		Gdx.input.setInputProcessor(stage);
		
	    batch.begin();
	    batch.draw(img, 0, 0, anchoPantalla, altoPantalla);
        batch.end();
        
		stage.draw();
		
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		cargarPantalla();
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		
		stage.act(delta);
		
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
