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
import com.badlogic.gdx.utils.Timer;

public class Minijuego implements Screen{
    final Code code;
    //private Stage stage;º
	private float anchoPantalla, altoPantalla, max, min;
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
    private boolean estaBajando, hasGanado;
    private float delay;

    public Minijuego(final Code code){
        this.code = code;
        anchoPantalla = Gdx.graphics.getWidth();
		altoPantalla = Gdx.graphics.getHeight();
        
        MAX_PUNTOS = 3;
        rnd = new Random();
        subir = 0;
        hasGanado = false;
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
        pezH = this.code.manager.get("minijuego1/pezHorizontal.png");

        //fondo
        fondo = this.code.manager.get("minijuego1/island_pixel_art.png");
        //pescador
        pescador = this.code.manager.get("minijuego1/pescador.png");
        //caña
        canaPescar = this.code.manager.get("minijuego1/canapescar.png");
        //barra pez
        barraVertical = this.code.manager.get("minijuego1/barraProgresoVertical.png");
        //indicador
        indicador = this.code.manager.get("minijuego1/indicadorVertical.png");

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

        barraProgreso = new ProgressBar(0.0f, 2.0f, 0.01f, true, progressBarStyle);
        barraProgreso.setValue(0.0f);
        barraProgreso.setAnimateDuration(0.15f);
        barraProgreso.setBounds(anchoPantalla/2 + 75 , altoPantalla/2 - 30, 30, 300);
        barraProgreso.setProgrammaticChangeEvents(false);
        //barraProgreso.setPosition(anchoPantalla/2 + 75 , altoPantalla/2 - 30);
        
        stage.addActor(barraProgreso);      

        //pez
        pezV = this.code.manager.get("minijuego1/pezVertical.png");
        Random rnd = new Random();
        max = (altoPantalla/2 + 211);
        min = (altoPantalla/2 - 22);
        if(!hasGanado) {
        	posY = min + rnd.nextFloat() * (max - min);
        }
    }
/*
    private boolean pescado(){
        return false;
    }
*/
    @Override
    public void render(float delta) {
        //Para hacer las colisiones
        float maxY = (altoPantalla/2 - 30) + 300 - 60; //50 altura barra
        float minY = (altoPantalla/2 - 30) + 13;
        float indicadorY = (altoPantalla/2 - 22) + subir;
        boolean empezar = false; 
        if (posY > minY && posY < maxY){
            //posY += -10 + rnd.nextFloat() * 20 ;
        	if (rnd.nextFloat()>0.5) {
        		posY -= 2;
        	} else {
        		posY += 2;
        	}
        } else if (posY <= minY){
            posY += 2;
        } else if (posY >= minY){
            posY -= 2;
        }
        if(Gdx.input.isKeyPressed(Keys.SPACE) && indicadorY < maxY && !hasGanado){
            if((maxY - indicadorY) >= 8){
                subir+=4;
            } else {
                subir += (maxY - indicadorY) + 3;
            }
        } else if (!Gdx.input.isKeyPressed(Keys.SPACE) && indicadorY > minY){
            if((indicadorY - minY) >= 10){
                subir-=5;
            } else {
                subir -= (indicadorY - minY) + 5;
            }
            }
    	if((posY >= indicadorY && posY + 50 < indicadorY +100) || (posY <= indicadorY && posY - 50 > indicadorY)){
    		float newvalue = barraProgreso.getValue() + 0.01f;
        	barraProgreso.setValue(newvalue);
            if(barraProgreso.getValue() >= 2f){
                puntuacion++;
                barraProgreso.setValue(0.0f);

                if(posY>min+200) {
                    posY = 520;
                } else {
                	posY = 750;
                }
                if(puntuacion >= 3){
                	hasGanado = true;
                }
            }
        }
        //dibujar puntuacion
        strPuntuacion = puntuacion + "/3";
        batch.begin();
        batch.draw(fondo, 0, 0, anchoPantalla, altoPantalla);

        batch.draw(pescador, anchoPantalla/2 + 225, altoPantalla/2 - 15, 90, 150);
        batch.draw(canaPescar,anchoPantalla/2 + 270, altoPantalla/2 - 15, 180, 150);
        batch.draw(barraVertical, anchoPantalla/2 + 120, altoPantalla/2 - 30, 50, 300);

        //posicion inicial indicador
        batch.draw(indicador, anchoPantalla/2 + 130, indicadorY, 30, 50);

        //dibujar pez en una posicion aleatoria de la barra
 
        fuente.draw(batch, strPuntuacion, 100, altoPantalla-70);
        batch.draw(pezH, 230, altoPantalla-125, 80, 50);
        batch.draw(pezV, anchoPantalla/2 + 130, posY, 30, 50);

        //Colisiones indicador y barra
       
        /*
        } else if (indicadorY <= minY){
            indicadorY += 50;
        } else if (indicadorY >= minY){
            indicadorY -= 50;
        }
        */
        //timeSeconds +=Gdx.graphics.getDeltaTime();
        float t1, t2;
        
        BitmapFont fuente2 = new BitmapFont();
        fuente2.setColor(0, 0, 0, 1);
        fuente2.getData().setScale(10, 10);
        if(hasGanado) {
        	delay += delta;
        	fuente2.draw(batch, "HAS GANADO!", 450, altoPantalla-700);
        }
        if(delay>2f) {
        	this.code.setScreen(new PantallaPartida(this.code));
        }
        /* 	MOSTRAR LIMITES COLISIONES
        BitmapFont fuente3 = new BitmapFont();
        fuente2.setColor(0, 0, 0, 1);
        fuente2.getData().setScale(1, 1);
    	fuente3.draw(batch, "-------------------", 1000, posY+50);
    	fuente3.draw(batch, "-------------------", 1000, posY+10);
    	fuente3.draw(batch, "===================", 1000, indicadorY+50);
    	fuente3.draw(batch, "===================", 1000, indicadorY+10);
    		MOSTRAR LIMITES COLISIONES 		*/
    	
        //pescar
    	//if((posY >= indicadorY && posY + 50 < indicadorY +100) || (posY <= indicadorY && posY - 50 > indicadorY)){

        
        //ganar
        
        batch.end();
        stage.act(delta);
        stage.draw();
                
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
