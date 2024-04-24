package elice_project_1.elice_project_1.service;

import elice_project_1.elice_project_1.entity.BoardEntity;
import elice_project_1.elice_project_1.entity.MemberEntity;
import elice_project_1.elice_project_1.repository.BoardRepository;
import elice_project_1.elice_project_1.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    /** 게시글 생성 **/
    @Transactional
    public Long write(String title, String content, Long id) {
        MemberEntity memberEntity = memberRepository.findOne(id).orElseThrow();

        BoardEntity boardEntity = BoardEntity.createBoard(title, content, memberEntity);
        boardRepository.save(boardEntity);
        return boardEntity.getId();
    }

    /** 게시글 조회 **/
    public List<BoardEntity> findAll() {
        return boardRepository.findAll();
    }

    /** 게시글 제목으로 조회 **/

    /** 게시글 수정 **/
    @Transactional
    public void updateBoard(Long id, String title, String content) {
        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow();
        boardEntity.update(title, content);
    }

    /** 게시글 삭제 **/
    @Transactional
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }
}
