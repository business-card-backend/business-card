package solverz.business_card.domain.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solverz.business_card.domain.card.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByIdAndMemberToken(Long id, String memberToken);
}
