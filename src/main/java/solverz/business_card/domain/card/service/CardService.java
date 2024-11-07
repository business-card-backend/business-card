package solverz.business_card.domain.card.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solverz.business_card.domain.card.entity.Card;
import solverz.business_card.domain.card.repository.CardRepository;
import solverz.business_card.domain.card.request.GetCardRequest;
import solverz.business_card.domain.card.request.PostCardRequest;
import solverz.business_card.domain.card.response.GetCardResponse;
import solverz.business_card.domain.card.response.PostCardResponse;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class CardService {
    private final CardRepository cardRepository;

    @Transactional(readOnly = true)
    public GetCardResponse getCard(GetCardRequest request) {
        Card card = cardRepository.findByIdAndMemberToken(request.cardId(), request.memberToken())
                .orElseThrow(() -> new NoSuchElementException("Card not found"));
        return GetCardResponse.from(card);
    }

    public PostCardResponse enrollCard(PostCardRequest request) {
        Card card = PostCardRequest.toCard(request);
        card.updateMember("Test");
        cardRepository.save(card);
        return PostCardResponse.from(card);
    }
}
