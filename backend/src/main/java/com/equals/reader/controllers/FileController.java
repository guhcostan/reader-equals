package com.equals.reader.controllers;

import com.equals.reader.services.FileService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javassist.NotFoundException;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
		throws IOException, FileUploadException {

		fileService.gravaArquivoAposValidar(arquivo);

		return new ResponseEntity<String>("Envio realizado com sucesso.", HttpStatus.OK);
	}

	@GetMapping("/baixaArquivoCard")
	public ResponseEntity<Resource> baixaArquivoCard(long id)
		throws NotFoundException, FileNotFoundException {

		File arquivo = fileService.baixarArquivoByCardId(id);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(arquivo));

		HttpHeaders header = new HttpHeaders();
		header.add("filename", arquivo.getName());
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + arquivo.getName());
		header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		header.add("Pragma", "no-cache");
		header.add("Expires", "0");

		return ResponseEntity.ok()
			.headers(header)
			.contentLength(arquivo.length())
			.contentType(MediaType.parseMediaType("application/octet-stream"))
			.body(resource);

	}
}
