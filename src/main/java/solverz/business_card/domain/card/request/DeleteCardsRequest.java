package solverz.business_card.domain.card.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Schema(description = "1개 이상의 명함 삭제 요청")
public class DeleteCardsRequest {
    @Schema(description = "명함 ids", example = "[1]")
    List<Long> cardIds;
}
