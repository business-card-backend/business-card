package solverz.business_card.domain.chatHistory.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import solverz.business_card.domain.chatHistory.entity.ChatHistory;

import java.time.LocalDateTime;

public record PutChatHistoryRequest(
        @Schema(description = "명함 아이디", defaultValue = "1")
        Long cardId,

        @Schema(description = "상담기록 아이디", defaultValue = "1")
        Long chatHistoryId,

        @Schema(description = "상담기록 제목", defaultValue = "슈트 상담기록")
        String title,

        @Schema(description = "상담기록 내용", defaultValue = "오늘 회의는 성공적")
        String content,

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        @Schema(description = "상담 일시", example = "2024-11-16T06:17")
        LocalDateTime chatAt
) {
    public static ChatHistory toChatHistory(PutChatHistoryRequest request) {
        return ChatHistory.builder()
                .title(request.title())
                .content(request.content())
                .chatAt(request.chatAt())
                .build();
    }
}
