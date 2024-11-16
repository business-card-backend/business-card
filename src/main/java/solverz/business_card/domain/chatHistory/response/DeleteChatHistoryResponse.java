package solverz.business_card.domain.chatHistory.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import solverz.business_card.domain.chatHistory.entity.ChatHistory;

@Builder
@Schema(description = "상담기록 삭제 응답")
public record DeleteChatHistoryResponse(
        @Schema(description = "상담기록 id")
        Long chatHistoryId,

        @Schema(description = "상담기록 제목")
        String title
) {
        public static DeleteChatHistoryResponse from(ChatHistory chatHistory) {
                return DeleteChatHistoryResponse.builder()
                        .chatHistoryId(chatHistory.getId())
                        .title(chatHistory.getTitle())
                        .build();
        }
}
