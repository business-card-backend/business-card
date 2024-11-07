package solverz.business_card.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import solverz.business_card.domain.common.BaseTimeEntity;


@Entity
@Getter
@Setter
@Builder
@Table(name = "member")
@IdClass(MemberKey.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자
public class Member extends BaseTimeEntity {
    @SequenceGenerator(
            name = "member_seq_gen1",
            sequenceName = "member_seq_gen1",
            allocationSize = 1
    )

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="member_seq_gen1")
    @EqualsAndHashCode.Include
    @Column(name = "memberId", nullable = false)
    Long memberId;

    @Id
    @Column(name = "memberToken", nullable = false)
    String memberToken;

    @Column(name = "email", nullable = false)
    String email;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "nameCardImgUrl")
    String nameCardImgUrl;

    @Column(name = "loginType", nullable = false)
    LoginType loginType;

    public Member(Long memberId, String memberToken, String email, String password, String name, String nameCardImgUrl, LoginType loginType) {
        this.memberId = memberId;
        this.memberToken = memberToken;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nameCardImgUrl = nameCardImgUrl;
        this.loginType = loginType;
    }
}
