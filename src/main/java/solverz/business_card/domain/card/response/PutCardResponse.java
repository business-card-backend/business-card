package solverz.business_card.domain.card.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import solverz.business_card.domain.card.entity.Card;

@Builder
@Schema(description = "명함 수정 응답")
public record PutCardResponse(
        @Schema(description = "등록된 명함 id")
        Long cardId,

        @Schema(description = "등록된 고객 이름")
        String name,

        @Schema(description = "등록된 고객 회사명")
        String companyName,

        @Schema(description = "등록된 고객 이메일")
        String email
) {
        public static PutCardResponse from(Card card) {
            return PutCardResponse.builder()
                    .cardId(card.getId())
                    .name(card.getName())
                    .companyName(card.getCompanyName())
                    .email(card.getEmail())
                    .build();
        }
}
