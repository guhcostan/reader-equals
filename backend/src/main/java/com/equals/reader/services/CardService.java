package com.equals.reader.services;

import com.equals.reader.enums.TipoCard;
import com.equals.reader.models.Card;
import com.equals.reader.models.CardCount;
import com.equals.reader.models.CardCountDate;
import com.equals.reader.models.CardsRecepcionados;
import com.equals.reader.models.FagammonCard;
import com.equals.reader.models.UflaCard;
import com.equals.reader.repositories.CardRepository;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CardService {

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private FileService fileService;

	public Card salvar(String conteudoArquivo, String nomeArquivo) throws IOException {

		Optional<Card> cardOptional = cardRepository.findCardByPathArquivo(nomeArquivo);

		Card card = null;

		cardOptional.ifPresent(value -> cardRepository.delete(value));

		TipoCard tipoArquivo = TipoCard.values()[Character
			.getNumericValue(conteudoArquivo.charAt(0))];

		switch (tipoArquivo) {

			case UFLA_CARD: {

				card = new UflaCard(
					tipoArquivo,
					conteudoArquivo.substring(1, 11),
					LocalDate.parse(
						conteudoArquivo.substring(11, 15) + "-" + conteudoArquivo.substring(15, 17)
							+ "-" + conteudoArquivo.substring(17, 19),
						DateTimeFormatter.ofPattern("yyyy-MM-dd")),
					LocalDate.parse(
						conteudoArquivo.substring(19, 23) + "-" + conteudoArquivo.substring(23, 25)
							+ "-" + conteudoArquivo.substring(25, 27),
						DateTimeFormatter.ofPattern("yyyy-MM-dd")),
					LocalDate.parse(
						conteudoArquivo.substring(27, 31) + "-" + conteudoArquivo.substring(31, 33)
							+ "-" + conteudoArquivo.substring(33, 35),
						DateTimeFormatter.ofPattern("yyyy-MM-dd")),
					conteudoArquivo.substring(35, 42),
					conteudoArquivo.substring(42));

				break;
			}

			case FGAMMON_CARD: {

				card = new FagammonCard(
					tipoArquivo,
					LocalDate.parse(
						conteudoArquivo.substring(1, 5) + "-" + conteudoArquivo.substring(5, 7)
							+ "-" + conteudoArquivo.substring(7, 9),
						DateTimeFormatter.ofPattern("yyyy-MM-dd")),
					conteudoArquivo.substring(9, 17),
					conteudoArquivo.substring(17, 29),
					conteudoArquivo.substring(29)
				);

				break;
			}

			default: {
				throw new IOException("O arquivo não possui um tipo de registro valido.");
			}
		}

		card.setRecepcionado(true);
		cardRepository.save(card);

		return card;

	}

	public void salvarCaminhoArquivo(Card card, String caminhoArquivo) {

		card.setPathArquivo(caminhoArquivo);
		cardRepository.save(card);
	}

	public List<CardCount> contabilizar() {
		List<CardCount> cardCounts = new ArrayList<>();
		ArrayList<TipoCard> listaTiposCard = new ArrayList<>(Arrays.asList(TipoCard.values()));

		listaTiposCard.forEach(
			tipoCard -> {
				cardCounts.add(new CardCount(tipoCard.getNome(),
					cardRepository.countCardByTipoRegistro(tipoCard)));
			}
		);

		return cardCounts;
	}

	public List<CardCountDate> contabilizarCardsPorData() {

		List<CardCountDate> cardCounts = new ArrayList<>();

		LocalDate dateInicial = LocalDate.now().minusDays(60);

		while (dateInicial.isBefore(LocalDate.now())) {
			cardCounts.add(new CardCountDate(dateInicial, cardRepository
				.countCardsByDataProcessamentoBetween(dateInicial, dateInicial.plusDays(10))));
			dateInicial = dateInicial.plusDays(10);
		}

		return cardCounts;
	}

	public Page<Card> getCardsByPage(int pagina) {
		PageRequest page_req = PageRequest.of(pagina, 10);
		return cardRepository.findAll(page_req);
	}

	public Path encontrarCaminhoByCardId(long id) throws NotFoundException {
		Optional<Card> card = cardRepository.findById(id);
		if (!card.isPresent()) {
			throw new NotFoundException("Card não encontrado");
		}
		return card.get().getPathArquivo();
	}

	public void deletarById(Long id) throws NotFoundException, IOException {
		Optional<Card> card = cardRepository.findById(id);

		if (!card.isPresent()) {
			throw new NotFoundException("Card informado não existe no sistema.");
		}

		Path pathArquivo = card.get().getPathArquivo();
		cardRepository.deleteById(id);

		card = cardRepository.findById(id);

		if (!card.isPresent()) {
			fileService.deletarArquivo(pathArquivo);
		}
	}

	public void cadastrarCard(Card card) {
		card.setRecepcionado(false);
		cardRepository.save(card);
	}

	public List<CardsRecepcionados> contabilizarCardsRecepcionados() {

		List<CardsRecepcionados> cardCounts = new ArrayList<>();

		cardCounts.add(
			new CardsRecepcionados("Recepcionados", cardRepository.countCardsByRecepcionado(true)));

		cardCounts.add(new CardsRecepcionados("Não recepcionados",
			cardRepository.countCardsByRecepcionado(false)));

		return cardCounts;
	}
}
