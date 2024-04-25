package elice_project_1.elice_project_1.service;

import elice_project_1.elice_project_1.entity.MemberEntity;
import elice_project_1.elice_project_1.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public MemberEntity login(String memberId, String memberPassword) {
        Optional<MemberEntity> findMember = memberRepository.findByMemberId(memberId);
        MemberEntity memberEntity = findMember.get();
        if (memberEntity.getMemberPassword().equals(memberPassword)) {
            return memberEntity;
        }
        else return null;
    }

}
