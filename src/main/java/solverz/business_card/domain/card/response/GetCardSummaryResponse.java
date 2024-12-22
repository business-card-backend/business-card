package solverz.business_card.domain.card.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import solverz.business_card.domain.card.entity.Card;

import java.math.BigDecimal;

@Builder
@Schema(description = "명함 요약 정보 응답")
public record GetCardSummaryResponse(
        @Schema(description = "명함 id")
        Long cardId,

        @Schema(description = "명함 이미지 주소")
        String cardImgURL,

        @Schema(description = "고객 이름")
        String name,

        @Schema(description = "고객 직책")
        String position,

        @Schema(description = "고객 회사명")
        String companyName,

        @Schema(description = "고객 회사 주소")
        String companyAddress,

        @Schema(description = "고객 회사 번호")
        String phoneNumber,

        @Schema(description = "고객 회사 위도")
        BigDecimal latitude,

        @Schema(description = "고객 회사 경도")
        BigDecimal longitude
) {
        public static GetCardSummaryResponse from(Card card) {
            return GetCardSummaryResponse.builder()
                    .cardId(card.getId())
                    .cardImgURL(card.getCardImgURL())
                    .name(card.getName())
                    .position(card.getPosition())
                    .companyName(card.getCompanyName())
                    .companyAddress(card.getCompanyAddress())
                    .phoneNumber(card.getPhoneNumber())
                    .latitude(card.getLatitude())
                    .longitude(card.getLongitude())
                    .build();
        }
}
