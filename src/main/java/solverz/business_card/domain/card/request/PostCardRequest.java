package solverz.business_card.domain.card.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import solverz.business_card.domain.card.entity.Card;
import solverz.business_card.domain.member.entity.Member;

import java.math.BigDecimal;

@Builder
@Schema(description = "명함 등록 요청")
public record PostCardRequest(
        @Schema(description = "고객 이름")
        String name,

        @Schema(description = "고객 회사명")
        String companyName,

        @Schema(description = "고객 이메일")
        String email,

        @Schema(description = "고객 전화번호")
        String phoneNumber,

        @Schema(description = "명함 이미지 URL")
        String cardImgURL,

        @Schema(description = "고객 회사 위도")
        BigDecimal latitude,

        @Schema(description = "고객 회사 경도")
        BigDecimal longitude,

        @Schema(description = "명함 메모")
        String memo
) {
        public static Card toCard(PostCardRequest request) {
                return Card.builder()
                        .name(request.name())
                        .companyName(request.companyName())
                        .email(request.email())
                        .phoneNumber(request.phoneNumber())
                        .cardImgURL(request.cardImgURL())
                        .latitude(request.latitude())
                        .longitude(request.longitude())
                        .memo(request.memo())
                        .build();
        }

}
