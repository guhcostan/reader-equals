package com.equals.reader.models;

import com.equals.reader.enums.TipoCard;
import java.nio.file.Path;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private TipoCard tipoRegistro;
	private byte[] estabelecimento;
	private byte[] dataProcessamento;
	private byte[] sequencia;
	private String empresaAdiquirente;
	private String pathArquivo;

	Card(TipoCard tipoRegistro, byte[] estabelecimento, byte[] dataProcessamento,
		byte[] sequencia,
		String empresaAdiquirente) {
		this.tipoRegistro = tipoRegistro;
		this.estabelecimento = estabelecimento;
		this.dataProcessamento = dataProcessamento;
		this.sequencia = sequencia;
		this.empresaAdiquirente = empresaAdiquirente;
	}

	public void setPathArquivo(String pathArquivo) {
		this.pathArquivo = pathArquivo;
	}
}
