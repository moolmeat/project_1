package elice_project_1.elice_project_1.controller;


import elice_project_1.elice_project_1.dto.MemberDTO;
import elice_project_1.elice_project_1.entity.MemberEntity;
import elice_project_1.elice_project_1.service.MemberService;
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
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/join")
    public String joinForm(@ModelAttribute MemberDTO memberDTO) {
        log.info("joinForm");
        return "join";
    }

    @PostMapping("/member/join")
    public String join(@Valid @ModelAttribute MemberDTO memberDTO, BindingResult bindingResult) {
        log.info("join");
        if (bindingResult.hasErrors()) {
            log.info("errors == {}", bindingResult);
        }
        MemberEntity memberEntity = MemberEntity.builder()
                .memberId(memberDTO.getMemberId())
                .memberPassword(memberDTO.getMemberPassword())
                .memberEmail(memberDTO.getMemberEmail())
                .memberNickname(memberDTO.getMemberNickname()).build();

        memberService.join(memberEntity);
        log.info("signup success");
        return "redirect:/login";
    }
}
