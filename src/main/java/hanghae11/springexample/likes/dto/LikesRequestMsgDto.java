package hanghae11.springexample.likes.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LikesRequestMsgDto {
    private String msg;

    private Integer httpStatus;
    private Integer count;

    public LikesRequestMsgDto(String msg, Integer httpStatus, Integer count) {
        this.msg = msg;
        this.httpStatus = httpStatus;
        this.count = count;
    }
}
