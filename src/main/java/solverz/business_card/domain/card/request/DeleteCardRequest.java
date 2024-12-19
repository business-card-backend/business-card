package solverz.business_card.domain.card.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "명함 삭제 요청")
public record DeleteCardRequest(
        @Schema(description = "명함 id", example = "1")
        Long cardId,

        @Schema(description = "멤버 토큰", defaultValue = "1234")
        String memberToken
) {
}
