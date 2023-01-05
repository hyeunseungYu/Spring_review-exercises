package hanghae11.springexample.controller;

import hanghae11.springexample.dto.*;
import hanghae11.springexample.entity.FreeBoard;
import hanghae11.springexample.member.dto.SignupRequestMsgDto;
import hanghae11.springexample.repository.FreeBoardRepository;
import hanghae11.springexample.service.FreeBoardService;
import lombok.RequiredArgsConstructor;
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
    public memberRequestDto postContents(@RequestBody FreeBoardDto freeBoardto, HttpServletRequest request) {
        return freeBoardService.createPost(freeBoardto, request);
    }

    @PutMapping("/posts/{id}")
    public SignupRequestMsgDto freeBoardEditRequestDto(@PathVariable Long id, @RequestBody FreeBoardEditRequestDto freeBoardEditRequestDto, HttpServletRequest request) {
        return freeBoardService.freeBoardEditRequestDto(id, freeBoardEditRequestDto, request);
    }

    @DeleteMapping("/posts/{id}")
    public SignupRequestMsgDto freeBoardDelete(@PathVariable Long id, HttpServletRequest request) {
        return freeBoardService.freeBoardDelete(id,request);
    }

}
