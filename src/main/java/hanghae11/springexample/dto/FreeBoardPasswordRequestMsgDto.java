package hanghae11.springexample.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FreeBoardPasswordRequestMsgDto {
    private String success = "true";

    public FreeBoardPasswordRequestMsgDto(String success){
        this.success = success;
    }
}
