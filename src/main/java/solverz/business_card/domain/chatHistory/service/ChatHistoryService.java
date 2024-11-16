package solverz.business_card.domain.chatHistory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solverz.business_card.domain.card.entity.Card;
import solverz.business_card.domain.card.service.CardService;
import solverz.business_card.domain.chatHistory.entity.ChatHistory;
import solverz.business_card.domain.chatHistory.repository.ChatHistoryRepository;
import solverz.business_card.domain.chatHistory.request.PostChatHistoryRequest;
import solverz.business_card.domain.chatHistory.response.GetChatHistoryResponse;
import solverz.business_card.domain.chatHistory.response.PostChatHistoryResponse;
import solverz.business_card.domain.common.response.PageResponse;

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
        PostChatHistoryResponse response = PostChatHistoryResponse.from(chatHistory);
        return response;
    }

    public PageResponse<GetChatHistoryResponse> getChatHistoryList(Long cardId, Pageable pageable) {
        Card card = cardService.getOnlyCard(cardId);
        Page<ChatHistory> chatHistories = chatHistoryRepository.findByCard(card, pageable);
        return PageResponse.of(chatHistories.stream().map(GetChatHistoryResponse::from).toList());
    }
}
