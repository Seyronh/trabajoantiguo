package com.mygdx.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MainMenuScreen implements Screen {

	final Code game;
	
	private Stage stage;
	
	private Skin textura;
	
	private Sprite nose;
	
	
	private float anchopantalla;
	private float altopantalla;
	
	public MainMenuScreen(Code game) {
		this.game = game;
		
	}
	
	private void cargarPantalla() {
		
		
			
	
			// Grafo de escena que contendrá todo el menú
			stage = new Stage();
						
			// Crea una tabla, donde añadiremos los elementos de menú
			Table table = new Table();
			table.setPosition(anchopantalla, altopantalla);
			// La tabla ocupa toda la pantalla
		    table.setFillParent(true);
		    table.setHeight(500);
		    stage.addActor(table);
		    
		    
		    // Etiqueta de texto
			Label label = new Label("Dragon Boat Racing Simulator",textura );  //TEXTURA
			table.addActor(label);
			
			
			// Botón Play
			TextButton buttonPlay = new TextButton("Play", textura);   // TEXTURA
			buttonPlay.setPosition(label.getOriginX(), label.getOriginY() - 120);
			buttonPlay.setWidth(200);
			buttonPlay.setHeight(40);
			buttonPlay.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					
					dispose(); //Pasar a jugar
				    //	game.setScreen(new GameScreen(game, GameType.QUICK));
				}
			});
			table.addActor(buttonPlay);
			
			/*
			
			if (Gdx.input.isTouched()) {
				game.setScreen(new GameScreen(game));
				dispose();
			}
			
			*/
			
			// Botón Opciones
			TextButton buttonConfig = new TextButton("Opciones", textura);  //TEXTURA
			buttonConfig.setPosition(label.getOriginX(), label.getOriginY() - 220);
			buttonConfig.setWidth(200);
			buttonConfig.setHeight(40);
			buttonConfig.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					
					dispose(); //Pasar a opciones
				//	game.setScreen(new Opciones(game));
				}
			});
			table.addActor(buttonConfig);
			
			//Salir 
			
			// Botón
			TextButton buttonQuit = new TextButton("Salir", textura);  //TEXTURA
			buttonQuit.setPosition(label.getOriginX(), label.getOriginY() - 270);
			buttonQuit.setWidth(200);
			buttonQuit.setHeight(40);
			buttonQuit.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;	
				}
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					
					game.dispose();
					System.exit(0);
				}
			});
			table.addActor(buttonQuit);
			
			
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
		
		//Pintar menú
		
		
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
