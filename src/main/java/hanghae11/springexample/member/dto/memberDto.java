package hanghae11.springexample.member.dto;

import hanghae11.springexample.entity.Member;
import hanghae11.springexample.entity.MemberRoleEnum;
import lombok.Data;

@Data
public class memberDto {
    private Long id;
    private String username;
//    private String password;
    private MemberRoleEnum role;

    public memberDto(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
//        this.password = member.getPassword();
        this.role = member.getRole();
    }
}
