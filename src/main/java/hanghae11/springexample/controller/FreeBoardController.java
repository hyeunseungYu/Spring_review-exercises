package hanghae11.springexample.controller;

import hanghae11.springexample.dto.*;
import hanghae11.springexample.entity.FreeBoard;
import hanghae11.springexample.repository.FreeBoardRepository;
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


    @GetMapping("/posts")
    public List<FreeBoardRequestDto> getPosts() {
        return freeBoardService.getFreeBoardInfo();
    }

    @GetMapping("/posts/{id}")
    public FreeBoardRequestDto targetSearch(@PathVariable Long id) {
        return freeBoardService.targetSearch(id);
    }

    @PostMapping("/posts")
    public FreeBoard postContents(@RequestBody FreeBoardDto freeBoardDto) {
        return freeBoardService.createPost(freeBoardDto);
    }

    @PutMapping("/posts/{id}")
    public FreeBoardEditRequestDto freeBoardEditRequestDto(@PathVariable Long id, @RequestBody FreeBoardEditRequestDto freeBoardEditRequestDto) {
        return freeBoardService.freeBoardEditRequestDto(id, freeBoardEditRequestDto);
    }

    @DeleteMapping("/posts/{id}")
    public FreeBoardPasswordRequestMsgDto freeBoardPasswordRequestDto(@PathVariable Long id,
                                                                   @RequestBody FreeBoardPasswordRequestDto freeBoardPasswordRequestDto,
                                                                   FreeBoardPasswordRequestMsgDto freeBoardPasswordRequestMsgDto
                                                                   ) {
        return freeBoardService.freeBoardPasswordRequestDto(id,freeBoardPasswordRequestDto, freeBoardPasswordRequestMsgDto);
    }

}
