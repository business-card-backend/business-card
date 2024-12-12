package solverz.business_card.domain.chatHistory.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "상담기록 삭제 응답")
public record DeleteChatHistoriesResponse(
        @Schema(description = "삭제된 상담기록 정보")
        List<DeleteChatHistoryResponse> chatHistories
) {
        public static DeleteChatHistoriesResponse from(List<DeleteChatHistoryResponse> chatHistories) {
            return DeleteChatHistoriesResponse.builder()
                    .chatHistories(chatHistories)
                    .build();
        }
}
