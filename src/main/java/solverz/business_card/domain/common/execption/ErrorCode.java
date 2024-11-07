package solverz.business_card.domain.common.execption;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Default
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", "서버 내부에 에러가 발생했습니다."),

    // Member
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "NOT_FOUND_MEMBER", "해당 Token을 가진 멤버가 존재하지 않습니다."),

    // Card
    NOT_FOUND_CARD(HttpStatus.NOT_FOUND, "NOT_FOUND_CARD", "해당 id을 가진 명함이 존재하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
