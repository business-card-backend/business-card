package solverz.business_card.domain.card.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "명함 삭제 요청")
public record DeleteCardRequest(
        @Schema(description = "Member Token", defaultValue = "1234")
        String memberToken,

        @Schema(description = "Card ID", example = "1")
        Long cardId
) {
}
