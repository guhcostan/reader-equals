package com.equals.reader.models;

import com.equals.reader.enums.TipoCard;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fagammon_card")
public class FagammonCard extends Card{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public FagammonCard(TipoCard tipoRegistro, byte[] dataProcessamento, byte[] estabelecimento,  String empresaAdiquirente,
		byte[] sequencia) {
		super(tipoRegistro, estabelecimento, dataProcessamento, sequencia, empresaAdiquirente);
	}
}
