package solverz.business_card.domain.card.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import solverz.business_card.domain.card.entity.Card;
import solverz.business_card.domain.member.entity.Member;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
        Page<Card> findByMember(Member member, Pageable pageable);

        Optional<Card> findByIdAndMember(Long cardId, Member member);
}
