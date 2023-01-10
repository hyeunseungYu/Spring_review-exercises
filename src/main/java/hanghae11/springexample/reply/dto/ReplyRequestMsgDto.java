package hanghae11.springexample.reply.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ReplyRequestMsgDto {
    private String msg;

    private Integer httpStatus;

    public ReplyRequestMsgDto(String msg, Integer httpStatus) {
        this.msg = msg;
        this.httpStatus = httpStatus;
    }
}
