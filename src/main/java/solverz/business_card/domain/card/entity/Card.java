package solverz.business_card.domain.card.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import solverz.business_card.domain.common.BaseTimeEntity;
import solverz.business_card.domain.member.entity.Member;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "birth")
    LocalDate birth;

    @Column(name = "companyName", nullable = false)
    String companyName;

    @Column(name = "email")
    String email;

    @Column(name = "phoneNumber")
    String phoneNumber;

    @Column(name = "cardImgURL")
    String cardImgURL;

    @Column(name = "companyAddress")
    String companyAddress;

    @Column(name = "latitude")
    BigDecimal latitude;

    @Column(name = "longitude")
    BigDecimal longitude;

    @Column(name = "memo")
    String memo;

    @ManyToOne
    @JoinColumn(name = "memberToken", nullable = false)
    private Member member;

    @Builder
    public Card(String name, String birth, String companyName, String email, String phoneNumber, String cardImgURL, String companyAddress, BigDecimal latitude, BigDecimal longitude, String memo) {
        this.name = name;
        this.birth = LocalDate.parse(birth);
        this.companyName = companyName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cardImgURL = cardImgURL;
        this.companyAddress = companyAddress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.memo = memo;
    }

    public void updateMember(Member member) {
        this.member = member;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateBirth(LocalDate birth) {
        this.birth = birth;
    }

    public void updateCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updatePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void updateCardImgURL(String cardImgURL) {
        this.cardImgURL = cardImgURL;
    }

    public void updateCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public void updateLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public void updateLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public void updateMemo(String memo) {
        this.memo = memo;
    }

    public void updateCard(Card card) {
        this.updateName(card.getName());
        this.updateBirth(card.getBirth());
        this.updateCompanyName(card.getCompanyName());
        this.updateEmail(card.getEmail());
        this.updatePhoneNumber(card.getPhoneNumber());
        this.updateCardImgURL(card.getCardImgURL());
        this.updateCompanyAddress(card.getCompanyAddress());
        this.updateLatitude(card.getLatitude());
        this.updateLongitude(card.getLongitude());
        this.updateMemo(card.getMemo());
    }
}
