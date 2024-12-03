package solverz.business_card.domain.chatHistory.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import solverz.business_card.domain.chatHistory.entity.ChatHistory;

import java.time.LocalDateTime;

@Schema(description = "상담기록 추가 요청")
public record PostChatHistoryRequest(
        @Schema(description = "명함 아이디", defaultValue = "1")
        Long cardId,

        @Schema(description = "상담기록 제목", defaultValue = "슈트 상담기록")
        String title,

        @Schema(description = "상담기록 내용", defaultValue = "오늘 회의는 성공적")
        String content,

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
        @Schema(description = "상담 일시", defaultValue = "2024-12-02T07:59:48.540", type="string")
        LocalDateTime chatAt
) {
        public static ChatHistory toChatHistory(PostChatHistoryRequest request) {
                // 현재 시간을 기본값으로 설정
                LocalDateTime defaultChatAt = LocalDateTime.now();

                return ChatHistory.builder()
                        .title(request.title())
                        .content(request.content())
                        .chatAt(request.chatAt() != null ? request.chatAt() : defaultChatAt)
                        .build();
        }
}
