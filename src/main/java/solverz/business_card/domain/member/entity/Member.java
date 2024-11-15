package solverz.business_card.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import solverz.business_card.domain.card.entity.Card;
import solverz.business_card.domain.common.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "member")
//@IdClass(MemberKey.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자
public class Member extends BaseTimeEntity {
//    @SequenceGenerator(
//            name = "member_seq_gen1",
//            sequenceName = "member_seq_gen1",
//            allocationSize = 1
//    )
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="member_seq_gen1")
//    @EqualsAndHashCode.Include
//    @Column(name = "memberId", nullable = false)
//    Long memberId;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "memberToken", nullable = false)
    String memberToken;

    @Column(name = "email", nullable = false)
    String email;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "nickname", nullable = false)
    String nickname;

    @Column(name = "nameCardImgUrl")
    String nameCardImgUrl;

    @Column(name = "loginType", nullable = false)
    LoginType loginType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", orphanRemoval = true)
    private List<Card> cards = new ArrayList<>();

    @Builder
    public Member(Long memberId, String memberToken, String email, String password, String nickname, String nameCardImgUrl, LoginType loginType) {
//        this.memberId = memberId;
        this.memberToken = memberToken;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.nameCardImgUrl = nameCardImgUrl;
        this.loginType = loginType;
    }
}
