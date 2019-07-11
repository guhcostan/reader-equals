package com.equals.reader.controllers;

import com.equals.reader.models.Card;
import com.equals.reader.models.CardCount;
import com.equals.reader.models.CardCountDate;
import com.equals.reader.models.CardsRecepcionados;
import com.equals.reader.services.CardService;
import java.io.IOException;
import java.util.List;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

	@Autowired
	private CardService cardService;

	@GetMapping("/contarCards")
	public 	List<CardCount> contarCards(){

		return cardService.contabilizar();

	}

	@PostMapping("/cadastrarCard")
	public ResponseEntity<String> cadastrarCard(@RequestBody Card card) {

		cardService.cadastrarCard(card);

		return new ResponseEntity<String>("Card cadastrado com sucesso.", HttpStatus.OK);
	}

	@GetMapping("/cardsByPage")
	public Page<Card> cardsByPage(Integer page) {

		return cardService.getCardsByPage(page);

	}

	@GetMapping("/contabilizarCardsPorData")
	public List<CardCountDate> contabilizarCardsPorData() {

		return cardService.contabilizarCardsPorData();

	}

	@GetMapping("/contabilizarCardsRecepcionados")
	public List<CardsRecepcionados> contabilizarCardsRecepcionados() {

		return cardService.contabilizarCardsRecepcionados();

	}

	@DeleteMapping("/deletarCard")
	public ResponseEntity<String> deletaCard(Long id) throws NotFoundException, IOException {

		cardService.deletarById(id);
		return new ResponseEntity<String>("Card deletado com sucesso.", HttpStatus.OK);
	}
}
