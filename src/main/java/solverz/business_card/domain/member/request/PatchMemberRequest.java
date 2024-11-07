package solverz.business_card.domain.member.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import solverz.business_card.domain.member.entity.LoginType;

@Getter
public class PatchMemberRequest {
    @Schema(description = "사용자 계정 패스워드", defaultValue = "1234")
    private String password;

    @Schema(description = "사용자 본명", defaultValue = "test")
    private String name;

    @Schema(description = "사용자 명함 이미지 URL", defaultValue = "hi.png")
    private String nameCardImgUrl;
}

