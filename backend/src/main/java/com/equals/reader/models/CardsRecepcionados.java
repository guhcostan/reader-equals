package com.equals.reader.models;

import lombok.Data;

@Data
public class CardsRecepcionados {

	long contador;
	String recepcionado;

	public CardsRecepcionados(String recepcionado, long contador) {
		this.contador = contador;
		this.recepcionado = recepcionado;
	}
}
