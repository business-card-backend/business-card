package solverz.business_card.domain.member.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import solverz.business_card.domain.member.entity.LoginType;

@Builder
@Getter
@Schema(description = "사용자 등록 요청")
public class PostMemberRequest {
    @Schema(description = "사용자 supabase 계정 토큰", defaultValue = "1234")
    private String memberToken;

    @Schema(description = "사용자 등록 이메일", defaultValue = "test@mail.com")
    private String email;

    @Schema(description = "사용자 계정 패스워드", defaultValue = "1234")
    private String password;

    @Schema(description = "사용자 닉네임", defaultValue = "test")
    private String nickname;

    @Schema(description = "사용자 명함 이미지 URL", defaultValue = "hi.png")
    private String nameCardImgUrl;

    @Schema(description = "사용자 로그인 타입 (EMAIL, GMAIL, APPLE_MAIL)", defaultValue = "EMAIL")
    private LoginType loginType;
}

