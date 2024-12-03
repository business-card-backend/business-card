package solverz.business_card.domain.chatHistory.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solverz.business_card.domain.chatHistory.request.DeleteChatHistoryRequest;
import solverz.business_card.domain.chatHistory.request.PostChatHistoryRequest;
import solverz.business_card.domain.chatHistory.request.PutChatHistoryRequest;
import solverz.business_card.domain.chatHistory.response.DeleteChatHistoryResponse;
import solverz.business_card.domain.chatHistory.response.GetChatHistoryResponse;
import solverz.business_card.domain.chatHistory.response.PostChatHistoryResponse;
import solverz.business_card.domain.chatHistory.response.PutChatHistoryResponse;
import solverz.business_card.domain.chatHistory.service.ChatHistoryService;
import solverz.business_card.domain.common.response.PageResponse;

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

    @Operation(summary = "상담기록 목록 API", description = "특정 명함의 상담기록을 요청하는 API")
    @Parameter(name = "page", description = "페이지 번호")
    @Parameter(name = "size", description = "페이지 크기")
    @GetMapping
    public ResponseEntity<PageResponse<GetChatHistoryResponse>> getChatHistoryList(
            @RequestParam Long cardId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        PageResponse<GetChatHistoryResponse> response = chatHistoryService.getChatHistoryList(cardId, pageable);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "상담기록 상세정보 API", description = "상담기록의 상세정보를 요청하는 API")
    @Parameter(name = "chatHistoryId", description = "상담기록 id")
    @GetMapping("/detail")
    public ResponseEntity<GetChatHistoryResponse> getChatHistory(@RequestParam Long chatHistoryId) {
        GetChatHistoryResponse response = chatHistoryService.getChatHistory(chatHistoryId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "상담기록 수정 API", description = "상담기록 수정을 요청하는 API")
    @PutMapping
    public ResponseEntity<PutChatHistoryResponse> modifyChatHistory(PutChatHistoryRequest request) {
        PutChatHistoryResponse response = chatHistoryService.modifyChatHistory(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "상담기록 삭제 API", description = "상담기록 삭제를 요청하는 API")
    @DeleteMapping
    public ResponseEntity<DeleteChatHistoryResponse> deleteChatHistory(DeleteChatHistoryRequest request) {
        DeleteChatHistoryResponse response = chatHistoryService.deleteChatHistory(request);
        return ResponseEntity.ok(response);
    }
}
