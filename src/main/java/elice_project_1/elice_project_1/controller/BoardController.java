package elice_project_1.elice_project_1.controller;

import elice_project_1.elice_project_1.Form.PostForm;
import elice_project_1.elice_project_1.entity.BoardEntity;
import elice_project_1.elice_project_1.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String postList(Model model) {
        model.addAttribute("list", boardService.findAll());
        return "home";
    }

    @GetMapping("/write")
    public String writeForm(@ModelAttribute PostForm postForm) {
        return "write";
    }

    @PostMapping("/write")
    public String writeForm(@Valid @ModelAttribute PostForm postForm, BindingResult bindingResult) {
        boardService.write(postForm.getTitle(), postForm.getContent(), loginUser.getId());
        return "redirect:/";
    }
}
