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
import solverz.business_card.domain.chatHistory.request.DeleteChatHistoriesRequest;
import solverz.business_card.domain.chatHistory.request.DeleteChatHistoryRequest;
import solverz.business_card.domain.chatHistory.request.PostChatHistoryRequest;
import solverz.business_card.domain.chatHistory.request.PutChatHistoryRequest;
import solverz.business_card.domain.chatHistory.response.*;
import solverz.business_card.domain.common.execption.BusinessException;
import solverz.business_card.domain.common.execption.ErrorCode;
import solverz.business_card.domain.common.response.PageResponse;
import solverz.business_card.domain.member.entity.Member;
import solverz.business_card.domain.member.service.MemberService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatHistoryService {
    private final CardService cardService;
    private final MemberService memberService;
    private final ChatHistoryRepository chatHistoryRepository;

    public PostChatHistoryResponse addChatHistory(PostChatHistoryRequest request) {
        Member member = memberService.getOnlyMember(request.memberToken());
        Card card = cardService.getOnlyCard(request.cardId(), request.memberToken());
        ChatHistory chatHistory = PostChatHistoryRequest.toChatHistory(request);
        chatHistory.updateMember(member);
        chatHistory.updateCard(card);
        chatHistoryRepository.save(chatHistory);
        return PostChatHistoryResponse.from(chatHistory);
    }

    public PageResponse<GetChatHistoryResponse> getChatHistoryList(Long cardId, String memberToken, Pageable pageable) {
        Card card = cardService.getOnlyCard(cardId, memberToken);
        Page<ChatHistory> chatHistories = chatHistoryRepository.findByCard(card, pageable);
        return PageResponse.of(chatHistories.stream().map(GetChatHistoryResponse::from).toList());
    }

    public GetChatHistoryResponse getChatHistory(Long chatHistoryId, String memberToken) {
        ChatHistory chatHistory = chatHistoryRepository.findById(chatHistoryId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_CHATHISTORY));
        if (!chatHistory.getMember().getMemberToken().equals(memberToken)) {
            throw new BusinessException(ErrorCode.NOT_CHATHISTORY_OWNER);
        }
        return GetChatHistoryResponse.from(chatHistory);
    }

    public PutChatHistoryResponse modifyChatHistory(PutChatHistoryRequest request) {
        ChatHistory chatHistory = chatHistoryRepository.findById(request.chatHistoryId())
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_CHATHISTORY));
        if (!chatHistory.getMember().getMemberToken().equals(request.memberToken())) {
            throw new BusinessException(ErrorCode.NOT_CHATHISTORY_OWNER);
        }
        ChatHistory modifyChatHistory = PutChatHistoryRequest.toChatHistory(request);
        chatHistory.updateChatHistory(modifyChatHistory);
        return PutChatHistoryResponse.from(chatHistory);
    }

    public DeleteChatHistoryResponse deleteChatHistory(DeleteChatHistoryRequest request) {
        ChatHistory chatHistory = chatHistoryRepository.findById(request.chatHistoryId())
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_CHATHISTORY));
        if (!chatHistory.getMember().getMemberToken().equals(request.memberToken())) {
            throw new BusinessException(ErrorCode.NOT_CHATHISTORY_OWNER);
        }
        chatHistoryRepository.deleteById(request.chatHistoryId());
        return DeleteChatHistoryResponse.from(chatHistory);
    }

    @Transactional
    public DeleteChatHistoriesResponse deleteChatHistories(DeleteChatHistoriesRequest request) {
        String ownerToken = request.memberToken();
        List<ChatHistory> chatHistories = request.chatHistoryIds().stream()
                                                .map(id -> {
                                                    ChatHistory chatHistory = chatHistoryRepository.findById(id)
                                                            .orElseThrow(() -> new BusinessException(ErrorCode.DELETION_FAILED_CHATHISTORY));
                                                    if (!chatHistory.getMember().getMemberToken().equals(ownerToken)) {
                                                        throw new BusinessException(ErrorCode.NOT_CHATHISTORY_OWNER);
                                                    }
                                                    return chatHistory;
                                                })
                                                .toList();
        List<DeleteChatHistoryResponse> deletedChatHistories;

        request.chatHistoryIds().forEach(id -> chatHistoryRepository.deleteById(id));
        deletedChatHistories = chatHistories.stream().map(DeleteChatHistoryResponse::from).toList();
        return DeleteChatHistoriesResponse.from(deletedChatHistories);
    }
}
