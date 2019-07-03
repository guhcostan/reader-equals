package com.equals.reader.models;

import com.equals.reader.enums.TipoCard;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ufla_card")
public class UflaCard extends Card{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	byte[] periodoInicial;
	byte[] periodoFinal;

	public UflaCard(TipoCard tipoRegistro, byte[] estabelecimento, byte[] dataProcessamento,
		byte[] periodoInicial, byte[] periodoFinal, byte[] sequencia, String empresaAdiquirente) {
		super(tipoRegistro, estabelecimento, dataProcessamento, sequencia, empresaAdiquirente);
		this.periodoInicial = periodoInicial;
		this.periodoFinal = periodoFinal;
	}
}
