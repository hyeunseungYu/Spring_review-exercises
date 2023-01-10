package hanghae11.springexample.member.controller;

//import hanghae11.springexample.member.dto.AdminRequestDto;
import hanghae11.springexample.member.dto.AdminRequestDto;
import hanghae11.springexample.member.dto.SignupRequestDto;
import hanghae11.springexample.member.dto.SignupRequestMsgDto;
import hanghae11.springexample.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public SignupRequestMsgDto signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        return memberService.signup(signupRequestDto);
    }

    @PostMapping("/login")
    public SignupRequestMsgDto login(@RequestBody SignupRequestDto signupRequestDto, HttpServletResponse response) {
        return memberService.login(signupRequestDto,response);
    }

    @PutMapping("/admin/{id}")
    public SignupRequestMsgDto giveAdmin(@PathVariable Long id, @RequestBody AdminRequestDto adminRequestDto, HttpServletRequest request) {
        return memberService.giveAdmin(id, adminRequestDto , request);
    }

    @GetMapping("/forbidden")
    public ModelAndView getForbidden() {
        return new ModelAndView("forbidden");
    }
}
