package com.mygdx.code;
import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BackgroundPartida {
	
	Code code;
	ArrayList<Sprite> fondos = new ArrayList<Sprite>();
	ArrayList<Vector2> posiciones = new ArrayList<Vector2>();
	
	float h;
	float w;
	
	OrthographicCamera camara;
	
	
	public BackgroundPartida(float relation,OrthographicCamera camara, Code code) {
		this.camara = camara;
		this.code = code;
		
		this.w = Gdx.graphics.getWidth()/relation;
		this.h = Gdx.graphics.getHeight()/relation;
		for(int y = -1;y<=1;y++) {
			for(int x = -1;x<=1;x++) {//Creamos los vectores de posicion
				posiciones.add(new Vector2(camara.position.x+w*x,camara.position.y+h*y));
			}
		}
		for(int i = 0;i<9;i++) { //Creamos los 9 fondos y les ponemos el tamaño de la camara y la posicion
			Sprite fondo = new Sprite(this.code.manager.get("pantallapartida/aguaRio.png",Texture.class),1024,1024);
			fondo.setSize(w, h);
			Vector2 pos = posiciones.get(i);
			fondo.setCenter(pos.x, pos.y);
			fondos.add(fondo);
		}
	}
	public void animate(float delta) {
		boolean actualizado = false;
		Vector2 pos = new Vector2(this.camara.position.x,this.camara.position.y);
		for(int i = 0;!actualizado&&i<fondos.size();i++) { //Comprobamos si el usuario se ha movido del fondo del centro
			if(i!=4) {
				Sprite fondo = fondos.get(i);
				if(fondo.getBoundingRectangle().contains(pos)) {
					Vector2 posfondo = posiciones.get(i);
					posiciones.set(4, posfondo);
					actualizado = true;
				}
			}
		}
		if(actualizado) {
			int i = 0;
			for(int y = -1;y<=1;y++) {
				for(int x = -1;x<=1;x++) {
					if(!(y==0 && x==0)) { //Actualizamos las posiciones menos la del centro
						Vector2 centro = posiciones.get(4);
						Vector2 nuevo = new Vector2(centro.x+w*x,centro.y+h*y);
						posiciones.set(i, nuevo);
					}
					i++;
				}
			}
			for(int index = 0;index<fondos.size();index++) {//Actualizamos la posicion de los sprites
				Vector2 pos2 = posiciones.get(index);
				fondos.get(index).setCenter(pos2.x, pos2.y);
			}
		}
	}
	public void draw(SpriteBatch batch) {
		for(int i = 0;i<fondos.size();i++) { //Dibujamos todos los fondos
			fondos.get(i).draw(batch);
		}
	}
}
