package com.mygdx.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class minijuego implements Screen{
    final Code game;
    private Stage stage;
	private float anchoPantalla;
	private float altoPantalla;
	private Texture fondo, pescador, canaPescar;
    private SpriteBatch batch;

    public minijuego(final Code game){
        this.game = game;
        anchoPantalla = Gdx.graphics.getWidth();
		altoPantalla = Gdx.graphics.getHeight();
    }

    @Override
    public void show() {
        stage = new Stage();
        batch = new SpriteBatch();

        //fondo
        fondo = new Texture("island_pixel_art.png");
        Image imageFondo = new Image(fondo);
        imageFondo.setSize(anchoPantalla, altoPantalla);
        stage.addActor(imageFondo);

        //pescador
        pescador = new Texture("pescador.png");
        /*Image imagePescador = new Image(pescador);
        imagePescador.setSize(30,30);
        imagePescador.setPosition(anchoPantalla/2 + 20, altoPantalla/2 + 20);
        stage.addActor(imagePescador); */
        
        
        //caña
        canaPescar = new Texture("canapescar.png");
        Image imageCanaPescar = new Image(canaPescar);
       /*  imageCanaPescar.setSize(5, 10);
        imageCanaPescar.setPosition(anchoPantalla/2 + 25, altoPantalla/2 + 25);
        stage.addActor(imageCanaPescar); */
    }

    @Override
    public void render(float delta) {
        
        batch.begin();
        batch.draw(fondo, 0, 0, anchoPantalla, altoPantalla);
        batch.draw(pescador, anchoPantalla/2 + 270, altoPantalla/2 - 15, 150, 150);
        batch.draw(canaPescar,anchoPantalla/2 + 270, altoPantalla/2 - 15, 150, 150);
        batch.end();
        /*stage.act(delta);
        stage.draw();*/

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    }
    
}
