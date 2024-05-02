package elice_project_1.elice_project_1.service;

import elice_project_1.elice_project_1.entity.BoardEntity;
import elice_project_1.elice_project_1.entity.MemberEntity;
import elice_project_1.elice_project_1.repository.BoardRepository;
import elice_project_1.elice_project_1.repository.MemberRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    private Specification<BoardEntity> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<BoardEntity> q, CriteriaQuery<?> query,
                CriteriaBuilder cb) {
                query.distinct(true);
                Join<BoardEntity, MemberEntity> u1 = q.join("memberEntity", JoinType.LEFT);

                return cb.or(cb.like(q.get("title"), "%" + kw + "%"), //제목
                    cb.like(q.get("content"), "%" + kw + "%"),  //내용
                    cb.like(u1.get("memberNickname"), "%" + kw + "%"));  //자악서엉자아
            }
        };
    }

    /** 게시글 생성 **/
    @Transactional
    public Long write(String title, String content, Long id, String category) {
        Optional<MemberEntity> memberEntity = memberRepository.findById(id);
        BoardEntity boardEntity = BoardEntity.createBoard(title, content, category, memberEntity.orElse(null));
        boardRepository.save(boardEntity);
        return boardEntity.getId();
    }

    /** 게시글 조회 **/
    public List<BoardEntity> findAll() {
        List<BoardEntity> boardList = boardRepository.findAll();
        return boardList.isEmpty() ? Collections.emptyList() : boardList; // 빈 목록인 경우 빈 리스트 반환
    }

    /** 게시글 단건 조회 **/
    @Transactional
    public Optional<BoardEntity> findOne(Long id) {
        Optional<BoardEntity> boardEntityOptional = boardRepository.findById(id);
        if (boardEntityOptional.isPresent()) {
            BoardEntity boardEntity = boardEntityOptional.get();
            boardEntity.setView(boardEntity.getView() + 1);
            boardRepository.save(boardEntity);
            // 새로운 트랜잭션에서 엔티티를 새로 가져옴
            return boardRepository.findById(id);
        } else {
            throw new NoSuchElementException("board not found");
        }
    }

    /** 게시글 수정 **/
    @Transactional
    public void updateBoard(Long id, String title, String content, String category) {
        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow();
        boardEntity.update(title, content, category);
    }

    /** 게시글 삭제 **/
    @Transactional
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardEntity> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<BoardEntity> spec = search(kw);
        return this.boardRepository.findAll(spec, pageable);
    }

}
