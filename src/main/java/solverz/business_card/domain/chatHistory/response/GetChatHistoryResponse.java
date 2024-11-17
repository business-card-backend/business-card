package solverz.business_card.domain.chatHistory.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import solverz.business_card.domain.chatHistory.entity.ChatHistory;

import java.time.LocalDateTime;

@Builder
@Schema(description = "상담기록 요청 응답")
public record GetChatHistoryResponse(
        Long id,

        String title,

        String content,

        LocalDateTime chatAt
) {
        public static GetChatHistoryResponse from(ChatHistory chatHistory) {
            return GetChatHistoryResponse.builder()
                    .id(chatHistory.getId())
                    .title(chatHistory.getTitle())
                    .content(chatHistory.getContent())
                    .chatAt(chatHistory.getChatAt())
                    .build();
        }
}
