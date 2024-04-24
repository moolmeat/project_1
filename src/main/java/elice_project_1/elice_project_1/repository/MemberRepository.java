package elice_project_1.elice_project_1.repository;

import elice_project_1.elice_project_1.entity.MemberEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    public List<MemberEntity> findAll();

    public Optional<MemberEntity> findByMemberId(String memberId);
}