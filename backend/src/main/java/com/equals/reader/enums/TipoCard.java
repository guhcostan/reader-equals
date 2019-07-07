package com.equals.reader.enums;

public enum TipoCard {

	UFLA_CARD(0, "Ufla_Card"),
	FGAMMON_CARD(1, "Fgammon_Card");

	private final int value;
	private final String nome;


	TipoCard(final int value, String nome) {
		this.value = value;
		this.nome = nome;
	}

	public int getValue() {
		return value;
	}

	public String getNome() {
		return nome;
	}
}
