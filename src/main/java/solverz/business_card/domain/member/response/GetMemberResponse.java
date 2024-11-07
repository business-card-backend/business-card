package solverz.business_card.domain.member.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import solverz.business_card.domain.member.entity.LoginType;
import solverz.business_card.domain.member.entity.Member;

import java.time.LocalDateTime;

@Getter
@Builder
public class GetMemberResponse {
    @Schema(description = "사용자 등록 이메일")
    String email;

    @Schema(description = "사용자 계정 패스워드")
    String password;

    @Schema(description = "사용자 본명")
    String name;

    @Schema(description = "사용자 명함 이미지 URL")
    String nameCardImgUrl;

    @Schema(description = "사용자 로그인 타입 (EMAIL, GMAIL, APPLE_MAIL)")
    LoginType loginType;

    @Schema(description = "사용자 계정 생성 시각")
    LocalDateTime createdAt;

    public static GetMemberResponse memberToResponse(Member member) {
        return GetMemberResponse.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .name(member.getName())
                .nameCardImgUrl(member.getNameCardImgUrl())
                .loginType(member.getLoginType())
                .createdAt(member.getCreatedAt())
                .build();
    }
}

