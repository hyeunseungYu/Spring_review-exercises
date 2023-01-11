package hanghae11.springexample.controller;

import hanghae11.springexample.dto.*;
import hanghae11.springexample.member.dto.SignupRequestMsgDto;
import hanghae11.springexample.member.dto.memberRequestDto;
import hanghae11.springexample.security.UserDetailsImpl;
import hanghae11.springexample.service.FreeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FreeBoardController {

    private final FreeBoardService freeBoardService;


    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }


    @GetMapping("/posts")
    public List<FreeBoardRequestDto> getPosts() {
        return freeBoardService.getFreeBoardInfo();
    }
    
    @GetMapping("/posts/{id}")
    public FreeBoardRequestDto targetSearch(@PathVariable Long id) {
        return freeBoardService.targetSearch(id);
    }

    @PostMapping("/posts")
    public memberRequestDto postContents(@RequestBody CreatePostRequestDto freeBoardto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return freeBoardService.createPost(freeBoardto, userDetails.getMember());
    }

    @PutMapping("/posts/{id}")
    public SignupRequestMsgDto freeBoardEditRequestDto(@PathVariable Long id, @RequestBody FreeBoardEditRequestDto freeBoardEditRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return freeBoardService.freeBoardEditRequestDto(id, freeBoardEditRequestDto, userDetails.getMember());
    }

    @DeleteMapping("/posts/{id}")
    public SignupRequestMsgDto freeBoardDelete(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return freeBoardService.freeBoardDelete(id,userDetails.getMember());
    }

}
