package solverz.business_card.domain.member.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "사용자 계정 복구 요청")
public class PostRecoverAccountRequest {
    @Schema(description = "사용자 supabase 계정 토큰", defaultValue = "1234")
    private String memberToken;
}
