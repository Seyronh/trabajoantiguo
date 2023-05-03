package com.mygdx.code;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

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

    public minijuego(final Code game){
        this.game = game;
        anchoPantalla = Gdx.graphics.getWidth();
		altoPantalla = Gdx.graphics.getHeight();
        puntuacion = 0;
        strPuntuacion = puntuacion + "/3";
        MAX_PUNTOS = 3;
        rnd = new Random();
        subir = 0;
        //ProgressBar(float min, float max, float stepSize, boolean vertical, Skin skin) 

        barraProgreso = new ProgressBar(0, 100, MAX_PUNTOS, true, null, strPuntuacion);
    }

    @Override
    public void show() {
        //stage = new Stage();
        batch = new SpriteBatch();
        //puntuacion
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
        //barra progreso
        barraVertical = new Texture("barraProgresoVertical.png");
        //indicador
        indicador = new Texture("indicadorVertical.png");
        //pez
        pezV = new Texture("pezVertical.png");
        Random rnd = new Random();
        float max = (altoPantalla/2 + 211);
        float min = (altoPantalla/2 - 22);
        posY = min + rnd.nextFloat() * (max - min);
    }
/*
    private boolean pescado(){

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
        //Colisiones
        if (posY > minY && posY < maxY){
            posY += -10 + rnd.nextFloat() * 20 ;
        } else if (posY <= minY){
            posY += 5;
        } else if (posY >= minY){
            posY -= 5;
        }
        /* 
        if(Gdx.input.touchDown()){
           empezar = true;
        }
        
        if(empezar){
            Random rnd = new Random();
            posY = posY + rnd.nextFloat() * ((altoPantalla/2 + 211) - posY)
            batch.draw(pezV, anchoPantalla/2 + 130, posY, 30, 50)
        }

        */
        
        //dibujar puntuacion
        fuente.draw(batch, strPuntuacion, 100, altoPantalla-70);
        batch.draw(pezH, 230, altoPantalla-125, 80, 50);

        /* 
        if(pescado){
            puntuacion++;
            fuente.draw(batch, strPuntuacion, 100, altoPantalla-70);
        } */

        //Colisiones
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
