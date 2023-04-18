package com.mygdx.code;

import javax.swing.JComponent;
import javax.swing.plaf.nimbus.State;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
	
	private float anchopantalla;
	private float altopantalla;
	
	// Almacena las preferencias (en %UserProfile%/.prefs/PreferencesName)
	//Preferences prefs;
	
	public Opciones(final Code juego) {

		this.juego = juego;
		
	}
	
	
	private void cargarPantalla() {
		
		stage = new Stage();
		
		// Crea una tabla, donde añadiremos los elementos de menú
		Table table = new Table();
		table.setPosition(anchopantalla, altopantalla);
		// La tabla ocupa toda la pantalla
	    table.setFillParent(true);
	    table.setHeight(500);
	    stage.addActor(table);
		
		Label label = new Label("Opciones", textura);  //TEXTURA
		table.addActor(label);
		
		
		final CheckBox checkSound = new CheckBox(" Sonido", textura); //TEXTURA
		
		
		//checkSound.setChecked(prefs.getBoolean("sound"));
		
		
		checkSound.setPosition(label.getOriginX(), label.getOriginY() - 40);
		checkSound.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;	
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				
			//	prefs.putBoolean("sound", checkSound.isChecked());
			}
		});
		
		
		//Boton Volver
		
		TextButton buttonMainMenu = new TextButton("Volver", textura); //TEXTURA
		buttonMainMenu.setPosition(label.getOriginX(), label.getOriginY() - 220);
		buttonMainMenu.setWidth(200);
		buttonMainMenu.setHeight(40);
		buttonMainMenu.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;	
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				
			//	prefs.flush();
				dispose(); //Volver a MainMenu
			//	game.setScreen(new MainMenuScreen(game));
			}
		});
		table.addActor(buttonMainMenu);
		
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
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		
        
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
