package solverz.business_card.domain.member.service;

import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import solverz.business_card.domain.common.execption.BusinessException;
import solverz.business_card.domain.common.execption.ErrorCode;
import solverz.business_card.domain.member.request.PatchMemberRequest;
import solverz.business_card.domain.member.request.PostMemberRequest;
import solverz.business_card.domain.member.entity.Member;
import solverz.business_card.domain.member.repository.MemberRepository;
import solverz.business_card.domain.member.response.GetMemberResponse;
import solverz.business_card.domain.member.response.PatchMemberResponse;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member getOnlyMember(String token){
        Member member = memberRepository.findByMemberTokenAndDeletedAtIsNull(token)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_MEMBER));
        return member;
    }

    // member 데이터 조회 (token 이용)
    @Transactional
    public GetMemberResponse getMember(String token){
        Member member = getOnlyMember(token);
        return GetMemberResponse.memberToResponse(member);
    }

    // 회원가입
    @Transactional
    public void registerMember(PostMemberRequest postMemberRequest) {
        Member newMember = Member.builder()
                .memberToken(postMemberRequest.getMemberToken())
                .email(postMemberRequest.getEmail())
                .password(postMemberRequest.getPassword())
                .nickname(postMemberRequest.getNickname())
                .nameCardImgUrl(postMemberRequest.getNameCardImgUrl())
                .memberToken(postMemberRequest.getMemberToken())
                .loginType(postMemberRequest.getLoginType())
                .build();
        memberRepository.save(newMember);
    }

    // 회원 정보 수정
    @Transactional
    public ResponseEntity<PatchMemberResponse> updateMember(String token, PatchMemberRequest patchMemberRequest) {
        Member member = getOnlyMember(token);

        // 회원 정보 update
        member.updatePassword(patchMemberRequest.getPassword());
        member.updateNickname(patchMemberRequest.getNickname());
        member.updateNameCardImgUrl(patchMemberRequest.getNameCardImgUrl());

        // 변경된 멤버 정보 저장
        memberRepository.save(member);
        PatchMemberResponse response = PatchMemberResponse.memberToResponse(member);
        return ResponseEntity.ok(response); // update된 고객 정보 반환
    }

    // 회원 삭제 요청
    @Transactional
    public ResponseEntity<Object> deleteMember(String token) {
        Member member = getOnlyMember(token);

        // member 삭제
        member.softDelete();
        return ResponseEntity.noContent().build(); // 삭제 성공 시 204 No Content 응답 반환 // TODO: 200 + Content 반환
    }
}


