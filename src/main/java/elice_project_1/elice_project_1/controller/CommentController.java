package elice_project_1.elice_project_1.controller;

import elice_project_1.elice_project_1.dto.CommentDTO;
import elice_project_1.elice_project_1.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}/comment")
    public String writeComment(@PathVariable(name = "id") Long id, CommentDTO commentDTO, Model model) {
        model.addAttribute("id", id);
        commentService.writeComment(commentDTO, id);
        return "redirect:/" + id;
    }

//    @PostMapping("/{id}/comment/{commentId}/update")
//    public String updateComment(@PathVariable(name = "id") Long id, @PathVariable(name = "commentId") Long commentId, CommentDTO commentDTO, Model model) {
//        model.addAttribute("id", id);
//        model.addAttribute("commentId", commentId);
//        commentService.updateComment(commentDTO, commentId);
//        return "redirect:/" + id;
//    }

    // 댓글 상세정보 조회
    @GetMapping("/{postId}/comment/{id}/update")
    public CommentDTO findCommentById(@PathVariable final Long postId, @PathVariable final Long id) {
        return commentService.findCommentById(id);
    }


    // 기존 댓글 수정
    @PatchMapping("/{postId}/comment/{id}/update")
    public CommentDTO updateComment(@PathVariable final Long postId, @PathVariable final Long id, @RequestBody final CommentDTO params) {
        commentService.updateComment(params, id);
        return commentService.findCommentById(id);
    }

    @GetMapping("/{id}/comment/{commentId}/remove")
    public String deleteComment(@PathVariable(name = "id") Long id, @PathVariable(name = "commentId") Long commentId, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("commentId", commentId);
        commentService.deleteComment(commentId);
        return "redirect:/" + id;
    }

}
