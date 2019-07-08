package com.equals.reader.controllers;

import com.equals.reader.models.CardCount;
import com.equals.reader.services.CardService;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

	@Autowired
	private CardService cardService;

	@GetMapping("/contarCards")
	public 	List<CardCount> contarCards(){

		return cardService.contabilizar();

	}
}
