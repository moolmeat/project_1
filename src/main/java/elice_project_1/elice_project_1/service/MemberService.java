package elice_project_1.elice_project_1.service;

import elice_project_1.elice_project_1.dto.MemberDTO;
import elice_project_1.elice_project_1.entity.MemberEntity;
import elice_project_1.elice_project_1.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void join(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    @Transactional
    public Long join(MemberEntity memberEntity) {
        validateDuplicateLoginId(memberEntity); //중복 로그인 아이디 검증
        memberRepository.save(memberEntity);
        return memberEntity.getId();
    }

    private void validateDuplicateLoginId(MemberEntity memberEntity) {
        Optional<MemberEntity> findUsers = memberRepository.findByMemberId(memberEntity.getMemberId());
        if (!findUsers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

    /**
     * 회원 전체 조회
     */
    public List<MemberEntity> findUsers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 단건 조회
     */
    public Optional<MemberEntity> findById(Long id) {
        return memberRepository.findById(id);
    }

}
