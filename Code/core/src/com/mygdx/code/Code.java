package com.mygdx.code;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Code extends Game {
	SpriteBatch batch;
	Texture img;
	Sprite barquito;
	Barco boat;
	TipoBarco elegido;
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PantallaPartida(this));
	}
	@Override
	public void dispose () {
		batch.dispose();
	}
}
