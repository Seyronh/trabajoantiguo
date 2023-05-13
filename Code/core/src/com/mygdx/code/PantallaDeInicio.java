package com.mygdx.code;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.audio.Music;

public class PantallaDeInicio implements Screen {

	private Code code;
	private Sprite splash;
	private Texture texture;
	private BitmapFont font;
	private float elapsedTime = 0.0f;

	public PantallaDeInicio(Code code) {
		this.code = code;
	}

	@Override
	public void show() {

		cargarImagenes();

		Skin skin = new Skin();
		Pixmap pixmap = new Pixmap(10, 10, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		this.texture = this.code.manager.get("Fondo_Inicio.jpg", Texture.class);
		this.splash = new Sprite(texture);
		this.splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	private void cargarImagenes() {
		this.code.manager.load("pezVertical.png",Texture.class);
		this.code.manager.load("pezHorizontal.png",Texture.class);
		this.code.manager.load("island_pixel_art.png",Texture.class);
		this.code.manager.load("pescador.png",Texture.class);
		this.code.manager.load("canapescar.png",Texture.class);
		this.code.manager.load("barraProgresoVertical.png",Texture.class);
		this.code.manager.load("indicadorVertical.png",Texture.class);
		this.code.manager.load("enpartida.ogg", Music.class);
		this.code.manager.load("fuerapartida.ogg", Music.class);
		this.code.manager.load("aguaMar.png", Texture.class);
		this.code.manager.load("aguaRio.png", Texture.class);
		this.code.manager.load("bronce.png", Texture.class);
		this.code.manager.load("oro.png", Texture.class);
		this.code.manager.load("plata.png", Texture.class);
		this.code.manager.load("powerUp.png", Texture.class);
		this.code.manager.load("vida.png", Texture.class);
		this.code.manager.load("velocidad.png", Texture.class);
		this.code.manager.load("movilidad.png", Texture.class);
		this.code.manager.load("aceleracion.png", Texture.class);
		this.code.manager.load("fondoMenuPrincipal.png", Texture.class);
		this.code.manager.load("opciones.png", Texture.class);
		this.code.manager.load("espana.png", Texture.class);
		this.code.manager.load("china.png", Texture.class);
		this.code.manager.load("japon.png", Texture.class);
		this.code.manager.load("coreaSur.png", Texture.class);
		this.code.manager.load("brasil.png", Texture.class);
		this.code.manager.load("reinoUnido.png", Texture.class);
		this.code.manager.load("indonesia.png", Texture.class);
		this.code.manager.load("australia.png", Texture.class);
		this.code.manager.load("estadosUnidos.png", Texture.class);
		this.code.manager.load("rusia.png", Texture.class);
		this.code.manager.load("sudafrica.png", Texture.class);
		this.code.manager.load("bolivia.png", Texture.class);
		this.code.manager.load("alemania.png", Texture.class);
		this.code.manager.load("francia.png", Texture.class);
		this.code.manager.load("chad.png", Texture.class);
		this.code.manager.load("nigeria.png", Texture.class);
		this.code.manager.load("costaMarfil.png", Texture.class);
		this.code.manager.load("camerun.png", Texture.class);
		this.code.manager.load("grecia.png", Texture.class);
		this.code.manager.load("egipto.png", Texture.class);
		this.code.manager.load("suecia.png", Texture.class);
		this.code.manager.load("suiza.png", Texture.class);
		this.code.manager.load("canada.png", Texture.class);
		this.code.manager.load("mexico.png", Texture.class);
		this.code.manager.load("argentina.png", Texture.class);
		this.code.manager.load("cuba.png", Texture.class);
		this.code.manager.load("sriLanka.png", Texture.class);
		this.code.manager.load("mauricio.png", Texture.class);
		this.code.manager.load("madagascar.png", Texture.class);
		this.code.manager.load("vaticano.png", Texture.class);
		this.code.manager.load("italia.png", Texture.class);
		this.code.manager.load("india.png", Texture.class);
		this.code.manager.load("Barra.png", Texture.class);
		this.code.manager.load("Volumen.png", Texture.class);
		this.code.manager.load("textoSeleccionBarcoPais.png", Texture.class);
		this.code.manager.load("flechaSeleccionDerecha.png", Texture.class);
		this.code.manager.load("flechaSeleccionIzquierda.png", Texture.class);
		this.code.manager.load("seleccionar.png", Texture.class);
		this.code.manager.load("botoncheckedplchld.png", Texture.class);
		this.code.manager.load("botonuncheckedplchld.png", Texture.class);
		this.code.manager.load("botondownplchld.png", Texture.class);
		this.code.manager.load("botonplchld.png", Texture.class);
		this.code.manager.load("Titulo.png", Texture.class);
		this.code.manager.load("Menus/madera.png", Texture.class);
		this.code.manager.load("PowerUp.png", Texture.class);
		this.code.manager.load("sliderbg.png", Texture.class);
		this.code.manager.load("sliderknob.png", Texture.class);
		this.code.manager.load("Menu.png", Texture.class);
		this.code.manager.load("Menus/Cuadro_sonido.png", Texture.class);
		this.code.manager.load("Menus/Volumen.png", Texture.class);
		this.code.manager.load("Menus/Silenciar.png", Texture.class);
		this.code.manager.load("Menus/Volver.png", Texture.class);
		this.code.manager.load("Menus/Hoyo.png", Texture.class);
		this.code.manager.load("Menus/Tick.png", Texture.class);
		this.code.manager.load("Menus/circulo.png", Texture.class);
		this.code.manager.load("Menus/mapeado.png", Texture.class);
		this.code.manager.load("Menus/opcion1.png", Texture.class);
		this.code.manager.load("Menus/opcion2.png", Texture.class);
		this.code.manager.load("Menus/1.png", Texture.class);
		this.code.manager.load("Menus/2.png", Texture.class);
		this.code.manager.load("Jugar.png", Texture.class);
		this.code.manager.load("Controles.png", Texture.class);
		this.code.manager.load("Sonido.png", Texture.class);
		this.code.manager.load("Salir.png", Texture.class);
		this.code.manager.load("barcoAceleracion.png", Texture.class);
		this.code.manager.load("barcoMovilidad.png", Texture.class);
		this.code.manager.load("barcoNormal.png", Texture.class);
		this.code.manager.load("barcoSpeed.png", Texture.class);
		this.code.manager.load("barcoVida.png", Texture.class);
		this.code.manager.load("carril.jpg", Texture.class);
		this.code.manager.load("roca.png", Texture.class);
	}

	@Override
	public void render(float delta) {

		BitmapFont fuente = new BitmapFont();
		fuente.setColor(Color.GOLDENROD);
		fuente.getData().setScale(5.0f, 5.0f);
		this.font = fuente;

		ScreenUtils.clear(0, 0, 0, 1);
		this.code.batch.begin();
		this.code.batch.draw(this.texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		if (!this.code.manager.update()) {
			elapsedTime += delta;
			if (elapsedTime < 0.5f) {
				font.draw(this.code.batch, "Cargando", 75, 75);
			} else if (elapsedTime < 1f) {
				font.draw(this.code.batch, "Cargando.", 75, 75);
			} else if (elapsedTime < 1.5f) {
				font.draw(this.code.batch, "Cargando..", 75, 75);
			} else if (elapsedTime < 2.0f) {
				font.draw(this.code.batch, "Cargando...", 75, 75);
			} else {
				elapsedTime = 0.0f;
			}
		} else {
			elapsedTime += delta;
			if (elapsedTime < 1f) {
				font.draw(this.code.batch, "Presiona la barra espaciadora para continuar", 200, 100);
			} else if (elapsedTime > 1.5f) {
				elapsedTime = 0f;
			}
			if (Gdx.input.isKeyPressed(Keys.SPACE)) {
				this.code.music = this.code.manager.get("fuerapartida.ogg");
				this.code.music.setLooping(true);
				this.code.music.play();
				this.code.music.setVolume(0.2f);
				this.code.setScreen(new MainMenuScreen(this.code));
			}
		}
		this.code.batch.end();

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

	}

}
