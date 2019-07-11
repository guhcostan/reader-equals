package com.equals.reader.models;

import com.equals.reader.enums.TipoCard;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ufla_card")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UflaCard extends Card {

	LocalDate periodoInicial;
	LocalDate periodoFinal;

	public UflaCard(TipoCard tipoRegistro, String estabelecimento, LocalDate dataProcessamento,
		LocalDate periodoInicial, LocalDate periodoFinal, String sequencia,
		String empresaAdiquirente) {
		super(tipoRegistro, estabelecimento, dataProcessamento, sequencia, empresaAdiquirente);
		this.periodoInicial = periodoInicial;
		this.periodoFinal = periodoFinal;
	}
}
