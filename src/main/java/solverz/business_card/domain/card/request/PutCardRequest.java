package solverz.business_card.domain.card.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import solverz.business_card.domain.card.entity.Card;

import java.math.BigDecimal;

@Builder
@Schema(description = "명함 수정 요청")
public record PutCardRequest(
        @Schema(description = "명함 id", defaultValue = "1")
        Long cardId,

        @Schema(description = "멤버 토큰", defaultValue = "1234")
        String memberToken,

        @Schema(description = "고객 이름", defaultValue = "customer")
        String name,

        @Schema(description = "고객 생일", defaultValue = "20000710")
        String birth,

        @Schema(description = "고객 회사명", defaultValue = "company1")
        String companyName,

        @Schema(description = "고객 이메일", defaultValue = "customer@email.com")
        String email,

        @Schema(description = "고객 전화번호", defaultValue = "000-0000-0000")
        String phoneNumber,

        @Schema(description = "명함 이미지 URL", defaultValue = "card.png")
        String cardImgURL,

        @Schema(description = "고객 회사 주소", defaultValue = "1")
        String companyAddress,

        @Schema(description = "고객 회사 위도", defaultValue = "2")
        BigDecimal latitude,

        @Schema(description = "고객 회사 경도", defaultValue = "3")
        BigDecimal longitude,

        @Schema(description = "명함 메모", defaultValue = "good")
        String memo
) {
        public static Card toCard(PutCardRequest request) {
            return Card.builder()
                    .name(request.name())
                    .birth(request.birth())
                    .companyName(request.companyName())
                    .email(request.email())
                    .phoneNumber(request.phoneNumber())
                    .cardImgURL(request.cardImgURL())
                    .companyAddress(request.companyAddress())
                    .latitude(request.latitude())
                    .longitude(request.longitude())
                    .memo(request.memo())
                    .build();
        }
}
