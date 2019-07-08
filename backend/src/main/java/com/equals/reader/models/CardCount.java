package com.equals.reader.models;

import lombok.Data;

@Data
public class CardCount {

	public CardCount(String tipoCard, long contador) {
		this.tipoCard = tipoCard;
		this.contador = contador;
	}

	String tipoCard;
	long contador;
}
