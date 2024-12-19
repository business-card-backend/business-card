package solverz.business_card.domain.card.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "명함 여러개 삭제 요청")
public record DeleteCardsRequest(
        @Schema(description = "명함 ids", example = "[1]")
        List<Long> cardIds,

        @Schema(description = "멤버 토큰", defaultValue = "1234")
        String memberToken
) {
}
