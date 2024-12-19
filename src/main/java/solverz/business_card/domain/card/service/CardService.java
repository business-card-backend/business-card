package solverz.business_card.domain.card.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solverz.business_card.domain.card.entity.Card;
import solverz.business_card.domain.card.repository.CardRepository;
import solverz.business_card.domain.card.request.*;
import solverz.business_card.domain.card.response.*;
import solverz.business_card.domain.common.execption.BusinessException;
import solverz.business_card.domain.common.execption.ErrorCode;
import solverz.business_card.domain.common.response.PageResponse;
import solverz.business_card.domain.member.entity.Member;
import solverz.business_card.domain.member.service.MemberService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CardService {
    private final CardRepository cardRepository;
    private final MemberService memberService;

    public Card getOnlyCard(Long id, String memberToken) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_CARD));
        if (!card.getMember().getMemberToken().equals(memberToken)) {
            throw new BusinessException(ErrorCode.NOT_CARD_OWNER);
        }
        return card;
    }

    @Transactional(readOnly = true)
    public GetCardResponse getCard(Long cardId, String memberToken) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_CARD));
        if (!card.getMember().getMemberToken().equals(memberToken)) {
            throw new BusinessException(ErrorCode.NOT_CARD_OWNER);
        }
        return GetCardResponse.from(card);
    }

    @Transactional(readOnly = true)
    public PageResponse<GetCardSummaryResponse> getCardList(String memberToken, Pageable pageable) {
        Member member = memberService.getOnlyMember(memberToken);
        Page<Card> pages = cardRepository.findByMember(member, pageable);
        return PageResponse.of(pages.getContent().stream().map(GetCardSummaryResponse::from).toList());
    }

    public PostCardResponse enrollCard(PostCardRequest request) {
        Member member = memberService.getOnlyMember(request.memberToken());
        Card card = PostCardRequest.toCard(request);
        card.updateMember(member);
        cardRepository.save(card);
        return PostCardResponse.from(card);
    }

    public PutCardResponse modifyCard(PutCardRequest request) {
        Card card = cardRepository.findById(request.cardId())
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_CARD));
        if (!card.getMember().getMemberToken().equals(request.memberToken())) {
            throw new BusinessException(ErrorCode.NOT_CARD_OWNER);
        }
        Card modifyCard = PutCardRequest.toCard(request);
        card.updateCard(modifyCard);
        return PutCardResponse.from(card);
    }

    @Transactional
    public DeleteCardsResponse deleteCards(DeleteCardsRequest request) {
        String ownerToken = request.memberToken();
        List<Card> cards = request.cardIds().stream()
                                .map(id -> {
                                    Card card = cardRepository.findById(id)
                                            .orElseThrow(() -> new BusinessException(ErrorCode.DELETION_FAILED_CARD));
                                    if (!card.getMember().getMemberToken().equals(ownerToken)) {
                                        throw new BusinessException(ErrorCode.NOT_CARD_OWNER);
                                    }
                                    return card; // 조건을 통과한 카드 반환
                                })
                                .toList();
        List<DeleteCardResponse> deletedCards;
        request.cardIds().forEach(cardRepository::deleteById);
        deletedCards = cards.stream().map(DeleteCardResponse::from).toList();
        return DeleteCardsResponse.from(deletedCards);
    }

    public DeleteCardResponse deleteCard(DeleteCardRequest request) {
        Card card = cardRepository.findById(request.cardId())
                .orElseThrow(() -> new BusinessException(ErrorCode.DELETION_FAILED_CARD));
        if (!card.getMember().getMemberToken().equals(request.memberToken())) {
            throw new BusinessException(ErrorCode.NOT_CARD_OWNER);
        }
        cardRepository.deleteById(request.cardId());
        return DeleteCardResponse.from(card);
    }
}
