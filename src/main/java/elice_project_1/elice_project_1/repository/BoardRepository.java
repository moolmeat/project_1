package elice_project_1.elice_project_1.repository;

import elice_project_1.elice_project_1.entity.BoardEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

}
