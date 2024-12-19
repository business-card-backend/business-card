package solverz.business_card.domain.chatHistory.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "상담기록 여러개 삭제 요청")
public record DeleteChatHistoriesRequest(
        @Schema(description = "상담기록 아이디", defaultValue = "[1, 2]")
        List<Long> chatHistoryIds,

        @Schema(description = "멤버 토큰", defaultValue = "1234")
        String memberToken
) {
}
