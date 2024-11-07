package solverz.business_card.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solverz.business_card.domain.member.entity.Member;
import solverz.business_card.domain.member.entity.MemberKey;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, MemberKey> {
    // 복합키 중 하나인 token으로 멤버 조회
    Optional<Member> findByMemberToken(String memberToken);
}
