package solverz.business_card.domain.card.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import solverz.business_card.domain.card.entity.Card;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
        Page<Card> findByMemberMemberToken(String memberToken, Pageable pageable);
}
