package solverz.business_card.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solverz.business_card.domain.member.request.DeleteMemberRequest;
import solverz.business_card.domain.member.request.PatchMemberRequest;
import solverz.business_card.domain.member.request.PostMemberRequest;
import solverz.business_card.domain.member.request.PostRecoverAccountRequest;
import solverz.business_card.domain.member.response.*;
import solverz.business_card.domain.member.service.MemberService;

@Tag(name = "User", description = "슈트 멤버 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "멤버 정보 획득", description = "멤버 정보를 획득할 수 있는 API")
    @GetMapping
    public ResponseEntity<GetMemberResponse> getMember(@RequestParam String memberToken) {
        GetMemberResponse response = memberService.getMember(memberToken);
        return ResponseEntity.ok(response); // 200 ok + response 반환
    }

    @Operation(summary = "멤버 회원가입", description = "새 멤버를 등록하는 API")
    @PostMapping("/signup")
    public ResponseEntity<PostMemberResponse> postMember(@RequestBody PostMemberRequest postMemberRequest) {
        PostMemberResponse response = memberService.registerMember(postMemberRequest); // 서비스에서 회원가입 처리
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "멤버 정보 수정", description = "멤버 정보를 수정하는 API")
    @PatchMapping
    public ResponseEntity<PatchMemberResponse> patchMember(@RequestBody PatchMemberRequest patchMemberRequest) {
        String memberToken = patchMemberRequest.getMemberToken();
        return memberService.updateMember(memberToken, patchMemberRequest); // 고객 정보 업데이트
    }

    @Operation(summary = "멤버 회원탈퇴", description = "멤버 정보를 삭제하는 API (soft delete)")
    @DeleteMapping
    public ResponseEntity<DeleteMemberResponse> deleteMember(@RequestBody DeleteMemberRequest deleteMemberRequest) {
        String memberToken = deleteMemberRequest.getMemberToken();
        return memberService.deleteMember(memberToken); // 멤버 삭제
    }

    @Operation(summary = "멤버 계정 복구", description = "soft delete된 멤버 계정 복구 API")
    @PostMapping("/recover-account")
    public ResponseEntity<PostRecoverAccountResponse> recoveryMember(@RequestBody PostRecoverAccountRequest postRecoverAccountRequest) {
        String memberToken = postRecoverAccountRequest.getMemberToken();
        return memberService.recoveryMember(memberToken); // 멤버 삭제
    }
}
