package solverz.business_card.domain.chatHistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solverz.business_card.domain.chatHistory.entity.ChatHistory;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {
}
