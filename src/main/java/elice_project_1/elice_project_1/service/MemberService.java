package elice_project_1.elice_project_1.service;

import elice_project_1.elice_project_1.dto.MemberDTO;
import elice_project_1.elice_project_1.entity.MemberEntity;
import elice_project_1.elice_project_1.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public void join(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }
}
