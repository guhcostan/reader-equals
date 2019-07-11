package com.equals.reader.services;

import com.equals.reader.models.Card;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javassist.NotFoundException;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	@Autowired
	private CardService cardService;

	private Path pathFiles = Paths.get(System.getProperty("user.dir") + "/arquivos");

	public void gravaArquivoAposValidar(MultipartFile arquivo)
		throws IOException, FileUploadException {

		if (!pathFiles.toFile().exists()) {
			pathFiles.toFile().mkdir();
		}
		Path filepath = Paths.get(pathFiles.toString(), arquivo.getOriginalFilename());

		if (filepath.toFile().exists()) {
			throw new FileUploadException(
				"O arquivo " + arquivo.getOriginalFilename() + " já existe no sistema.");
		}

		String conteudoArquivo = new String(arquivo.getBytes(), StandardCharsets.UTF_8);

		Card card = cardService.salvar(conteudoArquivo, arquivo.getOriginalFilename());

		try (OutputStream os = Files.newOutputStream(filepath)) {
			os.write(arquivo.getBytes());
		} catch (IOException e) {
			throw new IOException(e);
		}

		File arquivoSalvo = new File(filepath.toUri());

		if (!arquivoSalvo.exists()) {
			throw new FileNotFoundException(
				"Ocorreu um erro ao salvar arquivo " + arquivoSalvo.getName());
		}

		cardService.salvarCaminhoArquivo(card, arquivoSalvo.getAbsolutePath());
	}

	public File baixarArquivoByCardId(long id) throws NotFoundException {
		return cardService.encontrarCaminhoByCardId(id).toFile();
	}

	public void deletarArquivo(Path pathArquivo) throws IOException {
		if (!pathArquivo.toFile().delete()) {
			throw new IOException("Erro ao deletar arquivo");
		}
	}
}
