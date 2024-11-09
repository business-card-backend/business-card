package solverz.business_card.domain.card.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Builder;
import solverz.business_card.domain.card.entity.Card;
import solverz.business_card.domain.card.request.GetCardRequest;

import java.math.BigDecimal;

@Builder
@Schema(description = "명함 세부정보 응답")
public record GetCardResponse(
        @Schema(description = "고객 이름")
        String name,

        @Schema(description = "고객 회사명")
        String companyName,

        @Schema(description = "고객 이메일")
        String email,

        @Schema(description = "고객 전화번호")
        String phoneNumber,

        @Schema(description = "명함 이미지 URL")
        String cardImgURL,

        @Schema(description = "고객 회사 위도")
        BigDecimal latitude,

        @Schema(description = "고객 회사 경도")
        BigDecimal longitude,

        @Schema(description = "명함 메모")
        String memo
) {
        public static GetCardResponse from(Card card) {
            return GetCardResponse.builder()
                    .name(card.getName())
                    .companyName(card.getCompanyName())
                    .email(card.getEmail())
                    .phoneNumber(card.getPhoneNumber())
                    .cardImgURL(card.getCardImgURL())
                    .latitude(card.getLatitude())
                    .longitude(card.getLongitude())
                    .memo(card.getMemo())
                    .build();
        }
}
