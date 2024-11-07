package solverz.business_card.domain.member.service;

import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import solverz.business_card.domain.member.request.PatchMemberRequest;
import solverz.business_card.domain.member.request.PostMemberRequest;
import solverz.business_card.domain.member.entity.Member;
import solverz.business_card.domain.member.repository.MemberRepository;
import solverz.business_card.domain.member.response.PatchMemberResponse;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // member 데이터 조회 (token 이용)
    @Transactional
    public Optional<Member> getMember(String memberToken){
        return memberRepository.findByMemberToken(memberToken);
    }

    // 회원가입
    @Transactional
    public void registerMember(PostMemberRequest postMemberRequest) {
        Member newMember = Member.builder()
                .memberToken(postMemberRequest.getMemberToken())
                .email(postMemberRequest.getEmail())
                .password(postMemberRequest.getPassword())
                .name(postMemberRequest.getName())
                .nameCardImgUrl(postMemberRequest.getNameCardImgUrl())
                .memberToken(postMemberRequest.getMemberToken())
                .loginType(postMemberRequest.getLoginType())
                .build();
        memberRepository.save(newMember);
    }

    // 회원 정보 수정
    @Transactional
    public ResponseEntity<PatchMemberResponse> updateMember(String token, PatchMemberRequest patchMemberRequest) {
        Optional<Member> member = getMember(token);

        if (member.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 Not Found 응답 반환
        }

        if (patchMemberRequest.getPassword() != null) {
            member.get().setPassword(patchMemberRequest.getPassword());
        }
        if (patchMemberRequest.getName() != null) {
            member.get().setName(patchMemberRequest.getName());
        }
        if (patchMemberRequest.getNameCardImgUrl() != null) {
            member.get().setNameCardImgUrl(patchMemberRequest.getNameCardImgUrl());
        }

        // 변경된 멤버 정보 저장
        memberRepository.save(member.get());
        PatchMemberResponse updatedMemberResponse = PatchMemberResponse.memberToResponse(member.get());
        return ResponseEntity.ok(updatedMemberResponse); // update된 고객 정보 반환
    }

    // 회원 삭제 요청
    @Transactional
    public ResponseEntity<Object> deleteMember(String token) {
        Optional<Member> member = getMember(token);

        if (member.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 Not Found 응답 반환
        }

        // member 삭제
        memberRepository.delete(member.get());

        return ResponseEntity.noContent().build(); // 삭제 성공 시 204 No Content 응답 반환
    }
}


