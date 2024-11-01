package solverz.business_card.domain.card.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import solverz.business_card.domain.common.BaseTimeEntity;

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
    @Column(name = "cardId")
    Long cardId;

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
}
