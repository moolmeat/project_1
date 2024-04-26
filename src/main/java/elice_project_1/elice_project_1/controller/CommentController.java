package elice_project_1.elice_project_1.controller;

import elice_project_1.elice_project_1.dto.CommentDTO;
import elice_project_1.elice_project_1.repository.MemberRepository;
import elice_project_1.elice_project_1.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final MemberRepository memberRepository;

    @PostMapping("/{id}/comment")
    public String writeComment(@PathVariable(name = "id") Long id, CommentDTO commentDTO, Model model) {
        model.addAttribute("id", id);
        commentService.writeComment(commentDTO, id, commentDTO.getMemberEntity().getMemberId());
        return "redirect:/" + id;
    }

    @ResponseBody
    @PostMapping("/board/{id}/comment/{commentId}/update")
    public String updateComment(@PathVariable(name = "id") Long id, @PathVariable(name = "commentId") Long commentId, CommentDTO commentDTO, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("commentId", commentId);
        commentService.updateComment(commentDTO, commentId);
        return "/" + id;
    }

    @ResponseBody
    @GetMapping("/{id}/comment/{comment}/update")
    public String deleteComment(@PathVariable(name = "id") Long id, @PathVariable(name = "commentId") Long commentId, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("commentId", commentId);
        commentService.deleteComment(commentId);
        return "redirect:/" + id;
    }
}
