package solverz.business_card.domain.chatHistory.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@Schema(description = "1개 이상 상담기록 삭제 요청")
public class DeleteChatHistoriesRequest {
    @Schema(description = "상담기록 아이디", defaultValue = "[1]")
    List<Long> chatHistoryIds;
}
