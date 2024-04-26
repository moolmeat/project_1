package elice_project_1.elice_project_1.repository;

import elice_project_1.elice_project_1.entity.BoardEntity;
import elice_project_1.elice_project_1.entity.CommentEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByBoardEntity(BoardEntity boardEntity);
}
