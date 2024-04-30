package elice_project_1.elice_project_1.controller;

import elice_project_1.elice_project_1.dto.CommentDTO;
import elice_project_1.elice_project_1.dto.PostDTO;
import elice_project_1.elice_project_1.entity.BoardEntity;
import elice_project_1.elice_project_1.service.BoardService;
import elice_project_1.elice_project_1.service.CommentService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/{postId}")
    private String postView(@PathVariable("postId") Long postId, Model model) {
        log.info("postView");
        BoardEntity post = boardService.findOne(postId).orElseThrow();
        List<CommentDTO> comments = commentService.commentList(postId);
        model.addAttribute("post", post);
        model.addAttribute("id", postId);
        model.addAttribute("comments", comments);
        model.addAttribute("category", post.getCategory());
        return "post";
    }

    @GetMapping("/{postId}/edit")
    public String editForm(@PathVariable(name = "postId") Long postId, Model model){
        BoardEntity boardEntity = boardService.findOne(postId).orElseThrow();

        PostDTO postDTO = new PostDTO();
        postDTO.setTitle(boardEntity.getTitle());
        postDTO.setContent(boardEntity.getContent());

        model.addAttribute("postDTO", postDTO);
        model.addAttribute("postId", postId);
        model.addAttribute("category", postDTO.getCategory());

        return "editForm";
    }

    @PostMapping("/{postId}/edit")
    public String edit(@PathVariable(name = "postId") Long postId, @Valid @ModelAttribute PostDTO postDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "editForm";
        }
        model.addAttribute("postId", postId);
        model.addAttribute("postDTO", postDTO);
        model.addAttribute("category", postDTO.getCategory());
        boardService.updateBoard(postId, postDTO.getTitle(), postDTO.getContent(), postDTO.getCategory());

        return "redirect:/{postId}";
    }

    @PostMapping("/{postId}/delete")
    private String delete(@PathVariable(name = "postId") Long postId, Model model) {
        model.addAttribute("postId", postId);
        boardService.deleteById(postId);
        return "redirect:/";
    }
}
