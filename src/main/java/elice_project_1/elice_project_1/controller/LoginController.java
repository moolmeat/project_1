package elice_project_1.elice_project_1.controller;

import elice_project_1.elice_project_1.dto.LoginDTO;
import elice_project_1.elice_project_1.entity.MemberEntity;
import elice_project_1.elice_project_1.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginDTO") LoginDTO loginDTO) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginDTO") LoginDTO loginDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "login";
        }
        MemberEntity loginMember = loginService.login(loginDTO.getMemberId(), loginDTO.getMemberPassword());

        if (loginMember == null) {
            log.info("login Fail");
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login";
        }
        return "redirect:/";
    }
}
