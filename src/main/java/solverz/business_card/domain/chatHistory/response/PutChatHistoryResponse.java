package solverz.business_card.domain.chatHistory.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import solverz.business_card.domain.chatHistory.entity.ChatHistory;

@Builder
@Schema(description = "상담기록 수정 응답")
public record PutChatHistoryResponse(
        @Schema(description = "상담기록 id")
        Long chatHistoryId,

        @Schema(description = "상담기록 제목")
        String title
) {
        public static PutChatHistoryResponse from(ChatHistory chatHistory) {
            return PutChatHistoryResponse.builder()
                    .chatHistoryId(chatHistory.getId())
                    .title(chatHistory.getTitle())
                    .build();
        }
}
