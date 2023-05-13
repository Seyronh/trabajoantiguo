package com.mygdx.code;

public class Pais {
	
	private String idPais;
	private String nombre;
	private String bandera;
	
	public Pais() {
		
	}
	
	public Pais(String idPais, String nombre, String bandera) {
		this.idPais = idPais;
		this.nombre = nombre;
		this.bandera = bandera;
	}

	public String getIdPais() {
		return idPais;
	}

	public void setIdPais(String idPais) {
		this.idPais = idPais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getBandera() {
		return bandera;
	}

	public void setBandera(String bandera) {
		this.bandera = bandera;
	}

}
