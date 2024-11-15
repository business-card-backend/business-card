package solverz.business_card.domain.card.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solverz.business_card.domain.card.entity.Card;
import solverz.business_card.domain.card.repository.CardRepository;
import solverz.business_card.domain.card.request.GetCardRequest;
import solverz.business_card.domain.card.request.PostCardRequest;
import solverz.business_card.domain.card.response.GetCardResponse;
import solverz.business_card.domain.card.response.GetCardSummaryResponse;
import solverz.business_card.domain.card.response.PostCardResponse;
import solverz.business_card.domain.common.execption.BusinessException;
import solverz.business_card.domain.common.execption.ErrorCode;
import solverz.business_card.domain.common.response.PageResponse;
import solverz.business_card.domain.member.entity.Member;
import solverz.business_card.domain.member.service.MemberService;

@Service
@RequiredArgsConstructor
@Transactional
public class CardService {
    private final CardRepository cardRepository;
    private final MemberService memberService;

    @Transactional(readOnly = true)
    public GetCardResponse getCard(GetCardRequest request) {
        Card card = cardRepository.findById(request.cardId())
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_CARD));
        return GetCardResponse.from(card);
    }

    @Transactional(readOnly = true)
    public PageResponse<GetCardSummaryResponse> getCardList(String memberToken, Pageable pageable) {
        Page<Card> pages = cardRepository.findByMemberMemberToken(memberToken, pageable);
        return PageResponse.of(pages.getContent().stream().map(GetCardSummaryResponse::from).toList());
    }

    public PostCardResponse enrollCard(PostCardRequest request) {
        Member member = memberService.getOnlyMember(request.memberToken());
        Card card = PostCardRequest.toCard(request);
        card.updateMember(member);
        cardRepository.save(card);
        return PostCardResponse.from(card);
    }
}
