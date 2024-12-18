package solverz.business_card.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import solverz.business_card.domain.member.entity.Member;
import solverz.business_card.domain.member.entity.MemberKey;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, MemberKey> {
    // 복합키 중 하나인 token으로 멤버 조회
    Optional<Member> findByMemberToken(String memberToken);

    // token으로 softDeleted되지 않은 멤버 조회
    Optional<Member> findByMemberTokenAndDeletedAtIsNull(String memberToken);

    public Optional<Member> findByDeletedAtIsNull();


    // 30일 이상 지난 soft delete 멤버 조회
    @Transactional
    @Query("SELECT m FROM Member m WHERE m.deletedAt <= :threshold")
    List<Member> findExpiredMembers(@Param("threshold") LocalDateTime threshold);

    // 30일 이상 지난 Soft Delete 멤버 삭제
    @Transactional
    //@Modifying(clearAutomatically = true)
    @Modifying
    @Query("DELETE FROM Member m WHERE m.deletedAt <= :threshold")
    int deleteExpiredMembers(@Param("threshold") LocalDateTime threshold);
}
