package hanghae11.springexample.reply.dto;

import hanghae11.springexample.entity.FreeBoard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplyRequestDto {
    private String contents;
    private FreeBoard freeBoard;
}
