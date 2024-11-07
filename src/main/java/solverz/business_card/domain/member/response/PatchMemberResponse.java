package solverz.business_card.domain.member.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import solverz.business_card.domain.member.entity.LoginType;
import solverz.business_card.domain.member.entity.Member;

@Getter
@Builder
public class PatchMemberResponse {
    @Schema(description = "사용자 계정 패스워드")
    private String password;

    @Schema(description = "사용자 본명")
    private String name;

    @Schema(description = "사용자 명함 이미지 URL")
    private String nameCardImgUrl;

    public static PatchMemberResponse memberToResponse(Member member) {
        return PatchMemberResponse.builder()
                .password(member.getPassword())
                .name(member.getName())
                .nameCardImgUrl(member.getNameCardImgUrl())
                .build();
    }
}
