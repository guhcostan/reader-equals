package com.equals.reader.models;

import java.time.LocalDate;
import lombok.Data;

@Data
public class CardCountDate {

	long contador;
	LocalDate dataInicio;

	public CardCountDate(LocalDate dataInicio, long contador) {
		this.contador = contador;
		this.dataInicio = dataInicio;
	}
}
