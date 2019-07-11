package com.equals.reader.models;

import com.equals.reader.enums.TipoCard;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "card")
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private TipoCard tipoRegistro;
	LocalDate dataProcessamento;
	private String estabelecimento;
	private String sequencia;
	private String empresaAdiquirente;
	private String pathArquivo;
	private boolean recepcionado;

	Card(TipoCard tipoRegistro, String estabelecimento, LocalDate dataProcessamento,
		String sequencia,
		String empresaAdiquirente) {
		this.tipoRegistro = tipoRegistro;
		this.estabelecimento = estabelecimento;
		this.dataProcessamento = dataProcessamento;
		this.sequencia = sequencia;
		this.empresaAdiquirente = empresaAdiquirente;
		this.recepcionado = false;
	}

	public void setPathArquivo(String pathArquivo) {
		this.pathArquivo = pathArquivo;
	}

	public Path getPathArquivo() {
		return Paths.get(this.pathArquivo);
	}

	public boolean isRecepcionado() {
		return recepcionado;
	}

	public void setRecepcionado(boolean recepcionado) {
		this.recepcionado = recepcionado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoCard getTipo() {
		return this.tipoRegistro;
	}

}
