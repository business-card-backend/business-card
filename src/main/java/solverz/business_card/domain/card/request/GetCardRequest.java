package solverz.business_card.domain.card.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "명함 세부정보 요청")
public record GetCardRequest(
        @Schema(description = "명함 id", example = "1")
        Long cardId,

        @Schema(description = "멤버 토큰", defaultValue = "1234")
        String memberToken
) {
}
