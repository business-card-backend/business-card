package solverz.business_card.domain.member.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import solverz.business_card.domain.member.request.PatchMemberRequest;
import solverz.business_card.domain.member.request.PostMemberRequest;
import solverz.business_card.domain.member.entity.Member;
import solverz.business_card.domain.member.repository.MemberRepository;

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
    public Member updateMember(Member member, PatchMemberRequest patchMemberRequest) {
        if (patchMemberRequest.getEmail() != null) {
            member.setEmail(patchMemberRequest.getEmail());
        }
        if (patchMemberRequest.getPassword() != null) {
            member.setPassword(patchMemberRequest.getPassword());
        }
        if (patchMemberRequest.getName() != null) {
            member.setName(patchMemberRequest.getName());
        }
        if (patchMemberRequest.getNameCardImgUrl() != null) {
            member.setNameCardImgUrl(patchMemberRequest.getNameCardImgUrl());
        }
        if (patchMemberRequest.getLoginType() != null) {
            member.setLoginType(patchMemberRequest.getLoginType());
        }

        // 변경된 멤버 정보 저장
        memberRepository.save(member);
        return member;
    }

    // 회원 삭제 요청
    @Transactional
    public void deleteMember(Member member) {
        // member 삭제
        memberRepository.delete(member);
    }
}


