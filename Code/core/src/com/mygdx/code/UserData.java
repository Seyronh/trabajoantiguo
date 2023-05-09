package com.mygdx.code;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class UserData {
	public Sprite foto;
	public int id;
	public int tipo;
	public PowerUp poder;
	public Barco barco;
	public Obstaculo obstaculo;
	public UserData(Sprite foto,int id,Barco barco) {
		this.foto = foto;
		this.id = id;
		this.tipo = 1;
		this.barco = barco;
	}
	public UserData(Sprite foto,int id,PowerUp poder) {
		this.foto = foto;
		this.id = id;
		this.tipo = 2;
		this.poder = poder;
	}
	public UserData(Sprite foto,int id,Obstaculo obstaculo) {
		this.foto = foto;
		this.id = id;
		this.tipo = 3;
		this.obstaculo = obstaculo;
	}
}
