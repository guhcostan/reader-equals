package com.equals.reader.controllers;

import com.equals.reader.services.FileService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

	@Autowired
	private FileService fileService;

	//Recebe arquivo da interface e passa para camadas de tratamento.
	@PostMapping("/enviarCard")
	public ResponseEntity<String> enviarCard(@RequestParam("file") MultipartFile arquivo)
		throws IOException {

		fileService.gravaArquivoAposValidar(arquivo);

		return new ResponseEntity<String>("Envio realizado com sucesso.", HttpStatus.OK);
	}
}
