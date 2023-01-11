package hanghae11.springexample.likes.controller;

import hanghae11.springexample.likes.dto.LikesRequestDto;
import hanghae11.springexample.likes.dto.LikesRequestMsgDto;
import hanghae11.springexample.likes.service.LikesService;
import hanghae11.springexample.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikesController {
    private final LikesService likesService;

    @PostMapping("/posts/{id}")
    public LikesRequestMsgDto postLikes(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likesService.postLikes(id, userDetails.getMember());
    }

    @PostMapping("/posts/{id}/{replyId}")
    public LikesRequestMsgDto postReplyLikes(@PathVariable Long id, @PathVariable Long replyId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likesService.postReplyLikes(id, replyId, userDetails.getMember());
    }

    @DeleteMapping("/deletes/{id}")
    public LikesRequestMsgDto deletePostLikes(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likesService.deletePostLikes(id, userDetails.getMember());
    }

    @DeleteMapping("/deletes/{id}/{replyId}")
    public LikesRequestMsgDto deleteReplyLikes(@PathVariable Long id, @PathVariable Long replyId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likesService.deleteReplyLikes(id, replyId, userDetails.getMember());
    }
}
