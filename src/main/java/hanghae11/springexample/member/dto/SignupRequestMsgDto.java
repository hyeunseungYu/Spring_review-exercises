package hanghae11.springexample.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SignupRequestMsgDto {
    private String msg;

    private String statuscode;

    public SignupRequestMsgDto(String msg, String statuscode) {
        this.msg = msg;
        this.statuscode = statuscode;
    }
}
