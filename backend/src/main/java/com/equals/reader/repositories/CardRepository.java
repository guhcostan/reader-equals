package com.equals.reader.repositories;

import com.equals.reader.enums.TipoCard;
import com.equals.reader.models.Card;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

	long countCardsByDataProcessamentoBetween(LocalDate dataProcessamento,
		LocalDate dataProcessamento2);

	long countCardByTipoRegistro(TipoCard tipoCard);

	long countCardsByRecepcionado(boolean recepcionado);

	Optional<Card> findCardByPathArquivo(String pathArquivo);
}
