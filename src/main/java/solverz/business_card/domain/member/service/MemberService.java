package solverz.business_card.domain.member.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import solverz.business_card.domain.common.execption.BusinessException;
import solverz.business_card.domain.common.execption.ErrorCode;
import solverz.business_card.domain.member.request.PatchMemberRequest;
import solverz.business_card.domain.member.request.PostMemberRequest;
import solverz.business_card.domain.member.entity.Member;
import solverz.business_card.domain.member.repository.MemberRepository;
import solverz.business_card.domain.member.response.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member getOnlyMember(String token){
        Member member = memberRepository.findByDeletedAtIsNull()
                .filter(m -> passwordEncoder.matches(token, m.getMemberToken()))  // 암호화된 값과 비교
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
    public PostMemberResponse registerMember(PostMemberRequest postMemberRequest) {
        String encodedPassword = passwordEncoder.encode(postMemberRequest.getPassword()); // encoding
        String encodedToken = passwordEncoder.encode(postMemberRequest.getMemberToken()); // encoding

        Member newMember = Member.builder()
                .memberToken(encodedToken)
                .email(postMemberRequest.getEmail())
                .password(encodedPassword)
                .nickname(postMemberRequest.getNickname())
                .nameCardImgUrl(postMemberRequest.getNameCardImgUrl())
                .loginType(postMemberRequest.getLoginType())
                .build();
        memberRepository.save(newMember);

        // 갱신된 member 조회
        Member savedMember = memberRepository.findByMemberToken(newMember.getMemberToken())
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_MEMBER));

        return PostMemberResponse.memberToResponse(savedMember);
    }

    // 회원 정보 수정
    @Transactional
    public ResponseEntity<PatchMemberResponse> updateMember(String token, PatchMemberRequest patchMemberRequest) {
        Member member = getOnlyMember(token);

        // 회원 정보 update
        String encodedPassword = passwordEncoder.encode(patchMemberRequest.getPassword()); // encoding

        member.updatePassword(encodedPassword);
        member.updateNickname(patchMemberRequest.getNickname());
        member.updateNameCardImgUrl(patchMemberRequest.getNameCardImgUrl());

        // 변경된 멤버 정보 저장
        memberRepository.save(member);
        PatchMemberResponse response = PatchMemberResponse.memberToResponse(member);
        return ResponseEntity.ok(response); // update된 고객 정보 반환
    }

    // 회원 삭제 요청
    @Transactional
    public ResponseEntity<DeleteMemberResponse> deleteMember(String token) {
        Member member = getOnlyMember(token);

        // member 삭제
        member.softDelete();

        DeleteMemberResponse response = DeleteMemberResponse.memberToResponse(member);
        return ResponseEntity.ok(response);
    }

    // 회원 복구 요청
    @Transactional
    public ResponseEntity<PostRecoverAccountResponse> recoveryMember(String token) {
        Member member = memberRepository.findAll()
                .stream()
                .filter(m -> passwordEncoder.matches(token, m.getMemberToken()))  // 암호화된 값과 비교
                .findFirst()
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_MEMBER));

        // soft delete된 member인지 확인
        if (!member.isSoftDeleted()) {
            throw new BusinessException(ErrorCode.NOT_SOFT_DELETE_MEMBER);
        }

        // member 복구
        member.recoveryMember();
        PostRecoverAccountResponse response = PostRecoverAccountResponse.memberToResponse(member);
        return ResponseEntity.ok(response);
    }

    // 회원 영구 삭제
    @Transactional
    public int deleteExpiredMembers(LocalDateTime cutoffDate) {
        return memberRepository.deleteExpiredMembers(cutoffDate);
    }

    // 영구삭제 기한 만료 회원 조회
    @Transactional
    public List<Member> findExpiredMembers(LocalDateTime cutoffDate) {
        return memberRepository.findExpiredMembers(cutoffDate);
    }
}


