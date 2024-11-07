package solverz.business_card.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solverz.business_card.domain.member.entity.Member;
import solverz.business_card.domain.member.request.PatchMemberRequest;
import solverz.business_card.domain.member.request.PostMemberRequest;
import solverz.business_card.domain.member.response.GetMemberResponse;
import solverz.business_card.domain.member.response.PatchMemberResponse;
import solverz.business_card.domain.member.service.MemberService;

import java.util.Optional;

@Tag(name = "User", description = "슈트 멤버 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "멤버 정보 획득", description = "멤버 정보를 획득할 수 있는 API")
    @GetMapping
    public ResponseEntity<GetMemberResponse> getMember(@RequestParam String token) {
        Optional<Member> member = memberService.getMember(token);
        GetMemberResponse responseBody;

        if (member.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 Not Found 응답 반환
        }
        responseBody = GetMemberResponse.memberToResponse(member.get());
        ResponseEntity<GetMemberResponse> response = ResponseEntity.ok(responseBody); // 200 ok + response 반환
        return response;
    }

    @Operation(summary = "멤버 회원가입", description = "새 멤버를 등록하는 API")
    @PostMapping
    public ResponseEntity<Object> postMember(@RequestBody PostMemberRequest postMemberRequest) {
        memberService.registerMember(postMemberRequest); // 서비스에서 회원가입 처리
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).build(); // 201 Created 응답 반환
        return response;
    }

    @Operation(summary = "멤버 정보 수정", description = "멤버 정보를 수정하는 API")
    @PatchMapping
    public ResponseEntity<PatchMemberResponse> patchMember(@RequestParam String token, @RequestBody PatchMemberRequest patchMemberRequest) {
        ResponseEntity<PatchMemberResponse> response = memberService.updateMember(token, patchMemberRequest); // 고객 정보 업데이트
        return response;
    }

    @Operation(summary = "멤버 회원탈퇴", description = "멤버 정보를 삭제하는 API")
    @DeleteMapping
    public ResponseEntity<Object> deleteMember(@RequestParam String token) {
        ResponseEntity<Object> response = memberService.deleteMember(token); // 멤버 삭제
        return response;
    }



}
