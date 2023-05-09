package com.mygdx.code;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import javax.sound.sampled.AudioSystem;
import com.badlogic.gdx.audio.Music;
import javax.sound.sampled.Mixer;

public class Code extends Game {
	SpriteBatch batch;
  AssetManager manager;
  	int dificultad;
	Music music;
	private MyInputProcessor inputProcessor;

	
	
	
	//////

	public int moverIzquierda = Keys.A;
	public int moverDerecha = Keys.D;
	public int moverArriba = Keys.W;
	public int frenar = Keys.S;
	
	public int usarPowerUp = Keys.SPACE; // Provisional
	
	
	public Code() {

		super();
	}

	@Override
	public void create() {
		dificultad = 1;
		batch = new SpriteBatch();
    		/**
		 * Quita el puntero del rat�n
		 */
//		Gdx.input.setCursorCatched(true);
		/**
		 * Quita la interacci�n con el rat�n
		 */
//		inputProcessor = new MyInputProcessor();
//		Gdx.input.setInputProcessor(inputProcessor);
		/**
		 * Activar para escuchar m�sica, para probarlo hay que poner el nombre del
		 * fichero .ogg en la carpeta sonidos dentro de assets
		 */
//		music = Gdx.audio.newMusic(Gdx.files.internal("sonidos/juego.ogg"));
//		music.setLooping(true);
//		music.play();

		manager = new AssetManager();
		manager.load("Fondo_Inicio.jpg", Texture.class);
		manager.finishLoading();
		setScreen(new PantallaDeInicio(this));

	}
  
	@Override
	public void dispose() {
		batch.dispose();
	}
}
