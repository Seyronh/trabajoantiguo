package com.mygdx.code;

import java.util.ArrayList;

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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class SeleccionBarco implements Screen {

	final Code game;

	private Stage stage;

	private float anchoPantalla;
	private float altoPantalla;

	private SpriteBatch batch;
	private Texture fondo;
	private Texture tabla;
	private Texture imagenPais;
	private Texture imagenBarco;

	private Texture seleccionarIzquierda;
	private Texture seleccionarDerecha;
	private Texture textoSeleccion;

	private int commandnum;

	private boolean arrows;
	private ArrayList<Pais> paises = new ArrayList(); // Lista de paises a mostrar
	private ArrayList<Barco> barcos = new ArrayList(); // Lista de barcos a mostrar
	private int posPais, posBarco; // Posición cogida de país y barco del array

	float anchoBoton;
	float altoBoton;
	// Almacena las preferencias (en %UserProfile%/.prefs/PreferencesName)
	// Preferences prefs;

	public SeleccionBarco(final Code game) {

		this.game = game;
		anchoPantalla = Gdx.graphics.getWidth();
		altoPantalla = Gdx.graphics.getHeight();

		commandnum = 0;

		arrows = true;

		anchoBoton = anchoPantalla * 25 / 100;
		altoBoton = altoPantalla * 10 / 100;

		seleccionarIzquierda = new Texture("flechaSeleccionIzquierda.png");
		seleccionarDerecha = new Texture("flechaSeleccionDerecha.png");
		textoSeleccion = new Texture("textoSeleccionBarcoPais.png");
		
		cargarPaises();
		cargarBarcos();
		
		if (game.paisSeleccionado != null) {
			boolean encontrado = false;
			for(int i = 0; (i < paises.size()) && !encontrado; i++) {
				if (paises.get(i).getIdPais().equalsIgnoreCase(game.paisSeleccionado.getIdPais())) {
					posPais = i;
				}
			}
		}
		
		if (game.barcoSeleccionado != null) {
			boolean encontrado = false;
			for(int i = 0; (i < barcos.size()) && !encontrado; i++) {
				if (barcos.get(i).elegido.barco.equalsIgnoreCase(game.barcoSeleccionado.elegido.barco)) {
					posBarco = i;
				}
			}
		}
		// cargarPantalla();
	}

	public void cargarPantalla() {

		float BotonX = (anchoPantalla * 50 / 100) - (anchoBoton / 2);
		float BotonY = altoPantalla * 50 / 100;

		// OPCIONES CONTROLES

		stage = new Stage();
		batch = new SpriteBatch();

		
		fondo = new Texture("fondoMenuPrincipal.png");
		new Texture("pais" + posPais + ".png");
		imagenPais = new Texture("pais" + posPais + ".png");
		
		/**
		 * Manera correcta de coger el barco, aún no tenemos
		 */
		//imagenBarco = new Texture("barco" + posBarco + ".png");
		imagenBarco = new Texture("barquito.png");
		
		
		
		// Tabla = new Texture("opciones.png");

		Table table2 = new Table();
		table2.setPosition(0, 0);
		// La tabla ocupa toda la pantalla
		table2.setFillParent(true);
		table2.setHeight(altoPantalla);
		stage.addActor(table2);

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

		// SALIR
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {

			game.setScreen(new MainMenuScreen(game));
		}
		
		// Inicia Juego
		if (Gdx.input.isKeyPressed(Keys.ENTER)) {

			game.setScreen(new PantallaPartida(game));
		}


		batch.begin();
		batch.draw(fondo, 0, 0, anchoPantalla, altoPantalla);
		batch.draw(imagenPais, anchoPantalla * 35/100, altoPantalla*6/10, anchoPantalla/4, altoPantalla/4);
		batch.draw(imagenBarco, anchoPantalla * 35/100, altoPantalla*15/100, anchoPantalla/4, altoPantalla/4);
		// batch.draw(Tabla, anchoPantalla*38/100, altoPantalla*3/10, anchoPantalla/4,
		// altoPantalla*6/10);

		if (Gdx.input.isKeyPressed(Keys.DOWN)) {

			commandnum++;

			if (commandnum > 1) {

				commandnum = 0;
			}

		}
		if (Gdx.input.isKeyPressed(Keys.UP)) {

			commandnum--;

			if (commandnum < 0) {

				commandnum = 1;
			}

		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			if (commandnum == 0) {
				posPais++;

				if (posPais >= paises.size()) {
					posPais = 0;
				}

				game.paisSeleccionado = paises.get(posPais);
				imagenPais = new Texture("pais" + posPais + ".png");
				
			} else {
				posBarco++;

				if (posBarco >= barcos.size()) {
					posBarco = 0;
				}

				game.barcoSeleccionado = barcos.get(posBarco);
				/**
				 * Manera correcta de coger el barco, aún no tenemos
				 */
				//imagenBarco = new Texture("barco" + posBarco + ".png");
				imagenBarco = new Texture("barquito.png");
			}
		}
		
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			if (commandnum == 0) {
				posPais--;

				if (posPais < 0) {
					posPais = (paises.size() - 1);
				}

				game.paisSeleccionado = paises.get(posPais);
				imagenPais = new Texture("pais" + posPais + ".png");
			} else {
				posBarco--;

				if (posBarco < 0) {
					posBarco = (barcos.size() - 1);
				}

				game.barcoSeleccionado = barcos.get(posBarco);
				
				/**
				 * Manera correcta de coger el barco, aún no tenemos
				 */
				//imagenBarco = new Texture("barco" + posBarco + ".png");
				imagenBarco = new Texture("barquito.png");
			}
		}


		if (Gdx.graphics.isFullscreen()) {

			batch.draw(seleccionarIzquierda, (anchoPantalla * 52 / 100) - (anchoBoton),
					((altoPantalla * 52 / 100) + (altoPantalla * 15 / 100)) - altoPantalla * 15 / 100 * commandnum*3,
					anchoBoton / 4, altoBoton); // Pantalla completa
			
			batch.draw(seleccionarDerecha, (anchoPantalla * 51 / 100) + (anchoPantalla/9),
					((altoPantalla * 52 / 100) + (altoPantalla * 15 / 100)) - altoPantalla * 15 / 100 * commandnum*3,
					anchoBoton / 4, altoBoton);
			
			batch.draw(textoSeleccion, anchoPantalla * 23 / 100, altoPantalla * 88 / 100,anchoBoton * 2, altoBoton);

		} else {
			batch.draw(seleccionarIzquierda, anchoPantalla * 37 / 100,
					altoPantalla * 42 / 100 - altoPantalla * 15 / 100 * commandnum*3); // Modo ventana

		}

		switch (commandnum) {
		case 0:

			if (Gdx.input.isKeyPressed(Keys.ENTER)) {

				game.setScreen(new PantallaPartida(game));
			}
			break;
		case 1:

			if (Gdx.input.isKeyPressed(Keys.ENTER)) {

				game.setScreen(new Opciones(game));
			}
			break;

		}

		batch.end();

		stage.act(delta);
		stage.draw();
	}

	// Método que añade todos los países al array para mostrarlos
	private void cargarPaises() {
		paises.add(new Pais("ES", "España", "pais0.png"));
		paises.add(new Pais("CH", "China", "pais1.png"));
		// TODO meter el resto de países
	}

	// Método que añade todos los barcos al array para mostrarlos
	private void cargarBarcos() {
		barcos.add(new Barco(new TipoBarco(5f, 5f, "barquito.png", 10f, 10f), null));
		// TODO meter los barcos
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
