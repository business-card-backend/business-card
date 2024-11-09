package solverz.business_card.domain.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solverz.business_card.domain.card.entity.Card;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
}
