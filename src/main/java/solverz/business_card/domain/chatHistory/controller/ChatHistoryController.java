package solverz.business_card.domain.chatHistory.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solverz.business_card.domain.chatHistory.request.PostChatHistoryRequest;
import solverz.business_card.domain.chatHistory.response.PostChatHistoryResponse;
import solverz.business_card.domain.chatHistory.service.ChatHistoryService;

@Tag(name = "ChatHistory", description = "상담기록 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chatHistory")
public class ChatHistoryController {
    private final ChatHistoryService chatHistoryService;

    @Operation(summary = "상담기록 추가 API", description = "상담기록 추가를 요청하는 API")
    @PostMapping
    public ResponseEntity<PostChatHistoryResponse> addChatHistory(PostChatHistoryRequest request) {
        PostChatHistoryResponse response = chatHistoryService.addChatHistory(request);
        return ResponseEntity.ok(response);
    }
}
