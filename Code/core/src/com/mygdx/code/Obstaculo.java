package com.mygdx.code;

public class Obstaculo {
	static float tamaniobase = 20f;
	static float conversion = 10f;
	static float tamaniominimo = 10f;
	int danio;
	float tamanio;
	public Obstaculo(int danio) {
		this.danio = danio;
		this.tamanio = this.danio/Obstaculo.conversion*Obstaculo.tamaniobase;
		if(this.tamanio < Obstaculo.tamaniominimo) {
			this.tamanio = Obstaculo.tamaniominimo;
		}
	}
}
