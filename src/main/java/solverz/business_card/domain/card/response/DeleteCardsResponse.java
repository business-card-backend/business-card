package solverz.business_card.domain.card.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "명함 삭제 응답")
public record DeleteCardsResponse(
        @Schema(description = "삭제된 명함 기록 정보")
        List<DeleteCardResponse> cards
) {
        public static DeleteCardsResponse from(List<DeleteCardResponse> cards) {
            return DeleteCardsResponse.builder()
                    .cards(cards)
                    .build();
        }
}
