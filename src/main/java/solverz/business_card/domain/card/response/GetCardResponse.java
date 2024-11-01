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
        String name,

        String companyName,

        String email,

        String phoneNumber,

        String cardImgURL,

        BigDecimal latitude,

        BigDecimal longitude,

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
