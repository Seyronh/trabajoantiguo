package com.mygdx.code;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;

import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import com.badlogic.gdx.audio.Music;
import javax.sound.sampled.Mixer;

public class Code extends Game {
	SpriteBatch batch;
	AssetManager manager;
  	int dificultad;
	Music music;
	public float volumen;
	private MyInputProcessor inputProcessor;
	Pais paisSeleccionado;
	TipoBarco tipoBarcoSeleccionado;
	ArrayList<TipoBarco> tipoBarcos = new ArrayList(); // Lista de tipos de barcos a mostrar
	ArrayList<Pais> paises = new ArrayList(); // Lista de paises a mostrar

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
		dificultad = 2;
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

		manager = new AssetManager();
		manager.load("Fondo_Inicio.jpg", Texture.class);
		manager.finishLoading();
		paisSeleccionado = new Pais("ES", "España", "espana.png");
		tipoBarcoSeleccionado = new TipoBarco(5f, 5f, "barcoNormal.png", 5f, 5f);
		cargarPaises();
		cargarTiposBarcos();
		setScreen(new PantallaDeInicio(this));
	}
	
	// Método que añade todos los tipos de barcos al array

	private void cargarTiposBarcos() {
		tipoBarcos.add(new TipoBarco(5f, 5f, "barcoNormal.png", 5f, 5f));
		tipoBarcos.add(new TipoBarco(3f, 8f, "barcoMovilidad.png", 6f, 3f));
		tipoBarcos.add(new TipoBarco(2f, 5f, "barcoVida.png", 10f, 3f));
		tipoBarcos.add(new TipoBarco(4f, 3f, "barcoSpeed.png", 5f, 8f));
		tipoBarcos.add(new TipoBarco(8f, 6f, "barcoAceleracion.png", 3f, 3f));
		// TODO meter los barcos
	}
	
	// Método que añade todos los países al array para mostrarlos
		private void cargarPaises() {
			paises.add(new Pais("ES", "España", "espana.png"));
			paises.add(new Pais("CH", "China", "china.png"));
			paises.add(new Pais("JP", "Japon", "japon.png"));
			paises.add(new Pais("CS", "Corea del Sur", "coreaSur.png"));
			paises.add(new Pais("BR", "Brasil", "brasil.png"));
			paises.add(new Pais("RU", "Reino Unido", "reinoUnido.png"));
			paises.add(new Pais("ID", "Indonesia", "indonesia.png"));
			paises.add(new Pais("AU", "Australia", "australia.png"));
			paises.add(new Pais("EU", "Estados Unidos", "estadosUnidos.png"));
			paises.add(new Pais("RU", "Rusia", "rusia.png"));
			paises.add(new Pais("SD", "Sudáfrica", "sudafrica.png"));
			paises.add(new Pais("BO", "Bolivia", "bolivia.png"));
			paises.add(new Pais("AL", "Alemania", "alemania.png"));
			paises.add(new Pais("FR", "Francia", "francia.png"));
			paises.add(new Pais("CHAD", "Chad", "chad.png"));
			paises.add(new Pais("NIG", "Nigeria", "nigeria.png"));
			paises.add(new Pais("CM", "Costa de Marfil", "costaMarfil.png"));
			paises.add(new Pais("CAM", "Camerún", "camerun.png"));
			paises.add(new Pais("GR", "Grecia", "grecia.png"));
			paises.add(new Pais("EG", "Egipto", "egipto.png"));
			paises.add(new Pais("SU", "Suecia", "suecia.png"));
			paises.add(new Pais("SUI", "Suiza", "suiza.png"));
			paises.add(new Pais("CA", "Canada", "canada.png"));
			paises.add(new Pais("MX", "México", "mexico.png"));
			paises.add(new Pais("AR", "Argentina", "argentina.png"));
			paises.add(new Pais("CU", "Cuba", "cuba.png"));
			paises.add(new Pais("SL", "SriLanka", "sriLanka.png"));
			paises.add(new Pais("MAU", "Islas Mauricio", "mauricio.png"));
			paises.add(new Pais("MA", "Madagascar", "madagascar.png"));
			paises.add(new Pais("VA", "Vaticano", "vaticano.png"));
			paises.add(new Pais("IT", "Italia", "italia.png"));
			paises.add(new Pais("IN", "India", "india.png"));
		}

	@Override
	public void dispose() {
		batch.dispose();
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
		
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}
