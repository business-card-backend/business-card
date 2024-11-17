package solverz.business_card.domain.chatHistory.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import solverz.business_card.domain.card.entity.Card;
import solverz.business_card.domain.common.BaseTimeEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "chatHistory")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatHistory extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    Long id;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "content", nullable = false)
    String content;

    @Column(name = "chatAt", nullable = false)
    LocalDateTime chatAt;

    @ManyToOne
    @JoinColumn(name = "cardId", nullable = false)
    private Card card;

    @Builder
    public ChatHistory(String title, String content, LocalDateTime chatAt) {
        this.title = title;
        this.content = content;
        this.chatAt = chatAt;
    }

    public void updateCard(Card card) {
        this.card = card;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateChatAt(LocalDateTime chatAt) {
        this.chatAt = chatAt;
    }

    public void updateChatHistory(ChatHistory chatHistory) {
        this.updateTitle(chatHistory.getTitle());
        this.updateContent(chatHistory.getContent());
        this.updateChatAt(chatHistory.getChatAt());
    }
}
