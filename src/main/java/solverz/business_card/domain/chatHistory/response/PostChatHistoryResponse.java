package solverz.business_card.domain.chatHistory.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import solverz.business_card.domain.chatHistory.entity.ChatHistory;

@Builder
@Schema(description = "상담기록 추가 응답")
public record PostChatHistoryResponse(
        @Schema(description = "상담기록 id")
        Long cardId,

        @Schema(description = "상담기록 제목")
        String title
) {
        public static PostChatHistoryResponse from(ChatHistory chatHistory) {
            return PostChatHistoryResponse.builder()
                    .cardId(chatHistory.getCard().getId())
                    .title(chatHistory.getTitle())
                    .build();
        }
}
