package elice_project_1.elice_project_1.service;

import elice_project_1.elice_project_1.entity.BoardEntity;
import elice_project_1.elice_project_1.entity.MemberEntity;
import elice_project_1.elice_project_1.repository.BoardRepository;
import elice_project_1.elice_project_1.repository.MemberRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.parser.Entity;
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
        Optional<MemberEntity> memberEntity = memberRepository.findById(id);
        BoardEntity boardEntity = BoardEntity.createBoard(title, content, memberEntity.orElse(null));
        boardRepository.save(boardEntity);
        return boardEntity.getId();
    }

    /** 게시글 조회 **/
    public List<BoardEntity> findAll() {
        List<BoardEntity> boardList = boardRepository.findAll();
        return boardList.isEmpty() ? Collections.emptyList() : boardList; // 빈 목록인 경우 빈 리스트 반환
    }

    /** 게시글 단건 조회 **/
    public Optional<BoardEntity> findOne(Long id) {
        return boardRepository.findById(id);
    }

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
