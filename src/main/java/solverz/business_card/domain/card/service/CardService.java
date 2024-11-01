package solverz.business_card.domain.card.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solverz.business_card.domain.card.entity.Card;
import solverz.business_card.domain.card.repository.CardRepository;
import solverz.business_card.domain.card.request.GetCardRequest;
import solverz.business_card.domain.card.response.GetCardResponse;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    @Transactional(readOnly = true)
    public GetCardResponse getCard(GetCardRequest request) {
        Card card = cardRepository.findByIdAndMemberToken(request.cardId(), request.memberToken());
        return GetCardResponse.from(card);
    }
}
