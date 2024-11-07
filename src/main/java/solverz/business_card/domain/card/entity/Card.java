package solverz.business_card.domain.card.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import solverz.business_card.domain.common.BaseTimeEntity;
import solverz.business_card.domain.member.entity.Member;

import java.math.BigDecimal;

@Entity
@Getter
@Table(name = "card")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Card extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    Long id;

    @Column(name = "memberToken", nullable = false)
    String memberToken;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "companyName", nullable = false)
    String companyName;

    @Column(name = "email")
    String email;

    @Column(name = "phoneNumber")
    String phoneNumber;

    @Column(name = "cardImgURL")
    String cardImgURL;

    @Column(name = "latitude")
    BigDecimal latitude;

    @Column(name = "longitude")
    BigDecimal longitude;

    @Column(name = "memo")
    String memo;

    @Builder
    public Card(String member, String name, String companyName, String email, String phoneNumber, String cardImgURL, BigDecimal latitude, BigDecimal longitude, String memo) {
        this.memberToken = member;
        this.name = name;
        this.companyName = companyName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cardImgURL = cardImgURL;
        this.latitude = latitude;
        this.longitude = longitude;
        this.memo = memo;
    }

    public void updateMember(String member) {
        this.memberToken = member;
    }
}
