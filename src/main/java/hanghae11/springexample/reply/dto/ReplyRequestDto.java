package hanghae11.springexample.reply.dto;

import hanghae11.springexample.freeBoard.entity.FreeBoard;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplyRequestDto {
    private String contents;
    private FreeBoard freeBoard;
}
