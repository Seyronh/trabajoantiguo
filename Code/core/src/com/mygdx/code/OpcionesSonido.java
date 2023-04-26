package com.mygdx.code;

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
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class OpcionesSonido implements Screen {

	final Code game;

	private Stage stage;

	private float anchoPantalla;
	private float altoPantalla;

	private SpriteBatch batch;
	private Texture Fondo;
	private Texture Tabla;

	private Texture seleccionar;
	private Texture volumen;
	private Texture barra;

	private int commandnum;

	float AnchoBoton;
	float AltoBoton;

	boolean Silenciar;
	float Volumen;

	// Almacena las preferencias (en %UserProfile%/.prefs/PreferencesName)
	// Preferences prefs;

	public OpcionesSonido(final Code game) {

		this.game = game;

		Silenciar = false;
		Volumen = 1f;

		anchoPantalla = Gdx.graphics.getWidth();
		altoPantalla = Gdx.graphics.getHeight();

		commandnum = 0;

		AnchoBoton = anchoPantalla * 15 / 100;
		AltoBoton = altoPantalla * 10 / 100;

		seleccionar = new Texture("seleccionar.png");
		// cargarPantalla();
	}

	public void cargarPantalla() {

		float BotonX = anchoPantalla * 47 / 100;
		float BotonY = altoPantalla * 70 / 100;

		// OPCIONES SONIDO

		stage = new Stage();
		batch = new SpriteBatch();

		Fondo = new Texture("fondoMenuPrincipal.png");
		Tabla = new Texture("opciones.png");

		volumen = new Texture("Volumen.png");
		barra = new Texture("Barra.png");

		Table table2 = new Table();
		table2.setPosition(0, 0);
		// La tabla ocupa toda la pantalla
		table2.setFillParent(true);
		table2.setHeight(altoPantalla);

		// table.addActor(boton);
//	    stage.addActor(table2);
		stage.addActor(table2);

		Label label = new Label("Volumen", new Label.LabelStyle(new BitmapFont(), Color.BLACK));

		label.setPosition(anchoPantalla * 47 / 100, altoPantalla * 75 / 100);

		table2.addActor(label);

//	    SliderStyle styles = new SliderStyle();
//	    
//	    Texture sliderbg = new Texture("sliderbg.png");
//	    
//	    Texture sliderknob = new Texture("sliderknob.png");
//	    
//	    styles.knob = new TextureRegionDrawable(new TextureRegion(sliderknob));
//	    styles.background = new TextureRegionDrawable(new TextureRegion(sliderbg));
//	    
//	    
//		Slider slider = new Slider(0f,10f,1f,false,styles);
//		
//		slider.setPosition(anchoPantalla*40/100, BotonY);
//		
//	//	slider.setScale(1/2);
//		
//		table2.addActor(slider);

		BotonY = BotonY - altoPantalla * 25 / 100;

		Label label2 = new Label("Silenciar", new Label.LabelStyle(new BitmapFont(), Color.BLACK));

		label2.setPosition(anchoPantalla * 47 / 100, altoPantalla * 58 / 100);

		table2.addActor(label2);

		CheckBoxStyle style = new CheckBoxStyle();

		Texture checkSoundChecked = new Texture("botoncheckedplchld.png");
		Texture checkSoundUnchecked = new Texture("botonuncheckedplchld.png");
		// style.checkboxOn = new TextureRegionDrawable(new
		// TextureRegion(checkSoundOn));
		// style.checkboxOff = new TextureRegionDrawable(new
		// TextureRegion(checkSoundOff));

		style.checked = new TextureRegionDrawable(new TextureRegion(checkSoundChecked));
		style.up = new TextureRegionDrawable(new TextureRegion(checkSoundUnchecked));

		style.font = new BitmapFont();
		style.fontColor = Color.BLACK;

//		final CheckBox checkSound = new CheckBox("", style); //TEXTURA
//
//		//checkSound.setChecked(prefs.getBoolean("sound"));
//		
//		checkSound.setSize(AltoBoton/2, AltoBoton/2);
//		
//		checkSound.setPosition(anchoPantalla*58/100, altoPantalla*57/100);
//		checkSound.addListener(new InputListener() {
//			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//				return true;	
//			}
//			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//				
//				//checkSound.isChecked();
//				
//			//	prefs.putBoolean("sound", checkSound.isChecked());
//				
//				Silenciar = !Silenciar;
//			}
//			
//		});
//		
//		table2.addActor(checkSound);

		// Boton Volver

		TextButtonStyle styleb = new TextButtonStyle();

		Texture buttondown = new Texture("botondownplchld.png");
		Texture buttonup = new Texture("botonplchld.png");

		styleb.down = new TextureRegionDrawable(new TextureRegion(buttondown));
		styleb.up = new TextureRegionDrawable(new TextureRegion(buttonup));

		styleb.font = new BitmapFont();
		styleb.fontColor = Color.BLACK;

		TextButton buttonVolver = new TextButton("Volver", styleb); // TEXTURA
		buttonVolver.setPosition(BotonX, BotonY);
		buttonVolver.setSize(AnchoBoton, AltoBoton);
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
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {

			dispose();

			game.setScreen(new Opciones(game));
		}

		batch.begin();
		batch.draw(Fondo, 0, 0, anchoPantalla, altoPantalla);
		batch.draw(Tabla, anchoPantalla * 38 / 100, altoPantalla * 3 / 10, anchoPantalla / 4, altoPantalla * 6 / 10);

		batch.draw(volumen, anchoPantalla * 47 / 100, altoPantalla * 67 / 100, AnchoBoton, AltoBoton / 2);

		batch.draw(barra, anchoPantalla * 47 / 100, altoPantalla * 67 / 100, AnchoBoton * Volumen, AltoBoton / 2);

		if (Silenciar) {

			batch.draw(new Texture("botoncheckedplchld.png"), anchoPantalla * 58 / 100, altoPantalla * 57 / 100,
					AltoBoton / 2, AltoBoton / 2);
			game.music.setVolume(0f);
		} else {

			batch.draw(new Texture("botonuncheckedplchld.png"), anchoPantalla * 58 / 100, altoPantalla * 57 / 100,
					AltoBoton / 2, AltoBoton / 2);
			game.music.setVolume(Volumen);

		}

		if (Gdx.input.isKeyPressed(Keys.DOWN)) {

			commandnum++;

			if (commandnum > 2) {

				commandnum = 0;
			}

		}
		if (Gdx.input.isKeyPressed(Keys.UP)) {

			commandnum--;

			if (commandnum < 0) {

				commandnum = 2;
			}

		}

		if (Gdx.graphics.isFullscreen()) {

			batch.draw(seleccionar, anchoPantalla * 39 / 100,
					altoPantalla * 65 / 100 - altoPantalla * 10 / 100 * commandnum, AnchoBoton / 2, AltoBoton); // Pantalla
																												// completa

		} else {
			batch.draw(seleccionar, anchoPantalla * 39 / 100,
					altoPantalla * 65 / 100 - altoPantalla * 10 / 100 * commandnum); // Modo ventana

		}
		switch (commandnum) {
		case 0:

			if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
				Volumen += 0.1f;

				if (Volumen > 1f) {
					Volumen = 1f;
				}

			}
			if (Gdx.input.isKeyPressed(Keys.LEFT)) {

				Volumen -= 0.1f;
				if (Volumen < 0f) {
					Volumen = 0f;
				}
			}

			if (!Silenciar) {
				game.music.setVolume(Volumen);
			}
			break;
		case 1:

			if (Gdx.input.isKeyPressed(Keys.ENTER)) {

				Silenciar = !Silenciar;

			}
			break;
		case 2:
			if (Gdx.input.isKeyPressed(Keys.ENTER)) {

				game.setScreen(new Opciones(game));

				// game.setScreen(new OpcionesSonido(game));
			}
			break;

		}

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
