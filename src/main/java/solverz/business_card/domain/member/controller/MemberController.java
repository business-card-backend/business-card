package solverz.business_card.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "슈트 멤버 관련 API")
@RestController
@RequestMapping("/api/v1/member")
public class MemberController {
    @Operation(summary = "멤버 정보 획득", description = "멤버 정보를 획득할 수 있는 API")
    @Parameter(name = "id", description = "멤버 id")
    @GetMapping
    public void getMember(Long id) {
    }
}
