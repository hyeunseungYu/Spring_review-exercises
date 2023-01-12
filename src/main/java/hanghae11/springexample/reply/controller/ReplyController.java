package hanghae11.springexample.reply.controller;

import hanghae11.springexample.reply.dto.ReplyRequestDto;
import hanghae11.springexample.reply.dto.ReplyRequestMsgDto;
import hanghae11.springexample.reply.service.ReplyService;
import hanghae11.springexample.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    //댓글 등록하기
    @PostMapping("/posts/{id}")
    public ReplyRequestMsgDto postsReply(@PathVariable Long id, @RequestBody ReplyRequestDto replyRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return replyService.createReply(id, replyRequestDto, userDetails.getMember());
    }

    @PutMapping("/posts/{id}/{replyId}")
    public ReplyRequestMsgDto editReply(@PathVariable Long id, @PathVariable Long replyId, @RequestBody ReplyRequestDto replyRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return replyService.editReply(id, replyId, replyRequestDto, userDetails.getMember());
    }

    @DeleteMapping("/posts/{id}/{replyId}")
    public ReplyRequestMsgDto deleteReply(@PathVariable Long id, @PathVariable Long replyId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return replyService.deleteReply(id, replyId, userDetails.getMember());
    }
}
