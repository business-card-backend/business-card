package solverz.business_card.domain.chatHistory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solverz.business_card.domain.card.entity.Card;
import solverz.business_card.domain.card.response.DeleteCardResponse;
import solverz.business_card.domain.card.service.CardService;
import solverz.business_card.domain.chatHistory.entity.ChatHistory;
import solverz.business_card.domain.chatHistory.repository.ChatHistoryRepository;
import solverz.business_card.domain.chatHistory.request.DeleteChatHistoriesRequest;
import solverz.business_card.domain.chatHistory.request.DeleteChatHistoryRequest;
import solverz.business_card.domain.chatHistory.request.PostChatHistoryRequest;
import solverz.business_card.domain.chatHistory.request.PutChatHistoryRequest;
import solverz.business_card.domain.chatHistory.response.*;
import solverz.business_card.domain.common.execption.BusinessException;
import solverz.business_card.domain.common.execption.ErrorCode;
import solverz.business_card.domain.common.response.PageResponse;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatHistoryService {
    private final CardService cardService;
    private final ChatHistoryRepository chatHistoryRepository;

    public PostChatHistoryResponse addChatHistory(PostChatHistoryRequest request) {
        Card card = cardService.getOnlyCard(request.cardId());
        ChatHistory chatHistory = PostChatHistoryRequest.toChatHistory(request);
        chatHistory.updateCard(card);
        chatHistoryRepository.save(chatHistory);
        return PostChatHistoryResponse.from(chatHistory);
    }

    public PageResponse<GetChatHistoryResponse> getChatHistoryList(Long cardId, Pageable pageable) {
        Card card = cardService.getOnlyCard(cardId);
        Page<ChatHistory> chatHistories = chatHistoryRepository.findByCard(card, pageable);
        return PageResponse.of(chatHistories.stream().map(GetChatHistoryResponse::from).toList());
    }

    public GetChatHistoryResponse getChatHistory(Long chatHistoryId) {
        ChatHistory chatHistories = chatHistoryRepository.findById(chatHistoryId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_CHATHISTORY));
        return GetChatHistoryResponse.from(chatHistories);
    }

    public PutChatHistoryResponse modifyChatHistory(PutChatHistoryRequest request) {
        ChatHistory chatHistory = chatHistoryRepository.findById(request.chatHistoryId())
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_CHATHISTORY));
        ChatHistory modifyChatHistory = PutChatHistoryRequest.toChatHistory(request);
        chatHistory.updateChatHistory(modifyChatHistory);
        return PutChatHistoryResponse.from(chatHistory);
    }

    public DeleteChatHistoryResponse deleteChatHistory(DeleteChatHistoryRequest request) {
        ChatHistory chatHistory = chatHistoryRepository.findById(request.chatHistoryId())
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_CHATHISTORY));
        chatHistoryRepository.deleteById(request.chatHistoryId());
        return DeleteChatHistoryResponse.from(chatHistory);
    }

    @Transactional
    public List<DeleteChatHistoryResponse> deleteChatHistories(DeleteChatHistoriesRequest request) {
        List<DeleteChatHistoryResponse> responses = new ArrayList<>();

        for (Long id : request.getChatHistoryIds()) {
            ChatHistory chatHistory = chatHistoryRepository.findById(id)
                    .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_CHATHISTORY));
            chatHistoryRepository.deleteById(id);
            // 삭제된 명함에 대한 응답을 추가
            responses.add(DeleteChatHistoryResponse.from(chatHistory));
        }

        return responses;
    }
}
