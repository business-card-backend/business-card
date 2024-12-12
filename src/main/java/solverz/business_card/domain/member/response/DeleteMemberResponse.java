package solverz.business_card.domain.member.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import solverz.business_card.domain.member.entity.LoginType;
import solverz.business_card.domain.member.entity.Member;

import java.time.LocalDateTime;

@Getter
@Builder
@Schema(description = "사용자 삭제 응답 (soft delete)")
public class DeleteMemberResponse {
    @Schema(description = "사용자 닉네임")
    String nickname;

    @Schema(description = "사용자 등록 이메일")
    String email;

    @Schema(description = "사용자 로그인 타입 (EMAIL, GMAIL, APPLE_MAIL)")
    LoginType loginType;

    @Schema(description = "사용자 계정 삭제 요청 시각")
    LocalDateTime deletedAt;

    public static DeleteMemberResponse memberToResponse(Member member) {
        return DeleteMemberResponse.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .loginType(member.getLoginType())
                .deletedAt(member.getDeletedAt())
                .build();
    }
}
