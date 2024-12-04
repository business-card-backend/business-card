package solverz.business_card.domain.chatHistory.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import solverz.business_card.domain.chatHistory.entity.ChatHistory;

import java.time.LocalDateTime;

@Builder
@Schema(description = "상담기록 요청 응답")
public record GetChatHistoryResponse(
        @Schema(description = "상담기록 id")
        Long chatHistoryId,

        @Schema(description = "상담기록 제목")
        String title,

        @Schema(description = "상담기록 내용")
        String content,

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
        @Schema(description = "상담 일시", defaultValue = "2024-12-02T07:59:48.540", type="string")
        LocalDateTime chatAt
) {
        public static GetChatHistoryResponse from(ChatHistory chatHistory) {
            return GetChatHistoryResponse.builder()
                    .chatHistoryId(chatHistory.getId())
                    .title(chatHistory.getTitle())
                    .content(chatHistory.getContent())
                    .chatAt(chatHistory.getChatAt())
                    .build();
        }
}
