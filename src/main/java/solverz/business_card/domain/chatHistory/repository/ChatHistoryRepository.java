package solverz.business_card.domain.chatHistory.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import solverz.business_card.domain.card.entity.Card;
import solverz.business_card.domain.chatHistory.entity.ChatHistory;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {
        Page<ChatHistory> findByCard(Card card, Pageable pageable);
}
