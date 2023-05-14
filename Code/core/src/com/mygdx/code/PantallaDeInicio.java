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
	private boolean skipTime = false; //para no tener que pulsar espacio al iniciar

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
		//MINIJUEGO1
		this.code.manager.load("minijuego1/pezVertical.png",Texture.class);
		this.code.manager.load("minijuego1/pezHorizontal.png",Texture.class);
		this.code.manager.load("minijuego1/island_pixel_art.png",Texture.class);
		this.code.manager.load("minijuego1/pescador.png",Texture.class);
		this.code.manager.load("minijuego1/canapescar.png",Texture.class);
		this.code.manager.load("minijuego1/barraProgresoVertical.png",Texture.class);
		this.code.manager.load("minijuego1/indicadorVertical.png",Texture.class);
		//FIN MINIJUEGO1
		
		//MUSICA
		this.code.manager.load("musica/enpartida.ogg", Music.class);
		this.code.manager.load("musica/fuerapartida.ogg", Music.class);
		this.code.manager.load("musica/minijuego1.ogg", Music.class);
		//FIN MUSICA
		
		//MEDALLAS
		//this.code.manager.load("medallas/bronce.png", Texture.class);
		//this.code.manager.load("medallas/oro.png", Texture.class);
		//this.code.manager.load("medallas/plata.png", Texture.class);
		//FIN MEDALLAS
		
		//PANTALLAPARTIDA
		this.code.manager.load("pantallapartida/powerUp.png", Texture.class);
		this.code.manager.load("pantallapartida/carril.jpg", Texture.class);
		this.code.manager.load("pantallapartida/roca.png", Texture.class);
		this.code.manager.load("pantallapartida/Meta.jpg", Texture.class);
		this.code.manager.load("pantallapartida/aguaRio.png", Texture.class);
		//FIN PANTALLAPARTIDA
		
		//BANDERAS
		this.code.manager.load("banderas/espana.png", Texture.class);
		this.code.manager.load("banderas/china.png", Texture.class);
		this.code.manager.load("banderas/japon.png", Texture.class);
		this.code.manager.load("banderas/coreaSur.png", Texture.class);
		this.code.manager.load("banderas/brasil.png", Texture.class);
		this.code.manager.load("banderas/reinoUnido.png", Texture.class);
		this.code.manager.load("banderas/indonesia.png", Texture.class);
		this.code.manager.load("banderas/australia.png", Texture.class);
		this.code.manager.load("banderas/estadosUnidos.png", Texture.class);
		this.code.manager.load("banderas/rusia.png", Texture.class);
		this.code.manager.load("banderas/sudafrica.png", Texture.class);
		this.code.manager.load("banderas/bolivia.png", Texture.class);
		this.code.manager.load("banderas/alemania.png", Texture.class);
		this.code.manager.load("banderas/francia.png", Texture.class);
		this.code.manager.load("banderas/chad.png", Texture.class);
		this.code.manager.load("banderas/nigeria.png", Texture.class);
		this.code.manager.load("banderas/costaMarfil.png", Texture.class);
		this.code.manager.load("banderas/camerun.png", Texture.class);
		this.code.manager.load("banderas/grecia.png", Texture.class);
		this.code.manager.load("banderas/egipto.png", Texture.class);
		this.code.manager.load("banderas/suecia.png", Texture.class);
		this.code.manager.load("banderas/suiza.png", Texture.class);
		this.code.manager.load("banderas/canada.png", Texture.class);
		this.code.manager.load("banderas/mexico.png", Texture.class);
		this.code.manager.load("banderas/argentina.png", Texture.class);
		this.code.manager.load("banderas/cuba.png", Texture.class);
		this.code.manager.load("banderas/sriLanka.png", Texture.class);
		this.code.manager.load("banderas/mauricio.png", Texture.class);
		this.code.manager.load("banderas/madagascar.png", Texture.class);
		this.code.manager.load("banderas/vaticano.png", Texture.class);
		this.code.manager.load("banderas/italia.png", Texture.class);
		this.code.manager.load("banderas/india.png", Texture.class);
		//FIN BANDERAS
		
		//SELECCIONBARCO
		this.code.manager.load("seleccionbarco/textoSeleccionBarcoPais.png", Texture.class);
		this.code.manager.load("seleccionbarco/flechaSeleccionDerecha.png", Texture.class);
		this.code.manager.load("seleccionbarco/flechaSeleccionIzquierda.png", Texture.class);
		this.code.manager.load("seleccionbarco/vida.png", Texture.class);
		this.code.manager.load("seleccionbarco/velocidad.png", Texture.class);
		this.code.manager.load("seleccionbarco/movilidad.png", Texture.class);
		this.code.manager.load("seleccionbarco/aceleracion.png", Texture.class);
		//FIN SELECCIONBARCO
		
		//BOTONES
		this.code.manager.load("botones/botondownplchld.png", Texture.class);
		this.code.manager.load("botones/botonplchld.png", Texture.class);
		//FIN BOTONES
		
		//OPCIONES
		this.code.manager.load("opciones/Menu.png", Texture.class);
		this.code.manager.load("opciones/Barra.png", Texture.class);
		this.code.manager.load("opciones/Volumen.png", Texture.class);
		//FIN OPCIONES
		
		//MENUS
		this.code.manager.load("Menus/Combo.png", Texture.class);
		this.code.manager.load("Menus/Menu2.png", Texture.class);
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
		this.code.manager.load("Menus/madera.png", Texture.class);
		//FIN MENUS
		
		//MENU PRINCIPAL
		this.code.manager.load("menuprincipal/Jugar.png", Texture.class);
		this.code.manager.load("menuprincipal/Controles.png", Texture.class);
		this.code.manager.load("menuprincipal/Sonido.png", Texture.class);
		this.code.manager.load("menuprincipal/Salir.png", Texture.class);
		this.code.manager.load("menuprincipal/Titulo.png", Texture.class);
		this.code.manager.load("menuprincipal/fondoMenuPrincipal.png", Texture.class);
		//FIN MENU PRINCIPAL
		
		//BARCOS
		this.code.manager.load("barcos/barcoAceleracion.png", Texture.class);
		this.code.manager.load("barcos/barcoMovilidad.png", Texture.class);
		this.code.manager.load("barcos/barcoNormal.png", Texture.class);
		this.code.manager.load("barcos/barcoSpeed.png", Texture.class);
		this.code.manager.load("barcos/barcoVida.png", Texture.class);
		//FIN BARCOS
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
			if (Gdx.input.isKeyPressed(Keys.SPACE) || skipTime) {
				this.code.music = this.code.manager.get("musica/fuerapartida.ogg");
				this.code.music.setLooping(true);
				this.code.music.play();
				this.code.music.setVolume((float)Math.pow(this.code.volumen,2));
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
