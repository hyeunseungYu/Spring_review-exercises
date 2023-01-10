package hanghae11.springexample.reply.dto;

import hanghae11.springexample.entity.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplyNullReplaceDto {
    private Long id = -1L;

    public ReplyNullReplaceDto(Reply reply) {
        this.id = reply.getId();
    }
}
