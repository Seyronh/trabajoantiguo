package com.mygdx.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
	
	
	public OpcionesControles(Code game) {
		
		this.game = game;
		
		anchoPantalla = Gdx.graphics.getWidth();
		altoPantalla = Gdx.graphics.getHeight();
		
	}
	
	
	public void cargarPantalla() {
		
		
		//OPCIONES SONIDO
		
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
//	    stage.addActor(table2);
	    stage.addActor(table2);
		
		
		//Boton Volver
		
				TextButtonStyle styleb = new TextButtonStyle();
				
				
				Texture buttondown = new Texture("botondownplchld.png");
				Texture buttonup = new Texture("botonplchld.png");
				
				styleb.down = new TextureRegionDrawable(new TextureRegion(buttondown));
				styleb.up = new TextureRegionDrawable(new TextureRegion(buttonup));
				
				styleb.font = new BitmapFont();
				styleb.fontColor = Color.BLACK;
				
				TextButton buttonVolver = new TextButton("Volver", styleb); //TEXTURA
				buttonVolver.setPosition(anchoPantalla*45/100, altoPantalla*50/100);
				buttonVolver.setWidth(200);
				buttonVolver.setHeight(40);
				buttonVolver.addListener(new InputListener() {
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						return true;	
					}
					public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
						
						dispose();
						
						game.setScreen(new Opciones(game));
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
		batch.begin();
	    batch.draw(Fondo, 0, 0, anchoPantalla, altoPantalla);
	    batch.draw(Tabla, anchoPantalla*38/100, altoPantalla*3/10, anchoPantalla/4, altoPantalla*6/10);

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
