package elice_project_1.elice_project_1.controller;

import elice_project_1.elice_project_1.SessionConst;
import elice_project_1.elice_project_1.dto.PostDTO;
import elice_project_1.elice_project_1.entity.MemberEntity;
import elice_project_1.elice_project_1.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @GetMapping("/write")
    public String writeForm(@ModelAttribute PostDTO postForm) {
        return "write";
    }

//    @PostMapping("/write")
//    public String writeForm(@Valid @ModelAttribute PostDTO postForm, BindingResult bindingResult) {
//        boardService.write(postForm.getTitle(), postForm.getContent(), loginUser.getId());
//        return "redirect:/";
//    }
}
