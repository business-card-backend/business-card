package solverz.business_card.domain.chatHistory.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import solverz.business_card.domain.chatHistory.entity.ChatHistory;

import java.time.LocalDateTime;

@Builder
@Schema(description = "상담기록 요약 요청 응답")
public record GetChatHistorySummaryResponse(
        Long chatHistoryId,

        String title,

        LocalDateTime chatAt
) {
        public static GetChatHistorySummaryResponse from(ChatHistory chatHistory) {
            return GetChatHistorySummaryResponse.builder()
                    .chatHistoryId(chatHistory.getId())
                    .title(chatHistory.getTitle())
                    .chatAt(chatHistory.getChatAt())
                    .build();
        }
}
