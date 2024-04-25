package elice_project_1.elice_project_1.controller;

import elice_project_1.elice_project_1.SessionConst;
import elice_project_1.elice_project_1.dto.PostDTO;
import elice_project_1.elice_project_1.entity.BoardEntity;
import elice_project_1.elice_project_1.entity.MemberEntity;
import elice_project_1.elice_project_1.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final BoardService boardService;

//    @GetMapping("")
//    public String home(Model model) {
//        model.addAttribute("list", boardService.findAll());
//        return "home";
//    }

    @GetMapping("/")
    public String loginHome(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
        MemberEntity loginMember, Model model) {

        model.addAttribute("list", boardService.findAll());

        if (loginMember == null) {
            return "home";
        }
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    @GetMapping("/{postId}")
    private String postView(@PathVariable("postId") Long postId, Model model) {
        log.info("postView");

        BoardEntity post = boardService.findOne(postId).orElseThrow();
        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping("/write")
    public String writeForm(@ModelAttribute PostDTO postForm) {
        return "write";
    }

    @PostMapping("/write")
    public String write(@Valid @ModelAttribute PostDTO postDTO, BindingResult bindingResult, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) MemberEntity loginMember) {
        if (loginMember == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "write";
        }
        boardService.write(postDTO.getTitle(), postDTO.getContent(), loginMember.getId());
        return "redirect:/";
    }

    @GetMapping("/{postId}/edit")
    public String editForm(@PathVariable Long postId, Model model){
        BoardEntity boardEntity = boardService.findOne(postId).orElseThrow();

        PostDTO postDTO = new PostDTO();
        postDTO.setTitle(boardEntity.getTitle());
        postDTO.setContent(boardEntity.getContent());

        model.addAttribute("postDTO", postDTO);
        model.addAttribute("postId", postId);

        return "editForm";
    }

    @PostMapping("/{postId}/edit")
    public String edit(@PathVariable Long postId, @Valid @ModelAttribute PostDTO postDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "editForm";
        }
        boardService.updateBoard(postId, postDTO.getTitle(), postDTO.getContent());

        return "redirect:/{postId}";
    }

    @PostMapping("/{postId}/delete")
    private String delete(@PathVariable Long postId) {
        boardService.deleteById(postId);
        return "home";
    }

}
