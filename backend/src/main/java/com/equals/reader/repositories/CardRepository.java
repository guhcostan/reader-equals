package com.equals.reader.repositories;

import com.equals.reader.enums.TipoCard;
import com.equals.reader.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

	public long countCardByTipoRegistro(TipoCard tipoCard);
}
