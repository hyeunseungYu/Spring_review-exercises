package hanghae11.springexample.member.dto;

import hanghae11.springexample.entity.Member;
import hanghae11.springexample.entity.MemberRoleEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.*;

@Getter
@RequiredArgsConstructor
public class SignupRequestDto {
    @NotBlank
    @Size(min = 4, max = 10)
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])[a-z0-9]+", message = "알파벳 소문자, 숫자로 구성되어야 합니다.")
    private String username;

    @NotBlank
    @Size(min = 8, max = 15)
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%()])[a-zA-Z\\d!@#$%()]+", message = "알파벳 대소문자, 숫자, 특수문자[ !,@,#,$,%,(,) ]로 구성되어야 합니다.")
    private String password;

    private MemberRoleEnum role;

    private boolean admin = false;
    private String adminToken = "";

}
