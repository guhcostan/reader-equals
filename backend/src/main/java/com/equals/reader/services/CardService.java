package com.equals.reader.services;

import com.equals.reader.enums.TipoCard;
import com.equals.reader.models.Card;
import com.equals.reader.models.CardCount;
import com.equals.reader.models.FagammonCard;
import com.equals.reader.models.UflaCard;
import com.equals.reader.repositories.CardRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

	@Autowired
	private CardRepository cardRepository;

	public Card salvar(String conteudoArquivo) throws IOException {

		Card card = null;

		TipoCard tipoArquivo = TipoCard.values()[Character
			.getNumericValue(conteudoArquivo.charAt(0))];

		switch (tipoArquivo) {

			case UFLA_CARD: {

				card = new UflaCard(
					tipoArquivo,
					conteudoArquivo.substring(1, 10).getBytes(),
					conteudoArquivo.substring(11, 18).getBytes(),
					conteudoArquivo.substring(19, 26).getBytes(),
					conteudoArquivo.substring(27, 34).getBytes(),
					conteudoArquivo.substring(35, 41).getBytes(),
					conteudoArquivo.substring(42)

				);
				break;
			}

			case FGAMMON_CARD: {
				card = new FagammonCard(
					tipoArquivo,
					conteudoArquivo.substring(1, 8).getBytes(),
					conteudoArquivo.substring(9, 16).getBytes(),
					conteudoArquivo.substring(17, 28),
					conteudoArquivo.substring(29).getBytes()
				);
				break;
			}

			default: {
				throw new IOException("O arquivo não possui um tipo de registro valido.");
			}
		}

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
				CardCount cardCount = new CardCount(tipoCard.getNome(),  cardRepository.countCardByTipoRegistro(tipoCard));
				cardCounts.add(cardCount);
			}
		);

		return cardCounts;
	}
}
