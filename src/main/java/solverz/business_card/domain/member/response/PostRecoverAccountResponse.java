package solverz.business_card.domain.member.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import solverz.business_card.domain.member.entity.LoginType;
import solverz.business_card.domain.member.entity.Member;

import java.time.LocalDateTime;

@Getter
@Builder
@Schema(description = "사용자 계정 복구 응답")
public class PostRecoverAccountResponse {
    @Schema(description = "사용자 닉네임")
    String nickname;

    @Schema(description = "사용자 등록 이메일")
    String email;

    @Schema(description = "사용자 계정 패스워드")
    String password;

    public static PostRecoverAccountResponse memberToResponse(Member member) {
        return PostRecoverAccountResponse.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .password(member.getPassword())
                .build();
    }
}
