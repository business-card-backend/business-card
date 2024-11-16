package solverz.business_card.domain.chatHistory.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "상담기록 삭제 요청")
public record DeleteChatHistoryRequest(
        @Schema(description = "명함 아이디", defaultValue = "1")
        Long cardId,

        @Schema(description = "상담기록 아이디", defaultValue = "1")
        Long chatHistoryId
) {
}
