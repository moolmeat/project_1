package elice_project_1.elice_project_1.controller;

import elice_project_1.elice_project_1.SessionConst;
import elice_project_1.elice_project_1.dto.PostDTO;
import elice_project_1.elice_project_1.entity.BoardEntity;
import elice_project_1.elice_project_1.entity.MemberEntity;
import elice_project_1.elice_project_1.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final BoardService boardService;

    @GetMapping("/")
    public String loginHome(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) MemberEntity loginMember, @RequestParam(value = "page", defaultValue = "0") int page,
        Model model) {
        Page<BoardEntity> paging = this.boardService.getList(page);
        model.addAttribute("paging", paging);

        if (loginMember == null) {
            return "home";
        }
        model.addAttribute("member", loginMember);
        return "loginHome";
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
        boardService.write(postDTO.getTitle(), postDTO.getContent(), loginMember.getId(), postDTO.getCategory());
        return "redirect:/";
    }

}