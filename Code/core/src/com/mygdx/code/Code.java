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
	Music music;
	private MyInputProcessor inputProcessor;
	Pais paisSeleccionado;
	Barco barcoSeleccionado;

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
		batch = new SpriteBatch();
		/**
		 * Quita el puntero del ratï¿½n
		 */
//		Gdx.input.setCursorCatched(true);
		/**
		 * Quita la interacciï¿½n con el ratï¿½n
		 */
//		inputProcessor = new MyInputProcessor();
//		Gdx.input.setInputProcessor(inputProcessor);
		/**
		 * Activar para escuchar mï¿½sica, para probarlo hay que poner el nombre del
		 * fichero .ogg en la carpeta sonidos dentro de assets
		 */
//		music = Gdx.audio.newMusic(Gdx.files.internal("sonidos/juego.ogg"));
//		music.setLooping(true);
//		music.play();

		manager = new AssetManager();
		manager.load("Fondo_Inicio.jpg", Texture.class);
		manager.finishLoading();
		setScreen(new PantallaDeInicio(this));
		paisSeleccionado = new Pais("ES", "España", "espana.png");
		barcoSeleccionado = new Barco(new TipoBarco(5f, 5f, "barquito.png", 10f, 10f), null);

	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
