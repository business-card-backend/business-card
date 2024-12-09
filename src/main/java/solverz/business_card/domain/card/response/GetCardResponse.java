package solverz.business_card.domain.card.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import solverz.business_card.domain.card.entity.Card;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Schema(description = "명함 세부정보 응답")
public record GetCardResponse(
        @Schema(description = "고객 이름")
        String name,

        @Schema(description = "고객 생일")
        LocalDate birth,

        @Schema(description = "고객 회사명")
        String companyName,

        @Schema(description = "고객 이메일")
        String email,

        @Schema(description = "고객 전화번호")
        String phoneNumber,

        @Schema(description = "명함 이미지 URL")
        String cardImgURL,

        @Schema(description = "고객 회사 주소")
        String companyAddress,

        @Schema(description = "고객 회사 위도")
        BigDecimal latitude,

        @Schema(description = "고객 회사 경도")
        BigDecimal longitude,

        @Schema(description = "명함 메모")
        String memo,

        @Schema(description = "고객 직책", defaultValue = "과장")
        String position
) {
        public static GetCardResponse from(Card card) {
            return GetCardResponse.builder()
                    .name(card.getName())
                    .birth(card.getBirth())
                    .companyName(card.getCompanyName())
                    .email(card.getEmail())
                    .phoneNumber(card.getPhoneNumber())
                    .cardImgURL(card.getCardImgURL())
                    .companyAddress(card.getCompanyAddress())
                    .companyName(card.getCompanyName())
                    .latitude(card.getLatitude())
                    .longitude(card.getLongitude())
                    .memo(card.getMemo())
                    .position(card.getPosition())
                    .build();
        }
}
