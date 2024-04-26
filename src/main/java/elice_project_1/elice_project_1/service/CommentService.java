package elice_project_1.elice_project_1.service;

import elice_project_1.elice_project_1.dto.CommentDTO;
import elice_project_1.elice_project_1.entity.BoardEntity;
import elice_project_1.elice_project_1.entity.CommentEntity;
import elice_project_1.elice_project_1.entity.MemberEntity;
import elice_project_1.elice_project_1.repository.BoardRepository;
import elice_project_1.elice_project_1.repository.CommentRepository;
import elice_project_1.elice_project_1.repository.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public Long writeComment(CommentDTO commentDTO, Long boardId, String memberId) {
        MemberEntity memberEntity = memberRepository.findByMemberId(memberId).orElseThrow(() -> new UsernameNotFoundException("회원이 존재하지 않습니다."));
        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(() -> new UsernameNotFoundException("게시물을 찾을 수 없습니다."));
        CommentEntity result = CommentEntity.builder()
            .content(commentDTO.getContent())
            .boardEntity(boardEntity).memberEntity(memberEntity)
            .build();
        commentRepository.save(result);

        return result.getId();
    }

    public List<CommentDTO> commentList(Long id) {
        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));
        List<CommentEntity> comments = commentRepository.findByBoardEntity(boardEntity);

        return comments.stream()
            .map(commentEntity -> CommentDTO.builder().boardEntity(boardEntity).build())
            .collect(Collectors.toList());
    }

    public void updateComment(CommentDTO commentDTO, Long commentId) {
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
        commentEntity.update(commentDTO.getContent());
        commentRepository.save(commentEntity);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }


}
