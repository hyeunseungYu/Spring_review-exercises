package hanghae11.springexample.likes.controller;

import hanghae11.springexample.likes.dto.LikesRequestDto;
import hanghae11.springexample.likes.dto.LikesRequestMsgDto;
import hanghae11.springexample.likes.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikesController {
    private final LikesService likesService;

    @PostMapping("/posts/{id}")
    public LikesRequestMsgDto postLikes(@PathVariable Long id, HttpServletRequest request) {
        return likesService.postLikes(id, request);
    }

    @PostMapping("/posts/{id}/{replyId}")
    public LikesRequestMsgDto postReplyLikes(@PathVariable Long id, @PathVariable Long replyId, HttpServletRequest request) {
        return likesService.postReplyLikes(id, replyId, request);
    }

    @DeleteMapping("/deletes/{id}")
    public LikesRequestMsgDto deletePostLikes(@PathVariable Long id, HttpServletRequest request) {
        return likesService.deletePostLikes(id, request);
    }

    @DeleteMapping("/deletes/{id}/{replyId}")
    public LikesRequestMsgDto deleteReplyLikes(@PathVariable Long id, @PathVariable Long replyId, HttpServletRequest request) {
        return likesService.deleteReplyLikes(id, replyId, request);
    }
}
