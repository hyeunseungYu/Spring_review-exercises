package hanghae11.springexample.reply.controller;

import hanghae11.springexample.reply.dto.ReplyRequestDto;
import hanghae11.springexample.reply.dto.ReplyRequestMsgDto;
import hanghae11.springexample.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    //댓글 등록하기
    @PostMapping("/posts/{id}")
    public ReplyRequestMsgDto postsReply(@PathVariable Long id, @RequestBody ReplyRequestDto replyRequestDto, HttpServletRequest request) {
        return replyService.createReply(id, replyRequestDto, request);
    }

    @PutMapping("/posts/{id}")
    public ReplyRequestMsgDto editReply(@PathVariable Long id, @RequestBody ReplyRequestDto replyRequestDto, HttpServletRequest request) {
        return replyService.editReply(id, replyRequestDto, request);
    }

    @DeleteMapping("/posts/{id}")
    public ReplyRequestMsgDto deleteReply(@PathVariable Long id, HttpServletRequest request) {
        return replyService.deleteReply(id, request);
    }
}
