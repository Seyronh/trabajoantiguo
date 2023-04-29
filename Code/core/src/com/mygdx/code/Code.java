package com.mygdx.code;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;

public class Code extends Game {
	SpriteBatch batch;
	Texture img;
	Sprite barquito;
	Barco boat;
	TipoBarco elegido;
	Music music;
	private MyInputProcessor inputProcessor;

	
	
	
	//////

	public int moverizq = Keys.A;

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
		 * Activar para escuchar m�sica, para probarlo hay que poner el nombre del
		 * fichero .ogg en la carpeta sonidos dentro de assets
		 */
//		music = Gdx.audio.newMusic(Gdx.files.internal("sonidos/juego.ogg"));
//		music.setLooping(true);
//		music.play();
		setScreen(new MainMenuScreen(this));
		/*
		 * Vector2 pos = new Vector2(100,100); img = new Texture("barquito.png");
		 * barquito = new Sprite(img, 1024, 1024); barquito.setScale(0.3f); elegido =
		 * new TipoBarco(10f, 10f, "Neutro", 10f, 20f); boat = new Barco(elegido, pos);
		 * 
		 * setScreen(new MainMenuScreen(this));
		 */

		/**
		 * Quita la interacci�n con el rat�n
		 */
		inputProcessor = new MyInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);

		/**
		 * Quita el puntero del rat�n
		 */
		Gdx.input.setCursorCatched(true);
	}

	/*
	 * @Override public void render() {
	 * 
	 * super.render(); //ScreenUtils.clear(1, 1, 1, 1); if
	 * (Gdx.input.isKeyPressed(moverizq)) { boat.girarIzquierda(); } // if
	 * (Gdx.input.isKeyPressed(Keys.D)) { // boat.girarDerecha(); // } // if
	 * (Gdx.input.isKeyPressed(Keys.W)) { // boat.acelerar(); // } // if
	 * (Gdx.input.isKeyPressed(Keys.S)) { // boat.frenar(false); // } else { //
	 * boat.frenar(true); // } //
	 * 
	 * // // boat.actualizarposicion(); // barquito.setPosition(boat.posicion.x,
	 * boat.posicion.y); // barquito.setRotation(boat.angulo - 90); //
	 * batch.begin(); // barquito.draw(batch); // // batch.end();
	 * 
	 * // setScreen(new Opciones(this));
	 * 
	 * 
	 * 
	 * }
	 */
	@Override
	public void dispose() {
		batch.dispose();
		// img.dispose();
	}
}
