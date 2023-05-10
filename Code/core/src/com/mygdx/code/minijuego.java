package com.mygdx.code;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class minijuego implements Screen{
    final Code game;
    //private Stage stage;º
	private float anchoPantalla, altoPantalla;
	private Texture fondo, pescador, canaPescar, barraVertical, indicador, pezV, pezH;
    private SpriteBatch batch;
    private float posY;
    private int puntuacion;
    private String strPuntuacion;
    private final int MAX_PUNTOS;
    private BitmapFont fuente;
    private Random rnd;
    private int subir;
    private ProgressBar barraProgreso;
    private Stage stage;

    public minijuego(final Code game){
        this.game = game;
        anchoPantalla = Gdx.graphics.getWidth();
		altoPantalla = Gdx.graphics.getHeight();
        
        MAX_PUNTOS = 3;
        rnd = new Random();
        subir = 0;
    }

    @Override
    public void show() {
        stage = new Stage();
        batch = new SpriteBatch();
        //puntuacion
        puntuacion = 0;
        
        fuente = new BitmapFont();
        fuente.setColor(0, 0, 0, 1);
        fuente.getData().setScale(5, 5);
        pezH = new Texture("pezHorizontal.png");

        //fondo
        fondo = new Texture("island_pixel_art.png");
        //pescador
        pescador = new Texture("pescador.png");
        //caña
        canaPescar = new Texture("canapescar.png");
        //barra pez
        barraVertical = new Texture("barraProgresoVertical.png");
        //indicador
        indicador = new Texture("indicadorVertical.png");

        //barraProgresp
        Pixmap pixmap = new Pixmap(25, 9000, Format.RGBA8888);
        pixmap.setColor(Color.SCARLET);
        pixmap.fill();
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        ProgressBarStyle progressBarStyle = new ProgressBarStyle();
        progressBarStyle.background = drawable;

        Pixmap pixmap2 = new Pixmap(25, 10, Format.RGBA8888);
        pixmap2.setColor(Color.FOREST);
        pixmap2.fill();
        TextureRegionDrawable drawable2 = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap2)));
        pixmap2.dispose();
 
        progressBarStyle.knob = drawable2;

        Pixmap pixmap3 = new Pixmap(25, 9000, Format.RGBA8888);
        pixmap3.setColor(Color.CHARTREUSE);
        pixmap3.fill();
        TextureRegionDrawable drawable3 = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap3)));
        pixmap3.dispose();
        progressBarStyle.knobBefore = drawable3;

        barraProgreso = new ProgressBar(0.0f, 1.0f, 0.02f, true, progressBarStyle);
        barraProgreso.setValue(0.0f);
        barraProgreso.setAnimateDuration(0.25f);
        barraProgreso.setBounds(anchoPantalla/2 + 75 , altoPantalla/2 - 30, 30, 300);
        //barraProgreso.setPosition(anchoPantalla/2 + 75 , altoPantalla/2 - 30);
        
        stage.addActor(barraProgreso);      

        //pez
        pezV = new Texture("pezVertical.png");
        Random rnd = new Random();
        float max = (altoPantalla/2 + 211);
        float min = (altoPantalla/2 - 22);
        posY = min + rnd.nextFloat() * (max - min);
    }
/*
    private boolean pescado(){
        return false;
    }
*/
    @Override
    public void render(float delta) {
        
        batch.begin();
        batch.draw(fondo, 0, 0, anchoPantalla, altoPantalla);

        //Para hacer las colisiones
        float maxY = (altoPantalla/2 - 30) + 300 - 60; //50 altura barra
        float minY = (altoPantalla/2 - 30) + 13;

        batch.draw(pescador, anchoPantalla/2 + 225, altoPantalla/2 - 15, 90, 150);
        batch.draw(canaPescar,anchoPantalla/2 + 270, altoPantalla/2 - 15, 180, 150);
        batch.draw(barraVertical, anchoPantalla/2 + 120, altoPantalla/2 - 30, 50, 300);

        float indicadorY = (altoPantalla/2 - 22) + subir;
        //posicion inicial indicador
        batch.draw(indicador, anchoPantalla/2 + 130, indicadorY, 30, 50);

        //dibujar pez en una posicion aleatoria de la barra
        Boolean empezar = false; 
        
        batch.draw(pezV, anchoPantalla/2 + 130, posY, 30, 50);
        //Colisiones pez y barra
        if (posY > minY && posY < maxY){
            posY += -10 + rnd.nextFloat() * 20 ;
        } else if (posY <= minY){
            posY += 5;
        } else if (posY >= minY){
            posY -= 5;
        }

        //dibujar puntuacion
        strPuntuacion = puntuacion + "/3";
        fuente.draw(batch, strPuntuacion, 100, altoPantalla-70);
        batch.draw(pezH, 230, altoPantalla-125, 80, 50);

        //Colisiones indicador y barra
        if(Gdx.input.isKeyPressed(Keys.SPACE) && indicadorY < maxY){
            if((maxY - indicadorY) >= 8){
                subir+=8;
            } else {
                subir += (maxY - indicadorY) + 3;
            }
        } else if (!Gdx.input.isKeyPressed(Keys.SPACE) && indicadorY > minY){
            if((indicadorY - minY) >= 10){
                subir-=10;
            } else {
                subir -= (indicadorY - minY) + 5;
            }
        } else if (indicadorY <= minY){
            indicadorY += 50;
        } else if (indicadorY >= minY){
            indicadorY -= 50;
        }
        
        batch.end();
        stage.draw();
        stage.act();

        //pescar
        if((posY >= indicadorY && posY + 50 < indicadorY +100) || (posY <= indicadorY && posY - 50 > indicadorY)){
            barraProgreso.setValue(barraProgreso.getValue() + 0.02f);
            if(barraProgreso.getValue() == barraProgreso.getMaxValue()){
                puntuacion++;
                barraProgreso.setValue(0.0f);
                if(puntuacion == 3){
                    
                }
            }
        }

        //ganar
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
