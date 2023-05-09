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

	final Code code;

	private Stage stage;

	private float anchoPantalla;
	private float altoPantalla;

	private SpriteBatch batch;
	private Texture fondo;
	private Texture tabla;

	private Texture seleccionar;
	private Texture imagenVolumen;
	private Texture barra;

	private int commandnum;

	float anchoBoton;
	float altoBoton;

	boolean silenciar;
	float volumen;

	// Almacena las preferencias (en %UserProfile%/.prefs/PreferencesName)
	// Preferences prefs;

	public OpcionesSonido(final Code code) {

		this.code = code;

		if (code.music != null) {
			silenciar = code.music.getVolume() == 0;
			volumen = code.music.getVolume();
		}

		anchoPantalla = Gdx.graphics.getWidth();
		altoPantalla = Gdx.graphics.getHeight();

		commandnum = 0;

		anchoBoton = anchoPantalla * 15 / 100;
		altoBoton = altoPantalla * 10 / 100;


		seleccionar = new Texture("Menus/madera.png");
		// cargarPantalla();
		
		
	}

	public void cargarPantalla() {

		float BotonX = anchoPantalla * 47 / 100;
		float BotonY = altoPantalla * 42 / 100;

		
		
		// OPCIONES SONIDO

		stage = new Stage();
		batch = new SpriteBatch();

		fondo = code.manager.get("fondoMenuPrincipal.png", Texture.class);
		tabla = code.manager.get("Menu.png", Texture.class);

		imagenVolumen = code.manager.get("Volumen.png", Texture.class);
		barra = code.manager.get("Barra.png", Texture.class);

//		Table table2 = new Table();
//		table2.setPosition(0, 0);
//		// La tabla ocupa toda la pantalla
//		table2.setFillParent(true);
//		table2.setHeight(altoPantalla);
//
//		// table.addActor(boton);
////	    stage.addActor(table2);
//		stage.addActor(table2);
//
//		Label label = new Label("Volumen", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
//
//		label.setPosition(anchoPantalla * 47 / 100, altoPantalla * 75 / 100);
//
//		table2.addActor(label);

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
//
//		BotonY = BotonY - altoPantalla * 25 / 100;
//
//		Label label2 = new Label("Silenciar", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
//
//		label2.setPosition(anchoPantalla * 47 / 100, altoPantalla * 58 / 100);
//
//		table2.addActor(label2);
//
//		CheckBoxStyle style = new CheckBoxStyle();
//
//		Texture checkSoundChecked = new Texture("botoncheckedplchld.png");
//		Texture checkSoundUnchecked = new Texture("botonuncheckedplchld.png");
//		// style.checkboxOn = new TextureRegionDrawable(new
//		// TextureRegion(checkSoundOn));
//		// style.checkboxOff = new TextureRegionDrawable(new
//		// TextureRegion(checkSoundOff));
//
//		style.checked = new TextureRegionDrawable(new TextureRegion(checkSoundChecked));
//		style.up = new TextureRegionDrawable(new TextureRegion(checkSoundUnchecked));
//
//		style.font = new BitmapFont();
//		style.fontColor = Color.BLACK;

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

//
//		// Boton Volver
//
//		TextButtonStyle styleb = new TextButtonStyle();
//
//		Texture buttondown = new Texture("botondownplchld.png");
//		Texture buttonup = new Texture("botonplchld.png");
//
//		styleb.down = new TextureRegionDrawable(new TextureRegion(buttondown));
//		styleb.up = new TextureRegionDrawable(new TextureRegion(buttonup));
//
//		styleb.font = new BitmapFont();
//		styleb.fontColor = Color.BLACK;
//
//		TextButton buttonVolver = new TextButton("Volver", styleb); // TEXTURA
//		buttonVolver.setPosition(BotonX, BotonY);
//		buttonVolver.setSize(AnchoBoton, AltoBoton);
//		buttonVolver.addListener(new InputListener() {
//			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//				return true;
//			}
//
//			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//
//				dispose();
//
//				game.setScreen(new Opciones(game));
//			}
//		});
//		table2.addActor(buttonVolver);
//
//		Gdx.input.setInputProcessor(stage);

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
			code.setScreen(new MainMenuScreen(code));
		}

		

		stage.act(Gdx.graphics.getDeltaTime());
		
		stage.getBatch().begin();
		
		stage.getBatch().draw(Fondo, 0, 0, anchoPantalla, altoPantalla);
		
		
		
		stage.getBatch().draw(Tabla, anchoPantalla * 355 / 1000, altoPantalla * 3 / 10, anchoPantalla*30/100, altoPantalla * 60/100);

		//medio pantalla  
		// batch.draw(new Texture("sliderknob.png"), anchoPantalla/2, altoPantalla/2, 2, 200);
		    
		
		
		stage.getBatch().draw(barra, anchoPantalla * 45 / 100, altoPantalla * 65 / 100, anchoPantalla*14/100 * Volumen, AltoBoton/4);

		stage.getBatch().draw(new Texture("Menus/Cuadro_sonido.png"), anchoPantalla * 43 / 100, altoPantalla * 61 / 100, anchoPantalla*18/100, AltoBoton);

		
		float medidax = anchoPantalla*45/100;
	    float mediday =  altoPantalla*70/100;
		
	    stage.getBatch().draw(new Texture("Menus/Volumen.png"), medidax, mediday, anchoPantalla*10/100, altoPantalla*6/100);
	    
		mediday = mediday - altoPantalla*15/100;
		
		stage.getBatch().draw(new Texture("Menus/Silenciar.png"), medidax, mediday, anchoPantalla*10/100, altoPantalla*6/100);
	    
		mediday = mediday - altoPantalla*10/100;
		
		stage.getBatch().draw(new Texture("Menus/Volver.png"), medidax, mediday, anchoPantalla*10/100, altoPantalla*6/100);
	    
		
		if (Silenciar) {

			stage.getBatch().draw(new Texture("Menus/Hoyo.png"), anchoPantalla * 50 / 100, altoPantalla * 53 / 100, AnchoBoton , AnchoBoton*0.33f );
			//	game.music.setVolume(0f);
		} else {

			stage.getBatch().draw(new Texture("Menus/Hoyo.png"), anchoPantalla * 50 / 100, altoPantalla * 53 / 100, AnchoBoton , AnchoBoton*0.33f );
			
			stage.getBatch().draw(new Texture("Menus/Tick.png"), anchoPantalla * 50 / 100, altoPantalla * 53 / 100, AnchoBoton , AnchoBoton*0.33f );
			
			//	game.music.setVolume(Volumen);
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
			stage.getBatch().draw(seleccionar, anchoPantalla * 39 / 100, altoPantalla * 64 / 100 - altoPantalla * 9 / 100 * commandnum, AnchoBoton/2, AltoBoton/2); // Pantalla

		} else {
			stage.getBatch().draw(seleccionar, anchoPantalla * 39 / 100, altoPantalla * 65 / 100 - altoPantalla * 10 / 100 * commandnum); // Modo ventana

		}
		switch (commandnum) {
		case 0:

			if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
				volumen += 0.1f;

				if (volumen > 1f) {
					volumen = 1f;
				}

			}
			if (Gdx.input.isKeyPressed(Keys.LEFT)) {

				volumen -= 0.1f;
				if (volumen < 0f) {
					volumen = 0f;
				}
			}

			if (!silenciar && code.music != null) {
				code.music.setVolume(volumen);
      }
			break;
		case 1:

			if (Gdx.input.isKeyPressed(Keys.ENTER)) {

				silenciar = !silenciar;

			}
			break;
		case 2:
			if (Gdx.input.isKeyPressed(Keys.ENTER)) {
				code.setScreen(new MainMenuScreen(game));
			}
			break;

		}
		
		stage.getBatch().end();
		
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
