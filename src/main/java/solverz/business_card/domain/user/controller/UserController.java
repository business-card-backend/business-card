package solverz.business_card.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "슈트 유저 관련 API")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Operation(summary = "유저 정보 획득", description = "유저 정보를 획득할 수 있는 API")
    @Parameter(name = "id", description = "유저 id")
    @GetMapping
    public void getUser(Long id) {
    }
}
