package solverz.business_card.domain.card.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "명함 세부정보 요청")
public record GetCardRequest(
        @Schema(description = "Member Token")
        String memberToken,

        @Schema(description = "Card ID", example = "1")
        Long cardId
) {
}
