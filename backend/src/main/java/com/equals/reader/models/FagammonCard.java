package com.equals.reader.models;

import com.equals.reader.enums.TipoCard;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
@Data
@Table(name = "fagammon_card")
public class FagammonCard extends Card {

	public FagammonCard(TipoCard tipoRegistro, LocalDate dataProcessamento, String estabelecimento,
		String empresaAdiquirente,
		String sequencia) {
		super(tipoRegistro, estabelecimento, dataProcessamento, sequencia, empresaAdiquirente);
	}

}
