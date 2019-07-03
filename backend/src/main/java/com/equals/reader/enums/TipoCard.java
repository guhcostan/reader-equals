package com.equals.reader.enums;

public enum TipoCard {

	UFLA_CARD(0),
	FGAMMON_CARD(1);

	private final int value;

	TipoCard(final int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
