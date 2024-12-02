package solverz.business_card.domain.card.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import solverz.business_card.domain.card.entity.Card;

@Builder
@Schema(description = "명함 요약 정보 응답")
public record GetCardSummaryResponse(
        @Schema(description = "명함 id")
        Long cardId,

        @Schema(description = "명함 이미지 주소")
        String cardImgURL,

        @Schema(description = "명함 회사 이름")
        String companyName,

        @Schema(description = "명함 회사 주소")
        String companyAddress,

        @Schema(description = "명함 회사 번호")
        String phoneNumber
) {
        public static GetCardSummaryResponse from(Card card) {
            return GetCardSummaryResponse.builder()
                    .cardId(card.getId())
                    .cardImgURL(card.getCardImgURL())
                    .companyName(card.getCompanyName())
                    .companyAddress(card.getCompanyAddress())
                    .phoneNumber(card.getPhoneNumber())
                    .build();
        }
}
