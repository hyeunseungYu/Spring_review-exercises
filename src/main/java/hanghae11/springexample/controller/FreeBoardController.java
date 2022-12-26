package hanghae11.springexample.controller;

import hanghae11.springexample.dto.FreeBoardDto;
import hanghae11.springexample.entity.FreeBoard;
import hanghae11.springexample.service.FreeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FreeBoardController {

    private final FreeBoardService freeBoardService;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @GetMapping("/api/posts")
    public List<FreeBoard> getPosts() {
        return freeBoardService.getPosts();
    }

    @PostMapping("/api/posts")
    public FreeBoard postContents(@RequestBody FreeBoardDto freeBoardDto) {
        return freeBoardService.createPost(freeBoardDto);
    }


}
