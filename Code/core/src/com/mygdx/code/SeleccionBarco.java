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

	final Code code;

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
	private int posPais, posTipoBarco; // Posici�n cogida de pa�s y barco del array

	float anchoBoton;
	float altoBoton;
	private Texture vida;
	private Texture velocidad;
	private Texture movilidad;
	private Texture aceleracion;
	private Texture barraVida;
	private Texture barraVelocidad;
	private Texture barraMovilidad;
	private Texture barraAceleracion;
	private boolean delay;
	private float delayi;
	// Almacena las preferencias (en %UserProfile%/.prefs/PreferencesName)
	// Preferences prefs;

	public SeleccionBarco(final Code code) {

		this.code = code;
		anchoPantalla = Gdx.graphics.getWidth();
		altoPantalla = Gdx.graphics.getHeight();

		commandnum = 0;

		arrows = true;

		anchoBoton = anchoPantalla * 25 / 100;
		altoBoton = altoPantalla * 10 / 100;

		seleccionarIzquierda = code.manager.get("flechaSeleccionIzquierda.png", Texture.class);
		seleccionarDerecha = code.manager.get("flechaSeleccionDerecha.png", Texture.class);
		textoSeleccion = code.manager.get("textoSeleccionBarcoPais.png",Texture.class);
		
		vida = code.manager.get("Volumen.png", Texture.class);
		velocidad = code.manager.get("Volumen.png", Texture.class);
		movilidad = code.manager.get("Volumen.png", Texture.class);
		aceleracion = code.manager.get("Volumen.png", Texture.class);
		
		barraVida = code.manager.get("Barra.png", Texture.class);
		barraAceleracion = code.manager.get("Barra.png", Texture.class);
		barraMovilidad = code.manager.get("Barra.png", Texture.class);
		barraVelocidad= code.manager.get("Barra.png", Texture.class);
		
//		if (game.paisSeleccionado != null) {
//			boolean encontrado = false;
//			for(int i = 0; (i < paises.size()) && !encontrado; i++) {
//				if (paises.get(i).getIdPais().equalsIgnoreCase(game.paisSeleccionado.getIdPais())) {
//					posPais = i;
//				}
//			}
//		}
//		
//		if (game.barcoSeleccionado != null) {
//			boolean encontrado = false;
//			for(int i = 0; (i < barcos.size()) && !encontrado; i++) {
//				if (barcos.get(i).elegido.barco.equalsIgnoreCase(game.barcoSeleccionado.elegido.barco)) {
//					posBarco = i;
//				}
//			}
//		}
		// cargarPantalla();
	}

	public void cargarPantalla() {

		float BotonX = (anchoPantalla * 50 / 100) - (anchoBoton / 2);
		float BotonY = altoPantalla * 50 / 100;

		// OPCIONES CONTROLES

		stage = new Stage();
		batch = new SpriteBatch();

		
		fondo = code.manager.get("fondoMenuPrincipal.png", Texture.class);
		imagenPais = code.manager.get(code.paisSeleccionado.getBandera(), Texture.class);
		imagenBarco = code.manager.get(code.tipoBarcoSeleccionado.barco, Texture.class);
		
		
		
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
		delayi+=delta;
		if(delayi > 0.3f) {
			delayi = 0f;
			delay=false;
		}
		// TODO Auto-generated method stub

		// SALIR
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			
			code.setScreen(new MainMenuScreen(code));
		}
		
		// Inicia Juego
		if (Gdx.input.isKeyPressed(Keys.ENTER)) {
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			code.setScreen(new PantallaPartida(code));
		}

		BitmapFont fuente = new BitmapFont();
		fuente.setColor(Color.GOLDENROD);
		fuente.getData().setScale(4.0f, 4.0f);

		batch.begin();
		batch.draw(fondo, 0, 0, anchoPantalla, altoPantalla);
		batch.draw(imagenPais, anchoPantalla * 35/100, altoPantalla*6/10, anchoPantalla/4, altoPantalla/4);
		batch.draw(imagenBarco, anchoPantalla * 35/100, altoPantalla*15/100, anchoPantalla/4, altoPantalla/4);
		fuente.draw(batch, code.paisSeleccionado.getNombre(), anchoPantalla * 35/100, altoPantalla*57/100);
		
		setearVistaValoresBarco();
		// batch.draw(Tabla, anchoPantalla*38/100, altoPantalla*3/10, anchoPantalla/4,
		// altoPantalla*6/10);

		if (Gdx.input.isKeyPressed(Keys.DOWN) && !delay) {
			delay = true;
			commandnum++;

			if (commandnum > 1) {

				commandnum = 0;
			}

		}
		if (Gdx.input.isKeyPressed(Keys.UP) && !delay) {
			delay = true;
			commandnum--;

			if (commandnum < 0) {

				commandnum = 1;
			}

		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT) && !delay) {
			delay = true;
			if (commandnum == 0) {
				posPais++;

				if (posPais >= code.paises.size()) {
					posPais = 0;
				}

				code.paisSeleccionado = code.paises.get(posPais);
				imagenPais = new Texture(code.paisSeleccionado.getBandera());
				
			} else {
				posTipoBarco++;

				if (posTipoBarco >= code.tipoBarcos.size()) {
					posTipoBarco = 0;
				}
				code.tipoBarcoSeleccionado = code.tipoBarcos.get(posTipoBarco);
				imagenBarco = new Texture(code.tipoBarcoSeleccionado.barco);
				setearVistaValoresBarco();
			}
		}
		
		if (Gdx.input.isKeyPressed(Keys.LEFT) && !delay) {
			delay = true;
			if (commandnum == 0) {
				posPais--;

				if (posPais < 0) {
					posPais = (code.paises.size() - 1);
				}

				code.paisSeleccionado = code.paises.get(posPais);
				imagenPais = new Texture(code.paisSeleccionado.getBandera());
			} else {
				posTipoBarco--;

				if (posTipoBarco < 0) {
					posTipoBarco = (code.tipoBarcos.size() - 1);
				}

				code.tipoBarcoSeleccionado = code.tipoBarcos.get(posTipoBarco);
				imagenBarco = new Texture(code.tipoBarcoSeleccionado.barco);
				setearVistaValoresBarco();
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

		if (Gdx.input.isKeyPressed(Keys.ENTER)) {

			code.setScreen(new PantallaPartida(code));
		}

		batch.end();

		stage.act(delta);
		stage.draw();
	}
	
	private void setearVistaValoresBarco() {
		batch.draw(code.manager.get("vida.png", Texture.class), anchoPantalla * 72 / 100, altoPantalla * 38 / 100, anchoBoton / 4, altoBoton / 2);
		batch.draw(vida, anchoPantalla * 72 / 100, altoPantalla * 36 / 100, anchoBoton, altoBoton / 4);
		batch.draw(barraVida, anchoPantalla * 72 / 100, altoPantalla * 36 / 100, anchoBoton *(code.tipoBarcoSeleccionado.vidamax / 10), altoBoton / 4);
		batch.draw(code.manager.get("velocidad.png", Texture.class), anchoPantalla * 72 / 100, altoPantalla * 31 / 100, anchoBoton / 3, altoBoton / 2);
		batch.draw(velocidad, anchoPantalla * 72 / 100, altoPantalla * 29 / 100, anchoBoton, altoBoton / 4);
		batch.draw(barraVelocidad, anchoPantalla * 72 / 100, altoPantalla * 29 / 100, anchoBoton *(code.tipoBarcoSeleccionado.velocidadmax / 10), altoBoton / 4);
		batch.draw(code.manager.get("aceleracion.png", Texture.class), anchoPantalla * 72 / 100, altoPantalla * 24 / 100, anchoBoton / 3, altoBoton / 2);
		batch.draw(aceleracion, anchoPantalla * 72 / 100, altoPantalla * 22 / 100, anchoBoton, altoBoton / 4);
		batch.draw(barraAceleracion, anchoPantalla * 72 / 100, altoPantalla * 22 / 100, anchoBoton *(code.tipoBarcoSeleccionado.aceleracion / 10), altoBoton / 4);
		batch.draw(code.manager.get("movilidad.png", Texture.class), anchoPantalla * 72 / 100, altoPantalla * 17 / 100, anchoBoton / 3, altoBoton / 2);
		batch.draw(movilidad, anchoPantalla * 72 / 100, altoPantalla * 15 / 100, anchoBoton, altoBoton / 4);
		batch.draw(barraMovilidad, anchoPantalla * 72 / 100, altoPantalla * 15 / 100, anchoBoton *(code.tipoBarcoSeleccionado.movilidad / 10), altoBoton / 4);
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
